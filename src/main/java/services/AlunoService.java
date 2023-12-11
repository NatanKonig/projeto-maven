package services;

import model.Aluno;
import model.Disciplina;
import repository.AlunoRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/aluno")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlunoService {

    @Inject
    private AlunoRepository alunoRepository;

    @GET
    public Response listar() {
        return Response.ok().entity(alunoRepository.getListaAlunos()).build();
    }

    @GET
    @Path("/{id : \\d+}")
    public Response consultar(@PathParam("id") int id) {
        return Response.ok().entity(alunoRepository.consultar(id)).build();
    }

    @GET
    @Path("/{nome}")
    public Response consultar(@PathParam("nome") String nome) {
        return Response.ok().entity(alunoRepository.consultar(nome)).build();
    }

    @POST
    public Response cadastrar(Aluno aluno) {
        return Response.ok().entity(alunoRepository.cadastrar(aluno)).build();
    }

    @PUT
    public Response atualizar(Aluno aluno) {
        try {
            alunoRepository.remover(aluno);
            return Response.ok(alunoRepository.atualizar(aluno)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    public Response remover(Aluno aluno) {
        try {
            alunoRepository.remover(aluno);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}/matricular")
    public Response matricular(@PathParam("id") int id, Disciplina disciplina) {
        return Response.ok().entity(alunoRepository.matricular(alunoRepository.consultar(id), disciplina)).build();
    }
}
