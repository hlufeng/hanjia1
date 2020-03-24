package com.example.guochuang1;

public class Note {
    private int id;
    private String time;
    private String content;

    public Note(String time, String content) {
        super();
        this.time = time;
        this.content = content;
    }

    public Note() {
        super();
        this.time = "";
        this.content = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
