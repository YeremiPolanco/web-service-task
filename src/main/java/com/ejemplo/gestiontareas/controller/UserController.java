package com.ejemplo.gestiontareas.controller;

import com.ejemplo.gestiontareas.controller.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ejemplo.gestiontareas.model.User;
import com.ejemplo.gestiontareas.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDto> createUser(@RequestBody User user) {
        try {
            userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(1, "Creado correctamente"));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(1,"Creado incorrectamente, Error: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            User updatedUser =userService.update(id, user);
            if (updatedUser == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseDto(1, "Actualizado correctamente"));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(2,"Actualizado incorrectamente, Error: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable Long id) {
        try {
            boolean isDeleted = userService.delete(id);
            if (isDeleted) {
                return ResponseEntity.ok(new ResponseDto(1, "Eliminado correctamente"));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(2, "Eliminado incorrectamente, Error: " + e.getMessage()));
        }
    }

}
