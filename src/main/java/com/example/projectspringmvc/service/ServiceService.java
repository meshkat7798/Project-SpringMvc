package com.example.projectspringmvc.service;

import com.example.projectspringmvc.dto.ServiceDto;
import java.util.List;

@SuppressWarnings("unused")
public interface ServiceService{

    ServiceDto save(ServiceDto serviceDto);

    ServiceDto findById(Integer id);

    List<ServiceDto> findAll();

    boolean existsById(Integer id);

    boolean existByName(String name);

    ServiceDto deleteById(int id);



}
