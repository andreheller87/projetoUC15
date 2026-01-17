package DTO;

import java.time.LocalDate;

public class PagamentoDTO {
    private Integer idPagamento;
    private Integer idMatricula;
    private Double valor;
    private LocalDate dataPagamento;
    private String status;

    // ============================
    // Getters
    // ============================
    public Integer getIdPagamento() { return idPagamento; }
    public Integer getIdMatricula() { return idMatricula; }
    public Double getValor() { return valor; }
    public LocalDate getDataPagamento() { return dataPagamento; }
    public String getStatus() { return status; }

    // ============================
    // Setters
    // ============================
    public void setIdPagamento(Integer idPagamento) { this.idPagamento = idPagamento; }
    public void setIdMatricula(Integer idMatricula) { this.idMatricula = idMatricula; }
    public void setValor(Double valor) { this.valor = valor; }
    public void setDataPagamento(LocalDate dataPagamento) { this.dataPagamento = dataPagamento; }
    public void setStatus(String status) { this.status = status; }
}
