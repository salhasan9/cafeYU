package com.example.foodcafeuni;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class Sign_in extends AppCompatActivity {
    EditText V_A_sign_in_edt_id_stu , V_A_sign_in_edt_pass_stu;
    Button V_A_sign_in_BtnSginIn,V_A_sign_in_BtnSginIn_admin;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();

        //INI Var
        V_A_sign_in_BtnSginIn =(Button)findViewById(R.id.A_sign_in_BtnSginIn);
        V_A_sign_in_edt_id_stu =(EditText)findViewById(R.id.A_sign_in_edt_id_stu);
        V_A_sign_in_edt_pass_stu=(EditText)findViewById(R.id.A_sign_in_edt_pass_stu);

        //Click button
        V_A_sign_in_BtnSginIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });
    }
    private void LoginUser()
    {
        String id =  V_A_sign_in_edt_id_stu.getText().toString();
        String password = V_A_sign_in_edt_pass_stu.getText().toString();
        load = new ProgressDialog(this);
        if(id.isEmpty()){
            Toast.makeText(this, "Please fill in the university number", Toast.LENGTH_SHORT).show();
        }else if (password.isEmpty()){
            Toast.makeText(this, "Please fill in the password", Toast.LENGTH_SHORT).show();

        }else{
            load.setTitle("Sign In");
            load.setMessage("Please Wait....");
            load.setCanceledOnTouchOutside(false);
            load.show();
            FirebaseAccess(id,password);
        }
    }

    private void FirebaseAccess(final String id, final String password)
    {
        final DatabaseReference databaseReference;
        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("Users").child(id).exists()){
                    Users usersActive = dataSnapshot.child("Users").child(id).getValue(Users.class);
                    if(usersActive.getStudentNumber().equals(id)){
                        if(usersActive.getPassword().equals(password)){
                            Toast.makeText(Sign_in.this, "Signed in successfully", Toast.LENGTH_SHORT).show(); load.dismiss();
                            Current_Any.ActiveUsers =usersActive;
                            Intent Student_map_choose = new Intent(Sign_in.this,User_choose_map_or_cafe.class);
                            startActivity(Student_map_choose);
                        }else {

                            Toast.makeText(Sign_in.this, "Password is wrong", Toast.LENGTH_SHORT).show();

                            load.dismiss();
                        }
                    }else{
                        Toast.makeText(Sign_in.this, "User does not exist", Toast.LENGTH_SHORT).show();
                        load.dismiss();


                    }
                }else {

                    Toast.makeText(Sign_in.this, "User does not exist", Toast.LENGTH_SHORT).show();
                    load.dismiss();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
