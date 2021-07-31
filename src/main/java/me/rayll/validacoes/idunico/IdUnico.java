package me.rayll.validacoes.idunico;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import me.rayll.validacoes.valorunico.ValorUnicoValidator;

@Documented
@Constraint(validatedBy = { IdUnicoValidator.class })
@Target({ FIELD })
@Retention(RUNTIME)
public @interface IdUnico {

	Class<?> domainClass();

	String fieldName();

	String message() default "Já existe um registro com o campo.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
