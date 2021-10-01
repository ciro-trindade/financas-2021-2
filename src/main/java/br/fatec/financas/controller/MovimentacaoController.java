package br.fatec.financas.controller;

import java.util.Date;
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

import br.fatec.financas.model.Movimentacao;
import br.fatec.financas.service.MovimentacaoService;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController implements ControllerInterface<Movimentacao> {

	@Autowired
	private MovimentacaoService service;
	
	@Override
	@GetMapping
	public ResponseEntity<List<Movimentacao>> getAll() {		
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	@GetMapping("/{id}")	
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Movimentacao _obj = service.findById(id);
		if (_obj != null)
			return ResponseEntity.ok(_obj);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping("/data/{data}")	
	public ResponseEntity<List<Movimentacao>> getByDate(@PathVariable("data") Date data) {
		return ResponseEntity.ok(service.findByDate(data));
	}
	
	@Override
	@PostMapping
	public ResponseEntity<Movimentacao> post(@Valid @RequestBody Movimentacao obj) {
		service.create(obj);
		return ResponseEntity.ok(obj);
	}

	@Override
	@PutMapping
	public ResponseEntity<?> put(@Valid @RequestBody Movimentacao obj) {
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
