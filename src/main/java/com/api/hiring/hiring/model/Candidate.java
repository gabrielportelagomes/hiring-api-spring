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

    @NotBlank(message = "Nome é obrigatório")
    @Column(unique = true)
    String nome;

    @NotBlank(message = "Status é obrigatório")
    String status;

    public Candidate(String nome, String status) {
        this.nome = nome;
        this.status = status;
    }
}
