package com.myshop.order.command.domain;

public class UserInfo {
    private String id;
    private String name;

    public UserInfo() {}

//    1-1. get/set 메서드를 습관적으로 만드는 예시 -> Order의 메서드를 set 메서드로 변경해야 함
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
