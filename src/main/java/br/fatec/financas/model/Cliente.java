package br.fatec.financas.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_cliente")
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "nm_pertence_a_classe", length = 20)
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class Cliente extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "nm_nome", length = 60)
	@NotBlank
	@Length(max = 60)
	private String nome;

	@Column(name = "ds_endereco", length = 120)
	@Length(max = 120)
	private String endereco;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "fk_conta_id", unique = true)
	private Conta conta;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tb_perfil")
	@Setter(AccessLevel.NONE)
	private Set<Integer> perfis = new HashSet<>();
	
	@Column(name = "nm_login", length = 80, unique = true)
	private String login;
	
	@Getter(onMethod = @__(@JsonIgnore))
	@Setter(onMethod = @__(@JsonProperty))
	@Column(name = "nm_senha")
	private String senha;

	public Set<TipoPerfil> getPerfis() {
		return perfis.stream().map(x -> TipoPerfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(TipoPerfil perfil) {
		this.perfis.add(perfil.getCod());
	}
}
