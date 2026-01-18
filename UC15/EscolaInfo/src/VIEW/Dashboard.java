package VIEW;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {
       setTitle("Dashboard - Sistema Escola Informática");

    setExtendedState(JFrame.MAXIMIZED_BOTH); // abre em tela cheia
    setMinimumSize(new Dimension(900, 600)); // tamanho mínimo
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setLayout(new BorderLayout());

        // ======================
        // HEADER (IMAGEM TOPO)
        // ======================
        // ======================
// HEADER (IMAGEM TOPO ESTICÁVEL)
// ======================
        JPanel header = new JPanel() {

            Image image;

            {
                setPreferredSize(new Dimension(0, 200));

                java.net.URL imgURL = getClass().getResource("/IMG/escola principal.jpg");
                if (imgURL != null) {
                    image = new ImageIcon(imgURL).getImage();
                } else {
                    System.out.println("Imagem não encontrada!");
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (image != null) {
                    g.drawImage(
                            image,
                            0,
                            0,
                            getWidth(), // largura dinâmica
                            getHeight(), // altura fixa (200)
                            this
                    );
                }
            }
        };

        add(header, BorderLayout.NORTH);

        // ======================
        // SIDEBAR
        // ======================
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setBackground(new Color(52, 58, 64)); // #343a40
        sidebar.setLayout(new GridLayout(6, 1, 0, 10));

        sidebar.add(criarBotaoMenu("Alunos"));
        sidebar.add(criarBotaoMenu("Cursos"));
        sidebar.add(criarBotaoMenu("Pagamentos"));
        sidebar.add(criarBotaoMenu("Comunicados"));
        sidebar.add(criarBotaoMenu("Perfil"));

        add(sidebar, BorderLayout.WEST);

        // ======================
        // ÁREA PRINCIPAL
        // ======================
        JPanel main = new JPanel();
        main.setBackground(new Color(245, 245, 245));
        main.setLayout(new GridLayout(1, 3, 20, 20));
        main.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        main.add(criarCard("Total de Alunos", "50"));
        main.add(criarCard("Total de Cursos", "5"));
        main.add(criarCard("Pagamentos Pendentes", "3"));

        add(main, BorderLayout.CENTER);
    }

    // ======================
    // BOTÃO DO MENU
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
    // CARD
    // ======================
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
}
