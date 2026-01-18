package VIEW;

import DAO.AlunoDAO;
import DTO.AlunoDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class AlunoPanel extends JPanel {

    private JTable tabela;
    private DefaultTableModel model;
    private TableRowSorter<DefaultTableModel> sorter;
    private JTextField txtFiltro;

    private Dashboard dashboard;
    private AlunoDAO dao = new AlunoDAO();

    // ===========================
    // CONSTRUTOR
    // ===========================
    public AlunoPanel(Dashboard dashboard) {
        this.dashboard = dashboard;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(criarTopo(), BorderLayout.NORTH);
        add(criarTabela(), BorderLayout.CENTER);
        add(criarBotoes(), BorderLayout.SOUTH);

        carregarAlunos();
    }

    // ===========================
    // TOPO (FILTRO)
    // ===========================
    private JPanel criarTopo() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JLabel lbl = new JLabel("Pesquisar:");
        txtFiltro = new JTextField();

        panel.add(lbl, BorderLayout.WEST);
        panel.add(txtFiltro, BorderLayout.CENTER);

        txtFiltro.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
        });

        return panel;
    }

    // ===========================
    // TABELA
    // ===========================
    private JScrollPane criarTabela() {

        model = new DefaultTableModel(
                new Object[]{"ID", "Nome", "CPF", "Email", "Telefone", "Status"}, 0
        ) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela = new JTable(model);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        sorter = new TableRowSorter<>(model);
        tabela.setRowSorter(sorter);

        return new JScrollPane(tabela);
    }

    // ===========================
    // BOTÕES
    // ===========================
    private JPanel criarBotoes() {
        JPanel panel = new JPanel();

        JButton btnNovo = new JButton("Cadastrar");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");

        btnNovo.addActionListener(e -> {
            CadastroView view = new CadastroView(dashboard);
            view.setVisible(true);
            carregarAlunos();
        });

        btnEditar.addActionListener(e -> editarAluno());
        btnExcluir.addActionListener(e -> excluirAluno());

        panel.add(btnNovo);
        panel.add(btnEditar);
        panel.add(btnExcluir);

        return panel;
    }

    // ===========================
    // CARREGAR DADOS
    // ===========================
    private void carregarAlunos() {
        model.setRowCount(0);

        List<AlunoDTO> lista = dao.listarTodos();
        for (AlunoDTO a : lista) {
            model.addRow(new Object[]{
                    a.getIdAluno(),
                    a.getNome(),
                    a.getCpf(),
                    a.getEmail(),
                    a.getTelefone(),
                    a.getStatus()
            });
        }
    }

    // ===========================
    // FILTRO GLOBAL
    // ===========================
    private void filtrar() {
        String texto = txtFiltro.getText();

        if (texto.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
        }
    }

    // ===========================
    // EDITAR
    // ===========================
    private void editarAluno() {
        int row = tabela.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno!");
            return;
        }

        int id = (int) tabela.getValueAt(row, 0);

        CadastroView view = new CadastroView(dashboard, id);
        view.setVisible(true);
        carregarAlunos();
    }

    // ===========================
    // EXCLUIR
    // ===========================
    private void excluirAluno() {
        int row = tabela.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno!");
            return;
        }

        int id = (int) tabela.getValueAt(row, 0);

        int resp = JOptionPane.showConfirmDialog(
                this,
                "Deseja excluir este aluno?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (resp == JOptionPane.YES_OPTION) {
            dao.deletar(id);
            carregarAlunos();
        }
    }
}
