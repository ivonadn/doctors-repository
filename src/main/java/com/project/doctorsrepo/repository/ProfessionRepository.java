package com.project.doctorsrepo.repository;

import com.project.doctorsrepo.models.entities.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, Integer> {
    public Profession findProfessionByName(String name);
}
