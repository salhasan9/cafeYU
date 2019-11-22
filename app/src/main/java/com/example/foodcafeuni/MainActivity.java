package com.example.foodcafeuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_sgin_in , btn_sgin_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
//ini Var
        btn_sgin_up = (Button)findViewById(R.id.BtnSginup);
        btn_sgin_in= (Button)findViewById(R.id.BtnSginIn);
        //Click Function
        btn_sgin_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent obj = new Intent(MainActivity.this,Sign_up.class);
                startActivity(obj);
            }
        });

        btn_sgin_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent obj = new Intent(MainActivity.this,Sign_in.class);
                startActivity(obj);
            }
        });
    }


}


