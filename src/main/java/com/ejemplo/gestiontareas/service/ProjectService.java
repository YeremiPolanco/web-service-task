package com.ejemplo.gestiontareas.service;

import com.ejemplo.gestiontareas.model.Project;
import com.ejemplo.gestiontareas.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project) {
        // Add business logic for constraints here
        return projectRepository.save(project);
    }

    public List<Project> getProjectsByUser(Long userId) {
        return projectRepository.findByOwnerId(userId);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public List<Project> searchProjects(String query) {
        return projectRepository.findByNameContainingOrDescriptionContaining(query, query);
    }
}