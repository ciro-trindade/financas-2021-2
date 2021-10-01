package br.fatec.financas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_pessoa_juridica")
//@DiscriminatorValue("Pessoa Jurídica")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class PessoaJuridica extends Cliente {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "cd_cnpj", length = 14)
	@CNPJ
	@NonNull
	private String cnpj;
	
	@Column(name = "nm_ramo_atividade", length = 20)
	@NotBlank
	@Length(max = 20)
	private String ramoAtividade;
	
}
