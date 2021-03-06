package br.fatec.financas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.fatec.financas.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{
	
	//@Query("select c from Conta c where c.agencia=?1")
	List<Conta> findByAgencia(Integer agencia);
	
	//@Query("select c from Conta c where c.agencia=:pAgencia and"
	//			+ " c.saldo between :pInicio and :pFinal")
	List<Conta> findByAgenciaAndSaldoBetween(
			Integer agencia,
			Float from, 
			Float to);	
	
	@Query("select c from Conta c join Cliente cc on"
			+ " cc.conta = c where cc.nome like %?1%")
	List<Conta> listarPorNomeCliente(String nome);
}
