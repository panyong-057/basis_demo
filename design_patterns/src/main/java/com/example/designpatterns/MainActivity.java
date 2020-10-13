package com.example.designpatterns;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.designpatterns.createmode.factory.AbstractFactory;
import com.example.designpatterns.createmode.factory.FactoryProducer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showPk(View view) {
        AbstractFactory factory = FactoryProducer.getFactory(FactoryProducer.PK);
        factory.showDialog(this,AbstractFactory.PK_MAIN);
    }

    public void showStool(View view) {
        AbstractFactory factory = FactoryProducer.getFactory(FactoryProducer.STOOL);
        factory.showDialog(this,AbstractFactory.STOOL_MAIN);
    }
}
