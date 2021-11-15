package com.spring.cloud.microservice.app.courses.models.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.spring.cloud.microservice.app.courses.models.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {
	@Query("select c from Course c join fetch c.alumnos a where a.id= ?1")
	public Optional<Course> findCourseByStudentId(Long id);

}
