package UTIL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static final String URL = "jdbc:mysql://localhost:3306/sistemaescolainformatica?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // seu usuário
    private static final String PASS = ""; // sua senha

    public static Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexão realizada com sucesso!");
            return conn;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        conectar();
    }
}
