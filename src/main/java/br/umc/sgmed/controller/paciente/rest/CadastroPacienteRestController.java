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
import br.umc.sgmed.utils.DateUtils;

/**
 * @author Isaque Pestana
 *
 */

@Controller
@RequestMapping("/api/cadastroPaciente")
public class CadastroPacienteRestController {
	@Autowired
	private PacienteService pacienteService;

	@RequestMapping(value = "/cadastrarPaciente", method = RequestMethod.POST)
	public @ResponseBody Response cadastrarPaciente(@RequestBody PacientePO pacientePO) {
		Response response;

		pacientePO.setDtNascimentoPaciente(DateUtils.configDate(pacientePO.getDtNascimentoPaciente(), 2));

		PacientePO pacienteExistente = pacienteService.findPacienteByCpfPaciente(pacientePO.getCpfPaciente());

		if (null != pacienteExistente) {
			response = new Response("NOK", pacienteExistente);
		} else {
			pacienteService.savePaciente(pacientePO);
			response = new Response("OK", pacientePO);
		}

		return response;
	}
}
