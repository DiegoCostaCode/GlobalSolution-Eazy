package com.example.eazy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

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
    private String telefone;

    @Column(name = "senha")
    private String senha;

    @Column(name = "pontuacao")
    private double pontuacao = 0.0;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioRecompensas> usuarioRecompensas;



    public void removerPontuacao(double pontos) {
        if (pontos > 0) {
            this.pontuacao -= pontos;
            if (this.pontuacao < 0) {
                this.pontuacao = 0;
            }
        }
    }

    public void adicionarPontuacao(double pontos) {
        if (pontos > 0) {
            this.pontuacao += pontos;
        }
    }

    public void setSenha(String senha) {
        this.senha = hashSenha(senha);
    }

    private String hashSenha(String senha) {

        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public boolean verificarSenha(String senha) {

        return BCrypt.checkpw(senha, this.senha);
    }


}
