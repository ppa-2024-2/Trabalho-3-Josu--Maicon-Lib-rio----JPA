package br.edu.ifrs.riogrande.tads.ppa.ligaa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Aluno;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Disciplina;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Matricula;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Professor;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Situacao;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Turma;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.AlunoJpaRepository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.DisciplinaJpaRepository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.MatriculaJpaRepository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.ProfessorJpaRepository;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.repository.TurmaJpaRepository;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class LigaaApplication {

	@Autowired
	private MatriculaJpaRepository matriculaJpaRepository;

	@Autowired
	private AlunoJpaRepository alunoJpaRepository;

	@Autowired
	private ProfessorJpaRepository professorJpaRepository;

	@Autowired
	private DisciplinaJpaRepository disciplinaJpaRepository;

	@Autowired
	private TurmaJpaRepository turmaJpaRepository;

	public static void main(String[] args) {
		SpringApplication.run(LigaaApplication.class, args);
	}

	@PostConstruct
	void seed() {
		System.out.println("Semeando ... a discórdia!");

		// Criando o aluno
		var can = new Aluno();
		can.setCpf("11122233344");
		can.setDataHoraCriacao(LocalDateTime.now());
		can.setDataHoraAlteracao(LocalDateTime.now());
		can.setDesativado(false);
		can.setEnderecoEletronico("can.robert@gmail.com");
		can.setLogin(can.getEnderecoEletronico());
		can.setNome("Canrobert Junior");
		can.setId(UUID.randomUUID());
		alunoJpaRepository.save(can);

		// Criando o aluno 2
		var gabriel = new Aluno();
		gabriel.setCpf("123456");
		gabriel.setDataHoraCriacao(LocalDateTime.now());
		gabriel.setDataHoraAlteracao(LocalDateTime.now());
		gabriel.setDesativado(false);
		gabriel.setEnderecoEletronico("gab.gol@gmail.com");
		gabriel.setLogin(gabriel.getEnderecoEletronico());
		gabriel.setNome("Gabigol Flamengo");
		gabriel.setId(UUID.randomUUID());
		alunoJpaRepository.save(gabriel);

		// Criando o professor
		var marcio = new Professor();
		marcio.setNome("Marcio");
		marcio.setFormacao("ADS");
		marcio.setSiape("123456");
		marcio.setId(UUID.randomUUID());
		professorJpaRepository.save(marcio);

		// Criando a disciplina
		var ppa = new Disciplina();
		ppa.setId(UUID.randomUUID());
		ppa.setNome("PPA");
		ppa.setCodigo("123");
		disciplinaJpaRepository.save(ppa);

		// Criando a turma
		var turma = new Turma();
		turma.setId(UUID.randomUUID());
		turma.setCodigo("123");
		turma.setSala("220");
		turma.setSemestre("2");
		turma.setVagas(30);
		turma.setFechada(false);
		turma.setDisciplina(ppa);
		turma.setProfessor(marcio);

		// Criando a matrícula e associando à turma
		var canMatricula = new Matricula();
		canMatricula.setId(UUID.randomUUID());
		canMatricula.setAluno(can);
		canMatricula.setTurma(turma); // Associar a turma
		canMatricula.setNumero(7);
		canMatricula.setSituacao(Situacao.REGULAR);

		// Adicionando a matrícula à lista de matrículas da turma
		turma.getMatriculas().add(canMatricula);

		// Persistindo a turma com a matrícula
		turmaJpaRepository.save(turma);
	}

}
