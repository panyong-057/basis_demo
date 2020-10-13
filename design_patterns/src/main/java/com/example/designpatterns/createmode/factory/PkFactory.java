package com.example.designpatterns.createmode.factory;

import android.content.Context;

import com.example.designpatterns.createmode.builder.PkMainDialog;

import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;

public class PkFactory implements AbstractFactory {


    public CopyOnWriteArrayList<BaseDialog> baseDialogs = new CopyOnWriteArrayList<>();


    public BaseDialog showDialog(Context ctx, int dialog_type, Object... args) {
        BaseDialog dialog = null;
        try {


            for (BaseDialog baseDialog : baseDialogs) {
                if (baseDialog != null && baseDialog.isShowing()) {
                    baseDialog.dismiss();
                }
            }
            baseDialogs.clear();


            WeakReference<Context> wf = new WeakReference<>(ctx);
            Context context = wf.get();
            switch (dialog_type) {
                case PK_MAIN:
                case PK_JOIN:
                case PK_OPEN_GIFT:
                case PK_RESULT:
                case PK_RULE:
                case PK_COM_CLOSE:
                case PK_COM_JOIN:

                    dialog = new PkMainDialog.Builder(context).setType(dialog_type).create();

                    break;
                default:
                    break;
            }
            if (dialog != null) {
                dialog.show();
                WeakReference<BaseDialog> dialog_wf = new WeakReference<>(dialog);
                baseDialogs.add(dialog_wf.get());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dialog;
    }

}
