package DAO;

import DTO.PagamentoDTO;
import UTIL.ConexaoDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {

    // ============================
    // CREATE
    // ============================
    public boolean inserir(PagamentoDTO p) {
        String sql = "INSERT INTO pagamento (id_matricula, valor, data_pagamento, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, p.getIdMatricula());
            ps.setDouble(2, p.getValor());
            ps.setDate(3, p.getDataPagamento() != null ? Date.valueOf(p.getDataPagamento()) : null);
            ps.setString(4, p.getStatus());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) p.setIdPagamento(rs.getInt(1));
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
    public List<PagamentoDTO> listarTodos() {
        List<PagamentoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM pagamento";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PagamentoDTO p = new PagamentoDTO();
                p.setIdPagamento(rs.getInt("id_pagamento"));
                p.setIdMatricula(rs.getInt("id_matricula"));
                p.setValor(rs.getDouble("valor"));

                Date sqlData = rs.getDate("data_pagamento");
                if (sqlData != null) p.setDataPagamento(sqlData.toLocalDate());

                p.setStatus(rs.getString("status"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("ERRO: " + e);
        }

        return lista;
    }

    // ============================
    // READ BY ID
    // ============================
    public PagamentoDTO buscarPorId(int id) {
        String sql = "SELECT * FROM pagamento WHERE id_pagamento = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PagamentoDTO p = new PagamentoDTO();
                p.setIdPagamento(rs.getInt("id_pagamento"));
                p.setIdMatricula(rs.getInt("id_matricula"));
                p.setValor(rs.getDouble("valor"));

                Date sqlData = rs.getDate("data_pagamento");
                if (sqlData != null) p.setDataPagamento(sqlData.toLocalDate());

                p.setStatus(rs.getString("status"));
                return p;
            }

        } catch (SQLException e) {
            System.out.println("ERRO: " + e);
        }
        return null;
    }

    // ============================
    // UPDATE
    // ============================
    public boolean atualizar(PagamentoDTO p) {
        String sql = "UPDATE pagamento SET id_matricula = ?, valor = ?, data_pagamento = ?, status = ? WHERE id_pagamento = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getIdMatricula());
            ps.setDouble(2, p.getValor());
            ps.setDate(3, p.getDataPagamento() != null ? Date.valueOf(p.getDataPagamento()) : null);
            ps.setString(4, p.getStatus());
            ps.setInt(5, p.getIdPagamento());

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
        String sql = "DELETE FROM pagamento WHERE id_pagamento = ?";
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
