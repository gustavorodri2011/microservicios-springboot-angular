package com.example.eurekaclient1.models.services;

import java.util.List;

import com.curso.microservicios.commons.alumnos.models.entity.Alumno;
import com.spring.cloud.microservices.commons.services.CommonService;

public interface AlumnoService extends CommonService<Alumno> {
	
	public List<Alumno> findByNombreOrApellido(String term);
	
	public List<Alumno> findByEmail(String email);
}
