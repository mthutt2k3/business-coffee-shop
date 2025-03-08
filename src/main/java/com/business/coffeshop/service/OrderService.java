package com.business.coffeshop.service;

import com.business.coffeshop.entity.OrderDetail;
import com.business.coffeshop.entity.Orders;

import java.util.List;


public interface OrderService {
    Orders saveOrder(Orders order);
    void saveOrderDetails(List<OrderDetail> orderDetails);
}
