package com.example.designpatterns.createmode.builder;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.designpatterns.createmode.factory.BaseDialog;

public class StoolMainDialog extends BaseDialog {
    public StoolMainDialog( Context context) {
        super(context);
    }

    public StoolMainDialog( Context context, int themeResId) {
        super(context, themeResId);
    }

    public StoolMainDialog( Context context, Object... obj) {
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
        textView.setText("StoolMainDialog");
        textView.setGravity(Gravity.CENTER);
        setContentView(textView);
    }

    //建造者模式
    public static class Builder {
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


        public StoolMainDialog create(Object ... args) {
            StoolMainDialog dialog = new StoolMainDialog(mContext, args);

            return dialog;
        }
    }

}
