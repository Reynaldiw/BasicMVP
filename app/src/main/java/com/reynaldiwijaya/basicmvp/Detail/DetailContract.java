package com.reynaldiwijaya.basicmvp.Detail;

import android.os.Bundle;

import com.reynaldiwijaya.basicmvp.Model.UserData;

public interface DetailContract {
    interface View {
        void showProgress();
        void hideProgress();
        void showDataSingleUser(UserData userData);
        void showFailureMessage(String msg);
    }

    interface Presenter {
        void getDataSingleUser(Bundle bundle);
    }
}
