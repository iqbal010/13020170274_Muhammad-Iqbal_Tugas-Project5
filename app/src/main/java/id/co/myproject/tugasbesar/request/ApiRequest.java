package id.co.myproject.tugasbesar.request;

import id.co.myproject.tugasbesar.model.User;
import id.co.myproject.tugasbesar.model.Value;
import id.co.myproject.tugasbesar.response.BudayaResponse;
import id.co.myproject.tugasbesar.response.DetailBudayaResponse;
import id.co.myproject.tugasbesar.response.KotaResponse;
import id.co.myproject.tugasbesar.response.LoginResponse;
import id.co.myproject.tugasbesar.response.UserResponse;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("login_user.php")
    Call<LoginResponse> loginUserRequest(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("registrasi_user.php")
    Call<LoginResponse> registerUserRequest(
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("tampil_budaya.php")
    Call<BudayaResponse> budayaRequest();

    @GET("tampil_detail_budaya.php")
    Call<DetailBudayaResponse> detailBudayaRequest();

    @GET("tampil_populer.php")
    Call<DetailBudayaResponse> populerRequest();

    @GET("tampil_detail_budaya.php")
    Call<DetailBudayaResponse> detailBudayaByIdBudaya(
            @Query("id_budaya") String idBudaya
    );

    @GET("tampil_detail_budaya.php")
    Call<DetailBudayaResponse> detailBudayaByIdKota(
            @Query("id_kota") String idKota
    );

    @GET("tampil_detail.php")
    Call<DetailBudayaResponse> detailBudayaRequestById(
            @Query("id_detail") String idDetail
    );

    @GET("tampil_user.php")
    Call<UserResponse> userRequest(
            @Query("id_user") String idUser
    );

    @POST("edit_user.php")
    Call<Value> editUserRequest(
            @Body User user
    );

    @GET("tampil_kota.php")
    Call<KotaResponse> kotaRequest();

    @FormUrlEncoded
    @POST("tambah_like.php")
    Call<Value> addLike(
            @Field("id_detail") String idDetail,
            @Field("id_user") String idUser
    );

    @GET("cek_like.php")
    Call<Value> cekLike(
            @Query("id_detail") String idDetail,
            @Query("id_user") String idUser
    );

}
