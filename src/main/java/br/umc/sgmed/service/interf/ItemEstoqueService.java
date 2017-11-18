/**
 * 
 */
package br.umc.sgmed.service.interf;

import java.util.List;

import br.umc.sgmed.po.ItemEstoquePO;

/**
 * @author Isaque Pestana
 *
 */
public interface ItemEstoqueService {
	public List<ItemEstoquePO> findItensByLote(String lote);

	public List<ItemEstoquePO> findItensByNomeComercial(String nomeComercial);

	public ItemEstoquePO findItemEstoqueById(Integer idItemEstoque);

	public void saveItem(ItemEstoquePO itemEstoquePO);

	public void updateItem(ItemEstoquePO itemEstoquePO);
	
	public void removeItem(ItemEstoquePO itemEstoquePO);
	
}
