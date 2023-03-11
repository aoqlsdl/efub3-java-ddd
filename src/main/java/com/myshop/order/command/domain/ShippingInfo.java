package com.myshop.order.command.domain;

public class ShippingInfo {
//    1-1. value type 이용 전 배송정보 표현
//    private String receiverName;
//    private String receiverPhoneNumber;
//    private String shippingAddress1;
//    private String shippingAddress2;
//    private String shippingZipcode;

//    1-2. value type을 이용해서 배송정보를 표현하기,
    private Receiver receiver;
    private Address address;
}
