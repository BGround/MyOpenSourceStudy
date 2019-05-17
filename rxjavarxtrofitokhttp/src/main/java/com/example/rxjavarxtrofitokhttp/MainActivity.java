package com.example.rxjavarxtrofitokhttp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.rxjavarxtrofitokhttp.http.AppResultData;
import com.example.rxjavarxtrofitokhttp.http.BaseObserver;
import com.example.rxjavarxtrofitokhttp.http.RetrofitClient;
import com.example.rxjavarxtrofitokhttp.login.UserBean;
import com.example.rxjavarxtrofitokhttp.moudle.HomeActivity;
import com.example.rxjavarxtrofitokhttp.widgets.CustomToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editLogin;
    private EditText editPassword;
    @BindView(R.id.tv_login)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mfindViewById();
        ButterKnife.bind(this);
    }

    private void mfindViewById() {
        editLogin = findViewById(R.id.et_username);
        editPassword = findViewById(R.id.et_password);
//        Button textLogin = findViewById(R.id.tv_login);
//        textLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login :
//                login();
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.tv_login)
    void login() {
        String text = editLogin.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        if (TextUtils.isEmpty(text)){
            Toast.makeText(this,"请输入您的工号",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_LONG).show();
            return;
        }
        JSONObject param = new JSONObject();
        param.put("username",text);
        param.put("password",password);
        RetrofitClient.getMyCherryData("user/login.do",param)
//                .compose(this.<Response<AppResultData>>bindToLife())
                .subscribe(new BaseObserver() {
                    @Override
                    public void loadSuccess(int code, String msg, String result) {
//                        hideProgress();
//                        Log.d(TAG, "login: "+result);
                        if (code==RetrofitClient.CODE_OK){
                            UserBean userBean = JSON.parseObject(result,UserBean.class);
//                            UserManager.getInstance().saveUserBean(userBean);
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                            String str = JSON.toJSONString(userBean.getRealName());
//                            textView.setText(str);
//                            Log.d("zhangya", "loadSuccess: "+str);
                            finish();
                        }else {
                            CustomToast.showToast(msg);
                        }
                    }
                });

    }
}
