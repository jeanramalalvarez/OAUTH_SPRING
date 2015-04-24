package com.spring.app.service;

import java.util.List;

public interface IService<T> {

	String guardar(T bean);
	String eliminar(T bean);
	List<T> listarTodos();
	T buscarPorId(String id);
}
