package com.example.retrofit2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editLogin;
    private EditText editPassword;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mfindViewById();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
                .baseUrl("https://emap.mychery.com:4430/prod/api/v2/")
                .build();
    }

    private void mfindViewById() {
        editLogin = findViewById(R.id.et_username);
        editPassword = findViewById(R.id.et_password);
        Button textLogin = findViewById(R.id.tv_login);
        textLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login :
                login();
                break;
            default:
                break;
        }
    }

    private void login() {
    }
}
