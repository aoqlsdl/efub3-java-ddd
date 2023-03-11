package com.myshop.order.command.application;
import com.myshop.order.command.domain.Order;

public class CancelOrderService {
    @Transactional
    public void cancelOrder(String orderId) {

//        Order 도메인 객체에 취소 처리를 위임
        Order order = findOrderById(orderId);
        if (order == null) throw new OrderNotFoundException(orderId);
        order.cancel();
    }
}
