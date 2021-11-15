package com.curso.microservicios.app.examenes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curso.microservicios.app.examenes.models.service.ExamenService;
import com.curso.microservicios.commons.examenes.models.entity.Examen;
import com.spring.cloud.microservices.commons.controllers.CommonController;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Examen examen, @PathVariable Long id) {
		Optional<Examen> oExamen = service.findById(id);
		if (oExamen.isPresent()) {
			Examen examenDb = oExamen.get();
			examenDb.setNombre(examen.getNombre());

			examenDb.getPreguntas().stream().filter(pdb -> !examen.getPreguntas().contains(pdb))
					.forEach(examenDb::removePregunta);

			examenDb.setPreguntas(examen.getPreguntas());
			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/filter/{term}")
	public ResponseEntity<?> findByNombre(@PathVariable String term) {
		List<Examen> examenes = service.findByNombre(term);
		if (examenes.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(examenes);
	}
}
