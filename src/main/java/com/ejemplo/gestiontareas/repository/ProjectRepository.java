package com.ejemplo.gestiontareas.repository;

import com.ejemplo.gestiontareas.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project existsById(long id);

    @Query(value = "SELECT COUNT(*) FROM project WHERE user_id = :userId AND status = 'IN_PROGRESS'", nativeQuery = true)
    long countByUserId(@Param("userId") Long userId);
}