package com.example.rentit;

import android.os.Parcel;
import android.os.Parcelable;

public class personInfoRvModel {
    String UserNameDb;
    String UserEmailDb;
    String UserPhoneDb;
    String UserId;
    String Status;
    String UserIMage;


    public personInfoRvModel(){}


    public personInfoRvModel(String userNameDb, String userEmailDb, String userPhoneDb,String userId,String status,String userIMage) {
        UserNameDb = userNameDb;
        UserEmailDb = userEmailDb;
        UserPhoneDb = userPhoneDb;
        UserId = userId;
        Status = status;
        UserIMage = userIMage;
    }

    public String getStatus() {
        return Status;
    }

    public String getUserIMage() {
        return UserIMage;
    }

    public void setUserIMage(String userIMage) {
        UserIMage = userIMage;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserNameDb() {
        return UserNameDb;
    }

    public void setUserNameDb(String userNameDb) {
        UserNameDb = userNameDb;
    }

    public String getUserEmailDb() {
        return UserEmailDb;
    }

    public void setUserEmailDb(String userEmailDb) {
        UserEmailDb = userEmailDb;
    }

    public String getUserPhoneDb() {
        return UserPhoneDb;
    }

    public void setUserPhoneDb(String userPhoneDb) {
        UserPhoneDb = userPhoneDb;
    }




    }
