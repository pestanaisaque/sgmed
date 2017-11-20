package br.umc.sgmed.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "medicamento")
public class MedicamentoPO {

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "medicamento_id")
	private Integer idMedicamento;

	@OneToOne
	private ItemEstoquePO itemEstoquePO;

	@Column(name = "nome_comercial")
	private String nomeComercial;

	@Column(name = "principio_ativo")
	private String principioAtivo;

	@Column(name = "indicacoes")
	private String indicacoes;

	@Column(name = "contra_indicacoes")
	private String contraIndicacoes;

	@Column(name = "generico")
	private Integer generico;

	public Integer getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(Integer idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public ItemEstoquePO getItemEstoquePO() {
		return itemEstoquePO;
	}

	public void setItemEstoquePO(ItemEstoquePO itemEstoquePO) {
		this.itemEstoquePO = itemEstoquePO;
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

	public Integer getGenerico() {
		return generico;
	}

	public void setGenerico(Integer generico) {
		this.generico = generico;
	}

	// public String getGenericoToString(){
	// return generico == 1 ? "Sim" : "Não";
	// }

}
