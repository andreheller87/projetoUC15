package SERVICE;

import DAO.ComunicadoDAO;
import DTO.ComunicadoDTO;
import java.util.List;

public class ComunicadoService {
    private ComunicadoDAO dao = new ComunicadoDAO();

    public boolean enviarComunicado(ComunicadoDTO comunicado) {
        return dao.inserir(comunicado);
    }

    public List<ComunicadoDTO> listarComunicados() {
        return dao.listarTodos();
    }

    public boolean atualizarComunicado(ComunicadoDTO comunicado) {
        return dao.atualizar(comunicado);
    }

    public boolean deletarComunicado(int id) {
        return dao.deletar(id);
    }
}
