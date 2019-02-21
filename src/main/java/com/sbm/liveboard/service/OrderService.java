package com.sbm.liveboard.service;

import com.sbm.liveboard.domain.Order;
import com.sbm.liveboard.domain.OrderDTO;

import java.util.List;

public interface OrderService {

    /**
     * Register and order
     *
     * @param order to register`
     */
    void registerOrder(final Order order);

    /**
     * Cancel order given an order id
     *
     * @param orderId id of order to cancel
     */
    void cancelOrder(final String orderId);

    /**
     * Get sell order summary in ascending order of price after merging quantity of equal prices
     *
     * @return list if sell order
     */
    List<OrderDTO> getOrderSummaryForSell();

    /**
     * Get buy order summary in descending order of price after merging quantity of equal prices
     *
     * @return list if sell order
     */
    List<OrderDTO> getOrderSummaryForBuy();

}
