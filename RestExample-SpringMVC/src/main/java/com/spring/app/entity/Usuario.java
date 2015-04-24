package com.spring.app.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Cacheable(true)
@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "usuarios")
public class Usuario implements UserDetails, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String usuario;
	private String clave;
	private Set<Perfil> perfiles = new HashSet<Perfil>();

	private String expirado;
	private String bloqueado;
	private String credenciales_expirado;
	private String habilitado;

	@Id
	@JsonIgnore
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(length = 1024, nullable = false)
	@JsonIgnore
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Override
	@Transient
	@JsonIgnore
	public Collection<Perfil> getAuthorities() {
		return this.perfiles;
	}

	@Override
	@Transient
	@JsonIgnore
	public String getPassword() {
		return this.getClave();
	}

	@Override
	@Transient
	public String getUsername() {
		return this.getUsuario();
	}

	@Override
	@Transient
	public boolean isAccountNonExpired() {
		return getExpirado().equals("N") ? true : false;
	}

	@Override
	@Transient
	public boolean isAccountNonLocked() {
		return getBloqueado().equals("N") ? true : false;
	}

	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		return getCredenciales_expirado().equals("N") ? true : false;
	}

	@Override
	@Transient
	public boolean isEnabled() {
		return getHabilitado().equals("N") ? false : true;
	}

	@Column(length = 1, nullable = false)
	@JsonIgnore
	public String getExpirado() {
		return expirado;
	}

	public void setExpirado(String expirado) {
		this.expirado = expirado;
	}

	@Column(length = 1, nullable = false)
	@JsonIgnore
	public String getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(String bloqueado) {
		this.bloqueado = bloqueado;
	}

	@Column(name = "credencial_expirado", length = 1, nullable = false)
	@JsonIgnore
	public String getCredenciales_expirado() {
		return credenciales_expirado;
	}

	public void setCredenciales_expirado(String credenciales_expirado) {
		this.credenciales_expirado = credenciales_expirado;
	}

	@Column(length = 1, nullable = false)
	@JsonIgnore
	public String getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
	@JoinTable(name = "usuarios_perfiles", joinColumns = { @JoinColumn(name = "idUsuario", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "idPerfil", referencedColumnName = "id") })
	public Set<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(Set<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	@JsonIgnore
	@Column(name="usuario")
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}