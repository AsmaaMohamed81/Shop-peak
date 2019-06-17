package com.alatheer.shop_peak.service;

import com.alatheer.shop_peak.Model.BasketModel2;
import com.alatheer.shop_peak.Model.BasketModel3;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.NotificationModel;
import com.alatheer.shop_peak.Model.OfferModel1;
import com.alatheer.shop_peak.Model.Pill;
import com.alatheer.shop_peak.Model.Product_Specification;
import com.alatheer.shop_peak.Model.RatingModel;
import com.alatheer.shop_peak.Model.RatingModel2;
import com.alatheer.shop_peak.Model.Tasnefat;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.Model.Govern;
import com.alatheer.shop_peak.Model.list_cats;

import java.lang.ref.SoftReference;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @GET("Api/all_products/{user_id}")
    Call<List<HomeModel>> get_all_products(@Path("user_id") String user_id);

    @GET("Api/all_offers")
    Call<List<OfferModel1>> get_all_offers();

    @GET("Api/category_list")
    Call<List<Tasnefat>> getTasnef_Vonder();

    @Multipart
    @POST("Api/subscribre_vendor")
    Call<UserModel1> subscribre_vendor(@Part("id") RequestBody id,
                                       @Part("full_name") RequestBody full_name,
                                       @Part("mohafza") RequestBody mohafza,
                                       @Part("madina") RequestBody madina,
                                       @Part("address") RequestBody address,
                                       @Part("store_tasnef")RequestBody store_tasnef,
                                       @Part("lat") RequestBody lat,
                                       @Part("lang") RequestBody lang,
                                       @Part MultipartBody.Part logo_img);
    @Multipart
    @POST("Api/add_product")
    Call<RatingModel2>Add_Product(@Part("store_id_fk")RequestBody user_id,
                                  @Part("sanf_code")RequestBody sanf_code,
                                  @Part("sanf_name")RequestBody sanf_name,
                                  @Part("main_tasnef")RequestBody main_category,
                                  @Part("sub_tasnef")RequestBody sub_category,
                                  @Part("price_before_dis")RequestBody price_before_discount,
                                  @Part("price_after_dis")RequestBody price_after_discount,
                                  @Part("details")RequestBody details,
                                  @Part MultipartBody.Part main_img);
    @Multipart
    @POST("Api/add_product")
    Call<RatingModel2>Add_Product2(@Part("store_id_fk")RequestBody user_id,
                                   @Part("sanf_code")RequestBody sanf_code,
                                   @Part("sanf_name")RequestBody sanf_name,
                                   @Part("main_tasnef")RequestBody main_category,
                                   @Part("sub_tasnef")RequestBody sub_category,
                                   @Part("price_after_dis")RequestBody price_after_discount,
                                   @Part("price_before_dis")RequestBody price_before_discount,
                                   @Part("details")RequestBody element_desscribion,
                                   @Part MultipartBody.Part main_image,
                                   @Part("items[]")List<RequestBody> items,
                                   @Part("descriptions[]")List<RequestBody> descriptions,
                                   @Part List<MultipartBody.Part> images,
                                   @Part("colors[]")List<RequestBody> colors
                                  );
    @FormUrlEncoded
    @POST("Api/check_gmail")
    Call<RatingModel2> validate_email(@Field("email")String email);



    @GET("Api/get_rating/{id}")
    Call<List<RatingModel>> get_all_rating(@Path("id") long id);

    @FormUrlEncoded
    @POST("Api/make_rate")
    Call<RatingModel2> make_rate(@Field("product_id") int product_id,
                                 @Field("rate_value") int rate_value,
                                 @Field("user_id") int user_id);

    @GET("Api/get_list_cats")
    Call<List<list_cats>> get_list_cats();

    @GET("Api/product_cat_main/{cat_id}")
    Call<List<HomeModel>> get_all_main_product(@Path("cat_id") int id);


    @GET("Api/product_cat_sub/{cat_id}")
    Call<List<HomeModel>> get_all_sub_product(@Path("cat_id") int id);



    @FormUrlEncoded
    @POST("Api/add_to_favourite")
    Call<RatingModel2> add_to_favourite(@Field("client_id_fk") String client_id_fk,
                                 @Field("sanf_id_fk") String sanf_id_fk);



    @FormUrlEncoded
    @POST("Api/delete_favourite")
    Call<RatingModel2> delet_to_favourite(@Field("client_id_fk") String client_id_fk,
                                        @Field("sanf_id_fk") String sanf_id_fk);

    @GET("Api/my_favourite/{user_id}")
    Call<List<HomeModel>> get_all_favourite(@Path("user_id") String user_id);


    @GET("Api/store_products/{store_id_fk}")
    Call<List<HomeModel>> get_store_product(@Path("store_id_fk") String store_id_fk);

    @POST("Api/save_basket")
    Call<RatingModel2>add_to_basket(@Body BasketModel2 basketModel2);
    @FormUrlEncoded
    @POST("Api/filter")
    Call<List<HomeModel>>search_Home(@Field("sanf_name")String sanf_name);

    @GET("Api/get_store_folower/{store_id_fk}")
    Call<List<UserModel1>> get_storefollow(@Path("store_id_fk") String store_id_fk);



    @FormUrlEncoded
    @POST("Api/get_user_folow")
    Call<RatingModel2> get_user_folow(@Field("store_id_fk") String sanf_id_fk,
                                      @Field("client_id_fk") String client_id_fk
                                        );

    @GET("Api/get_store_flow/{user_id}")
    Call<List<UserModel1>> get_my_flow(@Path("user_id") String user_id);

    @FormUrlEncoded
    @POST("Api/make_follow")
    Call<RatingModel2> make_follow(@Field("store_id_fk") String sanf_id_fk,
                                      @Field("client_id_fk") String client_id_fk
    );

    @FormUrlEncoded
    @POST("Api/delete_flow")
    Call<RatingModel2> delete_flow(@Field("store_id_fk") String sanf_id_fk,
                                   @Field("client_id_fk") String client_id_fk
    );



    @GET("Api/get_offer_products/{offer_id}")
    Call<List<HomeModel>> get_offer_products(@Path("offer_id") String offer_id);

    @FormUrlEncoded
    @POST("Api/get_notification")
    Call<NotificationModel>get_notification(@Field("user_id")String user_id);

    @GET("Api/get_last_pill")
    Call<Pill>get_pill();
}

