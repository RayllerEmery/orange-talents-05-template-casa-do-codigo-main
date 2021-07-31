package me.rayll.validacoes.valorunico;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { ValorUnicoValidator.class})
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ValorUnico {

	Class<?> domainClass();
	
	String fieldName();
	
	String message() default "Foi encontrado um registro já salvo!";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
