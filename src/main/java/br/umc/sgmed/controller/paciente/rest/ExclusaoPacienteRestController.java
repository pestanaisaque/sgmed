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
@RequestMapping("/api/exclusaoPaciente")
public class ExclusaoPacienteRestController {
	@Autowired
	private PacienteService pacienteService;

	@RequestMapping(value = "/excluirPaciente", method = RequestMethod.POST)
	public @ResponseBody Response excluirPaciente(@RequestBody PacientePO pacientePO) {
		Response response;

		try {
			pacienteService.deletePaciente(pacientePO);
			response = new Response("OK", pacientePO);
		} catch (Exception e) {
			response = new Response("NOK", pacientePO);
		}
		return response;
	}
}
