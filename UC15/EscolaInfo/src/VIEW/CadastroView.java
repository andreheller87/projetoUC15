package VIEW;

import DAO.AlunoDAO;
import DTO.AlunoDTO;

import javax.swing.*;
import java.awt.*;

public class CadastroView extends JDialog {

    private Dashboard dashboard;
    private AlunoDAO dao = new AlunoDAO();
    private AlunoDTO aluno;

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JComboBox<String> cbStatus;

    // ===========================
    // CADASTRO
    // ===========================
    public CadastroView(Dashboard dashboard) {
        this(dashboard, null);
    }

    // ===========================
    // EDIÇÃO
    // ===========================
    public CadastroView(Dashboard dashboard, Integer idAluno) {
        super(dashboard, "Cadastro de Aluno", true);
        this.dashboard = dashboard;

        setSize(500, 400);
        setLocationRelativeTo(dashboard);
        setLayout(new BorderLayout());

        add(criarFormulario(), BorderLayout.CENTER);
        add(criarBotoes(), BorderLayout.SOUTH);

        if (idAluno != null) {
            aluno = dao.buscarPorId(idAluno);
            preencherCampos();
        } else {
            aluno = new AlunoDTO();
        }
    }

    // ===========================
    // FORMULÁRIO
    // ===========================
    private JPanel criarFormulario() {
        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txtNome = new JTextField();
        txtCpf = new JTextField();
        txtEmail = new JTextField();
        txtTelefone = new JTextField();
        cbStatus = new JComboBox<>(new String[]{"ATIVO", "INATIVO"});

        form.add(new JLabel("Nome:"));
        form.add(txtNome);

        form.add(new JLabel("CPF:"));
        form.add(txtCpf);

        form.add(new JLabel("Email:"));
        form.add(txtEmail);

        form.add(new JLabel("Telefone:"));
        form.add(txtTelefone);

        form.add(new JLabel("Status:"));
        form.add(cbStatus);

        return form;
    }

    // ===========================
    // BOTÕES
    // ===========================
    private JPanel criarBotoes() {
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnCancelar.addActionListener(e -> dispose());
        btnSalvar.addActionListener(e -> salvar());

        JPanel panel = new JPanel();
        panel.add(btnSalvar);
        panel.add(btnCancelar);

        return panel;
    }

    // ===========================
    // SALVAR
    // ===========================
   private void salvar() {

    aluno.setNome(txtNome.getText());
    aluno.setCpf(txtCpf.getText());
    aluno.setEmail(txtEmail.getText());
    aluno.setTelefone(txtTelefone.getText());
    aluno.setStatus(cbStatus.getSelectedItem().toString());

    boolean sucesso;

    // ✅ CORREÇÃO AQUI
    if (aluno.getIdAluno() == null) {
        sucesso = dao.inserir(aluno);   // CADASTRO
    } else {
        sucesso = dao.atualizar(aluno); // EDIÇÃO
    }

    if (sucesso) {
        JOptionPane.showMessageDialog(this, "Aluno salvo com sucesso!");
        dispose();
        dashboard.mostrarAlunos();
    } else {
        JOptionPane.showMessageDialog(this, "Erro ao salvar aluno!");
    }
}


    // ===========================
    // PREENCHER
    // ===========================
    private void preencherCampos() {
        txtNome.setText(aluno.getNome());
        txtCpf.setText(aluno.getCpf());
        txtEmail.setText(aluno.getEmail());
        txtTelefone.setText(aluno.getTelefone());
        cbStatus.setSelectedItem(aluno.getStatus());
    }
}
