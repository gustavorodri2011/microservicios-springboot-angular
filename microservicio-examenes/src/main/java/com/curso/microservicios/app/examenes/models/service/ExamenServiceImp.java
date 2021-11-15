package com.curso.microservicios.app.examenes.models.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.microservicios.app.examenes.models.repository.ExamenRepository;
import com.curso.microservicios.commons.examenes.models.entity.Examen;
import com.spring.cloud.microservices.commons.services.CommonServiceImpl;

@Service
public class ExamenServiceImp extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService {

	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombre(String term) {
		return repository.findByNombre(term);
	}

}
