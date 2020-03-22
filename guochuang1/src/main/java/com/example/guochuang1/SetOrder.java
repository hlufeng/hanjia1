package com.example.guochuang1;

public class SetOrder {
    private String order_time;
    private String start_time;
    private int during_time;
    private String check_time;
    private int money;
    private int state;
    private String type;
    private int content;

    public SetOrder() {
        super();
        order_time = "";
        start_time = "";
        during_time = 0;
        check_time = "";
        money = 0;
        state = 0;
        type = "";
        content = 0;
    }

    public SetOrder(String order_time, String start_time, int during_time, String check_time, int money,
                    int state, String type, int content) {
        super();
        this.order_time = order_time;
        this.start_time = start_time;
        this.during_time = during_time;
        this.check_time = check_time;
        this.money = money;
        this.state = state;
        this.type = type;
        this.content = content;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public int getDuring_time() {
        return during_time;
    }

    public void setDuring_time(int during_time) {
        this.during_time = during_time;
    }

    public String getCheck_time() {
        return check_time;
    }

    public void setCheck_time(String check_time) {
        this.check_time = check_time;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public String show() {
        String s = "order_time:" + order_time +
                "\nstart_time:" + start_time +
                "\nduring_time:" + during_time +
                "\ncheck_time:" + check_time +
                "\nmoney:" + money +
                "\nstate:" + state +
                "\ntype:" + type +
                "\ncontent:" + content;
        return s;
    }
}
