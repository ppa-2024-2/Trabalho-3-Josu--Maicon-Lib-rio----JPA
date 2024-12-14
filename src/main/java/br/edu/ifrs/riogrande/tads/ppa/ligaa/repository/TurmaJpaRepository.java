package br.edu.ifrs.riogrande.tads.ppa.ligaa.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Aluno;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Matricula;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Turma;

@Repository
public interface TurmaJpaRepository extends ListCrudRepository<Turma, UUID> {

    Turma findByCodigo(String codigoTurma);

    
	default List<Matricula> findByAluno(Aluno aluno){
        return findAll().stream().flatMap(t -> t.getMatriculas().stream())
            .filter(m -> m.getAluno().equals(aluno)).toList();
    };

    @Query("SELECT t FROM Turma t JOIN Matricula m ON m.turma = t WHERE m.aluno = :aluno")
    List<Turma> findAllByAluno(Aluno aluno);

}
