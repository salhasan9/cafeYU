package com.example.foodcafeuni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodcafeuni.Action.ItemClickListener;
import com.example.foodcafeuni.Classes.Food;
import com.example.foodcafeuni.ViewMenus.FoodViewMenu;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class search extends AppCompatActivity {
    Button V_SeaRch_Button;
    EditText V_Edt_Input_Search;
    RecyclerView V_Recycler_Search_List;
    String V_Search_Input_String;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search);
        V_SeaRch_Button = (Button)findViewById(R.id.btn_search);
        V_Edt_Input_Search = (EditText) findViewById(R.id.edt_search_food);
        V_Recycler_Search_List = (RecyclerView) findViewById(R.id.A_recycler_Search_List);
        V_Recycler_Search_List.setLayoutManager(new LinearLayoutManager(search.this));
        V_SeaRch_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            V_Search_Input_String=V_Edt_Input_Search.getText().toString();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Food");
        FirebaseRecyclerOptions<Food>options = new FirebaseRecyclerOptions.Builder<Food>().setQuery(
        reference.orderByChild("name").startAt(V_Search_Input_String),Food.class).build();
        FirebaseRecyclerAdapter<Food, FoodViewMenu>adapter=
                new FirebaseRecyclerAdapter<Food, FoodViewMenu>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull FoodViewMenu foodViewMenu, int i, @NonNull Food food) {
                        foodViewMenu.V_text_view_menu_category.setText(food.getName());
                        Picasso.get().load(food.getImage()).into(foodViewMenu.V_image_view_menu_category);
                        foodViewMenu.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onClickButton(View view, int position, boolean isClick) {

                            }
                        });
                    }

                    @NonNull
                    @Override
                    public FoodViewMenu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resource_menu_food_home,parent,false);
                        FoodViewMenu holder = new FoodViewMenu(view);
                        return holder;                    }
                };
        V_Recycler_Search_List.setAdapter(adapter);
        adapter.startListening();
    }
}
