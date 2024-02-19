package com.example.projectspringmvc.service.impl;

import com.example.projectspringmvc.dto.CustomerDto;
import com.example.projectspringmvc.dto.MyOrderDto;
import com.example.projectspringmvc.dto.response.ResponseCustomerDto;
import com.example.projectspringmvc.dto.response.ResponseOrderDto;
import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.enumeration.OrderStatus;
import com.example.projectspringmvc.entity.user.Customer;
import com.example.projectspringmvc.entity.user.Specialist;
import com.example.projectspringmvc.exception.NotFoundException;
import com.example.projectspringmvc.repository.CustomerRepository;
import com.example.projectspringmvc.repository.OrderRepository;
import com.example.projectspringmvc.repository.SpecialistRepository;
import com.example.projectspringmvc.service.CustomerService;
import com.example.projectspringmvc.service.OrderService;
import com.example.projectspringmvc.service.SpecialistService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final SpecialistRepository specialistRepository;

    private final OrderService orderService;

    private final SpecialistService specialistService;

    private final ModelMapper modelMapper;


    //Done
    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        if (Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)" +
                "(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", customer.getPassword())
                && Pattern.matches("^[a-zA-Z ]{2,30}$", customer.getFirstname())
                && Pattern.matches("^[a-zA-Z ]{2,30}$", customer.getLastname())
                && Pattern.matches("^[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}$", customer.getEmail())
                && !existByUserName(customer.getUsername())
                && !existByEmail(customer.getEmail())) {
            customer.setMyOrders(new ArrayList<>());
            customer = customerRepository.save(customer);
            customerDto = modelMapper.map(customer, CustomerDto.class);
            return customerDto;

        }
        throw new IllegalArgumentException("Invalid Data Or username or email Exists!");
    }


    //Done
    @Override
    public ResponseCustomerDto findByUserName(String userName) {
        Customer customer = customerRepository.findByUserName(userName).orElseThrow(
                () -> new NotFoundException(String.format("%s not Fount", userName)));
        return modelMapper.map(customer, ResponseCustomerDto.class);
    }

    //Done
    @Override
    public ResponseCustomerDto findById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("%d not Fount", id)));
        return modelMapper.map(customer, ResponseCustomerDto.class);
    }


    //Done
    @Override
    public List<ResponseCustomerDto> findAll() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream().map(customer -> modelMapper
                .map(customer, ResponseCustomerDto.class)).collect(Collectors.toList());
    }


    //Done
    @Override
    public ResponseCustomerDto update(ResponseCustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerDto.getId()).orElseThrow(() -> new NotFoundException("id not found"));
        customer.setFirstname(customerDto.getFirstname());
        customer.setLastname(customerDto.getLastname());
        customer.setUsername(customerDto.getUsername());
        customer.setPassword(customerDto.getPassword());
        customer.setEmail(customerDto.getEmail());
        customer = customerRepository.save(customer);
        customerDto = modelMapper.map(customer, ResponseCustomerDto.class);
        return customerDto;

    }

    //Done
    @Override
    public CustomerDto deleteById(int id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("id not found"));
        CustomerDto adminDto = modelMapper.map(customer, CustomerDto.class);
        customerRepository.deleteById(id);
        return adminDto;
    }


    //Done
    @Override
    public boolean existsById(Integer id) {
        return customerRepository.existsById(id);
    }


    //Done
    @Override
    public boolean existByUserName(String username) {
        return customerRepository.existByUserName(username);
    }


    //Done
    @Override
    public boolean existByEmail(String email) {
        return customerRepository.existByEmail(email);
    }


    //Done
    @Override
    public ResponseOrderDto payByCredit(int orderId) {
        MyOrder myOrder = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("id not found"));
        MyOrderDto orderDto = modelMapper.map(myOrder,MyOrderDto.class);
        Customer customer = myOrder.getCustomer();
        if (customer.getCredit() >= myOrder.getFinalPrice()) {
            customer.setCredit(orderDto.getCustomer().getCredit() - myOrder.getFinalPrice());
            customer.setId(orderDto.getCustomer().getId());
            specialistService.creditExchange(myOrder, myOrder.getFinalPrice());
            myOrder.setOrderStatus(OrderStatus.PAID);
            myOrder = orderRepository.save(myOrder);
            return modelMapper.map(myOrder, ResponseOrderDto.class);
        }
        throw new IllegalArgumentException("Not enough credit!");
    }

    //Done
    @Override
    public ResponseOrderDto payOnline(int orderId, String cardNumber, String cvv2, int month, int year, String password) {
        MyOrderDto orderDto = orderService.findById2(orderId);
        MyOrder myOrder = modelMapper.map(orderDto, MyOrder.class);
        Customer customer = myOrder.getCustomer();
        if (Pattern.matches("^[0-9]{16}$", cardNumber)
                && Pattern.matches("^[0-9]{3,4}$", cvv2)
                && customer.getPassword().equals(password)
                && (year > LocalDate.now().getYear()
                || (year == LocalDate.now().getYear()
                && month > LocalDate.now().getMonth().getValue()))) {
            specialistService.creditExchange(myOrder, orderDto.getFinalPrice());
            orderDto.setOrderStatus(OrderStatus.PAID);
            orderDto = orderService.save(orderDto);

            return modelMapper.map(orderDto, ResponseOrderDto.class);
        }
        throw new IllegalArgumentException("Invalid Input Data!");
    }

    //Done
    @Override
    public ResponseOrderDto confirmProjectStarted(int orderId) {
        MyOrder order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("id not found"));
        if (order.getDateOfNeed().isBefore(LocalDate.now()) || order.getDateOfNeed().equals(LocalDate.now())) {
            order.setStartingDate(LocalDate.now());
            order.setOrderStatus(OrderStatus.STARTED);
            order.setStartingHour(LocalTime.now());
            order.setId(orderId);
            order = orderRepository.save(order);
            return modelMapper.map(order, ResponseOrderDto.class);

        }
        throw new IllegalArgumentException("It is Not Starting Time Yet!");
    }

    //Done
    @Override
    public ResponseOrderDto confirmedProjectFinished(int orderId) {
        MyOrder order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("id not found"));
        if (order.getOrderStatus().equals(OrderStatus.STARTED)) {
            order.setOrderStatus(OrderStatus.FINISHED);
            order.setFinishedHour(LocalTime.now());
            order.setId(orderId);
            order = orderRepository.save(order);
            if (order.getFinishedHour().getHour() - order.getStartingHour().getHour() > order.getPredictedDuration()) {
                int delay = order.getFinishedHour().getHour() - order.getStartingHour().getHour() - order.getPredictedDuration();
                for (int i = 0; i < delay; i++) {
                    Specialist specialist = order.getSpecialist();
                  List<Integer> scores = specialist.getSpecialistScores();
                  scores.add(-1);
                  specialist.setSpecialistScores(scores);
                  specialist.setId(specialist.getId());
                  specialistRepository.save(specialist);
                }
            }
            return modelMapper.map(order, ResponseOrderDto.class);
        }
        throw new IllegalArgumentException("The Order Has Not Started Yet!");
    }


}
