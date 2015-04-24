package com.spring.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.spring.app.entity.Empleado;
import com.spring.app.util.Helper;
import com.spring.app.util.OauthTokenBean;
import com.spring.app.util.PropertiesInterno;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/spring_config.xml" })
public class TestWebApp {

	private OauthTokenBean getTokenOauth() {
		RestTemplate restTemplate = Helper.getRestTemplate();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", "admin");
		map.put("pass", "123");
		map.put("clientID", "restapp");
		map.put("clientSECRET", "secret");
		map.put("grantType", "password");
		HttpEntity<?> headers = Helper.getRequestEntity();

		ResponseEntity<OauthTokenBean> responseEntity = restTemplate.exchange(PropertiesInterno.URL_LOGIN, HttpMethod.GET,
				headers, OauthTokenBean.class, map);

		return responseEntity.getBody();
	}

	@Test
	public void buscarPorId() {
		RestTemplate restTemplate = Helper.getRestTemplate();

		OauthTokenBean token = getTokenOauth();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token.getTokenType() + " " + token.getValue());
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 1);

		ResponseEntity<Empleado> responseEntity = restTemplate.exchange(PropertiesInterno.URL_BUSCAR_EMPLEADO, HttpMethod.GET,
				entity, Empleado.class, map);
		Empleado emp = responseEntity.getBody();
		System.out.println("Empleado: " + emp.getId() + " | " + emp.getNombre() + " | " + emp.getApellidos() + " | "
				+ emp.getDni());
	}

	@Test
	public void listarEmpleados() {
		RestTemplate restTemplate = Helper.getRestTemplate();

		OauthTokenBean token = getTokenOauth();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token.getTokenType() + " " + token.getValue());
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<Empleado[]> responseEntity = restTemplate.exchange(PropertiesInterno.URL_LISTAR_EMPLEADO, HttpMethod.GET,
				entity, Empleado[].class);
		List<Empleado> lta = Arrays.asList(responseEntity.getBody());
		for (Empleado emp : lta) {
			System.out.println("Empleado: " + emp.getId() + " | " + emp.getNombre() + " | " + emp.getApellidos() + " | "
					+ emp.getDni());
		}
	}

	@Test
	public void buscarPorIdSINTOKEN() {
		RestTemplate restTemplate = Helper.getRestTemplate();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 1);

		ResponseEntity<Empleado[]> responseEntity = restTemplate.exchange(PropertiesInterno.URL_BUSCAR_EMPLEADO, HttpMethod.GET,
				null, Empleado[].class, map);
		List<Empleado> lta = Arrays.asList(responseEntity.getBody());
		for (Empleado emp : lta) {
			System.out.println("Empleado: " + emp.getId() + " | " + emp.getNombre() + " | " + emp.getApellidos() + " | "
					+ emp.getDni());
		}
	}
}
