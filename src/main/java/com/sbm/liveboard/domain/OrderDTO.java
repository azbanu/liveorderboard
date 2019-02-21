package com.sbm.liveboard.domain;

public class OrderDTO {

    private final Double price;

    private final Double quantity;

    public OrderDTO(Double price, Double quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Double getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
