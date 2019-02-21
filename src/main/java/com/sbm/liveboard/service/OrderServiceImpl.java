package com.sbm.liveboard.service;

import com.sbm.liveboard.domain.Order;
import com.sbm.liveboard.domain.OrderDTO;
import com.sbm.liveboard.domain.OrderType;
import com.sbm.liveboard.repository.OrderRepository;
import com.sbm.liveboard.repository.OrderRepositoryImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void registerOrder(final Order order) {
        orderRepository.addOrder(order);
    }

    @Override
    public void cancelOrder(final String orderId) {
        orderRepository.removeOrder(orderId);
    }

    @Override
    public List<OrderDTO> getOrderSummaryForSell() {
        return getMergedOrders(OrderType.SELL).entrySet().stream()
                .map(p -> new OrderDTO(p.getKey().doubleValue(), p.getValue()))
                .sorted(comparing(OrderDTO::getPrice))
                .collect(toList());
    }

    @Override
    public List<OrderDTO> getOrderSummaryForBuy() {
        return getMergedOrders(OrderType.BUY).entrySet().stream()
                .map(p -> new OrderDTO(p.getKey().doubleValue(), p.getValue()))
                .sorted(comparing(OrderDTO::getPrice).reversed())
                .collect(toList());
    }


    private Map<BigDecimal, Double> getMergedOrders(OrderType orderType) {
        return orderRepository.getOrdersForType(orderType)
                .collect(
                        Collectors.groupingBy(Order::getPrice, Collectors.summingDouble((Order x) -> x.getQuantity().doubleValue())));
    }


}
