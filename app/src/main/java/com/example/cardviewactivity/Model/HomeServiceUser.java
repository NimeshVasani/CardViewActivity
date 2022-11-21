package com.example.cardviewactivity.Model;

public class HomeServiceUser {
    private String name,expertization,degree,experiance,workplace,time,number;
    private String imageURL;

    public HomeServiceUser() {
    }

    public HomeServiceUser(String name, String expertization, String degree, String experiance, String workplace, String time, String number, String imageURL) {
        this.name = name;
        this.expertization = expertization;
        this.degree = degree;
        this.experiance = experiance;
        this.workplace = workplace;
        this.time = time;
        this.number = number;
        this.imageURL = imageURL;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpertization() {
        return expertization;
    }

    public void setExpertization(String expertization) {
        this.expertization = expertization;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getExperiance() {
        return experiance;
    }

    public void setExperiance(String experiance) {
        this.experiance = experiance;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

}
