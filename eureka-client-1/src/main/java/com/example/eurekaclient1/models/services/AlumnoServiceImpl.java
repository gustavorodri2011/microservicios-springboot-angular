package com.example.eurekaclient1.models.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.microservicios.commons.alumnos.models.entity.Alumno;
import com.example.eurekaclient1.models.entity.repository.AlumnoRepository;
import com.spring.cloud.microservices.commons.services.CommonServiceImpl;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findByNombreOrApellido(String term) {
		return repository.findByNombreOrApellido(term);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findByEmail(String email) {
		return repository.findByEmail(email);
	}
}
