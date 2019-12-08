package com.example.foodcafeuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class choose_change_pass_or_change_profile extends AppCompatActivity {
    ImageView change_pass ,change_profile ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_change_pass_or_change_profile);
        getSupportActionBar().hide();

        change_pass = (ImageView) findViewById(R.id.User_change_pass);
        change_profile= (ImageView)findViewById(R.id.User_Change_profile);

        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent obj = new Intent(choose_change_pass_or_change_profile.this,change_password.class);
                startActivity(obj);
            }
        });
        change_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent obj = new Intent(choose_change_pass_or_change_profile.this, setting.class);
                startActivity(obj);
            }
        });
    }
}
