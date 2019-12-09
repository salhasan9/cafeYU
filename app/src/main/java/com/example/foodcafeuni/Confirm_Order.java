package com.example.foodcafeuni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodcafeuni.Temp.Current_Any;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Confirm_Order extends AppCompatActivity {
EditText V_full_name_confirm , V_phone_number_confirm ;
Button V_btn_confirm ;
String V_Total = "";
String V_Res_Id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm__order);
        getSupportActionBar().hide();

        V_full_name_confirm  = (EditText)findViewById(R.id.A_full_name_confirm);
        V_phone_number_confirm  = (EditText)findViewById(R.id.A_phone_number_confirm);
        V_btn_confirm  = (Button)findViewById(R.id.A_btn_confirm);
        V_Total = getIntent().getStringExtra("TotalPrice_Cash");
        V_Res_Id = getIntent().getStringExtra("Resid_Cash");
        V_btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Check();
            }
        });

    }

    private void Check() {
        String name = V_full_name_confirm.getText().toString();
        String phone = V_phone_number_confirm.getText().toString();
        if(name.isEmpty()){
            Toast.makeText(this, "Please fill in the name", Toast.LENGTH_SHORT).show();
        }else if (phone.isEmpty()){
            Toast.makeText(this, "Please fill in the phone", Toast.LENGTH_SHORT).show();

        }else{
            AccpteOrder();
        }
    }

    private void AccpteOrder() {
        String V_Current_Time , V_Current_Date;
        Calendar CalForDate = Calendar.getInstance();
        SimpleDateFormat V_Simple_Date = new SimpleDateFormat("MMM DD , YYYY");
        V_Current_Date = V_Simple_Date.format(CalForDate.getTime());

        SimpleDateFormat V_Simple_Time = new SimpleDateFormat("HH:mm:ss a");
        V_Current_Time = V_Simple_Time.format(CalForDate.getTime());
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Orders");
        final     HashMap<String,Object>Hash_OrderMap = new HashMap<>();
        Hash_OrderMap.put("total",V_Total);
        Hash_OrderMap.put("stduentname",V_full_name_confirm.getText().toString());
        Hash_OrderMap.put("studentphone",V_phone_number_confirm.getText().toString());
        Hash_OrderMap.put("date",V_Current_Date);
        Hash_OrderMap.put("time",V_Current_Time);
        Hash_OrderMap.put("state","not ready");
        Hash_OrderMap.put("resid",V_Res_Id);
            databaseReference.child("UserView").child(Current_Any.ActiveUsers.getStudentNumber()).child("OrderCurrent").updateChildren(Hash_OrderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if(task.isSuccessful()){
            FirebaseDatabase.getInstance().getReference().child("Cart").child("UserView").child(Current_Any.ActiveUsers.getStudentNumber()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(Confirm_Order.this, "Your request has been successfully reached", Toast.LENGTH_SHORT).show();
                    databaseReference.child("AdminView").child(Current_Any.ActiveUsers.getStudentNumber()).updateChildren(Hash_OrderMap);
                    Intent intent = new Intent(Confirm_Order.this,Cart.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });

        }
    }
});

    }
}
