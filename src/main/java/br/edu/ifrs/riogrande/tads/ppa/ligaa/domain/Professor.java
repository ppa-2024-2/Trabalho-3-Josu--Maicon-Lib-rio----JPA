package br.edu.ifrs.riogrande.tads.ppa.ligaa.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

// Três modelos de relacionamento
// Um-Para-Um
// Um-Para-Muitos <-> Muitos-Para-Um
// Muitos-Para-Muitos

@Entity
@Table(name = "professores")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Professor extends Entidade {

    
    @Column(nullable = false, length = 254)
    private String nome;

    @Column(nullable = false, length = 50)
    private String formacao;

    // private Turma turma;

    // armazenamento: UTF-8, UTF-16, UTF-32, ASCII, WINDOWS-1252, ISO-8859-1
    //     1810497 木
    // 1 + 7 = 8 BYTES
    // INTEGER = 32 BITS = 4 BYTES
    // LONG = 64 BITS = 8 BYTES

    // competência inconsciente
    // competência consciente
    // incompetência consciente
    // incompetência inconsciente

    @Column
    private String siape;

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

}
