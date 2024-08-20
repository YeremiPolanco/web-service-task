package com.ejemplo.gestiontareas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ejemplo.gestiontareas.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

