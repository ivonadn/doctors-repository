package com.project.doctorsrepo.models.dtos;

import com.google.gson.annotations.Expose;

public class ProfessionAddDto {
    @Expose
    private String name;

    public ProfessionAddDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
