package com.example.projectspringmvc.service.impl;

import com.example.projectspringmvc.dto.SubServiceDto;
import com.example.projectspringmvc.entity.Service;
import com.example.projectspringmvc.entity.SubService;
import com.example.projectspringmvc.exception.DuplicateException;
import com.example.projectspringmvc.exception.NotFoundException;
import com.example.projectspringmvc.repository.SubServiceRepository;
import com.example.projectspringmvc.service.SpecialistService;
import com.example.projectspringmvc.service.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class SubServiceServiceImpl implements SubServiceService {


    private final SubServiceRepository subServiceRepository;

    private final SpecialistService specialistService;

    private final ModelMapper modelMapper;




    @Override
    public SubServiceDto save(SubServiceDto subServiceDto) {
        SubService subService = modelMapper.map(subServiceDto, SubService.class);
        if(!existByName(subServiceDto.getName())) {
            subService = subServiceRepository.save(subService);
            subServiceDto = modelMapper.map(subService, SubServiceDto.class);
            return subServiceDto;
        }
        throw new DuplicateException(String.format("%s is duplicate", subServiceDto.getName()));
    }


    @Override
    public SubServiceDto findById(Integer id) {
        SubService subService = subServiceRepository.findById(id).
                orElseThrow(
                        () -> new NotFoundException(String.format("%d not Fount",id)));
        return modelMapper.map(subService,SubServiceDto.class);
    }

    @Override
    public List<SubServiceDto> findAll() {
        List<SubService> subServiceList = subServiceRepository.findAll();
        return subServiceList.stream().map(subService -> modelMapper
                .map(subService, SubServiceDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<SubServiceDto> subServicesOfOneService(Service service) {
        List<SubService> subServiceList = subServiceRepository.subServicesOfOneService(service);
        return subServiceList.stream().map(subService -> modelMapper
                .map(subService, SubServiceDto.class)).collect(Collectors.toList());
    }

    @Override
    public SubServiceDto updateSubService(SubServiceDto subServiceDto) {
        SubService subService = subServiceRepository.findById(subServiceDto.getId()).orElseThrow(
                () -> new NotFoundException("id not Fount"));
        subService.setService(subServiceDto.getService());
        subService.setBasePrice(subServiceDto.getBasePrice());
        subService.setDetails(subServiceDto.getDetails());
        subService.setName(subServiceDto.getName());
        subService = subServiceRepository.save(subService);
        subServiceDto =modelMapper.map(subService,SubServiceDto.class);
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
