package com.example.projectspringmvc.service;

import com.example.projectspringmvc.dto.AdminDto;
import com.example.projectspringmvc.dto.SpecialistDto;
import com.example.projectspringmvc.dto.response.ResponseCustomerDto;
import com.example.projectspringmvc.dto.response.ResponseSpecialistDto;
import com.example.projectspringmvc.dto.response.ResponseSubServiceDto;
import java.util.List;

@SuppressWarnings("unused")
public interface AdminService {

    AdminDto save(AdminDto entity);

    AdminDto findById(Integer id);

    List<AdminDto> findAll();

    boolean existsById(Integer id);

    boolean existByUserName(String username);

    AdminDto findByUserName(String userName);

    ResponseSubServiceDto addSpecialistToSubService(int serviceId, int specialistId);

    ResponseSubServiceDto removeSpecialistFromSubService(int serviceId, int specialistId);

    AdminDto update(AdminDto adminDto);

    AdminDto deleteById(int id);

    ResponseSpecialistDto confirmSpecialist(int specialistId);

    ResponseSpecialistDto disableSpecialist(int specialistId);

    List<ResponseSpecialistDto> searchSpecialists(String firstName, String lastName, String email, String specialities, Boolean highscore);

    List<ResponseCustomerDto> searchCustomers(String firstName, String lastName, String email);
}
