package com.example.designpatterns.structmode.decorator;


public class TestDemo {

    public static void main(String[] args) {


        /**
         * 拿到对象的装饰者,在装饰者里做拓展
         * ContextWrapper
         *
         */
        Shap circle = new Circle();
        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
        //Shape redCircle = new RedShapeDecorator(new Circle());
        //Shape redRectangle = new RedShapeDecorator(new Rectangle());
        circle.draw();
        redCircle.draw();
        redRectangle.draw();
    }
}
