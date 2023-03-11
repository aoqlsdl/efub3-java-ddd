package com.myshop.order.command.domain;

// 받는 사람을 나타내는 도메인 개념
public class Receiver {
    private String name;
    private String phoneNumber;

    public  Receiver(String name, String  phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean equals(Object other) {
        if (other == null) return false;
        if (this == other) return true;
        if (!(other instanceof Receiver)) return false;

        Receiver that = (Receiver) other;
        return this.name.equals(that.name) && this.phoneNumber.equals(that.phoneNumber)
    }
}
