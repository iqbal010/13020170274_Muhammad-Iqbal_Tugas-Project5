package id.co.myproject.tugasbesar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import id.co.myproject.tugasbesar.R;
import id.co.myproject.tugasbesar.adapter.BudayaAdapter;
import id.co.myproject.tugasbesar.adapter.DetailBudayaAdapter;
import id.co.myproject.tugasbesar.adapter.PopulerAdapter;
import id.co.myproject.tugasbesar.databinding.ActivityMainBinding;
import id.co.myproject.tugasbesar.databinding.FragmentHomeBinding;
import id.co.myproject.tugasbesar.view.HomeFragment;
import id.co.myproject.tugasbesar.view.KotaFragment;
import id.co.myproject.tugasbesar.view.ProfilFragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    public ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home){
                    setFragment(new HomeFragment());
                }else if (item.getItemId() == R.id.nav_kota){
                    setFragment(new KotaFragment());
                }else if (item.getItemId() == R.id.nav_profil){
                    setFragment(new ProfilFragment());
                }

                return true;
            }
        });

        setFragment(new HomeFragment());

    }


    public void setFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(binding.frameMain.getId(), fragment);
        transaction.commit();
    }
}
