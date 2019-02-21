package com.sbm.liveboard.domain;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Order {

    private final String orderId;

    private final String userId;

    private final BigDecimal price;

    private final BigDecimal quantity;

    private final OrderType orderType;


    public Order(String userId, BigDecimal price, BigDecimal quantity, OrderType orderType) {
        this.orderId = UUID.randomUUID().toString();
        this.userId = userId;
        this.price = price;
        this.quantity = quantity;
        this.orderType = orderType;
        validateOrder();
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getOrderId(), order.getOrderId()) &&
                Objects.equals(getUserId(), order.getUserId()) &&
                Objects.equals(getPrice(), order.getPrice()) &&
                Objects.equals(getQuantity(), order.getQuantity()) &&
                getOrderType() == order.getOrderType();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getOrderId(), getUserId(), getPrice(), getQuantity(), getOrderType());
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", orderType=" + orderType +
                '}';
    }

    private void validateOrder()
    {
        Objects.requireNonNull(getUserId());
        Objects.requireNonNull(getPrice());
        Objects.requireNonNull(getOrderType());
        Objects.requireNonNull(getQuantity());
    }

}
