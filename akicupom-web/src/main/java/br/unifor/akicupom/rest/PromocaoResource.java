package br.unifor.akicupom.rest;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.unifor.akicupom.BO.PromocaoBO;
import br.unifor.akicupom.entities.Promocao;

@RequestScoped
@Path("akicupom/promocao")
public class PromocaoResource {

	@Inject
	private PromocaoBO promocaoBO;

	@GET
	@Path("/listarPromocoes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarPromocoes(){
		Collection<Promocao> promocao = promocaoBO.verTodasPromocoes();
		if(promocao == null || promocao.isEmpty()){
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(promocao).build();
	}

	@GET
	@Path("/listar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarPromocoes(@PathParam("id") Long id){
		Promocao promocao = promocaoBO.buscarPorId(id);
		if(promocao == null){
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.ok(promocao).build();
	}	

	@POST
	@Path("/novo/{nome}/{descricao}/{dataValidade}/{capa}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response novoPromocao(
			@PathParam("nome") String nome, 
			@PathParam("descricao") String descricao,
			@PathParam("dataValidade") String dataValidade,
			@PathParam("capa") String capa){
		Promocao promocao = new Promocao();
		promocao.setNome(nome);
		promocao.setDescricao(descricao);
		promocao.setDataValidade(dataValidade);
		promocao.setCapa(capa);
		promocaoBO.inserirPromocao(promocao);
		return Response.ok().build();
	}

	@PUT
	@Path("/atualizar/{nome}/{descricao}/{dataValidade}/{capa}")
	public Response atualizarPromocao(
			@PathParam("nome") String nome,
			@PathParam("descricao") String descricao,
			@PathParam("dataValidade") String dataValidade,
			@PathParam("capa") String capa){
		Promocao promocao = new Promocao();
		promocao.setNome(nome);
		promocao.setDescricao(descricao);
		promocao.setDataValidade(dataValidade);
		promocao.setCapa(capa);
		promocaoBO.atualizarPromocao(promocao);
		return Response.ok().build();
	}

	@PUT
	@Path("/atualizar/{id}")
	public Response atualizarPromocao(
			@PathParam("id") Long id, Promocao promocao){
		Promocao promocaoExistente = promocaoBO.buscarPorId(id);
		if(promocaoExistente == null){
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		promocaoExistente.setNome(promocao.getNome());
		promocaoExistente.setDescricao(promocao.getDescricao());
		promocaoExistente.setDataValidade(promocao.getDataValidade());
		promocaoExistente.setCapa(promocao.getCapa());
		promocaoBO.atualizarPromocao(promocaoExistente);
		return Response.ok().build();
	}

	@DELETE
	@Path("/remover/{id}")
	public Response removerPromocao(@PathParam("id") Long id){
		Promocao promocaoExistente = promocaoBO.buscarPorId(id);
		if(promocaoExistente == null){
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		promocaoBO.removerPromocao(promocaoExistente);
		return Response.ok().build();
	}	
}