package com.spring.app.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Util {

	@Value("${codigo.estandar.exito}")
	public String CODIGO_EXITO;

	@Value("${codigo.estandar.error}")
	public String CODIGO_ERROR;
	
	public static RespuestaAjax obtenerRespuesta(String rpta,Object msj){
		RespuestaAjax rptaAjax = new RespuestaAjax();
		rptaAjax.setStatus(rpta);
		rptaAjax.setRespuesta(msj == null ? "Ocurrio un error":msj);
		return rptaAjax;
	}

}