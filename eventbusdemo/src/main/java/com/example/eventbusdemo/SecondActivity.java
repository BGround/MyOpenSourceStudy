package com.example.eventbusdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;

public class SecondActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        button = findViewById(R.id.bt_message);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("111111111111111"));
                finish();
            }
        });
    }

//    @OnClick(R.id.bt_message)
//    void getMain() {
//        EventBus.getDefault().post(new MessageEvent("aaaaaaaaaaaaaaaaaaa"));
//
//        finish();
//    }
//

}
