package com.api.hiring.hiring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Column(unique = true)
    String name;

    @NotBlank(message = "Status is mandatory")
    String status;

    public Candidate(String name, String status) {
        this.name = name;
        this.status = status;
    }
}
