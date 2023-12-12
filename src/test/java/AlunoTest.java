import com.google.gson.Gson;
import model.Aluno;
import model.Disciplina;
import model.Endereco;
import org.junit.Assert;
import org.junit.Test;
import repository.AlunoRepository;
import repository.DisciplinaRepository;

import java.util.List;

public class AlunoTest {

    private AlunoRepository alunoRepository = new AlunoRepository();
    private DisciplinaRepository disciplinaRepository = new DisciplinaRepository();
    private DisciplinaTest disciplinaTest = new DisciplinaTest();
    private Gson gson = new Gson();

    @Test
    public void cadastrar() {
        Aluno novo = new Aluno();
        novo.setNome("Aluno 1");
        novo.setSobrenome("Sobrenome 1");
        novo.setNascimento("01/01/2000");
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Logradouro 1");
        endereco.setBairro("Bairro 1");
        endereco.setNumero(1);
        endereco.setMunicipio("Municipio 1");
        endereco.setEstado("Estado 1");
        endereco.setCep("CEP 1");
        novo.setEndereco(endereco);
        novo.setSerie("1 Serie");
        novo.setSexo("Masculino");
        int id = alunoRepository.cadastrar(novo);
        System.out.println("Id registrado: " + id);
        Assert.assertTrue(id > 0);
    }

    @Test
    public void atualizar() {
        Aluno novo = new Aluno();
        novo.setId(1);
        novo.setNome("Aluno 1");
        novo.setSobrenome("Sobrenome 1");
        novo.setNascimento("01/01/2000");
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Logradouro 1");
        endereco.setBairro("Bairro 1");
        endereco.setNumero(1);
        endereco.setMunicipio("Municipio 1");
        endereco.setEstado("Estado 1");
        endereco.setCep("CEP 1");
        novo.setEndereco(endereco);
        novo.setSerie("1 Serie");
        novo.setSexo("Masculino");
        Aluno atualizado = alunoRepository.atualizar(novo);
        System.out.println(gson.toJson(atualizado));
        Assert.assertTrue(atualizado != null);
    }

    @Test
    public void listar() {
        cadastrar();
        List<Aluno> lista = alunoRepository.getListaAlunos();
        System.out.println(gson.toJson(lista));
    }

    @Test
    public void consultarId() {
        cadastrar();
        Aluno aluno = alunoRepository.consultar(1);
        System.out.println(gson.toJson(aluno));
        Assert.assertTrue(aluno != null);
    }

    @Test
    public void consultarNome() {
        cadastrar();
        Aluno aluno = alunoRepository.consultar("Aluno 1");
        System.out.println(gson.toJson(aluno));
        Assert.assertTrue(aluno != null);
    }

    @Test
    public void remover() throws Exception{
        cadastrar();
        Aluno aluno = new Aluno();
        aluno.setId(1);
        alunoRepository.remover(aluno);
    }

    @Test
    public void matricular() throws Exception{
        cadastrar();
        disciplinaTest.cadastrar();
        Disciplina disciplina = disciplinaRepository.consultar(1);
        alunoRepository.matricular(1, disciplina);
    }

    @Test
    public void desmatricular() throws Exception {
        cadastrar();
        disciplinaTest.cadastrar();
        Disciplina disciplina = disciplinaRepository.consultar(1);
        alunoRepository.desmatricular(1, disciplina);
    }

    @Test
    public void obterAlunosPorDisciplina(){
        Disciplina disciplina = disciplinaRepository.consultar(1);
        alunoRepository.obterAlunosPorDisciplina(disciplina);
    }

}
