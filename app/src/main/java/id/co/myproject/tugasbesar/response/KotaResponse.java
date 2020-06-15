package id.co.myproject.tugasbesar.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.myproject.tugasbesar.model.Kota;

public class KotaResponse {
    @SerializedName("value")
    @Expose
    private int value;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("kota")
    @Expose
    private List<Kota> kotaList;

    public KotaResponse(int value, String message, List<Kota> kotaList) {
        this.value = value;
        this.message = message;
        this.kotaList = kotaList;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Kota> getKotaList() {
        return kotaList;
    }

    public void setKotaList(List<Kota> kotaList) {
        this.kotaList = kotaList;
    }
}
