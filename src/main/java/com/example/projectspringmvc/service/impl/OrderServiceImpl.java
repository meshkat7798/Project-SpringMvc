package com.example.projectspringmvc.service.impl;


import com.example.projectspringmvc.dto.MyOrderDto;
import com.example.projectspringmvc.dto.OfferDto;
import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.Offer;
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


    @Override
    public MyOrderDto save(MyOrderDto orderDto) {
        MyOrder order = modelMapper.map(orderDto, MyOrder.class);
        if (hasRightPrice(order) && hasRightTime(order)) {
            order = orderRepository.save(order);
            orderDto = modelMapper.map(order, MyOrderDto.class);
            return orderDto;
        }
        throw new IllegalArgumentException("Wrong time Or Price Below basePrice!");

    }

    @Override
    public MyOrderDto findById(Integer id) {
        MyOrder order = orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("%d not Fount", id)));
        return modelMapper.map(order, MyOrderDto.class);
    }

    @Override
    public List<MyOrderDto> findAll() {
        List<MyOrder> orderList = orderRepository.findAll();
        return orderList.stream().map(order -> modelMapper
                .map(order, MyOrderDto.class)).collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Integer id) {
        return orderRepository.existsById(id);
    }

    @Override
    public MyOrderDto chooseAnOfferForOrder(int orderId, int offerId) {
        MyOrder order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("id not found"));
        OfferDto offerDto = offerService.findById(offerId);
        order.setOrderStatus(OrderStatus.WAITING_FOR_SPECIALIST_TO_GET_TO_YOUR_PLACE);
        order.setFinalPrice(offerDto.getOfferedPrice());
        order.setSpecialist(offerDto.getSpecialist());
        Offer offer = modelMapper.map(offerDto,Offer.class);
        order.setOffer(offer);
        order = orderRepository.save(order);
        return modelMapper.map(order,MyOrderDto.class);
    }

    @Override
    public MyOrderDto changeTheOrderStatusAfterCreatingOffer(int offerId) {
        OfferDto offer= offerService.findById(offerId);
        MyOrder order = offer.getOrder();
        if (order.getOrderStatus().equals(OrderStatus.AWAITING_SPECIALIST_OFFER)) {
            order.setOrderStatus(OrderStatus.AWAITING_SPECIALIST_SELECTION);
            offer.getOrder().setId(offer.getOrder().getId());
        }
        return modelMapper.map(order,MyOrderDto.class);
    }

    @Override
    public boolean hasRightTime( MyOrder order) {

        return LocalDate.now().isBefore(order.getDateOfNeed());
    }

    @Override
    public boolean hasRightPrice( MyOrder order) {

        return order.getOfferedPrice() >= order.getSubService().getBasePrice();
    }

    @Override
    public MyOrderDto deleteById(int id) {
        MyOrder order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("%d not Fount", id)));
        MyOrderDto orderDto = modelMapper.map(order, MyOrderDto.class);
        orderRepository.deleteById(id);
        return orderDto;
    }

    @Override
    public List<MyOrderDto> findOrderByCustomer(int customerId) {
        List<MyOrder> orders = orderRepository.findAll();
        List<MyOrder> customerOrders = new ArrayList<>();
        for (MyOrder order : orders
        ) {
            if (order.getCustomer().getId().equals(customerId)) {
                customerOrders.add(order);
            }

        }
        return customerOrders.stream().map(order -> modelMapper
                .map(order, MyOrderDto.class)).collect(Collectors.toList());

    }

    @Override
    public List<MyOrderDto> findOrderByOrderStatus(OrderStatus orderStatus) {
        List<MyOrder> orders = orderRepository.findAll();
        List<MyOrder> statusOrders = new ArrayList<>();
        for (MyOrder order : orders
        ) {
            if (order.getOrderStatus().equals(orderStatus)) {
                statusOrders.add(order);
            }

        }
        return statusOrders.stream().map(order -> modelMapper
                .map(order, MyOrderDto.class)).collect(Collectors.toList());

    }

}



