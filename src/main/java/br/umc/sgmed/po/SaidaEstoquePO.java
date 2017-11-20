/**
 * 
 */
package br.umc.sgmed.po;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author Isaque Pestana
 *
 */

@Entity
@Table(name = "saida_estoque")
public class SaidaEstoquePO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "saida_estoque_id")
	private Integer idSaidaEstoque;

	@ManyToOne
	@JoinColumn(name = "paciente_id")
	@JsonBackReference(value = "pacientePO")
	private PacientePO pacientePO;

	@ManyToOne
	@JoinColumn(name = "item_estoque_id")
	@JsonBackReference(value = "itemEstoquePO")
	private ItemEstoquePO itemEstoquePO;

	@Column(name = "qtd_anterior")
	private Long qtdAnterior;

	@Column(name = "qtd_retirada")
	private Long qtdRetirada;

	@Column(name = "dt_retirada")
	private Date dataDaRetirada;

	/**
	 * @return the idSaidaEstoque
	 */
	public Integer getIdSaidaEstoque() {
		return idSaidaEstoque;
	}

	/**
	 * @param idSaidaEstoque
	 *            the idSaidaEstoque to set
	 */
	public void setIdSaidaEstoque(Integer idSaidaEstoque) {
		this.idSaidaEstoque = idSaidaEstoque;
	}

	/**
	 * @return the pacientePO
	 */
	public PacientePO getPacientePO() {
		return pacientePO;
	}

	/**
	 * @param pacientePO
	 *            the pacientePO to set
	 */
	public void setPacientePO(PacientePO pacientePO) {
		this.pacientePO = pacientePO;
	}

	/**
	 * @return the itemEstoquePO
	 */
	public ItemEstoquePO getItemEstoquePO() {
		return itemEstoquePO;
	}

	/**
	 * @param itemEstoquePO
	 *            the itemEstoquePO to set
	 */
	public void setItemEstoquePO(ItemEstoquePO itemEstoquePO) {
		this.itemEstoquePO = itemEstoquePO;
	}

	/**
	 * @return the qtdAnterior
	 */
	public Long getQtdAnterior() {
		return qtdAnterior;
	}

	/**
	 * @param qtdAnterior
	 *            the qtdAnterior to set
	 */
	public void setQtdAnterior(Long qtdAnterior) {
		this.qtdAnterior = qtdAnterior;
	}

	/**
	 * @return the qtdRetirada
	 */
	public Long getQtdRetirada() {
		return qtdRetirada;
	}

	/**
	 * @param qtdRetirada
	 *            the qtdRetirada to set
	 */
	public void setQtdRetirada(Long qtdRetirada) {
		this.qtdRetirada = qtdRetirada;
	}

	/**
	 * @return the dataDaRetirada
	 */
	public Date getDataDaRetirada() {
		return dataDaRetirada;
	}

	/**
	 * @param dataDaRetirada
	 *            the dataDaRetirada to set
	 */
	public void setDataDaRetirada(Date dataDaRetirada) {
		this.dataDaRetirada = dataDaRetirada;
	}

}
