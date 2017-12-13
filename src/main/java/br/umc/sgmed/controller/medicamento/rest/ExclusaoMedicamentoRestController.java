/**
 * 
 */
package br.umc.sgmed.controller.medicamento.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.umc.sgmed.po.ItemEstoquePO;
import br.umc.sgmed.po.MedicamentoPO;
import br.umc.sgmed.response.Response;
import br.umc.sgmed.service.interf.ItemEstoqueService;
import br.umc.sgmed.service.interf.MedicamentoService;

/**
 * @author Isaque Pestana
 *
 */

@Controller
@RequestMapping("/api/exclusaoMedicamento")
public class ExclusaoMedicamentoRestController {
	@Autowired
	private MedicamentoService medicamentoService;

	@Autowired
	private ItemEstoqueService itemService;

	@RequestMapping(value = "/excluirMedicamento", method = RequestMethod.POST)
	public @ResponseBody Response excluirMedicamento(@RequestBody MedicamentoPO medicamentoPO) {
		Response response;

		try {
			List<ItemEstoquePO> itens = itemService.findItensByIdMedicamento(medicamentoPO.getIdMedicamento());
			
//			Boolean listaVazia = false;
			
			// Valida se existem itens em estoque para este medicamento
			if (itens.isEmpty()){
//				listaVazia = true;
//			} 
//			else { 
				
//				// Valida se esses itens possuem a quantidade em estoque maior que 0 
//				Long qtdEstoque = itens.stream()
//						.filter(filter -> filter.getQtdItemEmEstoque() > 0)
//						.collect(Collectors.counting());
//				
//				if (qtdEstoque <= 0){
//					listaVazia = true;
//				}
//			}
			
//			if (listaVazia){
				medicamentoService.deleteMedicamento(medicamentoPO);
				response = new Response("OK", medicamentoPO);
			} else {
				response = new Response("NOK_ITEM", medicamentoPO);
			}
				
		} catch (Exception e) {
			response = new Response("NOK", medicamentoPO);
		}
		return response;
	}
}
