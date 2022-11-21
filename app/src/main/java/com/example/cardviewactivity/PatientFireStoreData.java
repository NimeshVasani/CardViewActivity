package com.example.cardviewactivity;

public class PatientFireStoreData {

    String name,address,age,gender,number,email,password,catagory;

    public PatientFireStoreData(String number, String email, String password, String catagory) {
        this.number = number;
        this.email = email;
        this.password = password;
        this.catagory = catagory;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public PatientFireStoreData() {
    }

    public PatientFireStoreData(String name, String address, String age, String gender, String number, String email, String password) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.number = number;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
