package com.project.doctorsrepo.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor extends BaseEntity{
    private String name;
    private Profession profession;
    private Hospital hospital;

    public Doctor() {
    }

    public Doctor(String name, Hospital hospital, Profession profession) {
        this.name = name;
        this.profession = profession;
        this.hospital = hospital;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    @ManyToOne
    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
