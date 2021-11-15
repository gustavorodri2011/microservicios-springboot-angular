package com.spring.cloud.microservice.app.courses.models.service;


import java.util.Optional;

import com.spring.cloud.microservice.app.courses.models.entity.Course;
import com.spring.cloud.microservices.commons.services.CommonService;

public interface CourseService extends CommonService<Course> {
	public Optional<Course> findCourseByStudentId(Long id);

}
