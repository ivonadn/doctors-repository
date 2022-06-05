package com.project.doctorsrepo.repository;

import com.project.doctorsrepo.models.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    public List<Doctor> getAllByHospital_IdOrderByName(int id);

    public List<Doctor> getAllByProfession_IdOrderByName(int id);

    public Doctor getDoctorByName(String name);

    @Query("select d from Doctor as d order by d.name asc")
    public List<Doctor> getAllOrdered();
}
