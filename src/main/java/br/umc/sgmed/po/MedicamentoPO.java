package br.umc.sgmed.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "medicamento")
public class MedicamentoPO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "medicamento_id")
	private int idMedicamento;
	
	
	@Column(name = "nome_comercial")
	@Length(min = 5, max = 50, message = "*O Nome Comercial deve ter entre 5 e 50 caracteres")
	@NotEmpty(message = "*Por favor, forneça um Nome Comercial")
	private String nomeComercial;

	
	@Column(name = "principio_ativo")
	@Length(min = 5, max = 50, message = "*O Princípio Ativo deve ter entre 5 e 50 caracteres")
	@NotEmpty(message = "*Por favor, forneça um Princípio Ativo")
	private String principioAtivo;

	
	@Column(name = "indicacoes")
	@Length(min = 5, max = 50, message = "*O campo Indicações deve ter entre 5 e 50 caracteres")
	@NotEmpty(message = "*O campo Indicações não deve estar em branco")
	private String indicacoes;

	
	@Column(name = "contra_indicacoes")
	@Length(min = 5, max = 50, message = "*O campo Contra-Indicações deve ter entre 5 e 50 caracteres")
	@NotEmpty(message = "*O campo Contra-Indicações não deve estar em branco")
	private String contraIndicacoes;

	@Column(name = "generico")
	@NotEmpty(message = "*O campo Genérico deve ser selecionado")
	private boolean generico;

	public int getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(int idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public String getNomeComercial() {
		return nomeComercial;
	}

	public void setNomeComercial(String nomeComercial) {
		this.nomeComercial = nomeComercial;
	}

	public String getPrincipioAtivo() {
		return principioAtivo;
	}

	public void setPrincipioAtivo(String principioAtivo) {
		this.principioAtivo = principioAtivo;
	}

	public String getIndicacoes() {
		return indicacoes;
	}

	public void setIndicacoes(String indicacoes) {
		this.indicacoes = indicacoes;
	}

	public String getContraIndicacoes() {
		return contraIndicacoes;
	}

	public void setContraIndicacoes(String contraIndicacoes) {
		this.contraIndicacoes = contraIndicacoes;
	}

	public boolean isGenerico() {
		return generico;
	}

	public void setGenerico(boolean generico) {
		this.generico = generico;
	}

}
