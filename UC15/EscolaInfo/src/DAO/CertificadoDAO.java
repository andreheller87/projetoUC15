package DAO;

import DTO.CertificadoDTO;
import UTIL.ConexaoDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CertificadoDAO {

    // ============================
    // CREATE
    // ============================
    public boolean inserir(CertificadoDTO c) {
        String sql = "INSERT INTO certificado (id_matricula, data_emissao, codigo_verificacao) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, c.getIdMatricula());
            ps.setDate(2, c.getDataEmissao() != null ? Date.valueOf(c.getDataEmissao()) : null);
            ps.setString(3, c.getCodigoVerificacao());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) c.setIdCertificado(rs.getInt(1));
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
    public List<CertificadoDTO> listarTodos() {
        List<CertificadoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM certificado";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CertificadoDTO c = new CertificadoDTO();
                c.setIdCertificado(rs.getInt("id_certificado"));
                c.setIdMatricula(rs.getInt("id_matricula"));

                Date sqlData = rs.getDate("data_emissao");
                if (sqlData != null) c.setDataEmissao(sqlData.toLocalDate());

                c.setCodigoVerificacao(rs.getString("codigo_verificacao"));
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
    public CertificadoDTO buscarPorId(int id) {
        String sql = "SELECT * FROM certificado WHERE id_certificado = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                CertificadoDTO c = new CertificadoDTO();
                c.setIdCertificado(rs.getInt("id_certificado"));
                c.setIdMatricula(rs.getInt("id_matricula"));

                Date sqlData = rs.getDate("data_emissao");
                if (sqlData != null) c.setDataEmissao(sqlData.toLocalDate());

                c.setCodigoVerificacao(rs.getString("codigo_verificacao"));
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
    public boolean atualizar(CertificadoDTO c) {
        String sql = "UPDATE certificado SET id_matricula = ?, data_emissao = ?, codigo_verificacao = ? WHERE id_certificado = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getIdMatricula());
            ps.setDate(2, c.getDataEmissao() != null ? Date.valueOf(c.getDataEmissao()) : null);
            ps.setString(3, c.getCodigoVerificacao());
            ps.setInt(4, c.getIdCertificado());

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
        String sql = "DELETE FROM certificado WHERE id_certificado = ?";
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
