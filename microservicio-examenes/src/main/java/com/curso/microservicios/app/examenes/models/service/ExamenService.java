package com.curso.microservicios.app.examenes.models.service;

import java.util.List;

import com.curso.microservicios.commons.examenes.models.entity.Examen;
import com.spring.cloud.microservices.commons.services.CommonService;

public interface ExamenService extends CommonService<Examen> {
	public List<Examen> findByNombre(String term);
}
