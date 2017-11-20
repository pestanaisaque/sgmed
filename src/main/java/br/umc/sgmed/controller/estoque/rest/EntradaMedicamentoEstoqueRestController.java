/**
 * 
 */
package br.umc.sgmed.controller.estoque.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.umc.sgmed.po.ItemEstoquePO;
import br.umc.sgmed.response.Response;
import br.umc.sgmed.service.interf.ItemEstoqueService;
import br.umc.sgmed.utils.DateUtils;

/**
 * @author Isaque Pestana
 *
 */

@Controller
@RequestMapping("/api/entradaMedicamentoEstoque")
public class EntradaMedicamentoEstoqueRestController {
	@Autowired
	private ItemEstoqueService itemEstoqueService;

	@RequestMapping(value = "/inserirMedicamentoEmEstoque", method = RequestMethod.POST)
	public @ResponseBody Response inserirMedicamentoEmEstoque(@RequestBody ItemEstoquePO itemEstoquePO) {
		Response response;

		itemEstoquePO.setDtValidadeItemEstoque(DateUtils.configDate(itemEstoquePO.getDtValidadeItemEstoque(), 2));

		ItemEstoquePO itemExistente = itemEstoqueService.findItemEstoqueById(itemEstoquePO.getIdItemEstoque());

		try {

			if (null != itemExistente) {
				response = new Response("NOK_ID", itemEstoquePO);
			} else {
				itemEstoqueService.saveItem(itemEstoquePO);
				response = new Response("OK", itemEstoquePO);
			}

		} catch (Exception e) {
			response = new Response("NOK", itemEstoquePO);
		}

		return response;
	}

}
