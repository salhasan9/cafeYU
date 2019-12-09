package com.example.foodcafeuni.ViewMenus;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodcafeuni.R;

public class Orders_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView  V_Price_Order , V_Date , V_Status , V_ID_Order ;
    public Orders_ViewHolder(@NonNull View itemView) {
        super(itemView);

        V_Price_Order = itemView.findViewById(R.id.A_price_order);
        V_Date = itemView.findViewById(R.id.A_date);
        V_Status =itemView.findViewById(R.id.A_Status);



    }

    @Override
    public void onClick(View view) {

    }
}
