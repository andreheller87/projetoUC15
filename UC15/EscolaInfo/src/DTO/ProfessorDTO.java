package DTO;

public class ProfessorDTO {
    private Integer idProfessor;
    private String nome;
    private String disciplina;
    private String horarioDisponivel;
    private String email;

    public Integer getIdProfessor() { return idProfessor; }
    public void setIdProfessor(Integer idProfessor) { this.idProfessor = idProfessor; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDisciplina() { return disciplina; }
    public void setDisciplina(String disciplina) { this.disciplina = disciplina; }

    public String getHorarioDisponivel() { return horarioDisponivel; }
    public void setHorarioDisponivel(String horarioDisponivel) { this.horarioDisponivel = horarioDisponivel; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
