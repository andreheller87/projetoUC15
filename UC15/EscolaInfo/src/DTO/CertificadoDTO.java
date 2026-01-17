package DTO;

import java.time.LocalDate;

public class CertificadoDTO {
    private Integer idCertificado;
    private Integer idMatricula;
    private LocalDate dataEmissao;
    private String codigoVerificacao;

    // ============================
    // Getters
    // ============================
    public Integer getIdCertificado() { return idCertificado; }
    public Integer getIdMatricula() { return idMatricula; }
    public LocalDate getDataEmissao() { return dataEmissao; }
    public String getCodigoVerificacao() { return codigoVerificacao; }

    // ============================
    // Setters
    // ============================
    public void setIdCertificado(Integer idCertificado) { this.idCertificado = idCertificado; }
    public void setIdMatricula(Integer idMatricula) { this.idMatricula = idMatricula; }
    public void setDataEmissao(LocalDate dataEmissao) { this.dataEmissao = dataEmissao; }
    public void setCodigoVerificacao(String codigoVerificacao) { this.codigoVerificacao = codigoVerificacao; }
}
