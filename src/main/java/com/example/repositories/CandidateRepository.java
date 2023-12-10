package com.example.repositories;

import com.example.entities.Candidate;
import com.example.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {
    List<Candidate> findByEmailNotNull();
    @Query("SELECT distinct e.candidate FROM Experience e WHERE e.role = ?1")
    List<Candidate> findCandidatesByExperienceRole(Roles role);
}
