package DTO;

import java.time.LocalDate;

public class CursoDTO {
    private Integer idCurso;
    private String nome;
    private String descricao;
    private Integer cargaHoraria;
    private Double valor;
    private LocalDate dataInicio;  // <-- LocalDate
    private LocalDate dataTermino; // <-- LocalDate
    private Integer idProfessor;

    // ============================
    // Getters
    // ============================
    public Integer getIdCurso() {
        return idCurso;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public Double getValor() {
        return valor;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public Integer getIdProfessor() {
        return idProfessor;
    }

    // ============================
    // Setters
    // ============================
    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public void setIdProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }
}
