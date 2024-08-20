package com.ejemplo.gestiontareas.model;


import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 30)
    @Column(unique = true, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Status status = Status.INITIATION;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Temporal(TemporalType.DATE)
    private Date dateCreated = new Date();

    @Temporal(TemporalType.DATE)
    private Date dateStarted;

    @Temporal(TemporalType.DATE)
    private Date dateFinished;


    public enum Status {
        INITIATION,
            IN_PROGRESS,
        COMPLETED;
    }


}