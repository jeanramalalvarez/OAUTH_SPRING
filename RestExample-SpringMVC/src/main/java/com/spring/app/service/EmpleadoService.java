package com.spring.app.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.app.daorepository.EmpleadoDAO;
import com.spring.app.entity.Empleado;
import com.spring.app.util.Util;

@Service
public class EmpleadoService implements IService<Empleado>,Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(EmpleadoService.class);
	
	@Autowired
	private EmpleadoDAO empleadoDAO;
	
	@Autowired
	private Util util;
	
	@Transactional
	public String guardar(Empleado empleado){
		try{
			empleadoDAO.saveAndFlush(empleado);
			return util.CODIGO_EXITO;
		}catch(Exception e){
			logger.error("Error al guardar el registro: "+e.getMessage() + " \n| e: "+e);
			return util.CODIGO_ERROR;
		}
	}
	
	@Transactional
	public String eliminar(Empleado empleado){
		try{
			empleadoDAO.delete(empleado);
			return util.CODIGO_EXITO;
		}catch(Exception e){
			logger.error("Error al eliminar el registro: "+e.getMessage() + " \n| e: "+e);
			return util.CODIGO_ERROR;
		}
	}
	
	@Transactional(readOnly = true)
	public List<Empleado> listarTodos(){
		try{
			return empleadoDAO.findAll();
		}catch(Exception e){
			logger.error("Error al listar todos los registros: "+e.getMessage() + " \n| e: "+e);
			return null;
		}
	}
	
	@Transactional(readOnly = true)
	public Empleado buscarPorId(String id){
		try{
			return empleadoDAO.findOne(Long.parseLong(id));
		}catch(Exception e){
			logger.error("Error al buscar el registro: "+e.getMessage() + " \n| e: "+e);
			return null;
		}
	}
}
