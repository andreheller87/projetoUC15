package DAO;

import DTO.ProfessorDTO;
import UTIL.ConexaoDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProfessorDAO {

    public boolean inserir(ProfessorDTO professor) {
        String sql = "INSERT INTO professor (nome, disciplina, horario_disponivel, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getDisciplina());
            ps.setString(3, professor.getHorarioDisponivel());
            ps.setString(4, professor.getEmail());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    professor.setIdProfessor(rs.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("ERRO: " + e);
        }
        return false;
    }

    public List<ProfessorDTO> listarTodos() {
        List<ProfessorDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM professor";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProfessorDTO p = new ProfessorDTO();
                p.setIdProfessor(rs.getInt("id_professor"));
                p.setNome(rs.getString("nome"));
                p.setDisciplina(rs.getString("disciplina"));
                p.setHorarioDisponivel(rs.getString("horario_disponivel"));
                p.setEmail(rs.getString("email"));
                lista.add(p);
            }

        } catch (SQLException e) {
             System.out.println("ERRO: " + e);
        }
        return lista;
    }

    public ProfessorDTO buscarPorId(int id) {
        String sql = "SELECT * FROM professor WHERE id_professor = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ProfessorDTO p = new ProfessorDTO();
                p.setIdProfessor(rs.getInt("id_professor"));
                p.setNome(rs.getString("nome"));
                p.setDisciplina(rs.getString("disciplina"));
                p.setHorarioDisponivel(rs.getString("horario_disponivel"));
                p.setEmail(rs.getString("email"));
                return p;
            }

        } catch (SQLException e) {
           System.out.println("ERRO: " + e);
        }
        return null;
    }

    public boolean atualizar(ProfessorDTO professor) {
        String sql = "UPDATE professor SET nome = ?, disciplina = ?, horario_disponivel = ?, email = ? WHERE id_professor = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getDisciplina());
            ps.setString(3, professor.getHorarioDisponivel());
            ps.setString(4, professor.getEmail());
            ps.setInt(5, professor.getIdProfessor());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
             System.out.println("ERRO: " + e);
        }
        return false;
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM professor WHERE id_professor = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("ERRO: " + e);
        }
        return false;
    }
}
