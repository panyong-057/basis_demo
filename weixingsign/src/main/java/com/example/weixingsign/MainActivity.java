package com.example.weixingsign;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.pm.PackageManager.GET_RESOLVED_FILTER;

public class MainActivity extends AppCompatActivity {


    private TextView errorTv;
    private Button getBtn;
    private EditText pkgNameEt;
    private TextView resultTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pkgNameEt = ((EditText) findViewById(R.id.et_package_name));
        resultTv = ((TextView) findViewById(R.id.tv_result));
        errorTv = ((TextView) findViewById(R.id.tv_error));
        getBtn = ((Button) findViewById(R.id.bt_get_result));
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultTv.setText("");
                errorTv.setText("");
                String str = pkgNameEt.getText().toString();
                if ((str != null) && (str.length() > 0)) {
                    getSign(str);
                }
            }
        });


    }

    @SuppressLint("WrongConstant")
    private Signature[] getRawSignature(Context paramContext, String paramString) {
        if ((paramString == null) || (paramString.length() == 0)) {
            errout("getSignature, packageName is null");
            return null;
        }
        PackageManager localPackageManager = paramContext.getPackageManager();
        PackageInfo localPackageInfo;
        try {
            localPackageInfo = localPackageManager.getPackageInfo(paramString, GET_RESOLVED_FILTER);
            if (localPackageInfo == null) {
                errout("info is null, packageName = " + paramString);
                return null;
            }
        } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
            errout("NameNotFoundException");
            return null;
        }
        return localPackageInfo.signatures;
    }

    private void getSign(String paramString) {
        Signature[] arrayOfSignature = getRawSignature(this, paramString);
        if ((arrayOfSignature == null) || (arrayOfSignature.length == 0)) {
            errout("signs is null");
        }
        if (arrayOfSignature != null) {
            for (Signature signature : arrayOfSignature) {
                stdout(MD5Utils.getMessageDigest(signature.toByteArray()));
            }
        } else {
            if (errorTv.getText().toString().trim().contains("NameNotFoundException")) {
                Toast.makeText(this, "请先安装输入包名的apk的正式版再试！", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void stdout(String paramString) {
        resultTv.append(paramString + "\n");
    }
    private void errout(String paramString) {
        errorTv.append(paramString + "\n");
    }
}
