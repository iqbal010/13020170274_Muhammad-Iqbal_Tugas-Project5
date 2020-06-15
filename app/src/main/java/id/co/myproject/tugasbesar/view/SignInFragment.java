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
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import id.co.myproject.tugasbesar.R;
import id.co.myproject.tugasbesar.databinding.FragmentSignInBinding;
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
public class SignInFragment extends Fragment {

    ApiRequest apiRequest;

    private FragmentSignInBinding binding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    ProgressDialog progressDialog;

    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiRequest = RetrofitRequest.getInstance().create(ApiRequest.class);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Memproses ...");
        sharedPreferences = getActivity().getSharedPreferences(LOGIN_KEY, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekForm();
            }
        });
        binding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignUpFragment());
            }
        });
    }

    private void cekForm() {
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        if (email.matches(emailPattern)) {
            if (password.length() >= 8) {
                progressDialog.show();
                binding.btnSignIn.setEnabled(true);
                prosesLogin(email, password);
            } else {
                Toast.makeText(getActivity(), "Password kurang boss", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Username atau Password salah boss", Toast.LENGTH_SHORT).show();
        }
    }

    private void prosesLogin(String email, String password) {
        progressDialog.show();
        Call<LoginResponse> callLogin = apiRequest.loginUserRequest(email, password);
        callLogin.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (loginResponse.getValue() == 1){
                    editor.putString(ID_USER_KEY, loginResponse.getUser().getIdUser());
                    editor.putBoolean(LOGIN_STATUS, true);
                    editor.commit();
                    progressDialog.dismiss();
                    updateUI();
                }else {
                    binding.btnSignIn.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }
    private void updateUI() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Proses ...");
        progressDialog.show();
        boolean statusLogin = sharedPreferences.getBoolean(LOGIN_STATUS, false);
        if (statusLogin){
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }else {
            progressDialog.dismiss();
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
