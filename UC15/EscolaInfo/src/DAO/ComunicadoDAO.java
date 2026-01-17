package DAO;

import DTO.ComunicadoDTO;
import UTIL.ConexaoDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComunicadoDAO {

    // ============================
    // CREATE
    // ============================
    public boolean inserir(ComunicadoDTO c) {
        String sql = "INSERT INTO comunicado (titulo, mensagem, data_envio) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getTitulo());
            ps.setString(2, c.getMensagem());
            ps.setDate(3, c.getDataEnvio() != null ? Date.valueOf(c.getDataEnvio()) : null);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) c.setIdComunicado(rs.getInt(1));
                return true;
            }

        } catch (SQLException e) {
            System.out.println("ERRO: " + e);
        }
        return false;
    }

    // ============================
    // READ ALL
    // ============================
    public List<ComunicadoDTO> listarTodos() {
        List<ComunicadoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM comunicado";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ComunicadoDTO c = new ComunicadoDTO();
                c.setIdComunicado(rs.getInt("id_comunicado"));
                c.setTitulo(rs.getString("titulo"));
                c.setMensagem(rs.getString("mensagem"));

                Date sqlData = rs.getDate("data_envio");
                if (sqlData != null) c.setDataEnvio(sqlData.toLocalDate());

                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("ERRO: " + e);
        }

        return lista;
    }

    // ============================
    // READ BY ID
    // ============================
    public ComunicadoDTO buscarPorId(int id) {
        String sql = "SELECT * FROM comunicado WHERE id_comunicado = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ComunicadoDTO c = new ComunicadoDTO();
                c.setIdComunicado(rs.getInt("id_comunicado"));
                c.setTitulo(rs.getString("titulo"));
                c.setMensagem(rs.getString("mensagem"));

                Date sqlData = rs.getDate("data_envio");
                if (sqlData != null) c.setDataEnvio(sqlData.toLocalDate());

                return c;
            }

        } catch (SQLException e) {
            System.out.println("ERRO: " + e);
        }
        return null;
    }

    // ============================
    // UPDATE
    // ============================
    public boolean atualizar(ComunicadoDTO c) {
        String sql = "UPDATE comunicado SET titulo = ?, mensagem = ?, data_envio = ? WHERE id_comunicado = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getTitulo());
            ps.setString(2, c.getMensagem());
            ps.setDate(3, c.getDataEnvio() != null ? Date.valueOf(c.getDataEnvio()) : null);
            ps.setInt(4, c.getIdComunicado());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("ERRO: " + e);
        }
        return false;
    }

    // ============================
    // DELETE
    // ============================
    public boolean deletar(int id) {
        String sql = "DELETE FROM comunicado WHERE id_comunicado = ?";
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
