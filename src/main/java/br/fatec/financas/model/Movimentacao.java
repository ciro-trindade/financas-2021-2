package br.fatec.financas.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Entity
@Table(name = "tb_movimentacao")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Movimentacao extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "vl_valor")
	@Min(1)
	@Max(100000)
	private Float valor;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "nm_tipo_movimentacao", length = 10)
	@NotBlank
	private TipoMovimentacao tipo;
	
	@Column(name = "ds_descricao", length = 100)
	@NotBlank
	@Length(max = 100)
	private String descricao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_data")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:ss")
	@NotBlank
	private Calendar data;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_conta_id")
	@NotBlank
	@Getter(onMethod = @__(@JsonIgnore))
	@Setter(onMethod = @__(@JsonProperty))
	private Conta conta;
	
	@ManyToMany
	@JoinTable(name = "tb_movimentacao_categoria",
	           joinColumns=@JoinColumn(name="fk_movimentacao_id"),
	           inverseJoinColumns=@JoinColumn(name="fk_categoria_id"))
	@Singular(value = "addCategoria")
	private List<Categoria> categorias;
			
}
