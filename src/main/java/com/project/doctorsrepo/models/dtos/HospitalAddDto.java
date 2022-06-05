package com.project.doctorsrepo.models.dtos;

import com.google.gson.annotations.Expose;

public class HospitalAddDto {
    @Expose
    private String name;

    public HospitalAddDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
