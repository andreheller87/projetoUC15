package MAIN;

import VIEW.Dashboard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JButton btnLogin;
    private JButton btnCancelar;

    public Login() {
        setTitle("Login - Sistema Escola Informática");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(null); // Layout absoluto
        getContentPane().add(panel);

        // Labels e campos
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 50, 80, 25);
        panel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(140, 50, 400, 25);
        panel.add(txtEmail);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(50, 90, 80, 25);
        panel.add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(140, 90, 400, 25);
        panel.add(txtSenha);

        // Botões
        btnLogin = new JButton("Login");
        btnLogin.setBounds(140, 140, 180, 30);
        panel.add(btnLogin);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(360, 140, 180, 30);
        panel.add(btnCancelar);

        // Adicionar imagem no final (últimos 200px)
        JLabel lblImagem = new JLabel();
        lblImagem.setBounds(0, 200, 600, 200);
        lblImagem.setOpaque(true); // ajuda a visualizar
        lblImagem.setBackground(Color.LIGHT_GRAY);

        java.net.URL imgURL = getClass().getResource("/IMG/escola login.jpg");

        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image img = icon.getImage().getScaledInstance(
                    600, 200, Image.SCALE_SMOOTH
            );
            lblImagem.setIcon(new ImageIcon(img));
        } else {
            System.out.println("Imagem NÃO encontrada!");
        }

panel.add(lblImagem);

        // Ações dos botões
        btnLogin.addActionListener(e -> efetuarLogin());
        btnCancelar.addActionListener(e -> System.exit(0));
    }

    private void efetuarLogin() {
      String email = txtEmail.getText();
      String senha = new String(txtSenha.getPassword());

      if (email.equals("") && senha.equals("1234")) {
          JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");

          // Abre a Dashboard
          Dashboard dashboard = new Dashboard();
          dashboard.setVisible(true);

          // Fecha a tela de login
          this.dispose();

      } else {
          JOptionPane.showMessageDialog(
              this,
              "Email ou senha incorretos.",
              "Erro",
              JOptionPane.ERROR_MESSAGE
          );
      }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}
