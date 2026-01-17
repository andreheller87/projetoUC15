package SERVICE;

import DAO.AlunoDAO;
import DTO.AlunoDTO;
import java.util.List;

public class AlunoService {
    private AlunoDAO dao = new AlunoDAO();

    public boolean cadastrarAluno(AlunoDTO aluno) {
        // Regras de negócio podem ser inseridas aqui
        if (aluno.getNome() == null || aluno.getCpf() == null) {
            System.out.println("Nome e CPF são obrigatórios!");
            return false;
        }
        return dao.inserir(aluno);
    }

    public List<AlunoDTO> listarAlunos() {
        return dao.listarTodos();
    }

    public AlunoDTO buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public boolean atualizarAluno(AlunoDTO aluno) {
        return dao.atualizar(aluno);
    }

    public boolean deletarAluno(int id) {
        return dao.deletar(id);
    }
}
