package com.reynaldiwijaya.basicmvp.Detail;

import android.os.Bundle;

import com.reynaldiwijaya.basicmvp.Model.SingleUserResponse;
import com.reynaldiwijaya.basicmvp.Network.ApiClient;
import com.reynaldiwijaya.basicmvp.Network.ApiInterface;
import com.reynaldiwijaya.basicmvp.Utills.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter implements DetailContract.Presenter {

    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private int id;

    private final DetailContract.View view;

    public DetailPresenter(DetailContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataSingleUser(Bundle bundle) {
        if (bundle != null) {
            // Mengambil id dan dimasukan ke dalam variable id
            id = bundle.getInt(Constants.KEY_ID);

            view.showProgress();

            Call<SingleUserResponse> call = apiInterface.getSingleUser(id);
            call.enqueue(new Callback<SingleUserResponse>() {
                @Override
                public void onResponse(Call<SingleUserResponse> call, Response<SingleUserResponse> response) {
                    view.hideProgress();
                    if (response.body() != null) {
                        SingleUserResponse singleUserResponse = response.body();

                        if (singleUserResponse.getUserData() != null) {
                            view.showDataSingleUser(singleUserResponse.getUserData());
                        }
                    }
                }

                @Override
                public void onFailure(Call<SingleUserResponse> call, Throwable t) {
                    view.hideProgress();
                    view.showFailureMessage(t.getMessage());
                }
            });
        }

    }
}
