package com.reynaldiwijaya.basicmvp.Network;

import com.reynaldiwijaya.basicmvp.Model.LoginBody;
import com.reynaldiwijaya.basicmvp.Model.LoginResponse;
import com.reynaldiwijaya.basicmvp.Model.SingleUserResponse;
import com.reynaldiwijaya.basicmvp.Model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("api/login")
    Call<LoginResponse> postLogin(@Body LoginBody loginBody);

    @GET("api/users")
    Call<UserResponse> user(
            @Query("per_page") int page
    );

    @GET("api/users/{id}")
    Call<SingleUserResponse> getSingleUser(
            @Path("id") int id
    );
}
