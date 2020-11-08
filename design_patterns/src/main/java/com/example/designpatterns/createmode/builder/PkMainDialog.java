package com.example.designpatterns.createmode.builder;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.designpatterns.behaviormode.broker.PkMainController;
import com.example.designpatterns.createmode.factory.BaseDialog;


public class PkMainDialog extends BaseDialog {

    public void setType(int type) {
        this.type = type;
    }

    int type;
    public PkMainDialog( Context context) {
        super(context);
    }

    public PkMainDialog( Context context, int themeResId) {
        super(context, themeResId);
    }

    public PkMainDialog( Context context, Object... obj) {
        super(context);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this.getContext());
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(
                ViewGroup.MarginLayoutParams.MATCH_PARENT,
                ViewGroup.MarginLayoutParams.MATCH_PARENT);
        textView.setLayoutParams(params);
        textView.setText("PkMainDialog");
        textView.setGravity(Gravity.CENTER);
        setContentView(textView);
    }

    public static class Builder {
        PkMainController pkMainController;
        private Context mContext;
        private int mTheme;

        public Builder setType(int type) {
            this.type = type;
            return this;
        }

        private int type;

        public Builder(Context context) {
            this(context, 0);
        }


        public Builder( Context context,  int themeResId) {
            mContext = context;
            this.mTheme = themeResId;
        }


        public PkMainDialog create(Object... args) {
            PkMainDialog dialog = new PkMainDialog(mContext, args);
            pkMainController = new PkMainController(mContext, dialog);
            pkMainController.setType(type);
            return dialog;
        }
    }

}
