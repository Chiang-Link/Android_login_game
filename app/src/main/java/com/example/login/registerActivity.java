package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class registerActivity extends AppCompatActivity {

    //声明控件
    private Button mBtnrgs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        //找到控件
        mBtnrgs = findViewById(R.id.btn_rgs);
        mBtnrgs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent= null;
                intent = new Intent(registerActivity.this,loginActivity.class);
                startActivity(intent);

            }
        });

    }
}