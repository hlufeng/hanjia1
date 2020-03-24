package com.example.guochuang1;

public class FoodOrder {
    private String order_time;
    private int money;
    private String content;

    public FoodOrder(String order_time, int money, String content) {
        this.order_time = order_time;
        this.money = money;
        this.content = content;
    }

    public FoodOrder() {
        this.order_time = "";
        this.content = "";
        this.money = 0;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
