package com.app.HospitalManagement.modals;

public class DoctorListModal {


    String id,name, email, designation , qualification;

    public DoctorListModal(String id, String name , String email, String designation , String qualification){
        this.id=id;
        this.name=name;
        this.email=email;
        this.designation=designation;
        this.qualification=qualification;

    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
