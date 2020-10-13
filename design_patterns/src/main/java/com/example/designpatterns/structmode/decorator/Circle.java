package com.example.designpatterns.structmode.decorator;

public class Circle  implements Shap{
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    private String id;
    @Override
    public void draw() {

    }
}
