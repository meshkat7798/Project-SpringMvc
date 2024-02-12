package com.example.projectspringmvc.service.impl;


import com.example.projectspringmvc.dto.AdminDto;
import com.example.projectspringmvc.dto.CustomerDto;
import com.example.projectspringmvc.dto.MyOrderDto;
import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.enumeration.OrderStatus;
import com.example.projectspringmvc.entity.user.Admin;
import com.example.projectspringmvc.entity.user.Customer;
import com.example.projectspringmvc.exception.NotFoundException;
import com.example.projectspringmvc.repository.CustomerRepository;
import com.example.projectspringmvc.service.CustomerService;
import com.example.projectspringmvc.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final OrderService orderService;

    private final ModelMapper modelMapper;


    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto,Customer.class);
        if (Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)" +
                "(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", customer.getPassword())
                && Pattern.matches("^[a-zA-Z ]{2,30}$", customer.getFirstname())
                && Pattern.matches("^[a-zA-Z ]{2,30}$", customer.getLastname())
                && Pattern.matches("^[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}$", customer.getEmail())
                && !existByUserName(customer.getUsername())
                && !existByEmail(customer.getEmail()))
        {
            customer = customerRepository.save(customer);
            customerDto= modelMapper.map(customer,CustomerDto.class);
            return customerDto;

        }
        throw new IllegalArgumentException("Invalid Data Or username or email Exists!");
    }


    @Override
    public CustomerDto findByUserName(String userName) {
        Customer customer = customerRepository.findByUserName(userName).orElseThrow(
                () -> new NotFoundException(String.format("%s not Fount",userName)));;
        return modelMapper.map(customer,CustomerDto.class);
    }

    @Override
    public CustomerDto findById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("%d not Fount",id)));;
        return modelMapper.map(customer,CustomerDto.class);
    }



    @Override
    public List<CustomerDto> findAll() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream().map(customer -> modelMapper
                .map(customer,CustomerDto.class)).collect(Collectors.toList());
    }


    @Override
    public CustomerDto update(CustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerDto.getId()).orElseThrow(()->new NotFoundException("id not found"));
        customer.setFirstname(customerDto.getFirstname());
        customer.setLastname(customerDto.getLastname());
        customer.setUsername(customerDto.getUsername());
        customer.setPassword(customerDto.getPassword());
        customer.setEmail(customerDto.getEmail());
        customer.setCredit(customerDto.getCredit());
        customer.setMyOrders(customerDto.getMyOrders());
        customer=customerRepository.save(customer);
        customerDto=modelMapper.map(customer,CustomerDto.class);
        return customerDto;

    }

    @Override
    public CustomerDto deleteById(int id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()->new NotFoundException("id not found"));
        CustomerDto adminDto = modelMapper.map(customer,CustomerDto.class);
        customerRepository.deleteById(id);
        return adminDto;
    }


    @Override
    public boolean existsById(Integer id) {
        return customerRepository.existsById(id);
    }


    @Override
    public boolean existByUserName(String username) {
        return customerRepository.existByUserName(username);
    }



    @Override
    public boolean existByEmail(String email) {
        return customerRepository.existByEmail(email);
    }


    @Override
    public Customer creditExchange(MyOrder myOrder, double finalPrice) {
        Customer customer = myOrder.getCustomer();
        customer.setCredit(myOrder.getCustomer().getCredit() - finalPrice);
        customer.setId(myOrder.getCustomer().getId());
        return customer;
    }

    @Override
    public MyOrderDto confirmProjectStarted(int orderId){
        MyOrderDto orderDto = orderService.findById(orderId);
      MyOrder order =  modelMapper.map(orderDto,MyOrder.class);
        if (order.getOffer().getOfferedStartingDate().isBefore(LocalDate.now())){
            order.setOrderStatus(OrderStatus.STARTED);
            orderDto = modelMapper.map(order,MyOrderDto.class);
            return orderDto;
        }
        throw new IllegalArgumentException("It is Not Starting Time Yet!");
    }

    @Override
    public MyOrderDto confirmedProjectFinished(int orderId){
        MyOrderDto orderDto = orderService.findById(orderId);
        MyOrder order =  modelMapper.map(orderDto,MyOrder.class);
        if (order.getOrderStatus().equals(OrderStatus.STARTED)){
            order.setOrderStatus(OrderStatus.FINISHED);
            orderDto = modelMapper.map(order,MyOrderDto.class);
            return orderDto;
        }
        throw new IllegalArgumentException("The Order Has Not Started Yet!");
    }

}
