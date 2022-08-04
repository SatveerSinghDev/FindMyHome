package com.example.findmyhome;

public class MemberModel {
    String houseId;
    String age;
    String name;
    String job;
    String rent;
    String joiningDate;
    String phoneNumber;
    String ownerId;
    String memberId;

    public String getMemberID() {
        return memberId;
    }

    public void setMemberID(String memberID) {
        this.memberId = memberID;
    }



    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public MemberModel() {
    }

    public MemberModel(String houseId, String age, String name, String job, String rent, String joiningDate, String phoneNumber, String ownerId, String memberID) {
        this.houseId = houseId;
        this.age = age;
        this.name = name;
        this.job = job;
        this.rent = rent;
        this.joiningDate = joiningDate;
        this.phoneNumber = phoneNumber;
        this.ownerId = ownerId;
        this.memberId = memberID;
    }
}
