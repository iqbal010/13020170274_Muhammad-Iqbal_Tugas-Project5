package id.co.myproject.tugasbesar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import id.co.myproject.tugasbesar.BuildConfig;


public class DetailBudaya {

    @SerializedName("id_detail")
    @Expose
    private String idDetail;

    @SerializedName("id_budaya")
    @Expose
    private String idBudaya;

    @SerializedName("id_kota")
    @Expose
    private String idKota;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;

    @SerializedName("gambar")
    @Expose
    private String gambar;

    @SerializedName("timestamp")
    @Expose
    private Date timestamp;

    @SerializedName("nama_kota")
    @Expose
    private String namaKota;

    @SerializedName("jumlah_like")
    @Expose
    private String jumlahLike;

    public DetailBudaya(String idDetail, String idBudaya, String idKota, String nama, String deskripsi, String gambar, Date timestamp) {
        this.idDetail = idDetail;
        this.idBudaya = idBudaya;
        this.idKota = idKota;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.gambar = gambar;
        this.timestamp = timestamp;
    }

    public String getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(String idDetail) {
        this.idDetail = idDetail;
    }

    public String getIdBudaya() {
        return idBudaya;
    }

    public void setIdBudaya(String idBudaya) {
        this.idBudaya = idBudaya;
    }

    public String getIdKota() {
        return idKota;
    }

    public void setIdKota(String idKota) {
        this.idKota = idKota;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }


    public String getGambar() {
        String url = BuildConfig.BASE_URL_GAMBAR+"detail_budaya/";
        return url+gambar;
    }
    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getNamaKota() {
        return namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }

    public String getJumlahLike() {
        return jumlahLike;
    }

    public void setJumlahLike(String jumlahLike) {
        this.jumlahLike = jumlahLike;
    }
}
