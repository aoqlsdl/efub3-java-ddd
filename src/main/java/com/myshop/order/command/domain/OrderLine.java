package com.myshop.order.command.domain;

// 주문 항목을 표현
public class OrderLine {
//    의미를 명확하게 표현하기 위해 밸류타입을 사용
    private Product product;
    private int price;
    private int quantity;
    private int amounts;
    private Money price;

    public OrderLine(Product product, int price, int quantity) {
        this.product = product;
//        this.price = price;
        // Money의 계산 데이터를 가져와서 사용
        this.price = new Money(price.getValue());
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    private int calculateAmounts() {
        return price * quantity;
    }

}
