package com.example.designpatterns.behaviormode.observer;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class PkObserver {

    public List<PkObserved> mList = new ArrayList<>();

    private volatile static PkObserver pkObserver;

    public static PkObserver getInstacne() {
        if (pkObserver == null) {
            synchronized (PkObserver.class) {
                if (pkObserver == null) {
                    pkObserver = new PkObserver();
                }
            }
        }
        return pkObserver;
    }

    public void addPkObserved(PkObserved pkObserved) {
        WeakReference<PkObserved> wf = new WeakReference<>(pkObserved);
        mList.add(wf.get());
    }

    public void removePkObserved(PkObserved pkObserved) {
        mList.remove(pkObserved);
    }

    public void noticeChange(Object ... args) {

        Log.e("onMsgNotice","mList size " +mList.size());
        for (PkObserved pkObserved : mList) {
            pkObserved.onUpdate(args);
        }

    }
}
