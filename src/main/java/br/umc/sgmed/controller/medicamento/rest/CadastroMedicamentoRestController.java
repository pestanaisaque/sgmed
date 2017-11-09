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
@RequestMapping("/api/cadastroMedicamento")
public class CadastroMedicamentoRestController {
	@Autowired
	private MedicamentoService medicamentoService;

	@RequestMapping(value = "/cadastrarMedicamento", method = RequestMethod.POST)
	public @ResponseBody Response cadastrarMedicamento(@RequestBody MedicamentoPO medicamentoPO) {
		Response response;

		MedicamentoPO medicamentoExistente = medicamentoService.findMedicamentoById(medicamentoPO.getIdMedicamento());
		
		if (null != medicamentoExistente) {
			response = new Response("NOK", medicamentoExistente);
		} else {
			medicamentoService.saveMedicamento(medicamentoPO);
			response = new Response("OK", medicamentoPO);
		}

		return response;
	}
}
