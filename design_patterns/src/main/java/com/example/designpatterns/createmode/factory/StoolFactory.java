package com.example.designpatterns.createmode.factory;

import android.content.Context;

import com.example.designpatterns.createmode.builder.StoolMainDialog;

import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;

public class StoolFactory implements AbstractFactory {


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
                case STOOL_MAIN:
                case STOOL_JOIN:
                case STOOL_OPEN_GIFT:
                case STOOL_RESULT:
                case STOOL_RULE:
                case STOOL_COM_CLOSE:
                case STOOL_COM_JOIN:

                    dialog = new StoolMainDialog.Builder(context).setType(dialog_type).create();

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
