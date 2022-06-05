package com.project.doctorsrepo.repository;

import com.project.doctorsrepo.models.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    public Hospital findHospitalByName(String name);
}
