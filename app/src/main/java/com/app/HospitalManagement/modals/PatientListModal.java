package com.app.HospitalManagement.modals;

public class PatientListModal {

    String id,name, email, doctorid ;

    public PatientListModal(String id, String name, String email, String doctorid) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.doctorid = doctorid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }
}
