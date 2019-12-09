package com.example.foodcafeuni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodcafeuni.Classes.CartClass;
import com.example.foodcafeuni.Temp.Current_Any;
import com.example.foodcafeuni.ViewMenus.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cart extends AppCompatActivity {

    Button V_btn_next;
    RecyclerView V_recycler_view_cart;
    RecyclerView.LayoutManager V_layoutManager;
    TextView V_total_price_cart;
    String V_ResID="";
    String V_DB="Cart";
    int V_Total_Price = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cart);
        V_recycler_view_cart =(RecyclerView)findViewById(R.id.A_recycler_view_cart);
        V_recycler_view_cart.setHasFixedSize(true);
        V_layoutManager = new LinearLayoutManager(this);
        V_recycler_view_cart.setLayoutManager(V_layoutManager);
        V_btn_next = (Button)findViewById(R.id.A_btn_next);
        V_total_price_cart = (TextView)findViewById(R.id.A_total_price_cart);
        V_ResID = getIntent().getStringExtra("IDRESCart");


        V_btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                V_total_price_cart.setText(String.valueOf("Total Price = "+V_Total_Price+"JD"));
                Intent obj = new Intent(Cart.this,Choose_Payment.class);
                obj.putExtra("TotalPrice",String.valueOf(V_Total_Price));
                obj.putExtra("Resid",V_ResID);
                startActivity(obj);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(V_DB);
        FirebaseRecyclerOptions<CartClass>options =
                new FirebaseRecyclerOptions.Builder<CartClass>().setQuery(databaseReference.child("UserView")
                .child(Current_Any.ActiveUsers.getStudentNumber()).child("Food").orderByChild("resid").equalTo(V_ResID),CartClass.class
                ).build();

        FirebaseRecyclerAdapter<CartClass, CartViewHolder>adapter =
                new FirebaseRecyclerAdapter<CartClass, CartViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final CartViewHolder cartViewHolder, int i, @NonNull final CartClass cartClass) {
                        cartViewHolder.V_Food_Restaurant_Cart.setText(cartClass.getResname());
                        cartViewHolder.V_Food_Price_Cart.setText(cartClass.getFoodprice());
                        cartViewHolder.V_Food_Name_Cart.setText(cartClass.getFoodname());

                        int V_TotalPriceNow = ((Integer.valueOf(cartClass.getFoodprice())));
                        V_Total_Price+=V_TotalPriceNow;

                        cartViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                CharSequence op[]=new CharSequence[]{
                                    "Edit",
                                        "Remove"
                                };
                                AlertDialog.Builder builder = new AlertDialog.Builder(Cart.this);
                                builder.setTitle("Cart Options");
                                builder.setItems(op, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if(i==0)
                                        {
                                            Intent intent = new Intent(Cart.this,Food_Details.class);
                                            intent.putExtra("FoodID",cartClass.getFoodid());
                                            startActivity(intent);
                                        }
                                        if(i==1)
                                        {
                                            databaseReference.child("UserView").child(Current_Any.ActiveUsers.getStudentNumber()).child("Food")
                                                    .child(cartClass.getFoodid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful())
                                                    {
                                                        Toast.makeText(Cart.this, "Item removed successfully", Toast.LENGTH_SHORT).show();

                                                    }
                                                }
                                            });
                                        }
                                    }
                                });
                                builder.show();
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resource_cart_item_student,parent,false);
                        CartViewHolder holder = new CartViewHolder(view);
                        return holder;
                    }
                };

            V_recycler_view_cart.setAdapter(adapter);
            adapter.startListening();
    }
}
