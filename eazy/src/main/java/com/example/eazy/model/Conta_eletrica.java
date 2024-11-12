package com.example.eazy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.YearMonth;

@Getter
@Setter
@Entity
@Table(name = "gs_conta_eletrica")
public class Conta_eletrica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "kwh")
    private double kwh;

    @Column(name = "valor")
    private double valor;

    @DateTimeFormat(pattern = "MM-dd")
    @Column(name = "data", unique = true)
    private Date data;
}
