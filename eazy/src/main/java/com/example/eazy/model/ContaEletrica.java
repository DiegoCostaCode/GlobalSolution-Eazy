package com.example.eazy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Getter
@Setter
@Entity
@Table(name = "gs_conta_eletrica")
public class ContaEletrica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "kwh")
    private double kwh;

    @Column(name = "valor")
    private double valor;

    @DateTimeFormat(pattern = "MM-dd")
    @Column(name = "data", unique = true)
    private Date data;


}
