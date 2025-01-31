package com.esprit.ms.job;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JobRepository extends JpaRepository <Job, Integer>{

    @Query("select job from Job job where job.nom like :nom")
    public Page<Job> jobByNom(@Param("nom") String n, Pageable pageable);

    Optional<Job> findByNom(String nom); // Méthode standard sans pagination


}
