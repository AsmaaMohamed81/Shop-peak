package com.alatheer.shop_peak.service;

import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.OfferModel1;
import com.alatheer.shop_peak.Model.RatingModel;
import com.alatheer.shop_peak.Model.Tasnefat;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.Model.Govern;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Services {

    @FormUrlEncoded
    @POST("Api/addUser")
    Call<UserModel1> register(@Field("full_name") String full_name,
                              @Field("email") String email,
                              @Field("phone") String phone,
                              @Field("mohafza") String mohafza,
                              @Field("madina") String madina,
                              @Field("address")String address,
                              @Field("password")String password);
    @FormUrlEncoded
    @POST("Api/login")
    Call<UserModel1> login(@Field("email") String email,
                           @Field("password") String password
    );

    @GET("Api/countries")
    Call<List<Govern>> getGovern();

    @GET("Api/all_products")
    Call<List<HomeModel>> get_all_products();

    @GET("Api/all_offers")
    Call<List<OfferModel1>> get_all_offers();

    @GET("Api/category_list")
    Call<List<Tasnefat>> getTasnef_Vonder();

    @FormUrlEncoded
    @POST("Api/addUser")
    Call<UserModel1> subscribre_vendor(@Field("full_name") String full_name,
                                       @Field("email") String email,
                                       @Field("phone") String phone,
                                       @Field("mohafza") String mohafza,
                                       @Field("madina") String madina,
                                       @Field("address")String address,
                                       @Field("password")String password);

    @GET("Api/get_rating/{id}")
    Call<List<RatingModel>> get_all_rating(@Path("id") long id);

    @POST("Api/make_rate")
    Call<RatingModel> make_rate(@Query("product_id") int product_id, @Query("rate_value") int rate_value,
                                @Query("user_id") int user_id);
}

