package com.sbm.liveboard.service;

import com.sbm.liveboard.domain.Order;
import com.sbm.liveboard.domain.OrderDTO;
import com.sbm.liveboard.domain.OrderType;
import com.sbm.liveboard.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


public class OrderServiceTest {

    @Test
    public void registerOrder() {
        //given
        Order order = new Order("user-1", new BigDecimal("306"), new BigDecimal("3.5"), OrderType.BUY);
        OrderRepository orderRepository = mock(OrderRepository.class);

        //when
        OrderService orderService = new OrderServiceImpl(orderRepository);
        orderService.registerOrder(order);

        // then
        verify(orderRepository).addOrder(order);
    }

    @Test
    public void cancelOrder() {
        //given
        Order order = new Order("user-1", new BigDecimal("306"), new BigDecimal("3.5"), OrderType.BUY);
        OrderRepository orderRepository = mock(OrderRepository.class);

        //when
        OrderService orderService = new OrderServiceImpl(orderRepository);
        orderService.cancelOrder(order.getOrderId());

        // then
        verify(orderRepository).removeOrder(order.getOrderId());
    }

    @Test
    public void getOrderSummaryForSell() {

        // given
        OrderRepository orderRepository = mock(OrderRepository.class);

        OrderService orderService = new OrderServiceImpl(orderRepository);

        Stream<Order> sellOrders = Stream.of(
                new Order("user-1", new BigDecimal("306"), new BigDecimal("3.5"), OrderType.SELL),
                new Order("user-2", new BigDecimal("310"), new BigDecimal("1.2"), OrderType.SELL),
                new Order("user-3", new BigDecimal("307"), new BigDecimal("1.5"), OrderType.SELL),
                new Order("user-4", new BigDecimal("306"), new BigDecimal("2.0"), OrderType.SELL));

        when(orderRepository.getOrdersForType((any(OrderType.class)))).thenReturn(sellOrders);

        // when
        List<OrderDTO> sellOrderSummary = orderService.getOrderSummaryForSell();

        // then
        verify(orderRepository).getOrdersForType(OrderType.SELL);

        assertEquals(306, sellOrderSummary.get(0).getPrice());
        assertEquals(5.5, sellOrderSummary.get(0).getQuantity());
        assertEquals(307, sellOrderSummary.get(1).getPrice());
        assertEquals(1.5, sellOrderSummary.get(1).getQuantity());
        assertEquals(310, sellOrderSummary.get(2).getPrice());
        assertEquals(1.2, sellOrderSummary.get(2).getQuantity());
        assertEquals(3, sellOrderSummary.size());

        // not part of the test
        System.out.println(sellOrderSummary);
    }


    @Test
    public void getOrderSummaryForBuy() {

        // given
        OrderRepository orderRepository = mock(OrderRepository.class);

        OrderService orderService = new OrderServiceImpl(orderRepository);

        Stream<Order> sellOrders = Stream.of(
                new Order("user-1", new BigDecimal("306"), new BigDecimal("3.5"), OrderType.BUY),
                new Order("user-2", new BigDecimal("310"), new BigDecimal("1.2"), OrderType.BUY),
                new Order("user-3", new BigDecimal("307"), new BigDecimal("1.5"), OrderType.BUY),
                new Order("user-4", new BigDecimal("306"), new BigDecimal("2.0"), OrderType.BUY));

        when(orderRepository.getOrdersForType((any(OrderType.class)))).thenReturn(sellOrders);

        // when
        List<OrderDTO> sellOrderSummary = orderService.getOrderSummaryForBuy();

        // then
        verify(orderRepository).getOrdersForType(OrderType.BUY);

        assertEquals(310, sellOrderSummary.get(0).getPrice());
        assertEquals(1.2, sellOrderSummary.get(0).getQuantity());
        assertEquals(307, sellOrderSummary.get(1).getPrice());
        assertEquals(1.5, sellOrderSummary.get(1).getQuantity());
        assertEquals(306, sellOrderSummary.get(2).getPrice());
        assertEquals(5.5, sellOrderSummary.get(2).getQuantity());
        assertEquals(3, sellOrderSummary.size());

        // not part of the test
        System.out.println(sellOrderSummary);
    }

}
