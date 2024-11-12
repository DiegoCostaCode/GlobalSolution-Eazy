package com.example.eazy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
@Entity
@Table(name = "gs_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario", unique = true)
    private String usuario;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "telefone", unique = true)
    private String telefonne;

    @Column(name = "senha")
    private String senha;

    @OneToOne
    @JoinColumn(name = "id_potuacao")
    private Potuacao potuacao;

    public void setSenha(String senha) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.senha = passwordEncoder.encode(senha);
    }

    public boolean verificarSenha(String senha) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(senha, this.senha);
    }
}
