package com.tismart.Reto2.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "SCHOOLS", schema = "USERRETO2")
public class School{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "NAME")
	@NotNull(message = "El nombre no puede ser vacía")
	@NotBlank(message = "El nombre no puede estar en blanco")
	@Size(min = 3, max = 50, message = "El nombre debe contener entre 3 y 50 caracteres")
	private String name;
	
	@Column(name = "NUMBEROFSTUDENTS")
	@NotNull(message = "El número de alumnos no puede ser vacía")
	@Min(value = 0, message = "El número de alumnos debe ser mayor o igual a 0.")
	private Integer numberOfStudents;
	
	@Column(name = "TAXRESOURCE")
	@NotNull(message = "El recurso fiscal no puede ser nulo.")
	@Min(value = 0, message = "El recurso fiscal debe ser mayor o igual a 0.")
	private Double taxResource;
	
	@Column(name = "LICENCED")
	@NotNull(message = "El campo licenciada no puede ser vacía")
	@Min(value = 0, message = "El campo Licenciada solo puede aceptar valores 0 ó 1")
	@Max(value = 1, message = "El campo Licenciada solo puede aceptar valores 0 ó 1")
	private Integer licensed;
	
	
	@Column(name = "CLASIFICATION")
	@NotNull(message = "El campo clasificación no puede ser vacía")
	@Min(value = 0, message = "El campo clasificación debe ser mayor o igual a 0.")
	private Integer clasification;
	
	@Column(name = "CREATEDAT")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FACULTIESID")
	@NotNull(message = "El campo facultad no puede ser nulo")
	private Faculty faculty;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setNumberOfStudents(Integer numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}

	public Double getTaxResource() {
		return taxResource;
	}

	public void setTaxResource(Double taxResource) {
		this.taxResource = taxResource;
	}

	public Integer getLicensed() {
		return licensed;
	}

	public void setLicensed(Integer licensed) {
		this.licensed = licensed;
	}

	public Integer getClasification() {
		return clasification;
	}

	public void setClasification(Integer clasification) {
		this.clasification = clasification;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	@Override
	public String toString() {
		return "School [id=" + id + ", name=" + name + ", numberOfStudents=" + numberOfStudents + ", taxResource="
				+ taxResource + ", licensed=" + licensed + ", clasification=" + clasification + ", createdAt="
				+ createdAt + ", faculty=" + faculty + "]";
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
}
