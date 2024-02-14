package com.example.projectspringmvc.service.impl;

import com.example.projectspringmvc.dto.ServiceDto;
import com.example.projectspringmvc.dto.SubServiceDto;
import com.example.projectspringmvc.dto.response.ResponseSubServiceDto;
import com.example.projectspringmvc.entity.Service;
import com.example.projectspringmvc.entity.SubService;
import com.example.projectspringmvc.exception.DuplicateException;
import com.example.projectspringmvc.exception.NotFoundException;
import com.example.projectspringmvc.repository.SubServiceRepository;
import com.example.projectspringmvc.service.ServiceService;
import com.example.projectspringmvc.service.SpecialistService;
import com.example.projectspringmvc.service.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class SubServiceServiceImpl implements SubServiceService {


    private final SubServiceRepository subServiceRepository;

    private final SpecialistService specialistService;

    private final ServiceService serviceService;

    private final ModelMapper modelMapper;




    @Override
    public SubServiceDto save(SubServiceDto subServiceDto) {
        SubService subService = modelMapper.map(subServiceDto, SubService.class);
        if(!existByName(subServiceDto.getName())) {
            subService.setSpecialists(new ArrayList<>());
            subService = subServiceRepository.save(subService);
            subServiceDto = modelMapper.map(subService, SubServiceDto.class);
            return subServiceDto;
        }
        throw new DuplicateException(String.format("%s is duplicate", subServiceDto.getName()));
    }


    @Override
    public ResponseSubServiceDto findById(Integer id) {
        SubService subService = subServiceRepository.findById(id).
                orElseThrow(
                        () -> new NotFoundException(String.format("%d not Fount",id)));
        return modelMapper.map(subService,ResponseSubServiceDto.class);
    }

    @Override
    public SubServiceDto findById2(Integer id) {
        SubService subService = subServiceRepository.findById(id).
                orElseThrow(
                        () -> new NotFoundException(String.format("%d not Fount",id)));
        return modelMapper.map(subService,SubServiceDto.class);

    }

    @Override
    public List<ResponseSubServiceDto> findAll() {
        List<SubService> subServiceList = subServiceRepository.findAll();
        return subServiceList.stream().map(subService -> modelMapper
                .map(subService, ResponseSubServiceDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ResponseSubServiceDto> subServicesOfOneService(int serviceId) {
        ServiceDto serviceDto = serviceService.findById2(serviceId);
        Service service = modelMapper.map(serviceDto,Service.class);
        List<SubService> subServiceList = subServiceRepository.subServicesOfOneService(service);
        return subServiceList.stream().map(subService -> modelMapper
                .map(subService, ResponseSubServiceDto.class)).collect(Collectors.toList());
    }

    @Override
    public ResponseSubServiceDto updateSubService(ResponseSubServiceDto subServiceDto) {
        SubService subService = subServiceRepository.findById(subServiceDto.getId()).orElseThrow(
                () -> new NotFoundException("id not Fount"));
        subService.setBasePrice(subServiceDto.getBasePrice());
        subService.setDetails(subServiceDto.getDetails());
        subService.setName(subServiceDto.getName());
        subService = subServiceRepository.save(subService);
        subServiceDto =modelMapper.map(subService,ResponseSubServiceDto.class);
        return subServiceDto;

    }

    @Override
    public SubServiceDto deleteById(int id) {
        SubService subService = subServiceRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("%d not Fount", id)));
        SubServiceDto subServiceDto = modelMapper.map(subService, SubServiceDto.class);
        subServiceRepository.deleteById(id);
        return subServiceDto;

    }

    @Override
    public boolean existsById(Integer id) {
        return subServiceRepository.existsById(id);
    }


    @Override
    public boolean existByName(String name) {
        return subServiceRepository.existByName(name);
    }


}
