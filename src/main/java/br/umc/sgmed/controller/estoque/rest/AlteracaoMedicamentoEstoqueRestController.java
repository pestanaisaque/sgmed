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
@RequestMapping("/api/alteracaoMedicamentoEstoque")
public class AlteracaoMedicamentoEstoqueRestController {
	@Autowired
	private ItemEstoqueService itemEstoqueService;

	@RequestMapping(value = "/alterarMedicamentoEmEstoque", method = RequestMethod.POST)
	public @ResponseBody Response alterarMedicamentoEmEstoque(@RequestBody ItemEstoquePO itemEstoquePO) {
		Response response;

		itemEstoquePO.setDtValidadeItemEstoque(DateUtils.configDate(itemEstoquePO.getDtValidadeItemEstoque(), 2));

		try {
			
			itemEstoqueService.updateItem(itemEstoquePO);
			response = new Response("OK", itemEstoquePO);

		} catch (Exception e) {
			response = new Response("NOK", itemEstoquePO);
		}

		return response;
	}
}
