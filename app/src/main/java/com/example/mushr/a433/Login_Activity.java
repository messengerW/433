package com.example.mushr.a433;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Login_Activity extends AppCompatActivity {

    private Button btn1;
    private Context momentContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        momentContext = this;
        btn1 = (Button) findViewById(R.id.btn_login);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(momentContext,Page1_Activity.class);
                startActivity(intent);
            }
        });
    }
}
