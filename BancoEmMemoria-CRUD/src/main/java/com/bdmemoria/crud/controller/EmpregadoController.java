package com.bdmemoria.crud.controller;

import java.util.HashMap;
import java.util.List;
//import javax.validation.Valid;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdmemoria.crud.entidade.Empregado;
import com.bdmemoria.crud.exception.ResourceNotFoundException;
import com.bdmemoria.crud.repository.EmpregadoRepository;

@RestController
@RequestMapping("api/bancoMemoria")
public class EmpregadoController {
	
	@Autowired
	private EmpregadoRepository empregadoRepository;
	
	@GetMapping("/empregado")
	public  List<Empregado>getEmpregado(){
		return empregadoRepository.findAll();		
	}
	
	@GetMapping("/empregado/{id}")
	public ResponseEntity<Empregado> getEmpregadoPorId(@PathVariable(value="id") Long empregadoId) throws ResourceNotFoundException{
		Empregado empregado = empregadoRepository.findById(empregadoId).
				orElseThrow(()-> new ResourceNotFoundException ("Nao ha empregado com esse id: " + empregadoId ));
	
		return ResponseEntity.ok().body(empregado);
		
	}
	
	@PostMapping("/empregado")
	public Empregado createEmpregado( @RequestBody Empregado empregado) {
		return empregadoRepository.save(empregado);
	}
	
	
	@PutMapping("/empregado/{id}")
	public ResponseEntity<Empregado> update(@PathVariable (value="id") Long empregadoID, @RequestBody Empregado empregadoDetalhes) throws ResourceNotFoundException{
		
		Empregado empregado = empregadoRepository.findById(empregadoID).
				orElseThrow(()-> new ResourceNotFoundException ("Nao ha empregado com esse id: " + empregadoID ));
		
		empregado.setId(empregadoDetalhes.getId());
		empregado.setIdade(empregadoDetalhes.getIdade());
		empregado.setNome(empregadoDetalhes.getNome());
		empregado.setSobrenome(empregadoDetalhes.getSobrenome());
		
		final Empregado updateEmpregado = empregadoRepository.save(empregado);
		
		return ResponseEntity.ok(updateEmpregado);
	}
	
	@DeleteMapping("/empregado/{id}")
	public Map <String, Boolean> deleteEmpregado (@PathVariable(value = "id") Long empregadoID) throws ResourceNotFoundException{
		
		Empregado empregado = empregadoRepository.findById(empregadoID).
				orElseThrow(()-> new ResourceNotFoundException ("Nao ha empregado com esse id: " + empregadoID ));
		
		empregadoRepository.delete(empregado);
		
		Map <String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}		

}
