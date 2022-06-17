package com.example.rentit;

public class ServiceRVModel {
    String Name;
    String Experience;
    String Address;
    String jobName;
    String Area;
    String Charge;
    String Img1;
    String Img2;
    String Img3;
    String workingTime;
    String LangaugeKnow;
    String Desc;
    String ArdharNum;
    String ArdharPic;

    public ServiceRVModel(){}

    public ServiceRVModel(String name, String experience, String address, String area, String charge, String img1, String img2, String img3, String workingTime, String langaugeKnow, String desc, String ardharNum, String ardharPic,String jobName) {
        Name = name;
        Experience = experience;
        Address = address;
        Area = area;
        Charge = charge;
        Img1 = img1;
        Img2 = img2;
        Img3 = img3;
        this.workingTime = workingTime;
        LangaugeKnow = langaugeKnow;
        Desc = desc;
        ArdharNum = ardharNum;
        ArdharPic = ardharPic;
        this.jobName = jobName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getCharge() {
        return Charge;
    }

    public void setCharge(String charge) {
        Charge = charge;
    }

    public String getImg1() {
        return Img1;
    }

    public void setImg1(String img1) {
        Img1 = img1;
    }

    public String getImg2() {
        return Img2;
    }

    public void setImg2(String img2) {
        Img2 = img2;
    }

    public String getImg3() {
        return Img3;
    }

    public void setImg3(String img3) {
        Img3 = img3;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public String getLangaugeKnow() {
        return LangaugeKnow;
    }

    public void setLangaugeKnow(String langaugeKnow) {
        LangaugeKnow = langaugeKnow;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getArdharNum() {
        return ArdharNum;
    }

    public void setArdharNum(String ardharNum) {
        ArdharNum = ardharNum;
    }

    public String getArdharPic() {
        return ArdharPic;
    }

    public void setArdharPic(String ardharPic) {
        ArdharPic = ardharPic;
    }
}
