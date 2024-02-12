package com.example.projectspringmvc.service;

import com.example.projectspringmvc.dto.MyOrderDto;
import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.enumeration.OrderStatus;
import java.util.List;


@SuppressWarnings("unused")
public interface OrderService {


    MyOrderDto save(MyOrderDto myOrderDto);

    MyOrderDto findById(Integer id);

    List<MyOrderDto> findAll();

    boolean existsById(Integer id);

    MyOrderDto chooseAnOfferForOrder(int orderId, int offerId);

    MyOrderDto changeTheOrderStatusAfterCreatingOffer( int offerId);

    boolean hasRightTime(MyOrder order);

    boolean hasRightPrice(MyOrder order);

    MyOrderDto deleteById(int id);

    List<MyOrderDto> findOrderByCustomer(int customerId);

    List<MyOrderDto> findOrderByOrderStatus(OrderStatus orderStatus);

}
