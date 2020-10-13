package com.example.designpatterns.createmode.prototype;

import com.example.designpatterns.structmode.decorator.Circle;
import com.example.designpatterns.structmode.decorator.Rectangle;
import com.example.designpatterns.structmode.decorator.Shap;

import java.util.Hashtable;

public class ShapeCache {

    private static Hashtable<String, Shap> shapeMap
            = new Hashtable<String, Shap>();

    public static Shap getShape(String shapeId) {
        Shap cachedShape = shapeMap.get(shapeId);
        return (Shap) cachedShape.clone();
    }

    // 对每种形状都运行数据库查询，并创建该形状
    // shapeMap.put(shapeKey, shape);
    // 例如，我们要添加三种形状
    public static void loadCache() {
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(),circle);


        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        shapeMap.put(rectangle.getId(),rectangle);
    }
}
