package repository;

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

    public Disciplina cadastrar(Disciplina nova) throws Exception {
        if (consultar(nova.getCod()) == null) {
            int maxId = listaDisciplinas.size() + 1;
            nova.setCod(maxId);
            listaDisciplinas.add(nova);
            return nova;
        }
        return consultar(nova.getCod());
    }
}
