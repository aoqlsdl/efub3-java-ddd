package com.myshop.order.command.domain;

import java.util.List;
import java.util.Objects;
import java.lang.Override;

public class Order {
// 도메인 객체가 불완전한 상태로 사용되는 것을 막기 위해 생성자를 통해 필요한 데이터를 모두 수집
    Order order = new Order(orderer, lines, shippingInfo, OrderState.PREPARING);
    private OrderState state;
    private ShippingInfo shippingInfo;
    private List<OrderLine> orderLines;
    private ShippingInfo shippingInfo;
    private Money totalAmounts;


    // 엔티티를 구현한 클래스 -> 식별자를 이용해 메서드를 구현
    private String orderNumber;

//    OrderNo 타입 자체로 id가 주문번호임을 알 수 있음
    private OrderNo id;

    private OrderNo getId() {
        return id;
    }

    public Order(Orderer orderer, List<OrderLine> orderLines, ShippingInfo shippingInfo, OrderState state) {
        setOrderer(orderer);
        setOrderLines(orderLines);
    }

    private void setOrderer(Orderer orderer) {
        if (orderer == null) throw new IllegalArgumentException("no orderer");
        this.orderer = orderer;
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        verifyAtLeastOneOrMoreOrderLines(orderLines);
        this.orderLines = orderLines;
        calculateTotalAmounts();
    }

    @Override
    public boolean equals(Objects obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj.getClass() != Order.class) return false;
        Order other = (Order) obj;
        if (this.orderNumber == null) return false;
        return this.orderNumber.equals(other.orderNumber);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
        return result;
    }
    public Order(List<OrderLine> orderLines, ShippingInfo shippingInfo) {
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
        calculateTotalAmounts();
    }

    public void changeShippingInfo(ShippingInfo newShippingInfo) {
//        1-1. 배송정보 수정 가능 여부를 판별
//        if (!state.isShippingInfoChangeable()) {
//            throw new IllegalStateException("can't change shipping in " + state);
//        }

//        1-2. 배송 시작했는지 확인하여 배송정보 수정 가능 여부를 결정
        verifyNotYetShipped();
        this.state = OrderState.CANCELED;

        this.shippingInfo = newShippingInfo;
    }

//    Order에서 배송지 정보 가능 여부에 대한 로직을 구현해두어야 주문 상태와 다른 정보를 함께 사용할 경우를 대비할 수 있음

    private boolean isShippingInfoChangeable() {
        return state == OrderState.PAYMENT_WAITING ||
                state == OrderState.PREPARING;
    }


    private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
        if (orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException("no OrderLine");
        }
    }

    private void calculateTotalAmounts() {
//        int sum = orderLines.stream()
//                .mapToInt(x -> x.getAmounts())
//                .sum();
//        this.totalAmounts = new Money(sum);
        this.totalAmounts = orderLines.stream().mapToInt(x -> x.getAmounts().sum());
    }

//    set 메서드 변경 전
//    private void setShippingInfo(ShippingInfo shippingInfo) {
//        if ( shipping == null )
//            throw new IllegalArgumentException("no ShippingInfo");
//        this.shippingInfo = shippingInfo;
//    }

//    set 메서드 변경 후
    private void setShippingInfo(ShippingInfo newShipping) {
        if ( shipping == null )
            throw new IllegalArgumentException("no ShippingInfo");
        this.shippingInfo = shippingInfo;
    }

    private void cancel() {
        verifyNotYetShipped();
        this.state() = OrderState.CANCELED;
    }

    private void verifyNotYetShipped() {
        if (state != OrderState.PAYMENT_WAITING && state != OrderState.PREPARING)
            throw new IllegalStateException("already shipped");
    }



}
