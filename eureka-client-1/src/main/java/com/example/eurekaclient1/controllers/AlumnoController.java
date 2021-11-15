package com.example.eurekaclient1.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.curso.microservicios.commons.alumnos.models.entity.Alumno;
import com.example.eurekaclient1.models.services.AlumnoService;

@RestController
public class AlumnoController {

	@Autowired
	private AlumnoService service;

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Optional<Alumno> oEntity = service.findById(id);
		if (oEntity.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oEntity);
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Alumno alumno) {
		if (!service.findByEmail(alumno.getEmail()).isEmpty())
			return ResponseEntity.badRequest().build();
		Alumno entityDb = service.save(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Alumno> oEntity = service.findById(id);
		if (oEntity.isPresent()) {
			service.delete(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Alumno alumno, @PathVariable Long id) {
		Optional<Alumno> oAlumno = service.findById(id);
		if (oAlumno.isPresent()) {
			Alumno alumnoDb = oAlumno.get();
			alumnoDb.setNombre(alumno.getNombre());
			alumnoDb.setApellido(alumno.getApellido());
			alumnoDb.setEmail(alumno.getEmail());

			service.save(alumnoDb);
			return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/filter/{term}")
	public ResponseEntity<?> findByNombreOrApellido(@PathVariable String term) {
		List<Alumno> students = service.findByNombreOrApellido(term);
		if (students.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(students);
	}
}
