package br.unifor.akicupom.DAO;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.unifor.akicupom.entities.Usuario;

@Stateless
public class UsuarioDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void salvar(Usuario usuario){
		em.persist(usuario);
	}
	
	public Usuario atualizar(Usuario usuario){
		return em.merge(usuario);
	}
	
	public void remover(Usuario usuario){
		em.remove(usuario);
	}
	
	public Usuario buscarPorId(Long id){
		return em.find(Usuario.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Usuario> buscarTodos(){
		String consulta = "select * from usuario";		
		TypedQuery<Usuario> query = (TypedQuery<Usuario>) em.createNativeQuery(consulta);
		return query.getResultList();
	}
}