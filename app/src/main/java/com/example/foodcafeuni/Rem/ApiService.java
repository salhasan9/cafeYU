package com.example.foodcafeuni.Rem;

import com.example.foodcafeuni.Classes.Res;
import com.example.foodcafeuni.Classes.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @Headers(
            {       "Authorization:key=AAAAnqJP8pg:APA91bGbc1eUvWfB6CHqbe0HTDyPDXmODZ9SZocufgzmigdLd6vPWPk_tN9nM1VQ7MnZsuv_e5Sq2JaznHBdXK9uadJzs0H-fwm1Ya2kEboczH3amvpV-IreeoDiIkw6UopDdYeh4Dbx",
                    "Content-Type:application/json"

            }


    )
    @POST("fcm/send")
    Call<Res> sendNotification(@Body Sender sender);
}
