package id.co.myproject.tugasbesar.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.myproject.tugasbesar.model.Budaya;

public class BudayaResponse {
    @SerializedName("value")
    @Expose
    private int value;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("budaya_data")
    @Expose
    private List<Budaya> budayaData;

    public BudayaResponse(int value, String message, List<Budaya> budayaData) {
        this.value = value;
        this.message = message;
        this.budayaData = budayaData;
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

    public List<Budaya> getBudayaData() {
        return budayaData;
    }

    public void setBudayaData(List<Budaya> budayaData) {
        this.budayaData = budayaData;
    }
}
