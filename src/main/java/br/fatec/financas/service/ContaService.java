package br.fatec.financas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.financas.model.Conta;
import br.fatec.financas.repository.ContaRepository;

@Service
public class ContaService implements ServiceInterface<Conta>{

	@Autowired
	private ContaRepository repository;
	
	public ContaService() {
	}

	@Override
	public Conta create(Conta conta) {
		repository.save(conta);
		return conta;
	}

	@Override
	public List<Conta> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Conta findById(Long id) {
		Optional<Conta> _conta = repository.findById(id);
		return _conta.orElse(null);
	}
	
	@Override
	public boolean update(Conta conta) {
		if (repository.existsById(conta.getId())) {
			repository.save(conta);
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
	
	public Float depositar(Long id, Float valor) {
		Optional<Conta> _conta = repository.findById(id);
		if (_conta.isPresent()) {
			Conta c = _conta.get();
			c.setSaldo(c.getSaldo() + valor);
			repository.save(c);
			return c.getSaldo();
		}
		return null;
	}
	
	public Float sacar(Long id, Float valor) throws IllegalArgumentException {
		Optional<Conta> _conta = repository.findById(id);
		if (_conta.isPresent()) {
			Conta c = _conta.get();
			if (c.getSaldo() >= valor) {
				c.setSaldo(c.getSaldo() - valor);
				repository.save(c);
				return c.getSaldo();
			}
			throw new IllegalArgumentException("Saldo insuficiente");
		}
		return null;		
	}
}
