package com.example.projectspringmvc.service;

import com.example.projectspringmvc.dto.SpecialistDto;
import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.enumeration.SpecialistStatus;
import com.example.projectspringmvc.entity.user.Specialist;
import java.util.List;

@SuppressWarnings("unused")
public interface SpecialistService {

    SpecialistDto save(SpecialistDto specialistDto);

    SpecialistDto findById(Integer id);

    SpecialistDto findByUserName(String userName);

    List<SpecialistDto> findAll();

    SpecialistDto update(SpecialistDto specialistDto);

    SpecialistDto deleteById(int id);

    boolean existByUserName(String username);

    boolean existByEmail(String email);

    double averageScore(Specialist specialist);

    boolean existsById(Integer id);

    List<SpecialistDto> loadBySpecialistStatus(SpecialistStatus specialistStatus);

    void addProfilePicture(Specialist specialist, String imagePath);

    void addScoreFromCommentToSpecialist(int specialistId, int commentId);

    boolean hasAccessToSystem(int specialistId);

    Specialist creditExchange(MyOrder myOrder, double finalPrice);
}
