package br.edu.ifrs.riogrande.tads.ppa.ligaa.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Professor;

@Repository
public interface ProfessorJpaRepository extends CrudRepository<Professor, UUID> {
    
}
