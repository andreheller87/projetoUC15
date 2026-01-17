package DAO;

import DTO.CursoDTO;
import UTIL.ConexaoDB;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public boolean inserir(CursoDTO curso) {
        String sql = "INSERT INTO curso (nome, descricao, carga_horaria, valor, data_inicio, data_termino, id_professor) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, curso.getNome());
            ps.setString(2, curso.getDescricao());
            ps.setInt(3, curso.getCargaHoraria());
            ps.setDouble(4, curso.getValor());
            
            if (curso.getDataInicio() != null) {
                ps.setDate(5, Date.valueOf(curso.getDataInicio()));
            } else {
                ps.setNull(5, Types.DATE);
            }

            if (curso.getDataTermino() != null) {
                ps.setDate(6, Date.valueOf(curso.getDataTermino()));
            } else {
                ps.setNull(6, Types.DATE);
            }

            ps.setInt(7, curso.getIdProfessor());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) curso.setIdCurso(rs.getInt(1));
                return true;
            }

        } catch (SQLException e) {
            System.out.println("ERRO: " + e);
        }
        return false;
    }

    public List<CursoDTO> listarTodos() {
        List<CursoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM curso";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CursoDTO c = new CursoDTO();
                c.setIdCurso(rs.getInt("id_curso"));
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                c.setCargaHoraria(rs.getInt("carga_horaria"));
                c.setValor(rs.getDouble("valor"));

                Date sqlDataInicio = rs.getDate("data_inicio");
                if (sqlDataInicio != null) c.setDataInicio(sqlDataInicio.toLocalDate());

                Date sqlDataTermino = rs.getDate("data_termino");
                if (sqlDataTermino != null) c.setDataTermino(sqlDataTermino.toLocalDate());

                c.setIdProfessor(rs.getInt("id_professor"));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("ERRO: " + e);
        }
        return lista;
    }

    public CursoDTO buscarPorId(int id) {
        String sql = "SELECT * FROM curso WHERE id_curso = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CursoDTO c = new CursoDTO();
                c.setIdCurso(rs.getInt("id_curso"));
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                c.setCargaHoraria(rs.getInt("carga_horaria"));
                c.setValor(rs.getDouble("valor"));

                Date sqlDataInicio = rs.getDate("data_inicio");
                if (sqlDataInicio != null) c.setDataInicio(sqlDataInicio.toLocalDate());

                Date sqlDataTermino = rs.getDate("data_termino");
                if (sqlDataTermino != null) c.setDataTermino(sqlDataTermino.toLocalDate());

                c.setIdProfessor(rs.getInt("id_professor"));
                return c;
            }

        } catch (SQLException e) {
            System.out.println("ERRO: " + e);
        }
        return null;
    }

    public boolean atualizar(CursoDTO curso) {
        String sql = "UPDATE curso SET nome = ?, descricao = ?, carga_horaria = ?, valor = ?, data_inicio = ?, data_termino = ?, id_professor = ? WHERE id_curso = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, curso.getNome());
            ps.setString(2, curso.getDescricao());
            ps.setInt(3, curso.getCargaHoraria());
            ps.setDouble(4, curso.getValor());

            if (curso.getDataInicio() != null) {
                ps.setDate(5, Date.valueOf(curso.getDataInicio()));
            } else {
                ps.setNull(5, Types.DATE);
            }

            if (curso.getDataTermino() != null) {
                ps.setDate(6, Date.valueOf(curso.getDataTermino()));
            } else {
                ps.setNull(6, Types.DATE);
            }

            ps.setInt(7, curso.getIdProfessor());
            ps.setInt(8, curso.getIdCurso());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("ERRO: " + e);
        }
        return false;
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM curso WHERE id_curso = ?";
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
