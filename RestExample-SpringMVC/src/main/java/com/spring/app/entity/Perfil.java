package com.spring.app.entity;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Cacheable(true)
@Entity
@Table(name = "springperfiles")
public class Perfil implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String nombrePerfil;

	public Perfil() {}
	
	@JsonIgnore
	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonIgnore
	@Column(name = "descripcion")
	public String getNombrePerfil() {
		return nombrePerfil;
	}

	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}

	@Override
	@JsonIgnore
	@Transient
	public String getAuthority() {
		return this.id;
	}
}