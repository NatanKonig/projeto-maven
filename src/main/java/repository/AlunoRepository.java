package repository;

import model.Aluno;
import model.Disciplina;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class AlunoRepository {

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

    public int matricular(Aluno aluno, Disciplina disciplina) {
        aluno.addDisciplina(disciplina);
        return aluno.getId();
    }

    public void desmatricular(Aluno aluno, Disciplina disciplina) {
        aluno.remDisciplina(disciplina);
    }
}
