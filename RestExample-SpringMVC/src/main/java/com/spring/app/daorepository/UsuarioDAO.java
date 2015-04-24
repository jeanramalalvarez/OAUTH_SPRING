package com.spring.app.daorepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.app.entity.Usuario;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {

	@Query("select u from Usuario u where u.usuario = :usuario and u.clave = :clave")
	Usuario obtenerUsuario(@Param("usuario") String usuario, @Param("clave") String clave);
	
	@Query("select u from Usuario u where u.usuario = :usuario")
	Usuario obtenerUsuario(@Param("usuario") String usuario);
}
