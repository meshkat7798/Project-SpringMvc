package com.example.projectspringmvc.service;

import com.example.projectspringmvc.dto.ServiceDto;
import com.example.projectspringmvc.dto.response.ResponseServiceDto;

import java.util.List;

@SuppressWarnings("unused")
public interface ServiceService{

    ServiceDto save(ServiceDto serviceDto);

    ResponseServiceDto findById(Integer id);

    ServiceDto findById2(Integer id);

    List<ResponseServiceDto> findAll();

    boolean existsById(Integer id);

    boolean existByName(String name);

    ServiceDto deleteById(int id);



}
