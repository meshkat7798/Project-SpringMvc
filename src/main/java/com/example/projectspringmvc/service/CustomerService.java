package com.example.projectspringmvc.service;

import com.example.projectspringmvc.dto.AdminDto;
import com.example.projectspringmvc.dto.CustomerDto;
import com.example.projectspringmvc.dto.MyOrderDto;
import com.example.projectspringmvc.dto.response.ResponseCustomerDto;
import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.user.Customer;

import java.util.List;

@SuppressWarnings("unused")
public interface CustomerService {
    ResponseCustomerDto findByUserName(String username);

    boolean existByUserName(String username);

    boolean existByEmail(String email);

    CustomerDto save(CustomerDto customerDto);

    ResponseCustomerDto findById(Integer id);

//    List<CustomerDto> findAll();

    List<ResponseCustomerDto> findAll();

    ResponseCustomerDto update(ResponseCustomerDto customerDto);

    CustomerDto deleteById(int id);

    boolean existsById(Integer id);

    Customer creditExchange(MyOrder myOrder, double finalPrice);

    MyOrderDto confirmProjectStarted(int orderId);

    MyOrderDto confirmedProjectFinished(int orderId);
}
