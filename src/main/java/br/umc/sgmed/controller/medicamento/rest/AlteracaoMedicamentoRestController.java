/**
 * 
 */
package br.umc.sgmed.controller.medicamento.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.umc.sgmed.po.MedicamentoPO;
import br.umc.sgmed.response.Response;
import br.umc.sgmed.service.interf.MedicamentoService;

/**
 * @author Isaque Pestana
 *
 */

@Controller
@RequestMapping("/api/alteracaoMedicamento")
public class AlteracaoMedicamentoRestController {
	@Autowired
	private MedicamentoService medicamentoService;

	@RequestMapping(value = "/alterarMedicamento", method = RequestMethod.POST)
	public @ResponseBody Response alterarMedicamento(@RequestBody MedicamentoPO medicamentoPO) {
		Response response;

		try {
			medicamentoService.updateMedicamento(medicamentoPO);
			response = new Response("OK", medicamentoPO);
		} catch (Exception e) {
			response = new Response("NOK", medicamentoPO);
		}
		return response;
	}
}
