package repository;

import model.Aluno;
import model.Disciplina;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class DisciplinaRepository {

    private static List<Disciplina> listaDisciplinas = new ArrayList<>();

    public List<Disciplina> getListaDisciplinas() {
        return listaDisciplinas;
    }

    public Disciplina consultar(int cod) {
        for (Disciplina disciplina : listaDisciplinas) {
            if (disciplina.getCod().intValue() == cod) {
                return disciplina;
            }
        }
        return null;
    }

    public int cadastrar(Disciplina nova) {
        int maxId = listaDisciplinas.size() + 1;
        nova.setCod(maxId);
        listaDisciplinas.add(nova);
        return nova.getCod();
    }

    public void remover(Disciplina disciplina) throws Exception {
        Disciplina atual = consultar(disciplina.getCod());
        if (atual == null) {
            throw new Exception("Disciplina não encontrado");
        }
        listaDisciplinas.remove(atual);
    }

}
