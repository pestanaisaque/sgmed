/**
 * 
 */
package br.umc.sgmed.dto;

import br.umc.sgmed.po.ItemEstoquePO;

/**
 * @author Isaque Pestana
 *
 */
public class RelatorioMedicamentoDTO {
	private ItemEstoquePO itemEstoquePO;
	private String generico;

	/**
	 * 
	 */
	public RelatorioMedicamentoDTO() {
	}

	/**
	 * @param itemEstoquePO
	 * @param generico
	 * @param naoGenerico
	 */
	public RelatorioMedicamentoDTO(ItemEstoquePO itemEstoquePO) {
		this.itemEstoquePO = itemEstoquePO;
		this.generico = (itemEstoquePO.getMedicamentoPO().getGenerico() == 1) ? "Sim" : "Não";
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
	 * @return the generico
	 */
	public String getGenerico() {
		return generico;
	}

	/**
	 * @param generico
	 *            the generico to set
	 */
	public void setGenerico(String generico) {
		this.generico = generico;
	}

}
