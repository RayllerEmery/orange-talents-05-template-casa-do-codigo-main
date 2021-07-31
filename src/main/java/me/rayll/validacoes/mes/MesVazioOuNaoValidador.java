package me.rayll.validacoes.mes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import me.rayll.cliente.ClienteDTO;
import me.rayll.estado.Estado;

public class MesVazioOuNaoValidador implements ConstraintValidator<MesVazioOuNao, ClienteDTO>{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public boolean isValid(ClienteDTO value, ConstraintValidatorContext context) {
		
		Long idPais = value.getIdPais();
		Long idEstado = value.getIdEstado();
		
		TypedQuery<Long> query = manager.createQuery("select count(e) from Estado e where e.pais.id=:PidPais", Long.class);
		query.setParameter("PidPais", idPais);
		long total = query.getSingleResult();
		
		if(total > 0 && idEstado == null) {
			return false;
		}
		
		TypedQuery<Estado> queryEstado = manager.createQuery("select es from Estado es where es.id=:PIdEstado and es.pais.id=:PIdPais", Estado.class);
		queryEstado.setParameter("PIdEstado", idEstado);
		queryEstado.setParameter("PIdPais", idPais);
		List<Estado> lista = queryEstado.getResultList();
		
		if(lista.size() == 0) {
			return false;
		}
		
		return true;
	}
	
	
}
