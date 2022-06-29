package com.example.rentit;

public class BookRvModel {
    String BuyerAdharIMage;
    String UserId;
    String BuyerName;
    String BuyerAddress;
    String ArdharNum;
    String Status;
    String ImageUrl1;
    String BuyerUid;
    String PhoneNo;

    public BookRvModel(){}

    public BookRvModel(String buyerAdharIMage, String userId, String buyerName, String buyerAddress, String ardharNum, String status, String imageUrl1, String buyerUid, String phoneNo) {
        BuyerAdharIMage = buyerAdharIMage;
        UserId = userId;
        BuyerName = buyerName;
        BuyerAddress = buyerAddress;
        ArdharNum = ardharNum;
        Status = status;
        ImageUrl1 = imageUrl1;
        BuyerUid = buyerUid;
        PhoneNo = phoneNo;
    }

    public String getBuyerAdharIMage() {
        return BuyerAdharIMage;
    }

    public void setBuyerAdharIMage(String buyerAdharIMage) {
        BuyerAdharIMage = buyerAdharIMage;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getBuyerName() {
        return BuyerName;
    }

    public void setBuyerName(String buyerName) {
        BuyerName = buyerName;
    }

    public String getBuyerAddress() {
        return BuyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        BuyerAddress = buyerAddress;
    }

    public String getArdharNum() {
        return ArdharNum;
    }

    public void setArdharNum(String ardharNum) {
        ArdharNum = ardharNum;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getImageUrl1() {
        return ImageUrl1;
    }

    public void setImageUrl1(String imageUrl1) {
        ImageUrl1 = imageUrl1;
    }

    public String getBuyerUid() {
        return BuyerUid;
    }

    public void setBuyerUid(String buyerUid) {
        BuyerUid = buyerUid;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }
}

