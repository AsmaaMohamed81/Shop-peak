package com.alatheer.shop_peak.service;

import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.Model.Govern;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Services {

    @FormUrlEncoded
    @POST("Api/addUser")
    Call<UserModel1> register(@Field("full_name") String full_name,
                              @Field("email") String email,
                              @Field("phone") String phone,
                              @Field("mohafza") String mohafza,
                              @Field("madina") String madina,
                              @Field("address")String address,
                              @Field("password")String password,
                              @Field("agree")Integer agree);

    @GET("Api/countries")
    Call<List<Govern>> getGovern();

    ////

}
