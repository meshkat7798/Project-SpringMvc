package com.example.projectspringmvc.service.impl;

import com.example.projectspringmvc.dto.CommentDto;
import com.example.projectspringmvc.dto.SpecialistDto;
import com.example.projectspringmvc.dto.response.ResponseSpecialistDto;
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


    //Done
    @Override
    public SpecialistDto save(SpecialistDto specialistDto) {
        Specialist specialist = modelMapper.map(specialistDto, Specialist.class);
        if (Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)" +
                "(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", specialist.getPassword())
                && Pattern.matches("^[a-zA-Z ]{2,30}$", specialist.getFirstname())
                && Pattern.matches("^[a-zA-Z ]{2,30}$", specialist.getLastname())
                && Pattern.matches("^[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}$", specialist.getEmail())
                && !existByUserName(specialist.getUsername())
                && !existByEmail(specialist.getEmail())) {
            specialist.setSpecialistStatus(SpecialistStatus.NEW);
            specialist.setSpecialistScores(new ArrayList<>());
            specialist.setCredit(0);
            specialist.setServices(new ArrayList<>());
            specialist.setSubServices(new ArrayList<>());
            specialist.setOffers(new ArrayList<>());
            specialist.setMyOrders(new ArrayList<>());
            specialist = specialistRepository.save(specialist);
            specialistDto = modelMapper.map(specialist, SpecialistDto.class);
            return specialistDto;
        }
        throw new IllegalArgumentException("Invalid Data Or username or email Exists!");

    }


    //Done
    @Override
    public ResponseSpecialistDto findById(Integer id) {
        Specialist specialist = specialistRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("%d not Fount", id)));
        return modelMapper.map(specialist, ResponseSpecialistDto.class);
    }


    //NoNeed
    @Override
    public SpecialistDto findById2(Integer id) {
        Specialist specialist = specialistRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("%d not Fount", id)));
        return modelMapper.map(specialist, SpecialistDto.class);

    }

    //Done
    @Override
    public ResponseSpecialistDto findByUserName(String userName) {
        Specialist specialist = specialistRepository.findByUserName(userName).orElseThrow(
                () -> new NotFoundException(String.format("%s not Fount", userName)));
        return modelMapper.map(specialist, ResponseSpecialistDto.class);
    }


    //Done
    @Override
    public List<ResponseSpecialistDto> findAll() {
        List<Specialist> specialistList = specialistRepository.findAll();
        return specialistList.stream().map(specialist -> modelMapper
                .map(specialist, ResponseSpecialistDto.class)).collect(Collectors.toList());
    }

    //Done
    @Override
    public List<Integer> showSpecialistScores(int specialistId) {
        Specialist specialist = specialistRepository.findById(specialistId)
                .orElseThrow(() -> new NotFoundException(String.format("%d not Fount", specialistId)));
        return specialist.getSpecialistScores();

    }


    //Done
    @Override
    public ResponseSpecialistDto update(ResponseSpecialistDto specialistDto) {
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
        specialistDto = modelMapper.map(specialist, ResponseSpecialistDto.class);
        return specialistDto;

    }

    //Done
    @Override
    public SpecialistDto deleteById(int id) {
        Specialist specialist = specialistRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("%d not Fount", id)));
        SpecialistDto specialistDto = modelMapper.map(specialist, SpecialistDto.class);
        specialistRepository.deleteById(id);
        return specialistDto;

    }

    //Done
    @Override
    public boolean existByUserName(String username) {
        return specialistRepository.existByUserName(username);
    }

    //Done
    @Override
    public boolean existByEmail(String email) {
        return specialistRepository.existByEmail(email);
    }

    //NoNeed
    @Override
    public double averageScore(int specialistId) {
        SpecialistDto specialistDto = findById2(specialistId);
        Specialist specialist = modelMapper.map(specialistDto,Specialist.class);
        List<Integer> scores = specialist.getSpecialistScores();
        Integer sum = 0;
        for (Integer score : scores
        ) {
            sum += score;
        }

        return sum / (scores.size());
    }


    //Done
    @Override
    public boolean existsById(Integer id) {
        return specialistRepository.existsById(id);
    }


    //Done
    @Override
    public List<ResponseSpecialistDto> loadBySpecialistStatus(SpecialistStatus specialistStatus) {
        List<Specialist> statusSpecialists = new ArrayList<>();
        List<Specialist> specialists = specialistRepository.findAll();
        for (Specialist specialist : specialists
        ) {
            if (specialist.getSpecialistStatus().equals(specialistStatus)) {
                statusSpecialists.add(specialist);
            }

        }
        return statusSpecialists.stream().map(specialist -> modelMapper
                .map(specialist, ResponseSpecialistDto.class)).collect(Collectors.toList());

    }

    //Done
    @Override
    public boolean hasAccessToSystem(int specialistId) {
        Specialist specialist = specialistRepository.findById(specialistId).orElseThrow(() -> new NotFoundException(String.format("%d not Fount", specialistId)));
        return specialist.getSpecialistStatus().equals(SpecialistStatus.CONFIRMED);
    }

    //Done
    @Override
    public void addProfilePicture(int specialistId, String imagePath) {
        SpecialistDto specialistDto = findById2(specialistId);
        Specialist specialist = modelMapper.map(specialistDto,Specialist.class);
        byte[] profileImage = null;
        try {
            profileImage = readImageBinary(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading image file");
        }
        if (isValidImage(profileImage)) {
            specialist.setProfilePicture(profileImage);
            specialist.setId(specialist.getId());
            specialistRepository.save(specialist);
            return;
        }
        throw new IllegalArgumentException("Invalid Data");
    }

    //NoNeed
    @Override
    public ResponseSpecialistDto addScoreFromCommentToSpecialist(int specialistId, int commentId) {
        SpecialistDto specialistDto = findById2(specialistId);
        CommentDto commentDto = commentService.findById2(commentId);
        Specialist specialist = modelMapper.map(specialistDto, Specialist.class);
        Comment comment = modelMapper.map(commentDto, Comment.class);
        List<Integer> scores = specialist.getSpecialistScores();
        scores.add(comment.getSpecialistScore());
        specialist.setAverageScore(averageScore(specialistId));
        specialist.setId(specialist.getId());
        specialistRepository.save(specialist);

        return modelMapper.map(specialist,ResponseSpecialistDto.class);

    }


    //NoNeed
    public static byte[] readImageBinary(String path) throws IOException {
        File file = new File(path);
        byte[] imageData = new byte[(int) file.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(imageData);
        }
        return imageData;
    }


    //NoNeed
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


   //Done
    @Override
    public  void saveImageFromBytes(byte[] imageData, String outputPath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            fos.write(imageData);
        }
        System.out.println("Image saved successfully to: " + outputPath);
    }



    //Done
    @Override
    public void creditExchange(MyOrder myOrder, double finalPrice) {
        Specialist specialist = myOrder.getSpecialist();
        specialist.setCredit(myOrder.getSpecialist().getCredit() + finalPrice * 0.7);
        specialist.setId(myOrder.getSpecialist().getId());
        specialistRepository.save(specialist);

    }


}
