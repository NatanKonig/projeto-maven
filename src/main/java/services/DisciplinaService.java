package services;

import model.Aluno;
import repository.AlunoRepository;
import repository.DisciplinaRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/disciplina")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DisciplinaService {

    @Inject
    private DisciplinaRepository disciplinaRepository;
    @Inject
    private AlunoRepository alunoRepository;

    @GET
    public Response listar() {
        return Response.ok().entity(disciplinaRepository.getListaDisciplinas()).build();
    }

    @GET
    @Path("/{cod : \\d+}")
    public Response consultar(@PathParam("cod") int cod) {
        return Response.ok().entity(disciplinaRepository.consultar(cod)).build();
    }

    @GET
    @Path("/consultar/{cod}")
    public Response consultaAluno(@PathParam("cod") int cod) {
        List<Aluno> alunosNaDisciplina = alunoRepository.obterAlunosPorDisciplina(disciplinaRepository.consultar(cod));
        return Response.ok().entity(alunosNaDisciplina).build();
    }
}
