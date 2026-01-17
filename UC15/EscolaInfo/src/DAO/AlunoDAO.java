package DAO;

import DTO.AlunoDTO;
import UTIL.ConexaoDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    // ===========================
    // CREATE
    // ===========================
    public boolean inserir(AlunoDTO aluno) {
        String sql = "INSERT INTO aluno (nome, cpf, email, telefone, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getCpf());
            ps.setString(3, aluno.getEmail());
            ps.setString(4, aluno.getTelefone());
            ps.setString(5, aluno.getStatus());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    aluno.setIdAluno(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
             System.out.println("ERRO: " + e);
        }
        return false;
    }

    // ===========================
    // READ ALL
    // ===========================
    public List<AlunoDTO> listarTodos() {
        List<AlunoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM aluno";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                AlunoDTO a = new AlunoDTO();
                a.setIdAluno(rs.getInt("id_aluno"));
                a.setNome(rs.getString("nome"));
                a.setCpf(rs.getString("cpf"));
                a.setEmail(rs.getString("email"));
                a.setTelefone(rs.getString("telefone"));
                a.setStatus(rs.getString("status"));
                lista.add(a);
            }

        } catch (SQLException e) {
             System.out.println("ERRO: " + e);
        }

        return lista;
    }

    // ===========================
    // READ BY ID
    // ===========================
    public AlunoDTO buscarPorId(int id) {
        String sql = "SELECT * FROM aluno WHERE id_aluno = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                AlunoDTO a = new AlunoDTO();
                a.setIdAluno(rs.getInt("id_aluno"));
                a.setNome(rs.getString("nome"));
                a.setCpf(rs.getString("cpf"));
                a.setEmail(rs.getString("email"));
                a.setTelefone(rs.getString("telefone"));
                a.setStatus(rs.getString("status"));
                return a;
            }

        } catch (SQLException e) {
             System.out.println("ERRO: " + e);
        }
        return null;
    }

    // ===========================
    // UPDATE
    // ===========================
    public boolean atualizar(AlunoDTO aluno) {
        String sql = "UPDATE aluno SET nome = ?, cpf = ?, email = ?, telefone = ?, status = ? WHERE id_aluno = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getCpf());
            ps.setString(3, aluno.getEmail());
            ps.setString(4, aluno.getTelefone());
            ps.setString(5, aluno.getStatus());
            ps.setInt(6, aluno.getIdAluno());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
           System.out.println("ERRO: " + e);
        }
        return false;
    }

    // ===========================
    // DELETE
    // ===========================
    public boolean deletar(int id) {
        String sql = "DELETE FROM aluno WHERE id_aluno = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
             System.out.println("ERRO: " + e);
        }
        return false;
    }
}
