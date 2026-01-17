package SERVICE;

import DAO.MatriculaDAO;
import DAO.PagamentoDAO;
import DAO.ComunicadoDAO;
import DTO.MatriculaDTO;
import DTO.PagamentoDTO;
import DTO.ComunicadoDTO;
import java.time.LocalDate;
import java.util.List;

public class MatriculaService {
    private MatriculaDAO matriculaDAO = new MatriculaDAO();
    private PagamentoDAO pagamentoDAO = new PagamentoDAO();
    private ComunicadoDAO comunicadoDAO = new ComunicadoDAO();

    public boolean matricularAluno(MatriculaDTO matricula, double valorCurso) {
        // Cria matrícula
        if (!matriculaDAO.inserir(matricula)) {
            System.out.println("Erro ao cadastrar matrícula!");
            return false;
        }

        // Cria pagamento automático
        PagamentoDTO pagamento = new PagamentoDTO();
        pagamento.setIdMatricula(matricula.getIdMatricula());
        pagamento.setValor(valorCurso);
        pagamento.setDataPagamento(LocalDate.now());
        pagamento.setStatus("Pendente");
        pagamentoDAO.inserir(pagamento);

        // Cria comunicado automático
        ComunicadoDTO comunicado = new ComunicadoDTO();
        comunicado.setTitulo("Confirmação de matrícula");
        comunicado.setMensagem("Aluno matriculado com sucesso no curso ID " + matricula.getIdCurso());
        comunicado.setDataEnvio(LocalDate.now());
        comunicadoDAO.inserir(comunicado);

        return true;
    }

    public List<MatriculaDTO> listarMatriculas() {
        return matriculaDAO.listarTodos();
    }

    public MatriculaDTO buscarPorId(int id) {
        return matriculaDAO.buscarPorId(id);
    }

    public boolean atualizarMatricula(MatriculaDTO matricula) {
        return matriculaDAO.atualizar(matricula);
    }

    public boolean deletarMatricula(int id) {
        return matriculaDAO.deletar(id);
    }
}
