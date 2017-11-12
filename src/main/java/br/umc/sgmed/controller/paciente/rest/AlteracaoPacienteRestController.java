/**
 * 
 */
package br.umc.sgmed.controller.paciente.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.umc.sgmed.po.PacientePO;
import br.umc.sgmed.response.Response;
import br.umc.sgmed.service.interf.PacienteService;

/**
 * @author Isaque Pestana
 *
 */

@Controller
@RequestMapping("/api/alteracaoPaciente")
public class AlteracaoPacienteRestController {
	@Autowired
	private PacienteService pacienteService;

	/**
	 * POST
	 */

	@RequestMapping(value = "/alterarPaciente", method = RequestMethod.POST)
	public @ResponseBody Response alterarPaciente(@RequestBody PacientePO pacientePO) {
		Response response;

		try {
			pacienteService.updatePaciente(pacientePO);
			response = new Response("OK", pacientePO);
		} catch (Exception e) {
			response = new Response("NOK", pacientePO);
		}
		return response;
	}
}
