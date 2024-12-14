package br.edu.ifrs.riogrande.tads.ppa.ligaa.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity // Notação Húngara (Hungarian Notation)
@Table(name = "tbl_alunos")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Aluno extends Entidade {
    
    @Column(nullable = false, length = 254) // VARCHAR(254)
    private String nome;

    @Column(name = "email", nullable = false, length = 254)
    private String enderecoEletronico;

    @Column(nullable = false, unique = true, length = 254)
    private String login; // e-mail

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Transient // não-persistido
    private String dado;

    public String getEnderecoEletronico() {
        return enderecoEletronico;
    }

    public void setEnderecoEletronico(String enderecoEletronico) {
        this.enderecoEletronico = enderecoEletronico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

   
    
}
