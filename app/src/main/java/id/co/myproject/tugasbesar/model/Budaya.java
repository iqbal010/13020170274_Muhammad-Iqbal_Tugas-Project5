package id.co.myproject.tugasbesar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import id.co.myproject.tugasbesar.BuildConfig;


public class Budaya {
    @SerializedName("id_budaya")
    @Expose
    private String idBudaya;

    @SerializedName("nama_budaya")
    @Expose
    private String namaBudaya;

    @SerializedName("gambar")
    @Expose
    private String gambar;

    public Budaya(String idBudaya, String namaBudaya, String gambar) {
        this.idBudaya = idBudaya;
        this.namaBudaya = namaBudaya;
        this.gambar = gambar;
    }

    public String getIdBudaya() {
        return idBudaya;
    }

    public void setIdBudaya(String idBudaya) {
        this.idBudaya = idBudaya;
    }

    public String getNamaBudaya() {
        return namaBudaya;
    }

    public void setNamaBudaya(String namaBudaya) {
        this.namaBudaya = namaBudaya;
    }

    public String getGambar() {
        String url = BuildConfig.BASE_URL_GAMBAR+"budaya/";
        return url+gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
