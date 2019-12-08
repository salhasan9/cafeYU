package com.example.foodcafeuni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodcafeuni.Temp.Current_Any;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class change_password extends AppCompatActivity {

    private EditText settings_password, settings_confirm_password ;
    private Button a_setting_update;
    private ProgressDialog load;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getSupportActionBar().hide();

        settings_password = (EditText) findViewById(R.id.settings_password);
        settings_confirm_password = (EditText) findViewById(R.id.settings_confirm_password);
        a_setting_update = (Button) findViewById(R.id.a_setting_update);
        load = new ProgressDialog(this);

        a_setting_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check(); ;

            }


        });

    }

    private void updateOnlyUserInfo(final String password)
    {

        final DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("password", settings_password.getText().toString());

                databaseReference.child(Current_Any.ActiveUsers.getStudentNumber()).updateChildren(userMap);
                // startActivity(new Intent(setting.this, choose_change_pass_or_change_profile.class));
                Toast.makeText(change_password.this, "Profile Information update successfully.", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void Check() {

        String password = settings_password.getText().toString();

        if(password.isEmpty()){
            Toast.makeText(this, " password is mandatory.", Toast.LENGTH_SHORT).show();

        }else if(settings_confirm_password.getText().toString().isEmpty()){
            Toast.makeText(this, "confirm password is mandatory.", Toast.LENGTH_SHORT).show();

        }else {
            load.setTitle("Create Account");
            load.setMessage("please wait ....");
            load.setCanceledOnTouchOutside(false);
            load.show();
            updateOnlyUserInfo( password);
        }
    }
}
