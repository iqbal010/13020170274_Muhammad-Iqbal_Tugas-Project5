package id.co.myproject.tugasbesar.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.FragmentTransaction;
import id.co.myproject.tugasbesar.R;
import id.co.myproject.tugasbesar.databinding.FragmentSignUpBinding;
import id.co.myproject.tugasbesar.MainActivity;
import id.co.myproject.tugasbesar.request.ApiRequest;
import id.co.myproject.tugasbesar.request.RetrofitRequest;
import id.co.myproject.tugasbesar.response.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static id.co.myproject.tugasbesar.helper.Utils.ID_USER_KEY;
import static id.co.myproject.tugasbesar.helper.Utils.LOGIN_KEY;
import static id.co.myproject.tugasbesar.helper.Utils.LOGIN_STATUS;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    private FragmentSignUpBinding binding;
    FrameLayout parentFrameLayout;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    String idUser;
    private ProgressDialog progressDialog;
    public static final String TAG = SignUpFragment.class.getSimpleName();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    ApiRequest apiRequest;
    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiRequest = RetrofitRequest.getInstance().create(ApiRequest.class);

        sharedPreferences = getActivity().getSharedPreferences(LOGIN_KEY, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Proses ...");

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrasiProses();
            }
        });

        binding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignInFragment());
            }
        });
    }


    private void registrasiProses() {
        String nama = binding.etNama.getText().toString();
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        if (binding.etConfirmPassword.getText().toString().equals(password)) {
            Call<LoginResponse> loginResponseCall = apiRequest.registerUserRequest(nama, email, password);
            loginResponseCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.getValue() == 1){
                        idUser = loginResponse.getUser().getIdUser();
                        editor.putString(ID_USER_KEY, idUser);
                        editor.putBoolean(LOGIN_STATUS, true);
                        editor.commit();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                    }else {
                        binding.btnSignUp.setEnabled(true);
                    }

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                }
            });
        }else {
            binding.etConfirmPassword.setError("Password tidak cocok");
        }
    }

    public void setFragment(Fragment fragment){
        FrameLayout parenLayout = ((LoginActivity)binding.getRoot().getContext()).binding.frameLogin;
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(parenLayout.getId(), fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
