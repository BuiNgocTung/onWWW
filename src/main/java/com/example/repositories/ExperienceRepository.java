package com.example.repositories;

import com.example.entities.Experience;
import com.example.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience,Long> {

    List<Experience> findExperienceByRole(Roles role);

}
