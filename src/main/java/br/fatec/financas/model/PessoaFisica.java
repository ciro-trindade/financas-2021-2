package br.fatec.financas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_pessoa_fisica")
//@DiscriminatorValue("Pessoa Física")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class PessoaFisica extends Cliente {
	private static final long serialVersionUID = 1L;

	@Column(name = "cd_cpf", length = 11)
	@CPF
	private String cpf;
	
	@Column(name = "nm_profissao", length = 30)
	@NotBlank
	@Length(max = 30)
	private String profissao;
	
}

