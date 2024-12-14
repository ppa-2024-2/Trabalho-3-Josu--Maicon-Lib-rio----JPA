package br.edu.ifrs.riogrande.tads.ppa.ligaa.service;
// new AlunoService()

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Aluno;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.AlunoJpaRepository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.AlunoRepository;

@Service // qualificando o objeto
public class AlunoService {

    // dependência
    private final AlunoJpaRepository alunoRepository;

    public AlunoService(AlunoJpaRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public void cadastrarAluno(NovoAluno novoAluno) {

        if (alunoRepository.existsByCpf(novoAluno.getCpf())) {
            throw new IllegalStateException("CPF já existe: " + novoAluno.getCpf());
        }

        // ONDE FICAM AS REGRAS DE DOMÍNIO
        Aluno aluno = new Aluno();

        aluno.setId(UUID.randomUUID());
        
        aluno.setCpf(novoAluno.getCpf());
        aluno.setNome(novoAluno.getNome());
        aluno.setLogin(novoAluno.getEnderecoEletronico());
        aluno.setEnderecoEletronico(novoAluno.getEnderecoEletronico());

        alunoRepository.save(aluno);        
    }

    public Iterable<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Aluno buscarAluno(@NonNull String cpf) {
        return new Aluno();
    }
 
}
