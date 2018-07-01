package com.droidsoftbd.hhbregistration.Classes;

/**
 * Created by BDDL-102 on 6/1/2018.
 */

public class UserInfo {
    private int id;
    private String fullName;
    private String gender;
    private String bloodGroup;
    private String age;
    private String mobileNumber;
    private String lastBloodDonateDate;
    private String division;
    private String occupation;
    private String fatherName;
    private String presentAddress;
    private String permanentAddress;
    private String emailFbID;
    private String regularity;
    private String status;

    //Constructor for local List show
    public UserInfo(String fullName, String gender, String regularity, String bloodGroup, String age, String mobileNumber,
                    String lastBloodDonateDate, String division, String occupation, String fatherName,
                    String presentAddress, String permanentAddress, String emailFbID,String status) {
        this.fullName = fullName;
        this.gender = gender;
        this.regularity = regularity;
        this.bloodGroup = bloodGroup;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.lastBloodDonateDate = lastBloodDonateDate;
        this.division = division;
        this.occupation = occupation;
        this.fatherName = fatherName;
        this.presentAddress = presentAddress;
        this.permanentAddress = permanentAddress;
        this.emailFbID = emailFbID;
        this.status = status;
    }
    //Constructor for UnSynced Information
    public UserInfo(String fullName, String gender, String regularity, String bloodGroup, String age, String mobileNumber,
                    String lastBloodDonateDate, String division, String occupation, String fatherName,
                    String presentAddress) {
        this.fullName = fullName;
        this.gender = gender;
        this.regularity = regularity;
        this.bloodGroup = bloodGroup;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.lastBloodDonateDate = lastBloodDonateDate;
        this.division = division;
        this.occupation = occupation;
        this.fatherName = fatherName;
        this.presentAddress = presentAddress;
        this.permanentAddress = permanentAddress;
        this.emailFbID = emailFbID;

    }

    public UserInfo(int id, String fullName, String gender, String regularity, String bloodGroup, String age, String mobileNumber,
                    String lastBloodDonateDate, String division, String occupation, String fatherName,
                    String presentAddress, String permanentAddress, String emailFbID, String status) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.regularity = regularity;
        this.bloodGroup = bloodGroup;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.lastBloodDonateDate = lastBloodDonateDate;
        this.division = division;
        this.occupation = occupation;
        this.fatherName = fatherName;
        this.presentAddress = presentAddress;
        this.permanentAddress = permanentAddress;
        this.emailFbID = emailFbID;
        this.status = status;
    }

    public UserInfo(int id, String fullName, String gender, String bloodGroup, String mobileNumber,
                    String lastBloodDonateDate, String division, String presentAddress) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.mobileNumber = mobileNumber;
        this.lastBloodDonateDate = lastBloodDonateDate;
        this.division = division;
        this.presentAddress = presentAddress;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getAge() {
        return age;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getLastBloodDonateDate() {
        return lastBloodDonateDate;
    }

    public String getDivision() {
        return division;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public String getRegularity() {
        return regularity;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public String getEmailFbID() {
        return emailFbID;
    }

    public String getStatus() {
        return status;
    }
}
