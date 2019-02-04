package com.reynaldiwijaya.basicmvp.Main;

import android.util.Log;

import com.reynaldiwijaya.basicmvp.Model.UserResponse;
import com.reynaldiwijaya.basicmvp.Network.ApiClient;
import com.reynaldiwijaya.basicmvp.Network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.presenter {

    private final MainContract.View view;

    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataListUser() {
        view.showProgress();

        Call<UserResponse> call = apiInterface.user(9);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                view.hideProgress();

                if (response.body() != null) {
                    UserResponse userResponse = response.body();
                    // Mencek data list user
                    if (userResponse.getUserDataList() != null) {
                        Log.i("TAG", userResponse.getUserDataList().toString());
                        // Mengirimkan data list ke user ke view untuk di tampilkan
                        view.showDataListUser(userResponse.getUserDataList());
                    }
                }


            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());
            }
        });

    }
}
