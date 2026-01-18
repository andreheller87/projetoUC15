package VIEW;

import javax.swing.*;
import java.awt.*;

public class CadastroView extends JDialog {

    private Dashboard dashboard;

    public CadastroView(Dashboard dashboard) {
        super(dashboard, "Cadastrar Aluno", true);
        this.dashboard = dashboard;

        setSize(500, 400);
        setLocationRelativeTo(dashboard);
        setLayout(new BorderLayout());

        add(criarFormulario(), BorderLayout.CENTER);
        add(criarBotoes(), BorderLayout.SOUTH);
    }

    private JPanel criarFormulario() {

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        form.add(new JLabel("Nome:"));
        form.add(new JTextField());

        form.add(new JLabel("CPF:"));
        form.add(new JTextField());

        form.add(new JLabel("Email:"));
        form.add(new JTextField());

        form.add(new JLabel("Telefone:"));
        form.add(new JTextField());

        form.add(new JLabel("Status:"));
        form.add(new JComboBox<>(new String[]{"ATIVO", "INATIVO"}));

        return form;
    }

    private JPanel criarBotoes() {

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnCancelar.addActionListener(e -> dispose());

        btnSalvar.addActionListener(e -> {
            // aqui depois entra o service.salvar()
            JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!");
            dispose();
            dashboard.mostrarAlunos();
        });

        JPanel panel = new JPanel();
        panel.add(btnSalvar);
        panel.add(btnCancelar);

        return panel;
    }
}
