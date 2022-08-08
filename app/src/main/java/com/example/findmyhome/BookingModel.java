package com.example.findmyhome;

public class BookingModel {
    String houseId;
    String houseLocation;
    String houseImage;
    String user;
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOnwerid() {
        return onwerid;
    }

    public void setOnwerid(String onwerid) {
        this.onwerid = onwerid;
    }

    String onwerid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }


    String userid;

    public BookingModel(String houseId, String houseLocation, String houseImage, String user,String userid,String status, String onwerid) {
        this.houseId = houseId;
        this.houseLocation = houseLocation;
        this.houseImage = houseImage;
        this.user = user;
        this.userid = userid;
        this.status = status;
        this.onwerid = onwerid;
    }

    public BookingModel(){

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
