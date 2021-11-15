package com.spring.cloud.microservices.commons.entityaudit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public class Audit {

	private static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";

//	@Column(name = "created_at")
//	@CreatedDate
//	private LocalDateTime createdAt;
//
//	@Column(name = "updated_at")
//	@LastModifiedDate
//	private LocalDateTime updatedAt;

	@Column(name = "created_at")
	@CreatedDate
	private String createdAt;

	@Column(name = "updated_at")
	@LastModifiedDate
	private String updatedAt;

	@PrePersist
	private void prePersist() {
		this.createdAt = formatLocalDateTime();
//		this.createdAt = formatLocalDateTime(LocalDateTime.now());
	}

	@PreUpdate
	private void postUpdate() {
		this.updatedAt = formatLocalDateTime();
//		this.updatedAt = formatLocalDateTime(LocalDateTime.now());
	}

//	private LocalDateTime formatLocalDateTime(LocalDateTime localDateTime) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
//		String formatDateTime = localDateTime.format(formatter);
//		return LocalDateTime.parse(formatDateTime, formatter);
//	}

	private String formatLocalDateTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
		return LocalDateTime.now().format(formatter);
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

//	public LocalDateTime getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(LocalDateTime createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public LocalDateTime getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(LocalDateTime updatedAt) {
//		this.updatedAt = updatedAt;
//	}

}
