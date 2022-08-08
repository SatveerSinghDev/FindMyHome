package com.example.findmyhome;

public class TenantBookingModel {
    String houseId;
    String houseLocation;
    String houseImage;
    String onwerid;
    String status;

    public TenantBookingModel(){

    }
    public TenantBookingModel(String houseId, String houseLocation, String houseImage, String onwerid, String status) {
        this.houseId = houseId;
        this.houseLocation = houseLocation;
        this.houseImage = houseImage;
        this.onwerid = onwerid;
        this.status = status;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getHouseLocation() {
        return houseLocation;
    }

    public void setHouseLocation(String houseLocation) {
        this.houseLocation = houseLocation;
    }

    public String getHouseImage() {
        return houseImage;
    }

    public void setHouseImage(String houseImage) {
        this.houseImage = houseImage;
    }

    public String getOnwerid() {
        return onwerid;
    }

    public void setOnwerid(String onwerid) {
        this.onwerid = onwerid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
