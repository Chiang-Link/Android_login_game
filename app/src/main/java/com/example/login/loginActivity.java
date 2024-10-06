package com.example.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.login.uti.toastuti;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class loginActivity extends AppCompatActivity {
    //声明控件
    private Button mBtnregister;
    private EditText mEtUser;
    private EditText mEtPassword;
    private Button mBtnLogin;
    private CheckBox checkBox;
    private boolean is_login;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //找到控件
        mBtnregister = findViewById(R.id.btn_register);
        mEtUser = findViewById(R.id.et_1);
        mEtPassword = findViewById(R.id.et_2);
        mBtnLogin = findViewById(R.id.btn_login);
        checkBox = findViewById(R.id.checkbox);
        mSharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        //判断是否记住密码
//        is_login = mSharedPreferences.getBoolean("is_login", false);
//        if (is_login){
//            String username = mSharedPreferences.getString("username", null);
//            String password = mSharedPreferences.getString("password",null);
//            mEtUser.setText(username);
//            mEtPassword.setText(password);
//            checkBox.setChecked(true);
//        }


        //注册跳转
        mBtnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(loginActivity.this, registerActivity.class);
                startActivity(intent);

            }
        });
        //登录跳转
        //匹配对应的用户名和密码才能进行登陆操作

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mEtUser.getText().toString();
                String password = mEtPassword.getText().toString();
                Intent A = null;
                String success = "登陆成功";
                String fail = "用户名或密码错误，请重新输入";
                //假设用户名为小皇帝，密码为woshitianzi
                if (username.equals("小皇帝") && password.equals("woshitianzi")) {
                    SharedPreferences.Editor edit = mSharedPreferences.edit();
                    edit.putBoolean("is_login",is_login);
                    edit.putString("username", String.valueOf(mEtUser));
                    boolean commit = edit.commit();

                    toastuti.showmsg(getApplicationContext(), success);
                    //正确 跳转
                    A = new Intent(loginActivity.this, skipActivity.class);
                    startActivity(A);
                } else {
                    //不正确弹出登陆失败 toast
                    toastuti.showmsg(getApplicationContext(), fail);
                }
            }

        });
        //checkbox点击事件
       checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               is_login=isChecked;
           }
       });
    }
}



