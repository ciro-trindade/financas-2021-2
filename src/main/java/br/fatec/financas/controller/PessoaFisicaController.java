package br.fatec.financas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fatec.financas.model.PessoaFisica;
import br.fatec.financas.service.PessoaFisicaService;

@RestController
@RequestMapping("/pessoas_fisicas")
public class PessoaFisicaController implements ControllerInterface<PessoaFisica> {

	@Autowired
	private PessoaFisicaService service;
	
	@Override
	@GetMapping
	public ResponseEntity<List<PessoaFisica>> getAll() {		
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	@GetMapping("/{id}")	
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		PessoaFisica _obj = service.findById(id);
		if (_obj != null)
			return ResponseEntity.ok(_obj);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@PostMapping
	public ResponseEntity<PessoaFisica> post(@Valid @RequestBody PessoaFisica obj) {
		service.create(obj);
		return ResponseEntity.ok(obj);
	}

	@Override
	@PutMapping
	public ResponseEntity<?> put(@Valid @RequestBody PessoaFisica obj) {
		if (service.update(obj)) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
