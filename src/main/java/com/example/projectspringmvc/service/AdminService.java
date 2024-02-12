package com.example.projectspringmvc.service;

import com.example.projectspringmvc.dto.AdminDto;
import com.example.projectspringmvc.dto.SpecialistDto;
import com.example.projectspringmvc.dto.SubServiceDto;


import java.util.List;

@SuppressWarnings("unused")
public interface AdminService {

    AdminDto save(AdminDto entity);

    AdminDto findById(Integer id);

    List<AdminDto> findAll();

    boolean existsById(Integer id);

    boolean existByUserName(String username);

    AdminDto findByUserName(String userName);

    void addSpecialistToSubService(int serviceId, int specialistId);

    void removeSpecialistFromSubService(int serviceId, int specialistId);

    AdminDto update(AdminDto adminDto);

    AdminDto deleteById(int id);

    SpecialistDto confirmSpecialist(int specialistId);

    SpecialistDto disableSpecialist(int specialistId);
}
