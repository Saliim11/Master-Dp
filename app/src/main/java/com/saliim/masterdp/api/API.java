package com.saliim.masterdp.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.saliim.masterdp.create.DataMotorFragment;
import com.saliim.masterdp.model.*;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class API {

    static Retrofit retrofit;
    public static String baseURL = "http://192.168.18.84/";

    public static Retrofit getInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build();

            Gson gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL + "master/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit;
    }

    public static Call<ArrayList<DataDp>> getDataDp() {
        MasterDp service = getInstance().create(MasterDp.class);
        return service.getDataDp();
    }

    public static Call<ArrayList<DataMotorKategori>> getDataKategoriMotor() {
        MasterDp service = getInstance().create(MasterDp.class);
        return service.getDataKategoriMotor();
    }

    public static Call<ArrayList<DataMotor>> getDataMotor(String kategori) {
        MasterDp service = getInstance().create(MasterDp.class);
        return service.getDataMotor(kategori);
    }

    public static Call<ArrayList<DataTenor>> getDataTenor() {
        MasterDp service = getInstance().create(MasterDp.class);
        return service.getDataTenor();
    }

    public static Call<ResponseBody> addDataDp(String motor_id, String jumlah_dp, ArrayList<DataTenor> tenor, String create_by) {
        MasterDp service = getInstance().create(MasterDp.class);
        return service.addDataDp(motor_id, jumlah_dp, tenor, create_by);
    }

    public interface MasterDp {

        @GET("get_master_dps.php")
        Call<ArrayList<DataDp>> getDataDp();

        @GET("get_motor_kategori.php")
        Call<ArrayList<DataMotorKategori>> getDataKategoriMotor();

        @GET("get_motor.php")
        Call<ArrayList<DataMotor>> getDataMotor(
                @Query("motor_category_id") String kategori);

        @GET("get_tenor.php")
        Call<ArrayList<DataTenor>> getDataTenor();

        @FormUrlEncoded
        @POST("insert_master_dp.php")
        Call<ResponseBody> addDataDp(
                @Field("motor_id") String motor_id,
                @Field("jumlah_dp") String jumlah_dp,
                @Field("tenor[]") ArrayList<DataTenor> tenor,
                @Field("create_by") String create_by);

    }
}
