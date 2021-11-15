package com.example.eurekaclient1.customvalidations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface UniqueEmail {
	public String message() default "El email ya existe";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};
}
