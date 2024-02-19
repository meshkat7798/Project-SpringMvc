package com.example.projectspringmvc.service;
import com.example.projectspringmvc.dto.CustomerDto;
import com.example.projectspringmvc.dto.response.ResponseCustomerDto;
import com.example.projectspringmvc.dto.response.ResponseOrderDto;
import java.util.List;

@SuppressWarnings("unused")
public interface CustomerService {
    ResponseCustomerDto findByUserName(String username);

    boolean existByUserName(String username);

    boolean existByEmail(String email);

    CustomerDto save(CustomerDto customerDto);

    ResponseCustomerDto findById(Integer id);

    List<ResponseCustomerDto> findAll();

    ResponseCustomerDto update(ResponseCustomerDto customerDto);

    CustomerDto deleteById(int id);

    boolean existsById(Integer id);

    ResponseOrderDto payByCredit(int orderId);

    ResponseOrderDto payOnline(int orderId, String cardNumber, String cvv2, int month, int year, String password);

    ResponseOrderDto confirmProjectStarted(int orderId);

    ResponseOrderDto confirmedProjectFinished(int orderId);
}
