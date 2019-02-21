package com.sbm.liveboard.repository;

import com.sbm.liveboard.domain.Order;
import com.sbm.liveboard.domain.OrderType;

import java.util.Set;
import java.util.stream.Stream;


public interface OrderRepository {


    /**
     * Add an order
     *
     * @param order to be registered
     */
    void addOrder(final Order order);

    /**
     * Remove an order
     *
     * @param orderId Id of order to be removed
     */
    void removeOrder(final String orderId);

    /**
     * Get order by type
     *
     * @param orderType type of order
     */
    Stream<Order> getOrdersForType(OrderType orderType);
}
