package com.example.designpatterns.createmode.singleton;

public class MySingleton {

    private volatile static MySingleton pkObserver;

    public static MySingleton getInstacne() {
        if (pkObserver == null) {
            synchronized (MySingleton.class) {
                if (pkObserver == null) {
                    pkObserver = new MySingleton();
                }
            }
        }
        return pkObserver;
    }

}
