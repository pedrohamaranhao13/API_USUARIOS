package br.com.phamtecnologia.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "telefone", length = 15, nullable = true)
    private String telefone;

    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;

    @Column(name = "senha", length = 255, nullable = false)
    private String senha;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dataHoraCriacao", nullable = false)
    private LocalDateTime dataHoraCriacao;

    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;

}
