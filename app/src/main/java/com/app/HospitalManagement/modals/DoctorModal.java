package com.app.HospitalManagement.modals;

public class DoctorModal {
    int id;
    String name, designation, email;

    public DoctorModal(int id, String name, String designation, String email) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
