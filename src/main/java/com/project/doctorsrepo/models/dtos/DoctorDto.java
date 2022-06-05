package com.project.doctorsrepo.models.dtos;

import com.google.gson.annotations.Expose;

public class DoctorDto {
    @Expose
    private String name;

    public DoctorDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
