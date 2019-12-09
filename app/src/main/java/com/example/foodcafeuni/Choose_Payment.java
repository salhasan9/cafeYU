package com.example.foodcafeuni;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.foodcafeuni.Temp.Current_Any;
import com.example.foodcafeuni.Temp.config;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class Choose_Payment extends AppCompatActivity {
    ImageView V_User_Choose_PayPal , V_User_Choose_Cash ;
    String V_TotalPrice="";
    String ResId = "";
    private static final int PAYPAL_REQUSET_CODE =9999 ;
    static PayPalConfiguration configuration = new PayPalConfiguration()
    .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
    .clientId(config.Pay_Pal_Clint_id);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose__payment);
        //inst
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,configuration);
        startService(intent);
        getSupportActionBar().hide();

        V_TotalPrice=getIntent().getStringExtra("TotalPrice");
        ResId=getIntent().getStringExtra("Resid");
        V_User_Choose_Cash  = (ImageView)findViewById(R.id.User_Choose_Cash);
        V_User_Choose_PayPal = (ImageView)findViewById(R.id.User_Choose_PayPal);
        V_User_Choose_Cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent obj = new Intent(Choose_Payment.this,Confirm_Order.class);
                obj.putExtra("TotalPrice_Cash",V_TotalPrice);
                obj.putExtra("Resid_Cash",ResId);
                startActivity(obj);
            }
        });
        V_User_Choose_PayPal.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Show_alert_Dig();
        }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYPAL_REQUSET_CODE) {
            if (requestCode == RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if(confirmation!=null){
                    try {
                        String PaymentDetails=confirmation.toJSONObject().toString(4);
                        JSONObject jsonObject = new JSONObject(PaymentDetails);
                        Intent obj = new Intent(Choose_Payment.this,Confirm_Order.class);
                        obj.putExtra("PymentStatus",jsonObject.getJSONObject("response").getString("state"));
                        startActivity(obj);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }else if(requestCode== Activity.RESULT_CANCELED)
            {
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
            }else  if (requestCode==PaymentActivity.RESULT_EXTRAS_INVALID){
                Toast.makeText(this, "Erorr", Toast.LENGTH_SHORT).show();


            }
        }
    }
    private void Show_alert_Dig() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Choose_Payment.this);
        alertDialog.setTitle("confirmation");
        alertDialog.setMessage("Service confirmation");


        alertDialog.setIcon(R.drawable.ic_credit_card_black_24dp);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String FormatPlace = "18".replace("$","").replace(",","");
                float Amoint = Float.parseFloat(FormatPlace);

                PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(FormatPlace)
                        ,"Jo","Payment Order PalyPal",PayPalPayment.PAYMENT_INTENT_SALE);
                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,configuration);
                intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
                startActivityForResult(intent,PAYPAL_REQUSET_CODE);



            }});

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();;
            }
        });

        alertDialog.show();
    }

}
