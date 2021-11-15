package com.spring.cloud.microservice.app.courses.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curso.microservicios.commons.alumnos.models.entity.Alumno;
import com.curso.microservicios.commons.examenes.models.entity.Examen;
import com.spring.cloud.microservice.app.courses.models.entity.Course;
import com.spring.cloud.microservice.app.courses.models.service.CourseService;
import com.spring.cloud.microservices.commons.controllers.CommonController;

@RestController
public class CourseController extends CommonController<Course, CourseService> {

	@GetMapping("/course/{student_id}")
	public ResponseEntity<?> findCoursesByStudentId(@PathVariable Long student_id) {
		Optional<Course> oCourse = service.findCourseByStudentId(student_id);
		if (!oCourse.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(oCourse.get());
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Course course, @PathVariable Long id) {
		Optional<Course> oCourse = service.findById(id);
		if (oCourse.isPresent()) {
			Course courseDb = oCourse.get();
			courseDb.setNombre(course.getNombre());

			service.save(courseDb);
			return ResponseEntity.status(HttpStatus.CREATED).body(courseDb);
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}/add-students")
	public ResponseEntity<?> addStudents(@RequestBody List<Alumno> students, @PathVariable Long id) {
		Optional<Course> oCourse = service.findById(id);
		if (!oCourse.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Course courseDb = oCourse.get();
		students.forEach(courseDb::addStudent);

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(courseDb));
	}

	@PutMapping("/{id}/remove-student")
	public ResponseEntity<?> removeStudent(@RequestBody Alumno student, @PathVariable Long id) {
		Optional<Course> oCourse = service.findById(id);
		if (!oCourse.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Course courseDb = oCourse.get();

		courseDb.removeStudent(student);

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(courseDb));
	}

	@PutMapping("/{id}/add-examenes")
	public ResponseEntity<?> addExamenes(@RequestBody List<Examen> examenes, @PathVariable Long id) {
		Optional<Course> oCourse = service.findById(id);
		if (!oCourse.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Course courseDb = oCourse.get();
		examenes.forEach(courseDb::addExamen);

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(courseDb));
	}

	@PutMapping("/{id}/remove-examen")
	public ResponseEntity<?> removeExamen(@RequestBody Examen examen, @PathVariable Long id) {
		Optional<Course> oCourse = service.findById(id);
		if (!oCourse.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Course courseDb = oCourse.get();

		courseDb.removeExamen(examen);

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(courseDb));
	}

}
