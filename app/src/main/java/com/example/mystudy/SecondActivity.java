package com.example.mystudy;

import android.os.Bundle;
import android.view.View;

import com.example.mystudy.base.BaseMvpActivity;


public class SecondActivity extends BaseMvpActivity {


    private static Thread sRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        sRunnable = new Thread() {
            @Override
            public void run() {
            }
        };

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
