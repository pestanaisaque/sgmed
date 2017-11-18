/**
 * 
 */
package br.umc.sgmed.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.umc.sgmed.dao.ItemEstoqueDAO;
import br.umc.sgmed.po.ItemEstoquePO;
import br.umc.sgmed.service.interf.ItemEstoqueService;

/**
 * @author Isaque Pestana
 *
 */

@Service("itemEstoqueService")
public class ItemEstoqueServiceImpl implements ItemEstoqueService {

	@Autowired
	private ItemEstoqueDAO itemEstoqueDAO;

	@Override
	public List<ItemEstoquePO> findItensByNomeComercial(String nomeComercial) {
		return itemEstoqueDAO.findAllItensByNomeComercial(nomeComercial);
	}

	@Override
	public ItemEstoquePO findItemEstoqueById(Integer idItemEstoque) {
		return itemEstoqueDAO.findOne(idItemEstoque);
	}

	@Override
	public void saveItem(ItemEstoquePO itemEstoquePO) {
		itemEstoqueDAO.save(itemEstoquePO);
	}

}
