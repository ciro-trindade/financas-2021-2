package br.fatec.financas.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pessoa_juridica")
//@DiscriminatorValue("Pessoa Jurídica")
public class PessoaJuridica extends Cliente {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "cd_cnpj", length = 14)
	private String cnpj;
	
	@Column(name = "nm_ramo_atividade", length = 20)
	private String ramoAtividade;
	
	public PessoaJuridica() { }

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}	

}
