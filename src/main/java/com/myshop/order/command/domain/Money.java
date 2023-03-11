package com.myshop.order.command.domain;

public class Money {
    private int value;

    public Money add(Money money) {
        return new Money(this.value + money.value);
    }
// 돈 계산을 위한 기능
    public Money multiply(int multiplier) {
        return new Money(value * multiplier);
    }
}
