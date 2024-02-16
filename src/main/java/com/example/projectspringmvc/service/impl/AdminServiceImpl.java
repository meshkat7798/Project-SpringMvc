package com.example.projectspringmvc.service.impl;

import com.example.projectspringmvc.dto.AdminDto;
import com.example.projectspringmvc.dto.SpecialistDto;
import com.example.projectspringmvc.dto.SubServiceDto;
import com.example.projectspringmvc.dto.response.ResponseCustomerDto;
import com.example.projectspringmvc.dto.response.ResponseSpecialistDto;
import com.example.projectspringmvc.entity.SubService;
import com.example.projectspringmvc.entity.enumeration.SpecialistStatus;
import com.example.projectspringmvc.entity.user.Admin;
import com.example.projectspringmvc.entity.user.Customer;
import com.example.projectspringmvc.entity.user.Specialist;
import com.example.projectspringmvc.exception.DuplicateException;
import com.example.projectspringmvc.exception.IllegalArgumentException;
import com.example.projectspringmvc.exception.NotFoundException;
import com.example.projectspringmvc.repository.AdminRepository;
import com.example.projectspringmvc.service.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Configurable
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {


    private final SubServiceService subServiceService;

    private final SpecialistService specialistService;

    private final AdminRepository adminRepository;

    private final ModelMapper modelMapper;

    private final EntityManager entityManager;


    //Done
    @Override
    public AdminDto save(AdminDto adminDto) {
        Admin admin = modelMapper.map(adminDto, Admin.class);
        if(!adminRepository.existByUserName(adminDto.getUsername())) {
            admin = adminRepository.save(admin);
            adminDto = modelMapper.map(admin, AdminDto.class);
            return adminDto;
        }
        throw new DuplicateException(String.format("%s is duplicate", adminDto.getUsername()));

    }

    //Done
    @Override
    public AdminDto findById(Integer id) {
        Admin admin = adminRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("%d not Fount", id)));
        return modelMapper.map(admin, AdminDto.class);
    }


    //Done
    @Override
    public AdminDto findByUserName(String userName) {
        Admin admin = adminRepository.findByUserName(userName).orElseThrow(
                () -> new NotFoundException(String.format("%s not Fount", userName)));
        return modelMapper.map(admin, AdminDto.class);
    }


    //Done
    @Override
    public List<AdminDto> findAll() {
        List<Admin> adminList = adminRepository.findAll();
        return adminList.stream().map(admin -> modelMapper
                .map(admin, AdminDto.class)).collect(Collectors.toList());
    }


    //Done
    @Override
    public AdminDto update(AdminDto adminDto) {
        Admin admin = adminRepository.findById(adminDto.getId()).orElseThrow(() -> new NotFoundException("id not found"));
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(adminDto.getPassword());
        admin = adminRepository.save(admin);
        adminDto = modelMapper.map(admin, AdminDto.class);
        return adminDto;

    }

    //Done
    @Override
    public SpecialistDto confirmSpecialist(int specialistId) {
        SpecialistDto specialistDto = specialistService.findById2(specialistId);
        Specialist specialist = modelMapper.map(specialistDto, Specialist.class);
        specialist.setSpecialistStatus(SpecialistStatus.CONFIRMED);
        specialistDto = modelMapper.map(specialist, SpecialistDto.class);
        return specialistDto;
    }

    //Done
    @Override
    public SpecialistDto disableSpecialist(int specialistId) {
        SpecialistDto specialistDto = specialistService.findById2(specialistId);
        Specialist specialist = modelMapper.map(specialistDto, Specialist.class);
        if(specialist.getAverageScore()<0) {
            specialist.setSpecialistStatus(SpecialistStatus.DISABLED);
        }
        specialistDto = modelMapper.map(specialist, SpecialistDto.class);
        return specialistDto;
    }

    //Done
    @Override
    public AdminDto deleteById(int id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("%d not Fount", id)));
        AdminDto adminDto = modelMapper.map(admin, AdminDto.class);
        adminRepository.deleteById(id);
        return adminDto;

    }


    //Done
    @Override
    public boolean existsById(Integer id) {
        return adminRepository.existsById(id);
    }


    //Done
    @Override
    public boolean existByUserName(String username) {
        return adminRepository.existByUserName(username);
    }


    //Done
    @Override
    public void addSpecialistToSubService(int subServiceId, int specialistId) {
        SubServiceDto subService = subServiceService.findById2(subServiceId);
        SpecialistDto specialist = specialistService.findById2(specialistId);
        List<Specialist> specialists = subService.getSpecialists();
        List<SubService> subServices = specialist.getSubServices();
        List<com.example.projectspringmvc.entity.Service> services = specialist.getServices();

        List<SpecialistDto> specialistDtos = specialists.stream().map(anySpecialist -> modelMapper
                .map(anySpecialist, SpecialistDto.class)).toList();

        List<SubServiceDto> subServiceDtos = subServices.stream().map(anySubService -> modelMapper
                .map(anySubService, SubServiceDto.class)).toList();

        if (!specialistDtos.contains(specialist)) {
            specialistDtos.add(specialist);
            subServiceDtos.add(subService);
            if (!services.contains(subService.getService())) {
                services.add(subService.getService());
            }
            return;
        }
        throw new IllegalArgumentException("Specialist Already Has The SubService");
    }

    //Done
    @Override
    public void removeSpecialistFromSubService(int subServiceId, int specialistId) {
        SubServiceDto subService = subServiceService.findById2(subServiceId);
        SpecialistDto specialist = specialistService.findById2(specialistId);
        List<Specialist> specialists = subService.getSpecialists();
        List<SubService> subServices = specialist.getSubServices();

        List<SpecialistDto> specialistDtos = specialists.stream().map(anySpecialist -> modelMapper
                .map(anySpecialist, SpecialistDto.class)).toList();

        List<SubServiceDto> subServiceDtos = subServices.stream().map(anySubService -> modelMapper
                .map(anySubService, SubServiceDto.class)).toList();

        if (specialistDtos.contains(specialist)) {
            specialistDtos.remove(specialist);
            subServiceDtos.remove(subService);
            return;
        }
        throw new NullPointerException("Specialist Does Not Have The SubService");
    }

    //Done
    @Override
    public List<ResponseSpecialistDto> searchSpecialists(String firstName, String lastName, String email, String specialities, Boolean highScore) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Specialist> query = criteriaBuilder.createQuery(Specialist.class);
        Root<Specialist> root = query.from(Specialist.class);

        List<Predicate> predicates = new ArrayList<>();

        if (firstName != null) {
            predicates.add(criteriaBuilder.equal(root.get("firstName"), firstName));
        }
        if (lastName != null) {
            predicates.add(criteriaBuilder.equal(root.get("lastName"), lastName));
        }
        if (email != null) {
            predicates.add(criteriaBuilder.equal(root.get("email"), email));
        }
        if (specialities != null) {
            predicates.add(criteriaBuilder.equal(root.get("specialities"), specialities));
        }

        if (highScore != null) {
            if (highScore) {
                query.orderBy(criteriaBuilder.desc(root.get("averageScore")));
            } else {
                query.orderBy(criteriaBuilder.asc(root.get("averageScore")));
            }
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList().stream().map(specialist -> modelMapper
                .map(specialist, ResponseSpecialistDto.class)).collect(Collectors.toList());
    }

    //Done
    @Override
    public List<ResponseCustomerDto> searchCustomers(String firstName, String lastName, String email) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);

        List<Predicate> predicates = new ArrayList<>();

        if (firstName != null) {
            predicates.add(criteriaBuilder.equal(root.get("firstName"), firstName));
        }
        if (lastName != null) {
            predicates.add(criteriaBuilder.equal(root.get("lastName"), lastName));
        }
        if (email != null) {
            predicates.add(criteriaBuilder.equal(root.get("email"), email));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList().stream().map(customer -> modelMapper
                .map(customer, ResponseCustomerDto.class)).collect(Collectors.toList());
    }
}


