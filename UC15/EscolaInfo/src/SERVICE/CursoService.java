package SERVICE;

import DAO.CursoDAO;
import DTO.CursoDTO;
import java.util.List;

public class CursoService {
    private CursoDAO dao = new CursoDAO();

    public boolean cadastrarCurso(CursoDTO curso) {
        if (curso.getNome() == null || curso.getIdProfessor() == null) {
            System.out.println("Nome e Professor são obrigatórios!");
            return false;
        }
        return dao.inserir(curso);
    }

    public List<CursoDTO> listarCursos() {
        return dao.listarTodos();
    }

    public CursoDTO buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public boolean atualizarCurso(CursoDTO curso) {
        return dao.atualizar(curso);
    }

    public boolean deletarCurso(int id) {
        return dao.deletar(id);
    }
}
