/**
 * 
 */
package br.umc.sgmed.controller.estoque.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.umc.sgmed.po.ItemEstoquePO;
import br.umc.sgmed.service.interf.ItemEstoqueService;

/**
 * @author Isaque Pestana
 *
 */

@Controller
@RequestMapping("/api/buscaMedicamentoEstoque")
public class BuscaMedicamentoEstoqueRestController {
	@Autowired
	private ItemEstoqueService itemEstoqueService;

	private List<ItemEstoquePO> itensBuscados;

	@RequestMapping(value = "/listarItensPorNomeComercial", method = RequestMethod.GET)
	public @ResponseBody List<ItemEstoquePO> getItensPorNomeComercial(@RequestParam("term") String nomeComercial) {

		itensBuscados = itemEstoqueService.findItensByNomeComercial(nomeComercial);

		return itensBuscados;
	}

	@RequestMapping(value = "/listarItensPorLote", method = RequestMethod.GET)
	public @ResponseBody List<ItemEstoquePO> getItensPorLote(@RequestParam("term") String lote) {

		itensBuscados = itemEstoqueService.findItensByLote(lote);

		return itensBuscados;
	}
}
