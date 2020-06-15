package id.co.myproject.tugasbesar.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import id.co.myproject.tugasbesar.R;
import id.co.myproject.tugasbesar.adapter.BudayaAdapter;
import id.co.myproject.tugasbesar.adapter.DetailBudayaAdapter;
import id.co.myproject.tugasbesar.adapter.PopulerAdapter;
import id.co.myproject.tugasbesar.databinding.FragmentHomeBinding;
import id.co.myproject.tugasbesar.helper.Utils;
import id.co.myproject.tugasbesar.request.ApiRequest;
import id.co.myproject.tugasbesar.request.RetrofitRequest;
import id.co.myproject.tugasbesar.response.BudayaResponse;
import id.co.myproject.tugasbesar.response.DetailBudayaResponse;
import id.co.myproject.tugasbesar.response.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    FragmentHomeBinding binding;
    PopulerAdapter populerAdapter;
    BudayaAdapter budayaAdapter;
    DetailBudayaAdapter detailBudayaAdapter;
    ApiRequest apiRequest;

    ViewModelProvider.Factory factory;
    SharedPreferences sharedPreferences;
    String idUser;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        sharedPreferences = getActivity().getSharedPreferences(Utils.LOGIN_KEY, Context.MODE_PRIVATE);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiRequest = RetrofitRequest.getInstance().create(ApiRequest.class);

        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT );

        populerAdapter = new PopulerAdapter(getActivity());
        budayaAdapter = new BudayaAdapter(getActivity());
        detailBudayaAdapter = new DetailBudayaAdapter(getActivity());

        idUser = sharedPreferences.getString(Utils.ID_USER_KEY, "");

        LinearLayoutManager layoutManagerHorizontal = new LinearLayoutManager(getActivity());
        layoutManagerHorizontal.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.rvPopuler.setLayoutManager(layoutManagerHorizontal);
        LinearLayoutManager layoutManagerHorizontal1 = new LinearLayoutManager(getActivity());
        layoutManagerHorizontal1.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.rvBudaya.setLayoutManager(layoutManagerHorizontal1);
        binding.rvBudayaSemua.setLayoutManager(new LinearLayoutManager(getActivity()));

        binding.rvPopuler.setAdapter(populerAdapter);
        binding.rvBudaya.setAdapter(budayaAdapter);
        binding.rvBudayaSemua.setAdapter(detailBudayaAdapter);

        loadData();
    }

    private void loadData() {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Proses ...");
        progressDialog.show();
        Call<BudayaResponse> budayaResponseCall = apiRequest.budayaRequest();
        budayaResponseCall.enqueue(new Callback<BudayaResponse>() {
            @Override
            public void onResponse(Call<BudayaResponse> call, Response<BudayaResponse> response) {
                if (response.isSuccessful()){
                    BudayaResponse budayaResponse = response.body();
                    budayaAdapter.setBudayaList(budayaResponse.getBudayaData());

                    Call<DetailBudayaResponse> detailBudayaResponseCall = apiRequest.detailBudayaRequest();
                    detailBudayaResponseCall.enqueue(new Callback<DetailBudayaResponse>() {
                        @Override
                        public void onResponse(Call<DetailBudayaResponse> call, Response<DetailBudayaResponse> response) {
                            if (response.isSuccessful()){
                                DetailBudayaResponse detailBudayaResponse = response.body();
                                detailBudayaAdapter.setDetailBudayaList(detailBudayaResponse.getDetailBudaya());
                                populerAdapter.setDetailBudayaList(detailBudayaResponse.getDetailBudaya());

                                Call<UserResponse> userResponseCall = apiRequest.userRequest(idUser);
                                userResponseCall.enqueue(new Callback<UserResponse>() {
                                    @Override
                                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                        if (response.isSuccessful()){
                                            UserResponse userResponse = response.body();
                                            binding.setUserData(userResponse.getUser());
                                            progressDialog.dismiss();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<UserResponse> call, Throwable t) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<DetailBudayaResponse> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<BudayaResponse> call, Throwable t) {

            }
        });
    }
}
