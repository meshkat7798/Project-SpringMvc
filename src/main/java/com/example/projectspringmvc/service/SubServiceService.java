package com.example.projectspringmvc.service;
import com.example.projectspringmvc.dto.SubServiceDto;
import com.example.projectspringmvc.entity.Service;

import java.util.List;

@SuppressWarnings("unused")
public interface SubServiceService {
    SubServiceDto save(SubServiceDto subServiceDto);

    SubServiceDto findById(Integer id);

    List<SubServiceDto> findAll();

    boolean existsById(Integer id);

    List<SubServiceDto> subServicesOfOneService(int serviceId);

    SubServiceDto updateSubService(SubServiceDto subServiceDto);

    boolean existByName(String name);

    SubServiceDto deleteById(int id);

  }
