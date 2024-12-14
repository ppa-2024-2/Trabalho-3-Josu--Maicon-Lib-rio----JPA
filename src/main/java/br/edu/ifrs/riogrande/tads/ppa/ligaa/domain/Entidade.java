package br.edu.ifrs.riogrande.tads.ppa.ligaa.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

// classe abstrata não tem dados específicos (concretos)
// serve apenas para ser estendida
// where desativado = false
@MappedSuperclass
public abstract class Entidade {
    // -- campos de controle
    @Id // primary key
    @Column
    private UUID id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "criacao")
    private LocalDateTime dataHoraCriacao;

    @Column(name = "alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataHoraAlteracao;

    @Column(nullable = false)
    private boolean desativado; // false

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public LocalDateTime getDataHoraAlteracao() {
        return dataHoraAlteracao;
    }

    public void setDataHoraAlteracao(LocalDateTime dataHoraAlteracao) {
        this.dataHoraAlteracao = dataHoraAlteracao;
    }

    public boolean isDesativado() {
        return desativado;
    }

    public void setDesativado(boolean desativado) {
        this.desativado = desativado;
    }
}
