package com.example.foodcafeuni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodcafeuni.Classes.Users;
import com.example.foodcafeuni.Temp.Current_Any;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class setting extends AppCompatActivity {

    private EditText settings_confirm_collage, settings_password, settings_confirm_password, settings_user_email;
    private Button A_setting_update, A_setting_close;
    private ProgressDialog load;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().hide();
        settings_confirm_collage = (EditText) findViewById(R.id.settings_confirm_collage);
        settings_user_email = (EditText) findViewById(R.id.settings_user_email);
        settings_password = (EditText) findViewById(R.id.settings_password);
        settings_confirm_password = (EditText) findViewById(R.id.settings_confirm_password);
        A_setting_update = (Button) findViewById(R.id.A_setting_update);
        A_setting_close = (Button) findViewById(R.id.A_setting_close);
        load = new ProgressDialog(this);
        settings_confirm_collage.setText(Current_Any.ActiveUsers.getCollage());
        settings_user_email.setText(Current_Any.ActiveUsers.getEmail());


        A_setting_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        A_setting_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check(); ;

            }


        });

    }

    private void updateOnlyUserInfo(final String collage ,final String password ,final String email )
    {

            final DatabaseReference databaseReference;
            databaseReference = FirebaseDatabase.getInstance().getReference("Users");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    HashMap<String, Object> userMap = new HashMap<>();
                    userMap.put("collage", settings_confirm_collage.getText().toString());
                    userMap.put("password", settings_password.getText().toString());
                    userMap.put("email", settings_user_email.getText().toString());
                    databaseReference.child(Current_Any.ActiveUsers.getStudentNumber()).updateChildren(userMap);

                    // startActivity(new Intent(setting.this, User_choose_map_or_cafe.class));
                    Toast.makeText(setting.this, "Profile Info update successfully.", Toast.LENGTH_SHORT).show();
                    finish();
                    //Toast.makeText(setting.this, "Mohammad", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

    private void Check() {
        String collage = settings_confirm_collage.getText().toString() ;
        String password = settings_password.getText().toString();
        String email = settings_user_email.getText().toString();

       if(password.isEmpty()){
           Toast.makeText(this, " password is mandatory.", Toast.LENGTH_SHORT).show();

       }else if(settings_confirm_password.getText().toString().isEmpty()){
           Toast.makeText(this, "confirm password is mandatory.", Toast.LENGTH_SHORT).show();

       }else {
        load.setTitle("Create Account");
        load.setMessage("please wait ....");
        load.setCanceledOnTouchOutside(false);
        load.show();
        updateOnlyUserInfo(collage , password , email);
       }
    }
}



