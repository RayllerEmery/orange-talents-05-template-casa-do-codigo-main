package me.rayll.validacoes.mes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { MesVazioOuNaoValidador.class})
public @interface MesVazioOuNao {
	
	String message() default "É necessário passar um Estado valido.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
