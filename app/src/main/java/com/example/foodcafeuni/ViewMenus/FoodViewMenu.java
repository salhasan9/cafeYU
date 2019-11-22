package com.example.foodcafeuni.ViewMenus;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodcafeuni.Action.ItemClickListener;
import com.example.foodcafeuni.R;


public class FoodViewMenu extends RecyclerView.ViewHolder implements View.OnClickListener {
    public   ImageView V_image_view_menu_category;
    public     TextView V_text_view_menu_category;
    private ItemClickListener itemClickListener;
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public FoodViewMenu(@NonNull View itemView) {
        super(itemView);
        Definition_Function();

    }

    @Override
    public void onClick(View view) {

itemClickListener.onClickButton(view,getAdapterPosition(),false);
    }

    void Definition_Function(){
        V_image_view_menu_category = (ImageView)itemView.findViewById(R.id.image_view_menu_category);
        V_text_view_menu_category = (TextView)itemView.findViewById(R.id.text_view_menu_category);
        itemView.setOnClickListener(this);
    }

}
