package com.curso.microservicios.commons.alumnos.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.spring.cloud.microservices.commons.entityaudit.Audit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alumnos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alumno extends Audit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "El Nombre es requerido")
	@Pattern(regexp = "^[A-Z]+(.)*", message = "La primera letra debe ser en mayúscula.")
	@Column(length = 100)
	private String nombre;

	@NotBlank(message = "El apellido es requerido")
	@Pattern(regexp = "^[A-Z]+(.)*", message = "La primera letra debe ser en mayúscula.")
	@Column(length = 100)
	private String apellido;

	@Email(message = "E-mail inválido")
	@NotBlank(message = "E-mail es requerido")
	@Column(length = 150, nullable = false, unique = true)
	private String email;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Alumno)) {
			return false;
		}
		Alumno a = (Alumno) obj;

		return this.id != null && this.id.equals(a.getId());
	}

}