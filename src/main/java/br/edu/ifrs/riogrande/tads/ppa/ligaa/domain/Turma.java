package br.edu.ifrs.riogrande.tads.ppa.ligaa.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

// relacionamento vertical
// classe/objeto/entidade pai
// classe/objeto/entidade filha

// relacionamento horizontal
// turma tem disciplina
// turma tem professor


// not my cup of tea (não é minha xícara de chá - não é minha praia)
@Entity
@Table(name = "turmas")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Turma extends Entidade {

    @Column(nullable = false)
    private String codigo; // ppa-2024-2
    // automaticamene todos os campos
    // são persistentes exceto os anotados com @Transient
    private String semestre; // 2024-2
    private String sala;
    private int vagas;
    private boolean fechada;

    // Turma turma = turmaRepository.findById(45); // SELECT JOIN
    // turma.getProfessor(); // select from professores

    @ManyToOne(optional = false)
    private Disciplina disciplina;

    // this ->  @ManyToOne -> professor
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Professor professor;
    
    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Matricula> matriculas = new ArrayList<>();

    public boolean isFechada() {
        return fechada;
    }

    public void setFechada(boolean fechada) {
        this.fechada = fechada;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }


    @Override
    public String toString() {
        return "Turma [codigo=" + codigo + ", disciplina=" + disciplina + ", professor=" + professor + ", semestre="
                + semestre + ", sala=" + sala + ", vagas=" + vagas + ", matriculas=" + matriculas + "]";
    }

    public boolean estaMatriculado(Aluno aluno) {
        return this.getMatriculas()
            .stream()
            .anyMatch(m -> m.getAluno().equals(aluno));
    }

    
}
