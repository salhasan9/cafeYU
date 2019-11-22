package com.example.foodcafeuni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Sign_up extends AppCompatActivity {
    EditText V_edt_id_stu,V_edt_name , V_edt_pass,V_edt_email,V_edt_collage;
    Button V_A_BtnSginUP;

    private ProgressDialog load ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);
        V_A_BtnSginUP = (Button)findViewById(R.id.A_BtnSginUP);
        V_edt_id_stu = (EditText)findViewById(R.id.edt_id_stu);
        V_edt_name = (EditText)findViewById(R.id.edt_name);
        V_edt_pass = (EditText)findViewById(R.id.edt_pass);
        V_edt_email = (EditText)findViewById(R.id.edt_email);
        V_edt_collage = (EditText)findViewById(R.id.edt_collage);
        V_A_BtnSginUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateUsers();
            }
        });
    }
    private void CreateUsers() {
        //ini id to EditText
        String id = V_edt_id_stu.getText().toString();
        String name = V_edt_name.getText().toString();
        String pass = V_edt_pass.getText().toString();
        String email = V_edt_email.getText().toString();
        String collage =V_edt_collage.getText().toString();
        load = new ProgressDialog(this);

        //Check empty
        if(id.isEmpty()){
            Toast.makeText(this, "Please fill in the university number", Toast.LENGTH_SHORT).show();
        }else if(name.isEmpty()){
            Toast.makeText(this, "Please fill in the name", Toast.LENGTH_SHORT).show();

        }else if(pass.isEmpty()){
            Toast.makeText(this, "Please fill in the password", Toast.LENGTH_SHORT).show();

        }else if(email.isEmpty()){
            Toast.makeText(this, "Please fill in the email", Toast.LENGTH_SHORT).show();

        }else if (collage.isEmpty()){
            Toast.makeText(this, "Please fill in the collage", Toast.LENGTH_SHORT).show();

        }else{
            load.setTitle("Create Account");
            load.setMessage("please wait ....");
            load.setCanceledOnTouchOutside(false);
            load.show();
            FirebaseCreate(id ,name ,pass ,email,collage);
        }
    }
    private void FirebaseCreate(final String id,final String name,final String pass, final String email, final String collage) {
        final DatabaseReference databaseReference ;
        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.child("Users").child(id).exists()){
                    HashMap<String,Object> addUser = new HashMap<>();
                    addUser.put("studentNumber",id);
                    addUser.put("name",name);
                    addUser.put("password",pass);
                    addUser.put("email",email);
                    addUser.put("collage",collage);
                    databaseReference.child("Users").child(id).updateChildren(addUser)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Sign_up.this, "Congratulations, was successfully register", Toast.LENGTH_SHORT).show();
                                        load.dismiss();
                                        Intent obj = new Intent(Sign_up.this,Sign_in.class);
                                        startActivity(obj);
                                    }else{
                                        load.dismiss();
                                        Toast.makeText(Sign_up.this, "Network error retry", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                }else{
                    Toast.makeText(Sign_up.this, "Our student number "+id+ " is registered", Toast.LENGTH_SHORT).show();
                    load.dismiss();
                    Toast.makeText(Sign_up.this, "Try Another student number", Toast.LENGTH_SHORT).show();
                    Intent obj = new Intent(Sign_up.this,MainActivity.class);
                    startActivity(obj);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
