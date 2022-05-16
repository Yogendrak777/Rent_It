package com.example.rentit;

import android.os.Parcel;
import android.os.Parcelable;

public class personInfoRvModel implements Parcelable {
    String UserNameDb;
    String UserEmailDb;
    String UserPhoneDb;
    String UserIdDb;


    public personInfoRvModel(){}


    public personInfoRvModel(String userNameDb, String userEmailDb, String userPhoneDb, String userIdDb) {
        UserNameDb = userNameDb;
        UserEmailDb = userEmailDb;
        UserPhoneDb = userPhoneDb;
        UserIdDb = userIdDb;
    }

    protected personInfoRvModel(Parcel in) {
        UserNameDb = in.readString();
        UserEmailDb = in.readString();
        UserPhoneDb = in.readString();
        UserIdDb = in.readString();
    }

    public static final Creator<personInfoRvModel> CREATOR = new Creator<personInfoRvModel>() {
        @Override
        public personInfoRvModel createFromParcel(Parcel in) {
            return new personInfoRvModel(in);
        }

        @Override
        public personInfoRvModel[] newArray(int size) {
            return new personInfoRvModel[size];
        }
    };

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

    public String getUserIdDb() {
        return UserIdDb;
    }

    public void setUserIdDb(String userIdDb) {
        UserIdDb = userIdDb;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(UserNameDb);
        parcel.writeString(UserEmailDb);
        parcel.writeString(UserPhoneDb);
        parcel.writeString(UserIdDb);
    }
}
