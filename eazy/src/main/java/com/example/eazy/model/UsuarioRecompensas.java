package com.example.eazy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "gs_usuario_recompensas")
public class UsuarioRecompensas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat()
    @Column(name = "data_resgate")
    private Date dataResgate = new Date(System.currentTimeMillis());

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_recompensa")
    private Recompensas recompensa;


}
