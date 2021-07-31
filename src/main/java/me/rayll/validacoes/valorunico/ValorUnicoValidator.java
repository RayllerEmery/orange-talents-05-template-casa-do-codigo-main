package me.rayll.validacoes.valorunico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object>{

	private String domainAttibute;
	private Class<?> klass;
	
	@Autowired
	private EntityManager manager;
	
	@Override
	public void initialize(ValorUnico params) {
		klass = params.domainClass();
		domainAttibute = params.fieldName();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		Query query = manager.createQuery("select 1 from " +klass.getName()+" where " +domainAttibute+ "=:value");
		query.setParameter("value", value.toString());
		List<?> list = query.getResultList();
		
		return list.isEmpty();
	}

}
