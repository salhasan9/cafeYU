package com.example.foodcafeuni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodcafeuni.Classes.CartClass;
import com.example.foodcafeuni.Classes.Orders;
import com.example.foodcafeuni.Temp.Current_Any;
import com.example.foodcafeuni.ViewMenus.CartViewHolder;
import com.example.foodcafeuni.ViewMenus.Orders_ViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Order_Track extends AppCompatActivity {
    RecyclerView V_Recycler_View_Orders ;
    DatabaseReference databaseReference ;
    String Id_Res = "";
    FirebaseRecyclerAdapter<Orders, Orders_ViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__track);
        getSupportActionBar().hide();
        Id_Res = getIntent().getStringExtra("Idres");
        V_Recycler_View_Orders = (RecyclerView)findViewById(R.id.A_recycler_Order_Track);
        V_Recycler_View_Orders.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseRecyclerOptions<Orders>options =
                new FirebaseRecyclerOptions.Builder<Orders>().setQuery(databaseReference.child("Orders").child("UserView").child(Current_Any.ActiveUsers.getStudentNumber()).orderByChild("resid").equalTo(Id_Res),Orders.class
                ).build();
        adapter = new FirebaseRecyclerAdapter<Orders, Orders_ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Orders_ViewHolder orders_viewHolder, int i, @NonNull Orders orders) {
               orders_viewHolder.V_Status.setText("Status : "+orders.getState());
                orders_viewHolder.V_Price_Order.setText("Total : "+orders.getTotal());
               orders_viewHolder.V_Date.setText("Date : "+orders.getDate()+" "+orders.getTime());
            }

            @NonNull
            @Override
            public Orders_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resource_order_track,parent,false);
                Orders_ViewHolder holder = new Orders_ViewHolder(view);
                return holder;
            }

        };
        V_Recycler_View_Orders.setAdapter(adapter);
        adapter.startListening();
    }
}
