package com.example.eurekaclient1.customvalidations;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.curso.microservicios.commons.alumnos.models.entity.Alumno;
import com.example.eurekaclient1.models.services.AlumnoService;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	private AlumnoService service;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null)
			return false;
		List<Alumno> alumnos = service.findByEmail(value);
		if (alumnos.isEmpty()) {
			return true;
		}
		return false;
	}
}
