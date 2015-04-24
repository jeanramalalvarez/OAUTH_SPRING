package com.spring.app.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.app.daorepository.UsuarioDAO;
import com.spring.app.entity.Usuario;
import com.spring.app.util.Util;

@Service
public class UsuarioService implements UserDetailsService, IService<Usuario>, Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Autowired
	private Util util;

	/**
	 * Retorna el Objeto con los permisos y su password para el spring security
	 */
	@Override
	@Transactional(readOnly = true)
	public Usuario loadUserByUsername(String username) {
		logger.info("Autenticando en el Sistema para: "+username);
		Usuario domainUser = usuarioDao.obtenerUsuario(username);
		return domainUser;
	}

	@Override
	@Transactional
	public String guardar(Usuario bean) {
		try{
			usuarioDao.saveAndFlush(bean);
			return util.CODIGO_EXITO;
		}catch(Exception e){
			logger.error("Error al guardar el registro: "+e.getMessage() + " \n| e: "+e);
			return util.CODIGO_ERROR;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public String eliminar(Usuario bean) {
		try{
			usuarioDao.delete(bean);
			return util.CODIGO_EXITO;
		}catch(Exception e){
			logger.error("Error al guardar el registro: "+e.getMessage() + " \n| e: "+e);
			return util.CODIGO_ERROR;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> listarTodos() {
		try{
			List<Usuario> lta = usuarioDao.findAll();
			logger.info("Lista de Usuarios: "+lta.size());
			return lta;
		}catch(Exception e){
			logger.error("Error al listar todos los registros: "+e.getMessage() + " \n| e: "+e);
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario buscarPorId(String id) {
		try{
			return usuarioDao.findOne(Long.parseLong(id));
		}catch(Exception e){
			logger.error("Error al buscar el registro: "+e.getMessage() + " \n| e: "+e);
			return null;
		}
	}
}