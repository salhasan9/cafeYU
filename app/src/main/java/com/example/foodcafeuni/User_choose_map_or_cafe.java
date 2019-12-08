package com.example.foodcafeuni;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class User_choose_map_or_cafe extends AppCompatActivity {
    ImageView Choose_cafe ,near_cafe ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choose_map_or_cafe);
        getSupportActionBar().hide();
        //ini Var
        Choose_cafe = (ImageView) findViewById(R.id.User_Choose_Cafe_Map_Or_Cafe);
        near_cafe= (ImageView)findViewById(R.id.User_Choose_Cafe_Map_Or_Cafe_2);
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
