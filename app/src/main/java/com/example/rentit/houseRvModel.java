package com.example.rentit;

public class houseRvModel {
    String houseAddress;
    String houseBHK;
    String housePrise;
    String houseUrl1;
    String type;

    public houseRvModel() {
    }



    public houseRvModel(String houseAddress, String houseBHK, String housePrise,String houseUrl1,String type) {
        this.houseAddress = houseAddress;
        this.houseBHK = houseBHK;
        this.housePrise = housePrise;
        this.houseUrl1 = houseUrl1;
        this.type = type;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getHouseBHK() {
        return houseBHK;
    }

    public void setHouseBHK(String houseBHK) {
        this.houseBHK = houseBHK;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHouseUrl1(String houseUrl1) {
        this.houseUrl1 = houseUrl1;
    }

    public String getHousePrise() {
        return housePrise;
    }

    public void setHousePrise(String housePrise) {
        this.housePrise = housePrise;
    }


    public String getHouseUrl1() {
        return houseUrl1;
    }

}
