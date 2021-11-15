package com.spring.cloud.microservice.app.courses.models.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.cloud.microservice.app.courses.models.entity.Course;
import com.spring.cloud.microservice.app.courses.models.repository.CourseRepository;
import com.spring.cloud.microservices.commons.services.CommonServiceImpl;

@Service
public class CourseServiceImpl extends CommonServiceImpl<Course, CourseRepository> implements CourseService {

	@Override
	@Transactional(readOnly = true)
	public Optional<Course> findCourseByStudentId(Long id) {
		return repository.findCourseByStudentId(id);
	}

}
