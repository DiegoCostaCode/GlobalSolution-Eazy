package com.example.eazy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "gs_conta")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "kwh")
    private double kwh;

    @Column(name = "valor")
    private double valor;

    @Column(name = "data", unique = true)
    private Date data;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
