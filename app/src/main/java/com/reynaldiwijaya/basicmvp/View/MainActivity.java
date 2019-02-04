package com.reynaldiwijaya.basicmvp.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.reynaldiwijaya.basicmvp.Adapter.MainAdapter;
import com.reynaldiwijaya.basicmvp.Main.MainContract;
import com.reynaldiwijaya.basicmvp.Main.MainPresenter;
import com.reynaldiwijaya.basicmvp.Model.UserData;
import com.reynaldiwijaya.basicmvp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.rv_user)
    RecyclerView rvUser;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private ProgressDialog progressDialog;
    private final MainPresenter mainPresenter = new MainPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Mengambil data ke internet yang dilakukan oleh Presenter
        mainPresenter.getDataListUser();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainPresenter.getDataListUser();
            }
        });
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ... ");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showDataListUser(List<UserData> userDataList) {
        rvUser.setLayoutManager(new LinearLayoutManager(this));
        rvUser.setAdapter(new MainAdapter(this, userDataList));
    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
