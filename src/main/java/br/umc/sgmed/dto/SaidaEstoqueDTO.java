/**
 * 
 */
package br.umc.sgmed.dto;

import br.umc.sgmed.po.ItemEstoquePO;
import br.umc.sgmed.po.PacientePO;

/**
 * @author Isaque Pestana
 *
 */

public class SaidaEstoqueDTO {
	private ItemEstoquePO itemEstoquePO;
	private PacientePO pacientePO;
	private Long qtdSaida;

	/**
	 * 
	 */
	public SaidaEstoqueDTO() {
	}

	/**
	 * @param itemEstoquePO
	 * @param pacientePO
	 */
	public SaidaEstoqueDTO(ItemEstoquePO itemEstoquePO, PacientePO pacientePO) {
		this.itemEstoquePO = itemEstoquePO;
		this.pacientePO = pacientePO;
	}

	/**
	 * @return the qtdSaida
	 */
	public Long getQtdSaida() {
		return qtdSaida;
	}

	/**
	 * @param qtdSaida
	 *            the qtdSaida to set
	 */
	public void setQtdSaida(Long qtdSaida) {
		this.qtdSaida = qtdSaida;
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
}
