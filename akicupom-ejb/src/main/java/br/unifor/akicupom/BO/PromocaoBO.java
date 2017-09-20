package br.unifor.akicupom.BO;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.unifor.akicupom.DAO.PromocaoDAO;
import br.unifor.akicupom.entities.Promocao;

@Stateless
public class PromocaoBO {

	@EJB
	private PromocaoDAO promocaoDAO;
	
	public void inserirPromocao(Promocao promocao){
		promocaoDAO.salvar(promocao);
	}
	
	public Promocao atualizarPromocao(Promocao promocao){
		return promocaoDAO.atualizar(promocao);
	}
	
	public void removerPromocao(Promocao promocao){
		Promocao promocaoARemover = promocaoDAO.buscarPorId(promocao.getId());
		promocaoDAO.remover(promocaoARemover);
	}
	
	public Promocao buscarPorId(Long id){
		return promocaoDAO.buscarPorId(id);
	}
	
	public Collection<Promocao> verTodasPromocoes(){
		return promocaoDAO.buscarTodos();
	}
}
