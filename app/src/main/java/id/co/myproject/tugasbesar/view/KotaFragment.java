package id.co.myproject.tugasbesar.view;

import android.app.ProgressDialog;
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
import id.co.myproject.tugasbesar.adapter.KotaAdapter;
import id.co.myproject.tugasbesar.databinding.FragmentKotaBinding;
import id.co.myproject.tugasbesar.request.ApiRequest;
import id.co.myproject.tugasbesar.request.RetrofitRequest;
import id.co.myproject.tugasbesar.response.KotaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class KotaFragment extends Fragment {

    private static final String TAG = "KotaFragment";
    ApiRequest apiRequest;
    FragmentKotaBinding binding;
    KotaAdapter kotaAdapter;
    ProgressDialog progressDialog;

    public KotaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kota, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiRequest = RetrofitRequest.getInstance().create(ApiRequest.class);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Memproses ...");

        kotaAdapter = new KotaAdapter(getActivity());
        binding.rvKota.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvKota.setAdapter(kotaAdapter);

        loadData();
    }

    private void loadData() {
        Call<KotaResponse> kotaResponseCall = apiRequest.kotaRequest();
        kotaResponseCall.enqueue(new Callback<KotaResponse>() {
            @Override
            public void onResponse(Call<KotaResponse> call, Response<KotaResponse> response) {
                if (response.isSuccessful()){
                    kotaAdapter.setDetailBudayaList(response.body().getKotaList());
                }
            }

            @Override
            public void onFailure(Call<KotaResponse> call, Throwable t) {

            }
        });
    }
}
