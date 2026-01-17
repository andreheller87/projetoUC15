package DTO;

public class AlunoDTO {
    private Integer idAluno;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String status;

    public Integer getIdAluno() { return idAluno; }
    public void setIdAluno(Integer idAluno) { this.idAluno = idAluno; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
