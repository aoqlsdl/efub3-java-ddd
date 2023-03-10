package com.myshop.order.command.domain;

public class Order {
    private OrderState state;
    private ShippingInfo shippingInfo;

    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        if (!state.isShippingInfoChangeable()) {
            throw new IllegalStateException("can't change shipping in " + state);
        }

        this.shippingInfo = newShippingInfo;
    }

//    Order에서 배송지 정보 가능 여부에 대한 로직을 구현해두어야 주문 상태와 다른 정보를 함께 사용할 경우를 대비할 수 있음
    private boolean isShippingInfoChangeable() {
        return state == OrderState.PAYMENT_WAITING ||
                state == OrderState.PREPARING;
    }

}
