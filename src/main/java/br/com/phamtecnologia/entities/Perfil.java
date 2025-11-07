package br.com.phamtecnologia.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "perfil")
    private List<Usuario> usuarios;
}
