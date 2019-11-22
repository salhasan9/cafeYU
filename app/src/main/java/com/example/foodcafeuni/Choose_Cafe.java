package com.example.foodcafeuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Choose_Cafe extends AppCompatActivity {
    ImageView V_Admin_Home_admin_imgae_res1 , V_Admin_Home_admin_imgae_res2 ,
            V_Admin_Home_admin_imgae_res3,Admin_Home_admin_imgae_res4 ,
            Admin_Home_admin_imgae_setting , Admin_Home_admin_imgae_logout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_choose__cafe);
        V_Admin_Home_admin_imgae_res1=(ImageView)findViewById(R.id.Admin_Home_admin_imgae_res1);
        V_Admin_Home_admin_imgae_res2 = (ImageView)findViewById(R.id.Admin_Home_admin_imgae_res2);
        V_Admin_Home_admin_imgae_res3 = (ImageView)findViewById(R.id.Admin_Home_admin_imgae_res3);
        Admin_Home_admin_imgae_res4 = (ImageView)findViewById(R.id.Admin_Home_admin_imgae_res4);
        Admin_Home_admin_imgae_setting = (ImageView)findViewById(R.id.Admin_Home_admin_imgae_setting);
        Admin_Home_admin_imgae_logout= (ImageView)findViewById(R.id.Admin_Home_admin_imgae_logout);


        V_Admin_Home_admin_imgae_res1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent obj = new Intent(Choose_Cafe.this , Student_Home.class);
                obj.putExtra("ID","1");
                startActivity(obj);
            }
        });
        V_Admin_Home_admin_imgae_res2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent obj = new Intent(Choose_Cafe.this , Student_Home.class);
                obj.putExtra("ID","2");
                startActivity(obj);      }

        });
        V_Admin_Home_admin_imgae_res3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent obj = new Intent(Choose_Cafe.this , Student_Home.class);
                obj.putExtra("ID","3");
                startActivity(obj);
            }
        });
        Admin_Home_admin_imgae_res4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent obj = new Intent(Choose_Cafe.this , Student_Home.class);
                obj.putExtra("ID","4");
                startActivity(obj);
            }
        });

        Admin_Home_admin_imgae_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj = new Intent(Choose_Cafe.this , setting.class);
                obj.putExtra("ID","Setting");
                startActivity(obj);

            }
        });
        Admin_Home_admin_imgae_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj = new Intent(Choose_Cafe.this , MainActivity.class);
                obj.putExtra("ID","LogOut");
                startActivity(obj);

            }
        });
    }
}
