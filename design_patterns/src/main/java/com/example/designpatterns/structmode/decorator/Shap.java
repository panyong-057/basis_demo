package com.example.designpatterns.structmode.decorator;

public abstract class Shap  implements Cloneable{
    abstract void draw();

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
