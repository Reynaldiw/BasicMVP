package com.reynaldiwijaya.basicmvp.Main;

import com.reynaldiwijaya.basicmvp.Model.UserData;

import java.util.List;

public interface MainContract {
    interface View {
        void showProgress();
        void hideProgress();
        // Menampilkan data list user ke view RecyclerView
        void showDataListUser(List<UserData> userDataList);

        // Menmpilkan pesan gagal
        void showFailureMessage(String msg);
    }

    interface presenter {
        void getDataListUser();
    }
}
