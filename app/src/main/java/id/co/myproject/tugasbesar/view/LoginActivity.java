package id.co.myproject.tugasbesar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import id.co.myproject.tugasbesar.R;
import id.co.myproject.tugasbesar.databinding.ActivityLoginBinding;
import id.co.myproject.tugasbesar.helper.Utils;
import id.co.myproject.tugasbesar.MainActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    private SharedPreferences sharedPreferences;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        sharedPreferences = getSharedPreferences(Utils.LOGIN_KEY, Context.MODE_PRIVATE);


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon tunggu");
        progressDialog.setMessage("Proses ...");
        progressDialog.setCancelable(false);

        cekLogin();
    }

    private void cekLogin() {
        progressDialog.show();
        boolean statusLogin = sharedPreferences.getBoolean(Utils.LOGIN_STATUS, false);
        if (statusLogin){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            progressDialog.dismiss();
            setFragment(new SignInFragment());
        }
    }

    public void setFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(binding.frameLogin.getId(), fragment);
        transaction.commit();
    }
}
