package com.ejemplo.gestiontareas.repository;

import com.ejemplo.gestiontareas.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByOwnerId(Long ownerId);
    List<Project> findByNameContainingOrDescriptionContaining(String name, String description);
}