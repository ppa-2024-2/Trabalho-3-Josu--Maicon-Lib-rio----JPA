package br.edu.ifrs.riogrande.tads.ppa.ligaa.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Historico;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Matricula;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Turma;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Situacao;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.AlunoJpaRepository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.DisciplinaRepository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.MatriculaJpaRepository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.ProfessorJpaRepository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.TurmaJpaRepository;
import jakarta.annotation.PostConstruct;

@Service
public class TurmaService {

    int numero;

    private final TurmaJpaRepository turmaJpaRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final MatriculaJpaRepository matriculaJpaRepository;
    private final AlunoJpaRepository alunoJpaRepository;
    private final ProfessorJpaRepository ProfessorJpaRepository;

    public TurmaService(
            TurmaJpaRepository turmaJpaRepository,
            DisciplinaRepository disciplinaRepository,
            AlunoJpaRepository alunoJpaRepository,
            MatriculaJpaRepository matriculaJpaRepository,
            ProfessorJpaRepository ProfessorJpaRepository) {
        this.turmaJpaRepository = turmaJpaRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.alunoJpaRepository = alunoJpaRepository;
        this.ProfessorJpaRepository = ProfessorJpaRepository;
        this.matriculaJpaRepository = matriculaJpaRepository;
    }

    public Matricula matricular(String cpf, String codigoTurma) {
        // turma existe?
        var turma = turmaJpaRepository.findByCodigo(codigoTurma);
        if (turma == null) {
            throw new ServiceException("Turma " + codigoTurma + " não encontrada");
        }
        System.out.println(turma);

        // turma já terminou o ciclo?
        if (turma.isFechada()) {
            throw new ServiceException("Turma " + codigoTurma + " está fechada");
        }

        // aluno existe?
        var aluno = alunoJpaRepository.findByCpf(cpf);
        if (aluno == null) {
            throw new ServiceException("Aluno " + cpf + " não encontrado");
        }
        System.out.println(aluno);

        // aluno já matriculado?
        if (turma.estaMatriculado(aluno)) {
            throw new ServiceException("Aluno " + cpf + " já está matriculado na turma " + codigoTurma);
        }

        // todas as turmas do aluno
        var turmas = turmaJpaRepository.findAllByAluno(aluno);

        // aluno já fez essa disciplina?
        if (turmas.stream().flatMap(t -> t.getMatriculas().stream())
                .anyMatch(m -> m.getAluno().equals(aluno) && m.getSituacao().equals(Situacao.APROVADO))) {
            throw new ServiceException(
                    "Aluno " + cpf + " já aprovado na disciplina " + turma.getDisciplina().getNome());
        }

        int qtdAlunosTurma = turma.getMatriculas().size();

        int qtdVagas = turma.getVagas();

        // há vagas?
        if (qtdAlunosTurma >= qtdVagas) { // não
            // turmas anteriores da disciplina
            var turmasAnterioresDaDisciplina = turmas.stream()
                    .filter(t -> t.getDisciplina().equals(turma.getDisciplina()))
                    .toList();

            boolean reprovadoAnteriormente = turmasAnterioresDaDisciplina.stream()
                    .flatMap(t -> t.getMatriculas().stream())
                    .anyMatch(m -> m.getAluno().equals(aluno) && m.getSituacao().equals(Situacao.REPROVADO));

            // se nunca foi reprovado, não pode matricular-se
            if (!reprovadoAnteriormente) {
                throw new ServiceException("Não há vagas na turma " + codigoTurma);
            }
        }

        var matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setSituacao(Situacao.REGULAR);
        matricula.setTurma(turma);
        matricula.setId(UUID.randomUUID());
        turma.getMatriculas().add(matricula);

        // persistir os dados
        turmaJpaRepository.save(turma);
        matriculaJpaRepository.save(matricula);
        System.out.println(matricula);
        return matricula;
    }

}
