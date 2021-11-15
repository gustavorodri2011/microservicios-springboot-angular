package com.curso.microservicios.commons.examenes.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.cloud.microservices.commons.entityaudit.Audit;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "preguntas")
@Getter
@Setter
@ToString
public class Pregunta extends Audit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 255)
	private String texto;

	@JsonIgnoreProperties(value = { "preguntas" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "examen_id")
	private Examen examen;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Pregunta))
			return false;
		Pregunta p = (Pregunta) obj;
		return this.id != null && this.id.equals(p.getId());
	}

}
