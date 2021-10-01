package br.fatec.financas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "tb_conta")
@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class Conta extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "nr_agencia")
	@NotNull
	@Min(1000)
	@Max(9999)
	private Integer agencia;

	@Column(name = "nm_numero", length = 10)
	@NotBlank
	@Length(max = 10)
	private String numero;

	@Column(name = "vl_saldo")
	@ToString.Exclude
	private Float saldo;

	/*
	@Builder
	public Conta(Long id, @NotNull @Min(1000) @Max(9999) Integer agencia, @NotBlank @Length(max = 10) String numero) {
		super(id);
		this.agencia = agencia;
		this.numero = numero;
	}
	*/
	
	

}
