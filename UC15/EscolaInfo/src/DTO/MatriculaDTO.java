package DTO;

import java.time.LocalDate;

public class MatriculaDTO {
    private Integer idMatricula;
    private Integer idAluno;
    private Integer idCurso;
    private LocalDate dataMatricula; // <-- LocalDate
    private String status;

    // ============================
    // Getters
    // ============================
    public Integer getIdMatricula() { return idMatricula; }
    public Integer getIdAluno() { return idAluno; }
    public Integer getIdCurso() { return idCurso; }
    public LocalDate getDataMatricula() { return dataMatricula; }
    public String getStatus() { return status; }

    // ============================
    // Setters
    // ============================
    public void setIdMatricula(Integer idMatricula) { this.idMatricula = idMatricula; }
    public void setIdAluno(Integer idAluno) { this.idAluno = idAluno; }
    public void setIdCurso(Integer idCurso) { this.idCurso = idCurso; }
    public void setDataMatricula(LocalDate dataMatricula) { this.dataMatricula = dataMatricula; }
    public void setStatus(String status) { this.status = status; }
}
