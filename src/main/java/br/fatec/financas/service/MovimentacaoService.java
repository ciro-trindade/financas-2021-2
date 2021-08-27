package br.fatec.financas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.financas.model.Movimentacao;
import br.fatec.financas.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService implements ServiceInterface<Movimentacao>{

	@Autowired
	private MovimentacaoRepository repository;
	
	public MovimentacaoService() {
	}

	@Override
	public Movimentacao create(Movimentacao obj) {
		repository.save(obj);
		return obj;
	}

	@Override
	public List<Movimentacao> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Movimentacao findById(Long id) {
		Optional<Movimentacao> _obj = repository.findById(id);
		return _obj.orElse(null);
	}
	
	@Override
	public boolean update(Movimentacao obj) {
		if (repository.existsById(obj.getId())) {
			repository.save(obj);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delete(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}			
		return false;
	}
	
}
