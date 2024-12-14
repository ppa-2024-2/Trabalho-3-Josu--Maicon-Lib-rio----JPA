package br.edu.ifrs.riogrande.tads.ppa.ligaa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "matriculas")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Matricula extends Entidade{

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @Column(nullable = false)
    private int numero;

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Turma turma;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return "Matricula [numero=" + numero + ", aluno=" + aluno + ", situacao=" + situacao + "]";
    }

    
}
