package com.example.projectspringmvc.service;
import com.example.projectspringmvc.dto.SubServiceDto;
import com.example.projectspringmvc.dto.response.ResponseSpecialistDto;
import com.example.projectspringmvc.dto.response.ResponseSubServiceDto;
import com.example.projectspringmvc.entity.Service;

import java.util.List;

@SuppressWarnings("unused")
public interface SubServiceService {

    SubServiceDto save(SubServiceDto subServiceDto);

    ResponseSubServiceDto findById(Integer id);

    SubServiceDto findById2(Integer id);

    List<ResponseSubServiceDto> findAll();

    boolean existsById(Integer id);

    List<ResponseSubServiceDto> subServicesOfOneService(int serviceId);

    ResponseSubServiceDto updateSubService(ResponseSubServiceDto subServiceDto);

    boolean existByName(String name);

    SubServiceDto deleteById(int id);

  }
