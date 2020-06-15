package id.co.myproject.tugasbesar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import id.co.myproject.tugasbesar.BuildConfig;


public class Kota {
    @SerializedName("id_kota")
    @Expose
    private String idKota;

    @SerializedName("nama_kota")
    @Expose
    private String namaKota;

    @SerializedName("gambar")
    @Expose
    private String gambar;

    public Kota(String idKota, String namaKota, String gambar) {
        this.idKota = idKota;
        this.namaKota = namaKota;
        this.gambar = gambar;
    }

    public String getIdKota() {
        return idKota;
    }

    public void setIdKota(String idKota) {
        this.idKota = idKota;
    }

    public String getNamaKota() {
        return namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }

    public String getGambar() {
        String url = BuildConfig.BASE_URL_GAMBAR+"kota/";
        return url+gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
