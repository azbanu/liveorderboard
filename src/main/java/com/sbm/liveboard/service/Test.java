package com.sbm.liveboard.service;

import com.sbm.liveboard.database.OrderDatabase;
import com.sbm.liveboard.domain.Order;
import com.sbm.liveboard.domain.OrderType;
import com.sbm.liveboard.repository.OrderRepositoryImpl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class Test {

    public static void main (String[] args)
    {

        //final Map<String, Order> orderDatabase = OrderDatabase.INSTANCE.getDatabase();

        OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl());

        Order order1 = new Order("1",new BigDecimal("306"), new BigDecimal("3.5"), null);
        Order order2 = new Order("2", new BigDecimal("310"), new BigDecimal("1.2"),OrderType.BUY);
        Order order3 = new Order("3", new BigDecimal("307"), new BigDecimal("1.5"),OrderType.BUY);
        Order order4 = new Order("4", new BigDecimal("306"), new BigDecimal("2.0"),OrderType.BUY);

        Order order5 = new Order("1",new BigDecimal("306"), new BigDecimal("3.5"), OrderType.SELL);
        Order order6 = new Order("2", new BigDecimal("310"), new BigDecimal("1.2"),OrderType.SELL);
        Order order7 = new Order("3", new BigDecimal("307"), new BigDecimal("1.5"),OrderType.SELL);
        Order order8 = new Order("4", new BigDecimal("306"), new BigDecimal("2.0"),OrderType.SELL);

        orderService.registerOrder(order1);
        orderService.registerOrder(order2);
        orderService.registerOrder(order3);
        orderService.registerOrder(order4);
        orderService.registerOrder(order5);

        orderService.registerOrder(order6);
        orderService.registerOrder(order7);
        orderService.registerOrder(order8);

        System.out.println(orderService.getOrderSummaryForBuy());

        System.out.println(orderService.getOrderSummaryForSell());

        orderService.cancelOrder(order1.getOrderId());
        orderService.cancelOrder(order6.getOrderId());

        System.out.println(orderService.getOrderSummaryForBuy());
        System.out.println(orderService.getOrderSummaryForSell());



    }





}
