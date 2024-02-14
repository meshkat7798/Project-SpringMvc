package com.example.projectspringmvc.service;

import com.example.projectspringmvc.dto.MyOrderDto;
import com.example.projectspringmvc.dto.response.ResponseOrderDto;
import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.enumeration.OrderStatus;
import java.util.List;


@SuppressWarnings("unused")
public interface OrderService {


    MyOrderDto save(MyOrderDto myOrderDto);

    ResponseOrderDto findById(Integer id);

    MyOrderDto findById2(Integer id);

    List<ResponseOrderDto> findAll();

    boolean existsById(Integer id);

    MyOrderDto chooseAnOfferForOrder(int orderId, int offerId);

    MyOrderDto changeTheOrderStatusAfterCreatingOffer( int offerId);

    boolean hasRightTime(MyOrder order);

    boolean hasRightPrice(MyOrder order);

    MyOrderDto deleteById(int id);

    List<ResponseOrderDto> findOrderByCustomer(int customerId);

    List<ResponseOrderDto> findOrderByOrderStatus(OrderStatus orderStatus);

}
