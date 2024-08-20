package com.ejemplo.gestiontareas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.gestiontareas.model.User;
import com.ejemplo.gestiontareas.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        try {
            return userService.registerUser(user);
        } catch (Exception e) {
            e.printStackTrace(); // Esto te ayudará a ver el error exacto en los registros
            throw e; // Vuelve a lanzar la excepción para que Spring la maneje y devuelva un error 500
        }
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        try {
            return userService.getUser(id);
        } catch (Exception e) {
            e.printStackTrace(); // Esto te ayudará a ver el error exacto en los registros
            throw e; // Vuelve a lanzar la excepción para que Spring la maneje y devuelva un error 500
        }
    }
}
