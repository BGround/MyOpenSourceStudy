package com.example.databindingdemo;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.databindingdemo.databinding.ActivityMain2Binding;
import com.example.databindingdemo.model.ObservableGood;
import com.example.databindingdemo.model.User;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMain2Binding binding2 = DataBindingUtil.setContentView(this,R.layout.activity_main2);

        User user = new User("zhangya", "123456");
        binding2.setUserInfo(user);

        ObservableGood mGood = new ObservableGood("zy", "hello");
        binding2.setGood(mGood);

    }
}