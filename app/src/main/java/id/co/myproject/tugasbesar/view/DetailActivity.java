package id.co.myproject.tugasbesar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import id.co.myproject.tugasbesar.R;
import id.co.myproject.tugasbesar.databinding.ActivityDetailBinding;
import id.co.myproject.tugasbesar.helper.Utils;
import id.co.myproject.tugasbesar.request.ApiRequest;
import id.co.myproject.tugasbesar.request.RetrofitRequest;
import id.co.myproject.tugasbesar.response.DetailBudayaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import static id.co.myproject.tugasbesar.helper.Utils.LOGIN_KEY;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    String idDetail;
    ApiRequest apiRequest;
    ProgressDialog progressDialog;
    String idUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        apiRequest = RetrofitRequest.getInstance().create(ApiRequest.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        idDetail = getIntent().getStringExtra("id_detail");
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Proses ...");

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadData();


    }

    private void loadData() {
        progressDialog.show();
        Call<DetailBudayaResponse> detailBudayaResponseCall = apiRequest.detailBudayaRequestById(idDetail);
        detailBudayaResponseCall.enqueue(new Callback<DetailBudayaResponse>() {
            @Override
            public void onResponse(Call<DetailBudayaResponse> call, Response<DetailBudayaResponse> response) {
                if (response.isSuccessful()){
                    binding.setBudaya(response.body().getData());
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<DetailBudayaResponse> call, Throwable t) {

            }
        });
    }
}
