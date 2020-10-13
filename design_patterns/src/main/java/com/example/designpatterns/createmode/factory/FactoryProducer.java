package com.example.designpatterns.createmode.factory;

public class FactoryProducer {

    public final static int PK = 0;
    public final static int STOOL = 1;

    public static AbstractFactory getFactory(int type) {
        switch (type) {
            case PK:
                return new PkFactory();
            default:
                return new StoolFactory();
        }

    }
}
