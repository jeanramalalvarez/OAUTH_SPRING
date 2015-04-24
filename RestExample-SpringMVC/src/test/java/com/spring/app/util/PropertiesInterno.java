package com.spring.app.util;

import java.util.ResourceBundle;

public class PropertiesInterno {
	
	private static final ResourceBundle RB = ResourceBundle.getBundle("aplication");
	
	public static final String CODIGO_EXITO = RB.getString("codigo.estandar.exito");
    public static final String CODIGO_ERROR = RB.getString("codigo.estandar.error");
    
    public static final String URL_LOGIN = RB.getString("url.gettoken");
    public static final String URL_LISTAR_EMPLEADO = RB.getString("url.listar.empleados");
    public static final String URL_BUSCAR_EMPLEADO = RB.getString("url.buscar.empleado");
}
