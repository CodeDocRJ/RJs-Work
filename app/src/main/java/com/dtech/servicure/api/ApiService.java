package com.dtech.servicure.api;

import com.dtech.servicure.model.ClaimData;
import com.dtech.servicure.model.ClaimResponse;
import com.dtech.servicure.model.LoginData;
import com.dtech.servicure.model.home.HomePickupModel;
import com.dtech.servicure.model.login.LoginModel;
import com.dtech.servicure.model.pending.PendingPickupModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @Headers("Authorization: 123")
    @POST("biker-login")
    Call<LoginModel> callLogin(@Body LoginData body);

    @Headers("Authorization: 123")
    @GET("biker-request-list")
    Call<HomePickupModel> getHomePickupList(@Query("biker_id") String biker_id,
                                            @Query("page") String page
    );

    @Headers("Authorization: 123")
    @GET("biker-pickup-pending-list")
    Call<PendingPickupModel> getPendingPickupList(@Query("biker_id") String biker_id,
                                                  @Query("page") String page
    );

    @Headers("Authorization: 123")
    @POST("biker-accept-claim")
    Call<ClaimResponse> callBikerAcceptClaim(@Body ClaimData body);

    @Headers("Authorization: 123")
    @POST("biker-cancel-claim")
    Call<ClaimResponse> callBikerCancelClaim(@Body ClaimData body);


}
