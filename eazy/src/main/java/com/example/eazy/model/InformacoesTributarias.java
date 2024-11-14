package com.example.eazy.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Column(name = "estado")
    private Enum_estado estado;

    @Column(name = "valorKwh")
    private double valorKwh;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "ultimaAtualizacao")
    private Date ultimaAtualizacao = new Date(System.currentTimeMillis());
}
