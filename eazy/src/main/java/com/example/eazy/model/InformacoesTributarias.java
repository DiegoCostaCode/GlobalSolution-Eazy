package com.example.eazy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "gs_informacoes_tributarias")
public class InformacoesTributarias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", unique = true)
    private Estado estado;

    @Column(name = "valorKwh")
    private double valorKwh;

    @Column(name = "ultimaAtualizacao")
    private Date ultimaAtualizacao;

    @PrePersist
    public void prePersist() {
        this.ultimaAtualizacao = new Date(System.currentTimeMillis());
    }

    @PreUpdate
    public void preUpdate() {
        this.ultimaAtualizacao = new Date(System.currentTimeMillis());
    }
}
