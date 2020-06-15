package id.co.myproject.tugasbesar.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import id.co.myproject.tugasbesar.R;
import id.co.myproject.tugasbesar.databinding.ActivityEditProfilBinding;
import id.co.myproject.tugasbesar.helper.ConvertBitmap;
import id.co.myproject.tugasbesar.helper.Utils;
import id.co.myproject.tugasbesar.model.User;
import id.co.myproject.tugasbesar.model.Value;
import id.co.myproject.tugasbesar.request.ApiRequest;
import id.co.myproject.tugasbesar.request.RetrofitRequest;
import id.co.myproject.tugasbesar.response.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

import static id.co.myproject.tugasbesar.helper.Utils.LOGIN_KEY;

public class EditProfilActivity extends AppCompatActivity implements ConvertBitmap{

    private static final String TAG = "EditProfilActivity";

    public static final int REQUEST_GALERY = 84;
    public static final int REQUEST_CAMERA = 12;
    public static final int CAMERA_PERMISSION = 13;

    ActivityEditProfilBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;
    String idUser;

    ApiRequest apiRequest;

    Bitmap bitmap = null;
    String photoUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profil);

        apiRequest = RetrofitRequest.getInstance().create(ApiRequest.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Memproses ...");
        sharedPreferences = getSharedPreferences(LOGIN_KEY, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        idUser = sharedPreferences.getString(Utils.ID_USER_KEY, "");

        loadData();

        binding.ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfilActivity.this);
                builder.setTitle("Pilih");
                String[] pilihJenis = {"Camera", "Galery"};
                builder.setItems(pilihJenis, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, REQUEST_CAMERA);
                        } else {
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(intent, REQUEST_GALERY);
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });

        binding.ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfilActivity.this);
                builder.setTitle("Pilih");
                String[] pilihJenis = {"Camera", "Galery"};
                builder.setItems(pilihJenis, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, REQUEST_CAMERA);
                        } else {
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(intent, REQUEST_GALERY);
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (photoUser != null) {
                    new LoadBitmapConverterCallback(EditProfilActivity.this, EditProfilActivity.this::bitmapToString).execute();
                }
                User user = new User();
                user.setIdUser(idUser);
                user.setNama(binding.etNama.getText().toString());
                user.setAlamat(binding.etAlamat.getText().toString());
                user.setEmail(binding.etEmail.getText().toString());
                user.setFoto(photoUser);
                Call<Value> valueCall = apiRequest.editUserRequest(user);
                valueCall.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        if (response.isSuccessful()) {
                            Value value = response.body();
                            if (value.getValue() == 1) {
                                Toast.makeText(EditProfilActivity.this, "Edit berhasil", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(EditProfilActivity.this, "Edit Data gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {

                    }
                });

            }
        });
    }

    private void loadData() {
        Call<UserResponse> userResponseCall = apiRequest.userRequest(idUser);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    binding.setUser(response.body().getUser());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_CAMERA);
            }else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_GALERY){
            if(resultCode == RESULT_OK && data != null){
                Uri imageUri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    binding.ivUser.setImageBitmap(bitmap);
                    new LoadBitmapConverterCallback(this, EditProfilActivity.this::bitmapToString).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if(requestCode == REQUEST_CAMERA){
            if (resultCode == RESULT_OK && data != null){
                bitmap = (Bitmap) data.getExtras().get("data");
                binding.ivUser.setImageBitmap(bitmap);
                new LoadBitmapConverterCallback(this, EditProfilActivity.this::bitmapToString).execute();
            }
        }
    }

    private void editProfil(String gambar){
        photoUser = gambar;
    }
    @Override
    public void bitmapToString(String imgConvert) {
        editProfil(imgConvert);
    }


    private class LoadBitmapConverterCallback extends AsyncTask<Void, Void, String> {

        private WeakReference<Context> weakContext;
        private ConvertBitmap convertBitmap;

        public LoadBitmapConverterCallback(Context context, ConvertBitmap convertBitmap){
            this.weakContext = new WeakReference<>(context);
            this.convertBitmap = convertBitmap;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakContext.get();
        }

        @Override
        protected String doInBackground(Void... voids) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
            byte[] imgByte = byteArrayOutputStream.toByteArray();
            String imgBitmap = Base64.encodeToString(imgByte, Base64.DEFAULT);
            return imgBitmap;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            convertBitmap.bitmapToString(s);
        }
    }
}
