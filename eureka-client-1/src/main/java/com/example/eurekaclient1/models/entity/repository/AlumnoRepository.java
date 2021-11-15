package com.example.eurekaclient1.models.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.curso.microservicios.commons.alumnos.models.entity.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
	@Query("select a from Alumno a where a.nombre like %?1% or a.apellido like %?1%")
	public List<Alumno> findByNombreOrApellido(String term);
	
	//@Query("select a from Alumno a where a.email=?1")
	List<Alumno> findByEmail(String email);
	
}
