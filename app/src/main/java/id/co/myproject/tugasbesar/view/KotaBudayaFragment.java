package id.co.myproject.tugasbesar.view;

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
import id.co.myproject.tugasbesar.adapter.DetailBudayaAdapter;
import id.co.myproject.tugasbesar.databinding.FragmentKotaBudayaBinding;
import id.co.myproject.tugasbesar.request.ApiRequest;
import id.co.myproject.tugasbesar.request.RetrofitRequest;
import id.co.myproject.tugasbesar.response.DetailBudayaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class KotaBudayaFragment extends Fragment {

    FragmentKotaBudayaBinding binding;
    ViewModelProvider.Factory factory;
    String idKota;
    DetailBudayaAdapter detailBudayaAdapter;
    ApiRequest apiRequest;

    public KotaBudayaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kota_budaya, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiRequest = RetrofitRequest.getInstance().create(ApiRequest.class);
        idKota = getArguments().getString("id_kota");
        detailBudayaAdapter = new DetailBudayaAdapter(getActivity());
        binding.rvKotaBudaya.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvKotaBudaya.setAdapter(detailBudayaAdapter);

        binding.tvTitle.setText(getArguments().getString("title"));

        loadData();

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
    }

    private void loadData() {
        Call<DetailBudayaResponse> budayaResponseCall = apiRequest.detailBudayaByIdKota(idKota);
        budayaResponseCall.enqueue(new Callback<DetailBudayaResponse>() {
            @Override
            public void onResponse(Call<DetailBudayaResponse> call, Response<DetailBudayaResponse> response) {
                if (response.isSuccessful()){
                    detailBudayaAdapter.setDetailBudayaList(response.body().getDetailBudaya());
                }
            }

            @Override
            public void onFailure(Call<DetailBudayaResponse> call, Throwable t) {

            }
        });
    }
}
