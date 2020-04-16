package com.example.sp2020group5;

public class StaffDetails {
    String fname, lname, empid, password;

    public StaffDetails() {
    }

    public StaffDetails(String fname, String lname, String empid, String password) {
        this.fname = fname;
        this.lname = lname;
        this.empid = empid;
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
