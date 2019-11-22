package com.example.foodcafeuni;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class User_choose_map_or_cafe extends AppCompatActivity {
    Button Choose_cafe ,near_cafe ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choose_map_or_cafe);
        //ini Var
        Choose_cafe = (Button)findViewById(R.id.Choose_cafe);
        near_cafe= (Button)findViewById(R.id.near_cafe);
        //Click Function
        Choose_cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent obj = new Intent(User_choose_map_or_cafe.this,Choose_Cafe.class);
                startActivity(obj);
            }
        });

        near_cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent obj = new Intent(User_choose_map_or_cafe.this, Student_map_choose.class);
                startActivity(obj);
            }
        });
    }
}
