package com.example.guochuang1;

public class Items {
    private int pic_id;//图片
    private String name;
    private int price;//单价
    private int num;

    public Items(){
        super();
        pic_id = 0;
        name = "";
        price=0;
        num=0;
    }

    public Items(int pic, String name, int price, int num) {
        super();
        this.pic_id=pic;
        this.name=name;
        this.price=price;
        this.num=num;
    }

    public int getPic() {
        return pic_id;
    }

    public void setPic(int pic) {
        this.pic_id = pic;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
