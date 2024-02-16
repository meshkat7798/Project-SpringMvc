package com.example.projectspringmvc.service.impl;



import com.example.projectspringmvc.dto.ServiceDto;
import com.example.projectspringmvc.dto.response.ResponseServiceDto;
import com.example.projectspringmvc.entity.Service;
import com.example.projectspringmvc.exception.DuplicateException;
import com.example.projectspringmvc.exception.NotFoundException;
import com.example.projectspringmvc.repository.ServiceRepository;
import com.example.projectspringmvc.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {


    private final ServiceRepository serviceRepository;

    private final ModelMapper modelMapper;

    //Done
    @Override
    public ServiceDto save(ServiceDto serviceDto) {
        Service service = modelMapper.map(serviceDto, Service.class);
        if(!existByName(serviceDto.getName())) {
            service.setSpecialists(new ArrayList<>());
            service = serviceRepository.save(service);
            serviceDto = modelMapper.map(service, ServiceDto.class);
            return serviceDto;
        }
        throw new DuplicateException(String.format("%s is duplicate", serviceDto.getName()));
    }

    //Done
    @Override
    public ServiceDto deleteById(int id) {
        Service service = serviceRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("%d not Fount", id)));
        ServiceDto serviceDto = modelMapper.map(service, ServiceDto.class);
        serviceRepository.deleteById(id);
        return serviceDto;

    }

    //Done
    @Override
    public ResponseServiceDto findById(Integer id) {
        Service service = serviceRepository.findById(id).
                orElseThrow(
                        () -> new NotFoundException(String.format("%d not Fount",id)));
        return modelMapper.map(service,ResponseServiceDto.class);
    }

    //NoNeed
    @Override
    public ServiceDto findById2(Integer id) {
        Service service = serviceRepository.findById(id).
                orElseThrow(
                        () -> new NotFoundException(String.format("%d not Fount",id)));
        return modelMapper.map(service,ServiceDto.class);

    }

    //Done
    @Override
    public List<ResponseServiceDto> findAll() {
        List<Service> serviceList = serviceRepository.findAll();
        return serviceList.stream().map(service -> modelMapper
                .map(service, ResponseServiceDto.class)).collect(Collectors.toList());

    }

    //Done
    @Override
    public boolean existsById(Integer id) {
        return serviceRepository.existsById(id);
    }


    //Done
    @Override
    public boolean existByName(String name) {
        return serviceRepository.existByName(name);
    }

}
