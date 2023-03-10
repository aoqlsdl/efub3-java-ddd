package com.myshop.order.command.domain;

// 주문 상태를 표현

public enum OrderState {
    // 배송지를 변경할 수 있는지 검사할 수 있는 메서드 isShippingChangeable -> 주문 대기, 상품 준비 상태에서는 배송지 변경 가능
    PAYMENT_WAITING {
        public boolean isShippingChangeable() {
            return true;
        }
    },
    PREPARING {
        public boolean isShippingChangeable() {
            return true;
        }
    },
    // 배송지를 변경할 수 있는지 검사할 수 있는 메서드 isShippingChangeable -> 수송, 배달, 배달 완료 상태에서는 배송지 변경 불가
    SHIPPED, DELEVERING, DELIVERY_COMPLETED;

    public boolean isShippingChangeable() {
        return false;
    }
}
