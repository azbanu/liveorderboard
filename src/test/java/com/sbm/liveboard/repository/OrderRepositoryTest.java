package com.sbm.liveboard.repository;

import com.sbm.liveboard.domain.Order;
import com.sbm.liveboard.domain.OrderType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class OrderRepositoryTest {


    @Test
    public void addOrder() {
        //Given
        List<Order> orders = Arrays.asList(new Order("user-1", new BigDecimal("306"), new BigDecimal("3.5"), OrderType.BUY),
                new Order("user-2", new BigDecimal("306"), new BigDecimal("2.0"), OrderType.SELL));

        //when
        OrderRepository orderRepository = new OrderRepositoryImpl();
        orders.forEach(orderRepository::addOrder);
        List<Order> sellOrders = orderRepository.getOrdersForType(OrderType.SELL).collect(toList());
        List<Order> buyOrders = orderRepository.getOrdersForType(OrderType.BUY).collect(toList());

        // Then
        assertTrue(sellOrders.size() == 1);
        assertTrue(buyOrders.size() == 1);
        assertTrue(buyOrders.contains(orders.get(0)));
        assertTrue(sellOrders.contains(orders.get(1)));

    }

    @Test
    public void removeOrder() {
        //Given
        List<Order> orders = Arrays.asList(new Order("user-11", new BigDecimal("306"), new BigDecimal("3.5"), OrderType.BUY),
                new Order("user-12", new BigDecimal("306"), new BigDecimal("2.0"), OrderType.SELL));

        //when
        OrderRepositoryImpl orderRepository = new OrderRepositoryImpl();
        orders.forEach(orderRepository::addOrder);

        orderRepository.removeOrder(orders.get(0).getOrderId());


        List<Order> buyOrders = orderRepository.getOrdersForType(OrderType.BUY).collect(toList());
        List<Order> sellOrders = orderRepository.getOrdersForType(OrderType.SELL).collect(toList());

        // Then
        assertFalse(buyOrders.contains(orders.get(0)));
        assertTrue(sellOrders.size() > 0);
    }
}
