import com.google.gson.Gson;
import model.Disciplina;
import org.junit.Assert;
import org.junit.Test;
import repository.DisciplinaRepository;

public class DisciplinaTest {

    private DisciplinaRepository disciplinaRepository = new DisciplinaRepository();
    private Gson gson = new Gson();

    @Test
    public void cadastrar() throws Exception{
        Disciplina disciplina = new Disciplina();
        disciplina.setCod(1);
        disciplina.setDescricao("Descricao 1");
        disciplina.setProfessor("Professor 1");
        disciplinaRepository.cadastrar(disciplina);
        System.out.println("Cod registrado: " + disciplina.getCod());
        Assert.assertTrue(disciplina.getCod() > 0);
    }

    @Test
    public void consultar() throws Exception{
        cadastrar();
        Disciplina disciplina = disciplinaRepository.consultar(1);
        System.out.println(gson.toJson(disciplina));
        Assert.assertTrue(disciplina != null);
    }
}
