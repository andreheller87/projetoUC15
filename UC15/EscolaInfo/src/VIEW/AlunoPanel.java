package VIEW;

import SERVICE.AlunoService;
import DTO.AlunoDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AlunoPanel extends JPanel {

    private JTable tabela;
    private DefaultTableModel model;
    private AlunoService service = new AlunoService();
    private Dashboard dashboard;

    public AlunoPanel(Dashboard dashboard) {
        this.dashboard = dashboard;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(criarTopo(), BorderLayout.NORTH);
        add(criarTabela(), BorderLayout.CENTER);

        carregarAlunos();
    }

    // ======================
    // TOPO (BOTÕES)
    // ======================
    private JPanel criarTopo() {

        JButton btnVoltar = new JButton("← Voltar");
        JButton btnCadastrar = new JButton("Cadastrar Aluno");

        btnVoltar.setFocusPainted(false);
        btnCadastrar.setFocusPainted(false);

        btnVoltar.addActionListener(e -> dashboard.mostrarDashboard());

        btnCadastrar.addActionListener(e -> {
            new CadastroView(dashboard).setVisible(true);
        });

        JPanel topo = new JPanel(new BorderLayout());
        topo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        topo.add(btnVoltar, BorderLayout.WEST);
        topo.add(btnCadastrar, BorderLayout.EAST);

        return topo;
    }

    // ======================
    // TABELA
    // ======================
    private JScrollPane criarTabela() {

        model = new DefaultTableModel(
            new Object[]{"ID", "Nome", "CPF", "Email", "Telefone", "Status"}, 0
        );

        tabela = new JTable(model);
        tabela.setRowHeight(25);

        return new JScrollPane(tabela);
    }

    // ======================
    // DADOS
    // ======================
    private void carregarAlunos() {
        model.setRowCount(0);

        List<AlunoDTO> alunos = service.listarAlunos();
        for (AlunoDTO a : alunos) {
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
}
