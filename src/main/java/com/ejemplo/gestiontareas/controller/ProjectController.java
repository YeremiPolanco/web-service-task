package com.ejemplo.gestiontareas.controller;

import com.ejemplo.gestiontareas.controller.dto.ResponseDto;
import com.ejemplo.gestiontareas.model.Project;
import com.ejemplo.gestiontareas.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping()
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createProject(@RequestBody Project project) {
        System.out.println(project);
        try {
            Project created = projectService.createProject(project);
            System.out.println(created);
            if (created == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(3, "Superó el número máximo permitido de proyectos por usuario"));
            } else {
                // Si no se creó el proyecto, significa que se superó el número máximo permitido
                return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(1, "Creado correctamente"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(2, "Error al crear el proyecto: " + e.getMessage()));
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateProject(@PathVariable Long id, @RequestBody Project project) {
        try {
            Project updatedProject = projectService.updateProject(id, project);
            if (updatedProject == null) {
                // En caso de que el proyecto no se haya encontrado para actualizar
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(new ResponseDto(1, "Actualizado correctamente"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(2, "Actualizado incorrectamente, Error: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteProject(@PathVariable Long id) {
        try {
            boolean isDeleted = projectService.deleteProject(id);
            if (isDeleted) {
                return ResponseEntity.ok(new ResponseDto(1, "Eliminado correctamente"));
            } else {
                // Si el proyecto no se encontró para eliminar
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(2, "Eliminado incorrectamente, Error: " + e.getMessage()));
        }
    }
}