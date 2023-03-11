package com.myshop.order.command.domain;

//    Order.java에 배송비 정보 변경 가능 여부를 판단하는 코드 삽입했으니, 여기에는 주문 상태만 작성
public enum OrderState {
    PAYMENT_WAITING, PREPARING, SHIPPED, DELEVERING, DELIVERY_COMPLETED,

    // 상태 반영하기 위해 추가
    CANCELED;
}
