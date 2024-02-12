package com.example.projectspringmvc.service.impl;
import com.example.projectspringmvc.dto.CommentDto;
import com.example.projectspringmvc.dto.SpecialistDto;
import com.example.projectspringmvc.entity.Comment;
import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.enumeration.SpecialistStatus;
import com.example.projectspringmvc.entity.user.Specialist;
import com.example.projectspringmvc.exception.NotFoundException;
import com.example.projectspringmvc.repository.SpecialistRepository;
import com.example.projectspringmvc.service.CommentService;
import com.example.projectspringmvc.service.SpecialistService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RequiredArgsConstructor
@Service
public class SpecialistServiceImpl implements SpecialistService {



    private final SpecialistRepository specialistRepository;

    private final CommentService commentService;

    private final ModelMapper modelMapper;


    @Override
    public SpecialistDto save(SpecialistDto specialistDto) {
        Specialist specialist = modelMapper.map(specialistDto, Specialist.class);
        if(Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)" +
                "(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", specialist.getPassword())
                && Pattern.matches("^[a-zA-Z ]{2,30}$", specialist.getFirstname())
                && Pattern.matches("^[a-zA-Z ]{2,30}$", specialist.getLastname())
                && Pattern.matches("^[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}$", specialist.getEmail())
                && !existByUserName(specialist.getUsername())
                && !existByEmail(specialist.getEmail())) {
            specialist = specialistRepository.save(specialist);
            specialistDto = modelMapper.map(specialist, SpecialistDto.class);
            return specialistDto;
        }
        throw new IllegalArgumentException("Invalid Data Or username or email Exists!");

    }


    @Override
    public SpecialistDto findById(Integer id) {
        Specialist specialist = specialistRepository.findById(id). orElseThrow(
                () -> new NotFoundException(String.format("%d not Fount",id)));
        return modelMapper.map(specialist,SpecialistDto.class);
    }

    @Override
    public SpecialistDto findByUserName(String userName) {
        Specialist specialist = specialistRepository.findByUserName(userName).orElseThrow(
                () -> new NotFoundException(String.format("%s not Fount", userName)));
        return modelMapper.map(specialist, SpecialistDto.class);
    }


    @Override
    public List<SpecialistDto> findAll() {
        List<Specialist> specialistList = specialistRepository.findAll();
        return specialistList.stream().map(specialist -> modelMapper
                .map(specialist, SpecialistDto.class)).collect(Collectors.toList());
    }


    @Override
    public SpecialistDto update(SpecialistDto specialistDto) {
        Specialist specialist = specialistRepository.findById(specialistDto.getId()).orElseThrow(() -> new NotFoundException("id not found"));
        specialist.setUsername(specialistDto.getUsername());
        specialist.setPassword(specialistDto.getPassword());
        specialist.setFirstname(specialistDto.getFirstname());
        specialist.setLastname(specialistDto.getLastname());
        specialist.setEmail(specialistDto.getEmail());
        specialist.setCredit(specialistDto.getCredit());
        specialist.setAverageScore(specialistDto.getAverageScore());
        specialist.setSpecialities(specialistDto.getSpecialities());
        specialist = specialistRepository.save(specialist);
        specialistDto = modelMapper.map(specialist, SpecialistDto.class);
        return specialistDto;

    }

    @Override
    public SpecialistDto deleteById(int id) {
        Specialist specialist = specialistRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("%d not Fount", id)));
        SpecialistDto specialistDto = modelMapper.map(specialist, SpecialistDto.class);
        specialistRepository.deleteById(id);
        return specialistDto;

    }

    @Override
    public boolean existByUserName(String username) {
        return specialistRepository.existByUserName(username);
    }

    @Override
    public boolean existByEmail(String email) {
        return specialistRepository.existByEmail(email);
    }

    @Override
    public double averageScore(Specialist specialist) {
        List<Integer> scores = specialist.getSpecialistScores();
        Integer sum = 0;
        for (Integer score : scores
        ) {
            sum += score;
        }

        return sum / (scores.size());
    }


    @Override
    public boolean existsById(Integer id) {
        return specialistRepository.existsById(id);
    }


    @Override
    public List<SpecialistDto> loadBySpecialistStatus(SpecialistStatus specialistStatus) {
        List<Specialist> statusSpecialists = new ArrayList<>();
        List<Specialist> specialists = specialistRepository.findAll();
        for (Specialist specialist : specialists
        ) {
            if (specialist.getSpecialistStatus().equals(specialistStatus)) {
                statusSpecialists.add(specialist);
            }

        }
        return statusSpecialists.stream().map(specialist -> modelMapper
                .map(specialist, SpecialistDto.class)).collect(Collectors.toList());

    }

@Override
    public void addProfilePicture(Specialist specialist, String imagePath) {
        byte[] profileImage = null;
        try {
            profileImage =readImageBinary(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading image file");
        }
        if(isValidImage(profileImage)){
        specialist.setProfilePicture(profileImage);
        specialist.setId(specialist.getId());
        specialistRepository.save(specialist);
        return;}
        throw new IllegalArgumentException("Invalid Data");
    }

    @Override
    public void addScoreFromCommentToSpecialist(int specialistId, int commentId) {
        SpecialistDto specialistDto = findById(specialistId);
        CommentDto commentDto = commentService.findById(commentId);
        Specialist specialist = modelMapper.map(specialistDto,Specialist.class);
        Comment comment = modelMapper.map(commentDto,Comment.class);
        List<Integer> scores = specialist.getSpecialistScores();
        scores.add(comment.getSpecialistScore());
        specialist.setAverageScore(averageScore(specialist));
        specialist.setId(specialist.getId());
        specialistRepository.save(specialist);

    }

    @Override
    public boolean hasAccessToSystem(int specialistId) {
        Specialist specialist = specialistRepository.findById(specialistId).orElseThrow(() -> new NotFoundException(String.format("%d not Fount", specialistId)));
        return specialist.getSpecialistStatus().equals(SpecialistStatus.CONFIRMED);
    }


    public static byte[] readImageBinary(String path) throws IOException {
        File file = new File(path);
        byte[] imageData = new byte[(int) file.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(imageData);
        }
        return imageData;
    }


    public static boolean isValidImage(byte[] imageBytes) {
        try {
            if (imageBytes.length > 300 * 1024) {
                return false;
            }
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));
            return image != null && ImageIO.write(image, "jpg", new ByteArrayOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Invalid Type");
            return false;
        }
    }


        public static void saveImageFromBytes(byte[] imageData, String outputPath) throws IOException {
            try (FileOutputStream fos = new FileOutputStream(outputPath)) {
                fos.write(imageData);
            }
            System.out.println("Image saved successfully to: " + outputPath);
    }

    @Override
    public Specialist creditExchange(MyOrder myOrder, double finalPrice) {
        Specialist specialist = myOrder.getSpecialist();
        specialist.setCredit(myOrder.getSpecialist().getCredit() + finalPrice);
        specialist.setId(myOrder.getSpecialist().getId());
        return specialist;

    }



}
