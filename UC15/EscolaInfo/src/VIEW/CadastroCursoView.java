package VIEW;

import DTO.CursoDTO;
import SERVICE.CursoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CadastroCursoView extends JDialog {

    private Dashboard dashboard;
    private Integer idCurso = null;
    private CursoService service = new CursoService();

    private JTextField txtNome;
    private JTextField txtDescricao;
    private JTextField txtCargaHoraria;
    private JTextField txtValor;
    private JTextField txtDataInicio;
    private JTextField txtDataTermino;
    private JTextField txtIdProfessor;

    private JButton btnSalvar;
    private JButton btnCancelar;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public CadastroCursoView(Dashboard dashboard) {
        this.dashboard = dashboard;
        inicializarComponentes();
        setTitle("Cadastrar Curso");
    }

    public CadastroCursoView(Dashboard dashboard, int idCurso) {
        this.dashboard = dashboard;
        this.idCurso = idCurso;
        inicializarComponentes();
        setTitle("Editar Curso");
        carregarCurso(idCurso);
    }

    private void inicializarComponentes() {
        setModal(true);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(9, 2, 5, 5));

        txtNome = new JTextField();
        txtDescricao = new JTextField();
        txtCargaHoraria = new JTextField();
        txtValor = new JTextField();
        txtDataInicio = new JTextField();
        txtDataTermino = new JTextField();
        txtIdProfessor = new JTextField();

        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        add(new JLabel("Nome:")); add(txtNome);
        add(new JLabel("Descrição:")); add(txtDescricao);
        add(new JLabel("Carga Horária:")); add(txtCargaHoraria);
        add(new JLabel("Valor:")); add(txtValor);
        add(new JLabel("Data Início (yyyy-MM-dd):")); add(txtDataInicio);
        add(new JLabel("Data Término (yyyy-MM-dd):")); add(txtDataTermino);
        add(new JLabel("ID Professor:")); add(txtIdProfessor);

        add(btnSalvar); add(btnCancelar);

        btnSalvar.addActionListener(e -> salvar());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void carregarCurso(int id) {
        CursoDTO c = service.buscarPorId(id);
        if (c != null) {
            txtNome.setText(c.getNome());
            txtDescricao.setText(c.getDescricao());
            txtCargaHoraria.setText(c.getCargaHoraria() != null ? c.getCargaHoraria().toString() : "");
            txtValor.setText(c.getValor() != null ? c.getValor().toString() : "");
            txtDataInicio.setText(c.getDataInicio() != null ? c.getDataInicio().format(formatter) : "");
            txtDataTermino.setText(c.getDataTermino() != null ? c.getDataTermino().format(formatter) : "");
            txtIdProfessor.setText(c.getIdProfessor() != null ? c.getIdProfessor().toString() : "");
        }
    }

    private void salvar() {
        try {
            CursoDTO c = new CursoDTO();

            if (idCurso != null) {
                c.setIdCurso(idCurso);
            }

            c.setNome(txtNome.getText().trim());
            c.setDescricao(txtDescricao.getText().trim());
            c.setCargaHoraria(!txtCargaHoraria.getText().isEmpty() ? Integer.parseInt(txtCargaHoraria.getText()) : null);
            c.setValor(!txtValor.getText().isEmpty() ? Double.parseDouble(txtValor.getText()) : null);
            c.setDataInicio(!txtDataInicio.getText().isEmpty() ? LocalDate.parse(txtDataInicio.getText(), formatter) : null);
            c.setDataTermino(!txtDataTermino.getText().isEmpty() ? LocalDate.parse(txtDataTermino.getText(), formatter) : null);
            c.setIdProfessor(!txtIdProfessor.getText().isEmpty() ? Integer.parseInt(txtIdProfessor.getText()) : null);

            boolean sucesso;
            if (idCurso != null) {
                sucesso = service.atualizarCurso(c);
            } else {
                sucesso = service.cadastrarCurso(c);
            }

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Curso salvo com sucesso!");
                dashboard.mostrarCursos();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao salvar curso.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}