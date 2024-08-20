package com.ejemplo.gestiontareas.service;

import com.ejemplo.gestiontareas.model.Project;
import com.ejemplo.gestiontareas.model.User;
import com.ejemplo.gestiontareas.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public Project createProject(Project project) {
        Long countNum = projectRepository.countByUserId(project.getUser().getId());
        System.out.println("Número de proyectos en progreso para el usuario: " + countNum);

        // Verificar si el número de proyectos en progreso es mayor o igual a 2
        if (countNum >= 2) {
            return null; // Retornar null si se supera el límite
        } else {
            // Si el estado del proyecto es 'IN_PROGRESS', establecer la fecha de inicio actual
            if (project.getStatus() == Project.Status.IN_PROGRESS ) {
                project.setDateStarted(new Date()); // Establecer la fecha actual
            }
            if (project.getStatus() == Project.Status.COMPLETED) {
                project.setDateStarted(new Date());
                project.setDateFinished(new Date());
            }
            return projectRepository.save(project); // Guardar el proyecto en el repositorio
        }
    }


    public Project updateProject(Long id, Project project) {
        Project updatedProject = findById(id);
        Project.builder()
                .name(project.getName())
                .description(project.getDescription())
                .status(project.getStatus())
                .build();

        if (project.getStatus() == Project.Status.IN_PROGRESS) {
            updatedProject.setDateStarted(new Date());
        }
        if (project.getStatus() == Project.Status.COMPLETED) {
            updatedProject.setDateStarted(new Date());
            updatedProject.setDateFinished(new Date());
        }
        return updatedProject;
    }

    public boolean deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return !projectRepository.existsById(id);
        } else {
            return false;
        }
    }
}