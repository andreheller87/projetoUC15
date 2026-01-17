package DTO;

import java.time.LocalDate;

public class ComunicadoDTO {
    private Integer idComunicado;
    private String titulo;
    private String mensagem;
    private LocalDate dataEnvio;

    // ============================
    // Getters
    // ============================
    public Integer getIdComunicado() { return idComunicado; }
    public String getTitulo() { return titulo; }
    public String getMensagem() { return mensagem; }
    public LocalDate getDataEnvio() { return dataEnvio; }

    // ============================
    // Setters
    // ============================
    public void setIdComunicado(Integer idComunicado) { this.idComunicado = idComunicado; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public void setDataEnvio(LocalDate dataEnvio) { this.dataEnvio = dataEnvio; }
}
