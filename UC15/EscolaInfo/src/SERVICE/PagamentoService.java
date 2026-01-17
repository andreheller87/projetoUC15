package SERVICE;

import DAO.PagamentoDAO;
import DTO.PagamentoDTO;
import java.util.List;

public class PagamentoService {
    private PagamentoDAO dao = new PagamentoDAO();

    public boolean registrarPagamento(PagamentoDTO pagamento) {
        return dao.inserir(pagamento);
    }

    public List<PagamentoDTO> listarPagamentos() {
        return dao.listarTodos();
    }

    public boolean atualizarPagamento(PagamentoDTO pagamento) {
        return dao.atualizar(pagamento);
    }

    public boolean deletarPagamento(int id) {
        return dao.deletar(id);
    }
}
