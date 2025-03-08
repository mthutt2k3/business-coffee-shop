package com.business.coffeshop.service.impl;

import com.business.coffeshop.entity.OrderDetail;
import com.business.coffeshop.entity.Orders;
import com.business.coffeshop.repository.OrderRepository;
import com.business.coffeshop.repository.OrderDetailRepository;
import com.business.coffeshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    @Transactional
    public Orders saveOrder(Orders order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void saveOrderDetails(List<OrderDetail> orderDetails) {
        orderDetailRepository.saveAll(orderDetails);
    }
}
