package br.edu.ifrs.riogrande.tads.ppa.ligaa.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Aluno;

@Repository
public interface AlunoJpaRepository extends CrudRepository<Aluno, UUID> {

    boolean existsByCpf(String cpf);

    Aluno findByCpf(String cpf);
    
}
