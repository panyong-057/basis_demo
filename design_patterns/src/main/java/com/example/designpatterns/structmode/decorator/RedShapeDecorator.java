package com.example.designpatterns.structmode.decorator;

public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shap decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shap decoratedShape){
        System.out.println("Border Color: Red");
    }
}
