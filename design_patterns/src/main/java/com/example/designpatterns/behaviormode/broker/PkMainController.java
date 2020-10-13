package com.example.designpatterns.behaviormode.broker;

import android.content.Context;

import com.example.designpatterns.createmode.builder.PkMainDialog;

public class PkMainController {
    private final Context mContext;
    final PkMainDialog mDialog;


    public void setType(int type) {
        this.type = type;
        mDialog.setType(type);
    }

    int type = 0;

    public PkMainController(Context context, PkMainDialog di) {
        this.mContext = context;
        this.mDialog = di;
    }

}
