package com.bdmemoria.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.bdmemoria.crud.entidade.Empregado;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BancoEmMemoriaCrudApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmpregadoTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}
	
	
	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetEmpregado() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/empregado",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetEmpregadoById() {
		Empregado empregado = restTemplate.getForObject(getRootUrl() + "/empregado/1", Empregado.class);
		System.out.println(empregado.getNome());
		assertNotNull(empregado);
	}

	@Test
	public void testCreateEmpregado() {
		Empregado empregado = new Empregado();
		empregado.setIdade(12);
		empregado.setNome("admin");
		empregado.setSobrenome("admin");

		ResponseEntity<Empregado> postResponse = restTemplate.postForEntity(getRootUrl() + "/empregado", empregado, Empregado.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}
	
	@Test
	public void testUpdateEmpregado() {
		int id = 1;
		Empregado empregado = restTemplate.getForObject(getRootUrl() + "/empregado/" + id, Empregado.class);
		empregado.setNome("admin1");
		empregado.setSobrenome("admin2");

		restTemplate.put(getRootUrl() + "/empregado/" + id, empregado);

		Empregado updatedEmpregado = restTemplate.getForObject(getRootUrl() + "/empregado/" + id, Empregado.class);
		assertNotNull(updatedEmpregado);
	}

	@Test
	public void testDeleteEmpregado() {
		int id = 2;
		Empregado empregado = restTemplate.getForObject(getRootUrl() + "/empregado/" + id, Empregado.class);
		assertNotNull(empregado);

		restTemplate.delete(getRootUrl() + "/empregado/" + id);

		try {
			empregado = restTemplate.getForObject(getRootUrl() + "/empregado/" + id, Empregado.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
