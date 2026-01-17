package MAIN;

import DTO.*;
import SERVICE.*;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // =========================
        // Inicializa Services
        // =========================
        AlunoService alunoService = new AlunoService();
        ProfessorService professorService = new ProfessorService();
        CursoService cursoService = new CursoService();
        MatriculaService matriculaService = new MatriculaService();
        PagamentoService pagamentoService = new PagamentoService();
        ComunicadoService comunicadoService = new ComunicadoService();

        // =========================
        // Cadastrar Aluno
        // =========================
        AlunoDTO aluno = new AlunoDTO();
        aluno.setNome("João Silva");
        aluno.setCpf("111.222.333-44");
        aluno.setEmail("joao.silva@example.com");
        aluno.setTelefone("(11)98765-4321");
        aluno.setStatus("Ativo");
        alunoService.cadastrarAluno(aluno);

        System.out.println("Aluno cadastrado: " + aluno.getIdAluno() + " - " + aluno.getNome());

        // =========================
        // Cadastrar Professor
        // =========================
        ProfessorDTO professor = new ProfessorDTO();
        professor.setNome("Carlos Alberto");
        professor.setDisciplina("Lógica de Programação");
        professor.setHorarioDisponivel("Segunda e Quarta - 14h às 16h");
        professor.setEmail("carlos.alberto@example.com");
        professorService.cadastrarProfessor(professor);

        System.out.println("Professor cadastrado: " + professor.getIdProfessor() + " - " + professor.getNome());

        // =========================
        // Cadastrar Curso
        // =========================
        CursoDTO curso = new CursoDTO();
        curso.setNome("Introdução à Programação");
        curso.setDescricao("Curso básico de lógica e algoritmos.");
        curso.setCargaHoraria(40);
        curso.setValor(500.0);
        curso.setDataInicio(LocalDate.of(2025, 6, 1));
        curso.setDataTermino(LocalDate.of(2025, 7, 1));
        curso.setIdProfessor(professor.getIdProfessor());
        cursoService.cadastrarCurso(curso);

        System.out.println("Curso cadastrado: " + curso.getIdCurso() + " - " + curso.getNome());

        // =========================
        // Matricular Aluno no Curso
        // =========================
        MatriculaDTO matricula = new MatriculaDTO();
        matricula.setIdAluno(aluno.getIdAluno());
        matricula.setIdCurso(curso.getIdCurso());
        matricula.setDataMatricula(LocalDate.now());
        matricula.setStatus("Matriculado");

        matriculaService.matricularAluno(matricula, curso.getValor());

        System.out.println("Aluno matriculado: " + aluno.getNome() + " no curso " + curso.getNome());

        // =========================
        // Listar Alunos
        // =========================
        System.out.println("\nLista de alunos:");
        List<AlunoDTO> alunos = alunoService.listarAlunos();
        for (AlunoDTO a : alunos) {
            System.out.println(a.getIdAluno() + " - " + a.getNome());
        }

        // =========================
        // Listar Cursos
        // =========================
        System.out.println("\nLista de cursos:");
        List<CursoDTO> cursos = cursoService.listarCursos();
        for (CursoDTO c : cursos) {
            System.out.println(c.getIdCurso() + " - " + c.getNome() + " - Professor ID: " + c.getIdProfessor());
        }

        // =========================
        // Listar Matrículas
        // =========================
        System.out.println("\nLista de matrículas:");
        List<MatriculaDTO> matriculas = matriculaService.listarMatriculas();
        for (MatriculaDTO m : matriculas) {
            System.out.println("Matricula ID: " + m.getIdMatricula() + 
                               ", Aluno ID: " + m.getIdAluno() + 
                               ", Curso ID: " + m.getIdCurso() + 
                               ", Status: " + m.getStatus());
        }

        // =========================
        // Listar Comunicados
        // =========================
        System.out.println("\nLista de comunicados:");
        List<ComunicadoDTO> comunicados = comunicadoService.listarComunicados();
        for (ComunicadoDTO com : comunicados) {
            System.out.println(com.getIdComunicado() + " - " + com.getTitulo() + " (" + com.getDataEnvio() + ")");
        }

        // =========================
        // Listar Pagamentos
        // =========================
        System.out.println("\nLista de pagamentos:");
        List<PagamentoDTO> pagamentos = pagamentoService.listarPagamentos();
        for (PagamentoDTO p : pagamentos) {
            System.out.println("Pagamento ID: " + p.getIdPagamento() + 
                               ", Matrícula ID: " + p.getIdMatricula() + 
                               ", Valor: " + p.getValor() + 
                               ", Status: " + p.getStatus());
        }

        System.out.println("\n=== Fluxo completo executado com sucesso! ===");
    }
}
