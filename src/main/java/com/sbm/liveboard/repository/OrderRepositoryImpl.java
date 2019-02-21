package com.sbm.liveboard.repository;

import com.sbm.liveboard.database.OrderDatabase;
import com.sbm.liveboard.domain.Order;
import com.sbm.liveboard.domain.OrderType;

import java.util.Map;
import java.util.stream.Stream;


public class OrderRepositoryImpl implements OrderRepository {

    private final Map<String, Order> orderDatabase = OrderDatabase.INSTANCE.getDatabase();

    @Override
    public void addOrder(final Order order) {
        orderDatabase.put(order.getOrderId(), order);
    }

    @Override
    public void removeOrder(final String orderId) {
        orderDatabase.remove(orderId);
    }


    @Override
    public Stream<Order> getOrdersForType(OrderType orderType) {
        return orderDatabase.values().stream().filter(order -> order.getOrderType() == orderType);
    }

}
