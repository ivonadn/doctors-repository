package com.project.doctorsrepo.models.dtos;

public class DoctorMakeNewDto {
    private String name;
    private String hospital;
    private String profession;

    public DoctorMakeNewDto(String name, String hospital, String profession) {
        this.name = name;
        this.hospital = hospital;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
