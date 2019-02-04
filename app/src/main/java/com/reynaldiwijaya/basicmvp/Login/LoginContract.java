package com.reynaldiwijaya.basicmvp.Login;

public interface LoginContract {
    // Membuat interface untuk method yang dibutuhkan pada view / interaksi dengan user
    interface View {
        // Menampilkan progress loading dialog
        void showProgress();
        void hideProgress();

        // Menampilkan dan melakukan sesuatu pada saat server merespon berhasil ataupun gagal
        void loginSucces(String token);
        void loginFailure(String msg);
    }

    // Membuat interface untuk method yang dibutuhkan pada presenter
    interface Presenter {
        // Method untuk mengeksekusi bisnis logic untuk login , contoh pengecekan data dan pengiriman data ke internet
        void doLogin(String email, String password);
    }
}
