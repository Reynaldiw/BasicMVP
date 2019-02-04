package com.reynaldiwijaya.basicmvp.Login;

import com.reynaldiwijaya.basicmvp.Model.LoginBody;
import com.reynaldiwijaya.basicmvp.Model.LoginResponse;
import com.reynaldiwijaya.basicmvp.Network.ApiClient;
import com.reynaldiwijaya.basicmvp.Network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {

    // Menyiapkan variable yang dibutuhkan
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);


   // Membuat object LoginContract berupa view
    private final LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void doLogin(String email, String password) {
        // Mengecek email dan password apakah null or not ?
        if (email == null || email.isEmpty()) {
            view.loginFailure("Email Kosong");
            return;
        }

        if (password == null || password.isEmpty()) {
            view.loginFailure("Password kosong");
            return;
        }

        // Menampilkan progress dialog agar memberitahu ada proses yang sedang berjalan
        view.showProgress();

        // Memasukan data ke dalam email dan password ke dalam LoginBody
        LoginBody loginBody = new LoginBody();
        loginBody.setEmail(email);
        loginBody.setPassword(password);

        // Mengeksekusi data ke server
        // Membuat object call untuk mengirim LoginBody
        Call<LoginResponse> call = apiInterface.postLogin(loginBody);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                view.hideProgress();

                // Mencek response apakah ada isinya
                if (response.body() != null) {
                    // Mengambil data response body dan memasukan ke dalam class model LoginResponse
                    LoginResponse loginResponse = response.body();
                    // Mencek isi token apakah ada isinya ? Agar tidak forceclose
                    if (loginResponse.getToken() != null) {
                        view.loginSucces(loginResponse.getToken());
                    } else {
                        view.loginFailure("User tidak terdaftar");
                    }

                } else {
                    view.loginFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Menutup progress Dialog
                view.hideProgress();
                view.loginFailure(t.getMessage());

            }
        });
    }
}
