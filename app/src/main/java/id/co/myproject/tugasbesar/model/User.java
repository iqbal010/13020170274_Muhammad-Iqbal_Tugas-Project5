package id.co.myproject.tugasbesar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import id.co.myproject.tugasbesar.BuildConfig;


public class User {
    @SerializedName("id_user")
    @Expose
    private String idUser;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("foto")
    @Expose
    private String foto;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    public User(String idUser, String email, String password, String nama, String foto, String alamat) {
        this.idUser = idUser;
        this.email = email;
        this.password = password;
        this.nama = nama;
        this.foto = foto;
        this.alamat = alamat;
    }

    public User() {
    }

    public String getIdUser() {
        return idUser;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNama() {
        return nama;
    }

    public String getFoto() {
        String url = BuildConfig.BASE_URL_GAMBAR+"profil/";
        return url+foto;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
