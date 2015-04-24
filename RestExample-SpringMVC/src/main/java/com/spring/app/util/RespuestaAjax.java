package com.spring.app.util;

import java.io.Serializable;

public class RespuestaAjax implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String status;
	private Object respuesta;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Object respuesta) {
		this.respuesta = respuesta;
	}
}
