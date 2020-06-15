package id.co.myproject.tugasbesar.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.myproject.tugasbesar.model.DetailBudaya;

public class DetailBudayaResponse {
    @SerializedName("value")
    @Expose
    private int value;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("budaya_detail_data")
    @Expose
    private List<DetailBudaya> detailBudaya;

    @SerializedName("data")
    @Expose
    private DetailBudaya data;


    public DetailBudayaResponse(int value, String message, List<DetailBudaya> detailBudaya) {
        this.value = value;
        this.message = message;
        this.detailBudaya = detailBudaya;
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

    public List<DetailBudaya> getDetailBudaya() {
        return detailBudaya;
    }

    public void setDetailBudaya(List<DetailBudaya> detailBudaya) {
        this.detailBudaya = detailBudaya;
    }

    public DetailBudaya getData() {
        return data;
    }

    public void setData(DetailBudaya data) {
        this.data = data;
    }
}
