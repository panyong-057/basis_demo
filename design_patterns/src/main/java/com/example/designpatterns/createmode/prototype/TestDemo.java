package com.example.designpatterns.createmode.prototype;

import com.example.designpatterns.structmode.decorator.Shap;

public class TestDemo {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shap clonedShape = (Shap) ShapeCache.getShape("1");


        Shap clonedShape2 = (Shap) ShapeCache.getShape("2");


        Shap clonedShape3 = (Shap) ShapeCache.getShape("3");

    }
}
