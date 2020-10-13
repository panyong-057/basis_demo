package com.example.designpatterns.structmode.decorator;



public abstract class ShapeDecorator implements Shap {
    protected Shap decoratedShape;

    public ShapeDecorator(Shap decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public void draw(){
        decoratedShape.draw();
    }
}