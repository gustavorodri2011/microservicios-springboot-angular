package com.spring.cloud.microservice.app.courses.models.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.curso.microservicios.commons.alumnos.models.entity.Alumno;
import com.curso.microservicios.commons.examenes.models.entity.Examen;
import com.spring.cloud.microservices.commons.entityaudit.Audit;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cursos")
@Setter
@Getter
public class Course extends Audit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	@NotNull(message = "El nombre no puede ser Nulo")
	@Size(min = 2, message = "El nombre debe tener al menos dos caracteres")
	private String nombre;

	@OneToMany(fetch = FetchType.LAZY)
	List<Alumno> alumnos;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Examen> examenes;

	public Course() {
		this.alumnos = new ArrayList<>();
		this.examenes = new ArrayList<>();
	}

	public void addExamen(Examen examen) {
		this.examenes.add(examen);

	}

	public void removeExamen(Examen examen) {
		this.examenes.remove(examen);

	}

	public void addStudent(Alumno student) {
		this.alumnos.add(student);
	}

	public void removeStudent(Alumno student) {
		this.alumnos.remove(student);
	}
}
