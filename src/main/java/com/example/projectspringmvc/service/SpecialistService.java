package com.example.projectspringmvc.service;

import com.example.projectspringmvc.dto.SpecialistDto;
import com.example.projectspringmvc.dto.response.ResponseSpecialistDto;
import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.enumeration.SpecialistStatus;
import com.example.projectspringmvc.entity.user.Specialist;
import java.util.List;

@SuppressWarnings("unused")
public interface SpecialistService {

    SpecialistDto save(SpecialistDto specialistDto);

    ResponseSpecialistDto findById(Integer id);

    SpecialistDto findById2(Integer id);

    ResponseSpecialistDto findByUserName(String userName);

    List<ResponseSpecialistDto> findAll();

    ResponseSpecialistDto update(ResponseSpecialistDto specialistDto);

    SpecialistDto deleteById(int id);

    boolean existByUserName(String username);

    boolean existByEmail(String email);

    double averageScore(Specialist specialist);

    boolean existsById(Integer id);

    List<ResponseSpecialistDto> loadBySpecialistStatus(SpecialistStatus specialistStatus);

    void addProfilePicture(Specialist specialist, String imagePath);

    void addScoreFromCommentToSpecialist(int specialistId, int commentId);

    boolean hasAccessToSystem(int specialistId);

    Specialist creditExchange(MyOrder myOrder, double finalPrice);

    List<Integer> showُSpecialistScores(int specialistId);
}
