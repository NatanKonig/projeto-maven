package repository;

import model.Aluno;
import model.Disciplina;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class AlunoRepository {

    private DisciplinaRepository disciplinaRepository = new DisciplinaRepository();
    private static List<Aluno> listaAlunos = new ArrayList<>();

    public List<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public Aluno consultar(int id) {
        for (Aluno aluno : listaAlunos) {
            if (aluno.getId().intValue() == id) {
                return aluno;
            }
        }
        return null;
    }

    public Aluno consultar(String nome) {
        for (Aluno aluno : listaAlunos) {
            if (aluno.getNome().equals(nome)) {
                return aluno;
            }
        }
        return null;
    }

    public int cadastrar(Aluno novo) {
        int maxId = listaAlunos.size() + 1;
        novo.setId(maxId);
        listaAlunos.add(novo);
        return novo.getId();
    }

    public Aluno atualizar(Aluno aluno) {
        listaAlunos.add(aluno);
        return aluno;
    }

    public void remover(Aluno aluno) throws Exception {
        Aluno atual = consultar(aluno.getId());
        if (atual == null) {
            throw new Exception("Aluno não encontrado");
        }
        listaAlunos.remove(atual);
    }

    public Aluno matricular(int id, Disciplina disciplina) throws Exception {
        Aluno aluno = consultar(id);
        if (aluno == null) {
            throw new Exception("Aluno não encontrado");
        }
        Disciplina disciplinaCadastrada = disciplinaRepository.consultar(disciplina.getCod());
        if (disciplinaCadastrada == null) {
        disciplina = disciplinaRepository.cadastrar(disciplina);
        } else {
            disciplina = disciplinaCadastrada;
        }
        aluno.getDisciplinas().add(disciplina);
        return aluno;
    }

    public Aluno desmatricular(int id, Disciplina disciplina) throws Exception {
        Aluno aluno = consultar(id);
        if (aluno == null) {
            throw new Exception("Aluno não encontrado");
        }
        disciplina = disciplinaRepository.consultar(disciplina.getCod());
        if (disciplina == null) {
            throw new Exception("Disciplina não encontrada");
        }
        for (Aluno al:  listaAlunos) {
            if (al.getDisciplinas().contains(disciplina)) {
                aluno.getDisciplinas().remove(disciplina);
                return al;
            }
        }
        throw new Exception("O aluno não se encontra na disciplina informada");
    }

    public List<Aluno> obterAlunosPorDisciplina(Disciplina disciplina) {
        List<Aluno> alunosNaDisciplina = new ArrayList<>();

        for (Aluno aluno : listaAlunos) {
            for (Disciplina disc : aluno.getDisciplinas()) {
                if (disc.getCod().equals(disciplina.getCod())) {
                    alunosNaDisciplina.add(aluno);
                    break;
                }
            }
        }
        return alunosNaDisciplina;
    }
}
