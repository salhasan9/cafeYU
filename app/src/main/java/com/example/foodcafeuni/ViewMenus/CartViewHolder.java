package com.example.foodcafeuni.ViewMenus;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodcafeuni.Action.ItemClickListener;
import com.example.foodcafeuni.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView V_Food_Name_Cart , V_Food_Price_Cart ,V_Food_Restaurant_Cart ,V_txt_Quant;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        V_Food_Name_Cart =(TextView)itemView.findViewById(R.id.A_Food_Name_Cart);
        V_Food_Price_Cart =(TextView)itemView.findViewById(R.id.A_Food_Price_Cart);
        V_Food_Restaurant_Cart = (TextView)itemView.findViewById(R.id.A_Food_Restaurant_Cart);
        V_txt_Quant=(TextView)itemView.findViewById(R.id.A_txt_Quant);
    }

    @Override
    public void onClick(View view) {
itemClickListener.onClickButton(view,getAdapterPosition(),false);
    }
}
