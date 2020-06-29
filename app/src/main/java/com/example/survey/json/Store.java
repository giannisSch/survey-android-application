package com.example.survey.json;

public class Store {
    String Id;
    int Bgroud;
    String BranchArea;
    String BranchCity;
    String BranchName;
    String BranchPassword;

    public Store(String id, int bgroud, String branchArea, String branchCity, String branchName, String branchPassword) {
        Id = id;
        Bgroud = bgroud;
        BranchArea = branchArea;
        BranchCity = branchCity;
        BranchName = branchName;
        BranchPassword = branchPassword;
    }


    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public int getBgroud() {
        return Bgroud;
    }

    public void setBgroud(int bgroud) {
        Bgroud = bgroud;
    }

    public String getBranchArea() {
        return BranchArea;
    }

    public void setBranchArea(String branchArea) {
        BranchArea = branchArea;
    }

    public String getBranchCity() {
        return BranchCity;
    }

    public void setBranchCity(String branchCity) {
        BranchCity = branchCity;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public String getBranchPassword() {
        return BranchPassword;
    }

    public void setBranchPassword(String branchPassword) {
        BranchPassword = branchPassword;
    }
}
