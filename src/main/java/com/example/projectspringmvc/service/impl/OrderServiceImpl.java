package com.example.projectspringmvc.service.impl;


import com.example.projectspringmvc.dto.MyOrderDto;
import com.example.projectspringmvc.dto.OfferDto;
import com.example.projectspringmvc.dto.response.ResponseOrderDto;
import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.enumeration.OrderStatus;
import com.example.projectspringmvc.exception.NotFoundException;
import com.example.projectspringmvc.repository.OrderRepository;
import com.example.projectspringmvc.service.OfferService;
import com.example.projectspringmvc.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;

    private final OfferService offerService;

    private final ModelMapper modelMapper;


    //Done
    @Override
    public MyOrderDto save(MyOrderDto orderDto) {
        MyOrder order = modelMapper.map(orderDto, MyOrder.class);
        order.setOffers(new ArrayList<>());
        if (hasRightPrice(order) && hasRightTime(order)) {
            order = orderRepository.save(order);
            orderDto = modelMapper.map(order, MyOrderDto.class);
            return orderDto;
        }
        throw new IllegalArgumentException("Wrong time Or Price Below basePrice!");

    }

    //Done
    @Override
    public ResponseOrderDto findById(Integer id) {
        MyOrder order = orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("%d not Fount", id)));
        return modelMapper.map(order, ResponseOrderDto.class);
    }

    //NoNeed
    @Override
    public MyOrderDto findById2(Integer id) {
        MyOrder order = orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("%d not Fount", id)));
        return modelMapper.map(order, MyOrderDto.class);

    }

    //Done
    @Override
    public List<ResponseOrderDto> findAll() {
        List<MyOrder> orderList = orderRepository.findAll();
        return orderList.stream().map(order -> modelMapper
                .map(order, ResponseOrderDto.class)).collect(Collectors.toList());
    }

    //Done
    @Override
    public boolean existsById(Integer id) {
        return orderRepository.existsById(id);
    }

    //Done
    @Override
    public ResponseOrderDto chooseAnOfferForOrder(int orderId, int offerId) {
        MyOrder order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("id not found"));
        OfferDto offerDto = offerService.findById2(offerId);
        order.setOrderStatus(OrderStatus.WAITING_FOR_SPECIALIST_TO_GET_TO_YOUR_PLACE);
        order.setFinalPrice(offerDto.getOfferedPrice());
        order.setSpecialist(offerDto.getSpecialist());
        order.setStartingDate(offerDto.getOfferedStartingDate());
        order.setPredictedDuration((int) offerDto.getDurationHoursOfOrder());
        order = orderRepository.save(order);
        return modelMapper.map(order,ResponseOrderDto.class);
    }

    //Done
    @Override
    public ResponseOrderDto changeTheOrderStatusAfterCreatingOffer(int orderId) {
        MyOrder order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("id not found"));
        if (!order.getOffers().isEmpty()&&order.getOrderStatus().equals(OrderStatus.AWAITING_SPECIALIST_OFFER)) {
            order.setOrderStatus(OrderStatus.AWAITING_SPECIALIST_SELECTION);
            order.setId(orderId);
        }
        return modelMapper.map(order,ResponseOrderDto.class);
    }

    //NoNeed
    @Override
    public boolean hasRightTime( MyOrder order) {

        return LocalDate.now().isBefore(order.getDateOfNeed());
    }

    //NoNeed
    @Override
    public boolean hasRightPrice( MyOrder order) {

        return order.getOfferedPrice() >= order.getSubService().getBasePrice();
    }

    //Done
    @Override
    public MyOrderDto deleteById(int id) {
        MyOrder order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("%d not Fount", id)));
        MyOrderDto orderDto = modelMapper.map(order, MyOrderDto.class);
        orderRepository.deleteById(id);
        return orderDto;
    }

    //Done
    @Override
    public List<ResponseOrderDto> findOrderByCustomer(int customerId) {
        List<MyOrder> orders = orderRepository.findAll();
        List<MyOrder> customerOrders = new ArrayList<>();
        for (MyOrder order : orders
        ) {
            if (order.getCustomer().getId().equals(customerId)) {
                customerOrders.add(order);
            }

        }
        return customerOrders.stream().map(order -> modelMapper
                .map(order, ResponseOrderDto.class)).collect(Collectors.toList());

    }

    //Done
    @Override
    public List<ResponseOrderDto> findOrderByOrderStatus(OrderStatus orderStatus) {
        List<MyOrder> orders = orderRepository.findAll();
        List<MyOrder> statusOrders = new ArrayList<>();
        for (MyOrder order : orders
        ) {
            if (order.getOrderStatus().equals(orderStatus)) {
                statusOrders.add(order);
            }

        }
        return statusOrders.stream().map(order -> modelMapper
                .map(order, ResponseOrderDto.class)).collect(Collectors.toList());

    }

}



