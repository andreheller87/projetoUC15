/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
     
    String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

    conn = new conectaDAO().connectDB();

    try {
        prep = conn.prepareStatement(sql);
        prep.setString(1, produto.getNome());
        prep.setDouble(2, produto.getValor());
        prep.setString(3, produto.getStatus());

        prep.executeUpdate();

        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

    } catch (Exception erro) {
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + erro);
    }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
         String sql = "SELECT * FROM produtos";

    conn = new conectaDAO().connectDB();
    listagem = new ArrayList<>();

    try {
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();

        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();

            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getDouble("valor"));
            produto.setStatus(resultset.getString("status"));

            listagem.add(produto);
        }

    } catch (Exception erro) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro);
    }

    return listagem;
    }
    
    public void venderProduto(int idProduto) {

    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

    try {
        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement(sql);
        prep.setInt(1, idProduto);

        prep.executeUpdate();

        JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
    }
}

    
    
        
}

