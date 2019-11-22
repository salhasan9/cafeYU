package com.example.foodcafeuni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodcafeuni.Classes.Food;
import com.example.foodcafeuni.Temp.Current_Any;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Food_Details extends AppCompatActivity {
        FloatingActionButton V_btn_add;
        TextView V_food_name_details ,V_food_price_details ,V_food_dec_details;
        ImageView V_image_food_details;
        String V_FoodID ;
        String V_ResID = "";
        String V_ResName = "";
        String V_DB= "Food";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__details);
        getSupportActionBar().hide();

        V_btn_add = (FloatingActionButton)findViewById(R.id.A_btn_add);
        V_food_name_details = (TextView)findViewById(R.id.A_food_name_details);
        V_food_price_details = (TextView)findViewById(R.id.A_food_price_details);
        V_image_food_details = (ImageView)findViewById(R.id.A_image_food_details);
        V_food_dec_details = (TextView)findViewById(R.id.A_food_dec_details);
        V_FoodID = getIntent().getStringExtra("FoodID");
        V_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            AddingToCart();
            }


        });
       // Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
getFoodDetails(V_FoodID);
    }
    private void AddingToCart() {
        String V_Current_Time , V_Current_Date;
        Calendar CalForDate = Calendar.getInstance();
        SimpleDateFormat V_Simple_Date = new SimpleDateFormat("MMM DD , YYYY");
        V_Current_Date = V_Simple_Date.format(CalForDate.getTime());

        SimpleDateFormat V_Simple_Time = new SimpleDateFormat("HH:mm:ss a");
        V_Current_Time = V_Simple_Time.format(CalForDate.getTime());

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Cart");
        final HashMap<String,Object>Hash_CartMap = new HashMap<>();
        Hash_CartMap.put("foodid",V_FoodID);
        Hash_CartMap.put("foodname",V_food_name_details.getText().toString());
        Hash_CartMap.put("foodprice",V_food_price_details.getText().toString());
        Hash_CartMap.put("date",V_Current_Date);
        Hash_CartMap.put("time",V_Current_Time);
        Hash_CartMap.put("resid",V_ResID);
        Hash_CartMap.put("resname",V_ResName);
        databaseReference.child("UserView").child(Current_Any.ActiveUsers.getStudentNumber()).child("Food").child(V_FoodID).updateChildren(
                Hash_CartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            if(task.isSuccessful()){
                databaseReference.child("AdminView").child(Current_Any.ActiveUsers.getStudentNumber()).child("Food").child(V_FoodID).updateChildren(
                        Hash_CartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Food_Details.this, "Added To Cat", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            }
        });


    }
    private void getFoodDetails(String v_foodID) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(V_DB);
        databaseReference.child(v_foodID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Food food = dataSnapshot.getValue(Food.class);
                    V_food_dec_details.setText(food.getDesc());
                    V_food_name_details.setText(food.getName());
                    V_food_price_details.setText(food.getPrice());
                    V_ResID = food.getResid();
                    V_ResName = food.getResname();
                  Picasso.get().load(food.getImage()).into(V_image_food_details);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
