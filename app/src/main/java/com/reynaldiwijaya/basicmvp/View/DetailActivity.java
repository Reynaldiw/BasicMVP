package com.reynaldiwijaya.basicmvp.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reynaldiwijaya.basicmvp.Detail.DetailContract;
import com.reynaldiwijaya.basicmvp.Detail.DetailPresenter;
import com.reynaldiwijaya.basicmvp.Model.UserData;
import com.reynaldiwijaya.basicmvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailContract.View {

    @BindView(R.id.imgAvatar)
    ImageView imgAvatar;
    @BindView(R.id.txtFirst)
    TextView txtFirst;
    @BindView(R.id.txtLast)
    TextView txtLast;

    private Bundle bundle;
    private ProgressDialog progressDialog;
    private final DetailPresenter detailPresenter = new DetailPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        // Mengirimkan Bundle ke Presenter untuk di cek dan merequest single user dengan id
        bundle = getIntent().getExtras();
        detailPresenter.getDataSingleUser(bundle);
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showDataSingleUser(UserData userData) {
        // Menampilkan data yang diberika oleh presenter ke layar
        txtFirst.setText(userData.getFirst_name());
        txtLast.setText(userData.getLast_name());

        RequestOptions requestOptions = new RequestOptions().error(R.drawable.ic_broken_image_black_24dp).placeholder(R.drawable.ic_broken_image_black_24dp);
        Glide.with(this)
                .load(userData.getAvatar())
                .apply(requestOptions)
                .into(imgAvatar);

    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
