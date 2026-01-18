package VIEW;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    private JPanel mainPanel;

    public Dashboard() {
        setTitle("Dashboard - Sistema Escola Informática");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ======================
        // HEADER (IMAGEM)
        // ======================
        JPanel header = new JPanel() {
            Image image;

            {
                setPreferredSize(new Dimension(0, 180));
                var imgURL = getClass().getResource("/IMG/escola principal.jpg");
                if (imgURL != null) {
                    image = new ImageIcon(imgURL).getImage();
                } else {
                    System.out.println("Imagem NÃO encontrada!");
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        add(header, BorderLayout.NORTH);

        // ======================
        // SIDEBAR
        // ======================
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setBackground(new Color(52, 58, 64));
        sidebar.setLayout(new GridLayout(8, 1, 0, 10));

        JButton btnDashboard = criarBotaoMenu("Dashboard");
        JButton btnAlunos = criarBotaoMenu("Alunos");
        JButton btnCursos = criarBotaoMenu("Cursos");
        JButton btnPagamentos = criarBotaoMenu("Pagamentos");
        JButton btnComunicados = criarBotaoMenu("Comunicados");
        JButton btnPerfil = criarBotaoMenu("Perfil");

        sidebar.add(btnDashboard);
        sidebar.add(btnAlunos);
        sidebar.add(btnCursos);
        sidebar.add(btnPagamentos);
        sidebar.add(btnComunicados);
        sidebar.add(btnPerfil);

        add(sidebar, BorderLayout.WEST);

        // ======================
        // MAIN PANEL (DINÂMICO)
        // ======================
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);

        mostrarDashboard(); // tela inicial

        // ======================
        // AÇÕES DOS BOTÕES
        // ======================
        btnDashboard.addActionListener(e -> mostrarDashboard());
        btnAlunos.addActionListener(e -> mostrarAlunos());
        btnCursos.addActionListener(e -> mostrarCursos());
        // adicionar ações para os outros botões conforme necessidade
    }

    // ======================
    // TELAS DINÂMICAS
    // ======================
    public void mostrarDashboard() {
        mainPanel.removeAll();
        mainPanel.add(criarDashboardCards(), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void mostrarAlunos() {
        mainPanel.removeAll();
        mainPanel.add(new AlunoPanel(this), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void mostrarCursos() {
        mainPanel.removeAll();
        mainPanel.add(new CursoPanel(this), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // ======================
    // BOTÃO MENU
    // ======================
    private JButton criarBotaoMenu(String texto) {
        JButton btn = new JButton(texto);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(52, 58, 64));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        return btn;
    }

    // ======================
    // DASHBOARD CARDS
    // ======================
    private JPanel criarDashboardCards() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 20, 20));
        panel.setBackground(new Color(245, 245, 245));

        panel.add(criarCard("Total de Alunos", "50"));
        panel.add(criarCard("Total de Cursos", "5"));
        panel.add(criarCard("Pagamentos Pendentes", "3"));

        return panel;
    }

    private JPanel criarCard(String titulo, String valor) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Arial", Font.BOLD, 28));
        lblValor.setForeground(new Color(33, 150, 243));

        card.add(lblTitulo, BorderLayout.NORTH);
        card.add(lblValor, BorderLayout.CENTER);

        return card;
    }

    // ======================
    // MAIN
    // ======================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Dashboard dash = new Dashboard();
            dash.setVisible(true);
        });
    }
}
