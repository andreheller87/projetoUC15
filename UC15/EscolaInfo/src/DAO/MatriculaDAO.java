package DAO;

import DTO.MatriculaDTO;
import UTIL.ConexaoDB;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {

    public boolean inserir(MatriculaDTO m) {
        String sql = "INSERT INTO matricula (id_aluno, id_curso, data_matricula, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, m.getIdAluno());
            ps.setInt(2, m.getIdCurso());

            if (m.getDataMatricula() != null) {
                ps.setDate(3, Date.valueOf(m.getDataMatricula()));
            } else {
                ps.setNull(3, Types.DATE);
            }

            ps.setString(4, m.getStatus());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) m.setIdMatricula(rs.getInt(1));
                return true;
            }

        } catch (SQLException e) {
            System.out.println("ERRO: " + e);
        }
        return false;
    }

    public List<MatriculaDTO> listarTodos() {
        List<MatriculaDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM matricula";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MatriculaDTO m = new MatriculaDTO();
                m.setIdMatricula(rs.getInt("id_matricula"));
                m.setIdAluno(rs.getInt("id_aluno"));
                m.setIdCurso(rs.getInt("id_curso"));

                Date sqlDate = rs.getDate("data_matricula");
                if (sqlDate != null) m.setDataMatricula(sqlDate.toLocalDate());

                m.setStatus(rs.getString("status"));
                lista.add(m);
            }

        } catch (SQLException e) {
            System.out.println("ERRO: " + e);
        }
        return lista;
    }

    public MatriculaDTO buscarPorId(int id) {
        String sql = "SELECT * FROM matricula WHERE id_matricula = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                MatriculaDTO m = new MatriculaDTO();
                m.setIdMatricula(rs.getInt("id_matricula"));
                m.setIdAluno(rs.getInt("id_aluno"));
                m.setIdCurso(rs.getInt("id_curso"));

                Date sqlDate = rs.getDate("data_matricula");
                if (sqlDate != null) m.setDataMatricula(sqlDate.toLocalDate());

                m.setStatus(rs.getString("status"));
                return m;
            }

        } catch (SQLException e) {
            System.out.println("ERRO: " + e);
        }
        return null;
    }

    public boolean atualizar(MatriculaDTO m) {
        String sql = "UPDATE matricula SET id_aluno = ?, id_curso = ?, data_matricula = ?, status = ? WHERE id_matricula = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, m.getIdAluno());
            ps.setInt(2, m.getIdCurso());

            if (m.getDataMatricula() != null) {
                ps.setDate(3, Date.valueOf(m.getDataMatricula()));
            } else {
                ps.setNull(3, Types.DATE);
            }

            ps.setString(4, m.getStatus());
            ps.setInt(5, m.getIdMatricula());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("ERRO: " + e);
        }
        return false;
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM matricula WHERE id_matricula = ?";
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
