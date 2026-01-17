package SERVICE;

import DAO.ProfessorDAO;
import DTO.ProfessorDTO;
import java.util.List;

public class ProfessorService {
    private ProfessorDAO dao = new ProfessorDAO();

    public boolean cadastrarProfessor(ProfessorDTO professor) {
        if (professor.getNome() == null || professor.getDisciplina() == null) {
            System.out.println("Nome e Disciplina são obrigatórios!");
            return false;
        }
        return dao.inserir(professor);
    }

    public List<ProfessorDTO> listarProfessores() {
        return dao.listarTodos();
    }

    public ProfessorDTO buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public boolean atualizarProfessor(ProfessorDTO professor) {
        return dao.atualizar(professor);
    }

    public boolean deletarProfessor(int id) {
        return dao.deletar(id);
    }
}
