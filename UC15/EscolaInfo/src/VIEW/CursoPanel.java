package VIEW;

import SERVICE.CursoService;
import DTO.CursoDTO;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class CursoPanel extends JPanel {

    private JTable tabela;
    private DefaultTableModel model;
    private CursoService service = new CursoService();
    private Dashboard dashboard;
    private JTextField txtFiltro;
    private TableRowSorter<DefaultTableModel> sorter;

    public CursoPanel(Dashboard dashboard) {
        this.dashboard = dashboard;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(criarTopo(), BorderLayout.NORTH);
        add(criarTabela(), BorderLayout.CENTER);

        carregarCursos();
    }

    // ======================
    // TOPO
    // ======================
    private JPanel criarTopo() {

        JButton btnVoltar = new JButton("← Voltar");
        JButton btnCadastrar = new JButton("Cadastrar Curso");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");

        btnVoltar.addActionListener(e -> dashboard.mostrarDashboard());

        btnCadastrar.addActionListener(e -> {
            new CadastroCursoView(dashboard).setVisible(true);
        });

        btnEditar.addActionListener(e -> editarCurso());
        btnExcluir.addActionListener(e -> excluirCurso());

        txtFiltro = new JTextField(20);
        txtFiltro.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filtrar(); }
            public void removeUpdate(DocumentEvent e) { filtrar(); }
            public void changedUpdate(DocumentEvent e) { filtrar(); }
        });

        JPanel esquerda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        esquerda.add(btnVoltar);

        JPanel centro = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centro.add(new JLabel("Pesquisar:"));
        centro.add(txtFiltro);

        JPanel direita = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        direita.add(btnCadastrar);
        direita.add(btnEditar);
        direita.add(btnExcluir);

        JPanel topo = new JPanel(new BorderLayout());
        topo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topo.add(esquerda, BorderLayout.WEST);
        topo.add(centro, BorderLayout.CENTER);
        topo.add(direita, BorderLayout.EAST);

        return topo;
    }

    // ======================
    // TABELA
    // ======================
    private JScrollPane criarTabela() {

        model = new DefaultTableModel(
            new Object[]{"ID", "Nome", "Descrição", "Carga Horária", "Valor", "Início", "Término", "Professor"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela = new JTable(model);
        tabela.setRowHeight(25);

        sorter = new TableRowSorter<>(model);
        tabela.setRowSorter(sorter);

        return new JScrollPane(tabela);
    }

    // ======================
    // DADOS
    // ======================
    private void carregarCursos() {
        model.setRowCount(0);

        List<CursoDTO> cursos = service.listarCursos();
        for (CursoDTO c : cursos) {
            model.addRow(new Object[]{
                c.getIdCurso(),
                c.getNome(),
                c.getDescricao(),
                c.getCargaHoraria(),
                c.getValor(),
                c.getDataInicio(),
                c.getDataTermino(),
                c.getIdProfessor()
            });
        }
    }

    // ======================
    // AÇÕES
    // ======================
    private void editarCurso() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um curso para editar.");
            return;
        }

        int id = (int) tabela.getValueAt(linha, 0);
        new CadastroCursoView(dashboard, id).setVisible(true);
    }

    private void excluirCurso() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um curso para excluir.");
            return;
        }

        int id = (int) tabela.getValueAt(linha, 0);
        int opcao = JOptionPane.showConfirmDialog(
            this,
            "Tem certeza que deseja excluir este curso?",
            "Confirmação",
            JOptionPane.YES_NO_OPTION
        );

        if (opcao == JOptionPane.YES_OPTION) {
            if (service.deletarCurso(id)) {
                JOptionPane.showMessageDialog(this, "Curso excluído com sucesso!");
                carregarCursos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir curso!");
            }
        }
    }

    private void filtrar() {
        String texto = txtFiltro.getText();
        if (texto.trim().isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
        }
    }
}
