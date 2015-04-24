package com.spring.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.app.entity.Empleado;
import com.spring.app.service.EmpleadoService;
import com.spring.app.util.RespuestaAjax;
import com.spring.app.util.Util;

@RestController
@RequestMapping("/rest/empleado/*")
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private Util util;

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public RespuestaAjax guardar(@RequestBody Empleado bean){
		return Util.obtenerRespuesta(empleadoService.guardar(bean),null);
	}
	
	@RequestMapping(value = "/eliminar", method = RequestMethod.POST)
	public RespuestaAjax eliminar(@RequestBody Empleado bean){
		return Util.obtenerRespuesta(empleadoService.eliminar(bean),null);
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public List<Empleado> listaTodos(){
		return empleadoService.listarTodos();
	}
	
	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET)
	public Empleado buscar(@PathVariable("id") String id){
		return empleadoService.buscarPorId(id);
	}
}
