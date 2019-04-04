package com.example.databindingdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void startActivity(Class c) {
        startActivity(new Intent(this,c));
    }

    public void startActivity2(View view) {
        startActivity(Main2Activity.class);
    }
}
