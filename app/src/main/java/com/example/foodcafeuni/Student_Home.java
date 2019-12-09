package com.example.foodcafeuni;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.example.foodcafeuni.Action.ItemClickListener;
import com.example.foodcafeuni.Classes.Food;
import com.example.foodcafeuni.Temp.Current_Any;
import com.example.foodcafeuni.ViewMenus.FoodViewMenu;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Student_Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference databaseReference ;
    private RecyclerView V_recycler_view_home_Student;
    RecyclerView.LayoutManager V_layoutManager;

    String V_ID_Restaurant ="";
    String V_DB = "Food";
    TextView V_TextFullName ;
    String V_Name_Restaurant= "";

   public FirebaseRecyclerAdapter<Food, FoodViewMenu> adapter;
    private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        V_Name_Restaurant = getIntent().getStringExtra("Name");

        toolbar.setTitle(V_Name_Restaurant);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent obj = new Intent(Student_Home.this,Cart.class);
                obj.putExtra("IDRESCart",V_ID_Restaurant);
                startActivity(obj);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        View headerView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        //navigationView.getBackground().setAlpha(122);
        databaseReference = FirebaseDatabase.getInstance().getReference().child(V_DB);
        V_recycler_view_home_Student = (RecyclerView)findViewById(R.id.A_recycler_view_home_Student);
        V_recycler_view_home_Student.setHasFixedSize(true);
        V_layoutManager = new LinearLayoutManager(this);
        V_recycler_view_home_Student.setLayoutManager(V_layoutManager);
        V_ID_Restaurant = getIntent().getStringExtra("ID");

        V_TextFullName =(TextView) headerView.findViewById(R.id.A_UserName);
        V_TextFullName.setText(Current_Any.ActiveUsers.getName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Food> options =
                new FirebaseRecyclerOptions.Builder<Food>().setQuery(databaseReference.orderByChild("resid").equalTo(V_ID_Restaurant),Food.class).build();


          adapter =
                new FirebaseRecyclerAdapter<Food, FoodViewMenu>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull FoodViewMenu foodViewMenu, int i, @NonNull final Food food) {
                        foodViewMenu.V_text_view_menu_category.setText(food.getName());
                        Picasso.get().load(food.getImage()).into(foodViewMenu.V_image_view_menu_category);
                        foodViewMenu.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onClickButton(View view, int position, boolean isClick) {
                                Intent obj = new Intent(Student_Home.this,Food_Details.class);
                                obj.putExtra("FoodID",adapter.getRef(position).getKey());
                                startActivity(obj);
                                Toast.makeText(Student_Home.this, "hi", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public FoodViewMenu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resource_menu_food_home,parent,false);
                    FoodViewMenu holder = new FoodViewMenu(view);
                    return holder;
                    }
                };
        V_recycler_view_home_Student.setAdapter(adapter);
        adapter.startListening();
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if(id==R.id.nav_home)
        {
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
            Intent obj = new Intent(Student_Home.this , User_choose_map_or_cafe.class);
            startActivity(obj);
        }else if (id==R.id.nav_Cart){
            Toast.makeText(this, "Cart", Toast.LENGTH_SHORT).show();
            Intent obj = new Intent(Student_Home.this ,Cart.class);
            startActivity(obj);


        }else if (id==R.id.nav_Search){
            Intent obj = new Intent(Student_Home.this,Order_Track.class);
            obj.putExtra("Idres",V_ID_Restaurant);
            startActivity(obj);

        }else if (id==R.id.nav_Setting){
            Intent obj = new Intent(Student_Home.this , choose_change_pass_or_change_profile.class);
            startActivity(obj);



        }else if(id==R.id.nav_Logout){
            Intent obj = new Intent(Student_Home.this,MainActivity.class);
            obj.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(obj);
        }




        DrawerLayout drawer =(DrawerLayout)findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
