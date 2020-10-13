package com.example.designpatterns.createmode.factory;

import android.content.Context;

public interface AbstractFactory {
    public final int STOOL_MAIN = 1 << 1;
    public final int STOOL_JOIN = 1 << 2;
    public final int STOOL_OPEN_GIFT = 1 << 3;
    public final int STOOL_RESULT = 1 << 4;
    public final int STOOL_RULE = 1 << 5;
    public final int STOOL_COM_CLOSE = 1 << 6;
    public final int STOOL_COM_JOIN = 1 << 7;

    public final int PK_MAIN = 1 << 1;
    public final int PK_JOIN = 1 << 2;
    public final int PK_OPEN_GIFT = 1 << 3;
    public final int PK_RESULT = 1 << 4;
    public final int PK_RULE = 1 << 5;
    public final int PK_COM_CLOSE = 1 << 6;
    public final int PK_COM_JOIN = 1 << 7;


    public BaseDialog showDialog(Context ctx, int dialog_type, Object... args);



}
