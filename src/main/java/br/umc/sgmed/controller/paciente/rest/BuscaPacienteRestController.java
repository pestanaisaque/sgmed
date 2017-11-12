/**
 * 
 */
package br.umc.sgmed.controller.paciente.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.umc.sgmed.po.PacientePO;
import br.umc.sgmed.service.interf.PacienteService;

/**
 * @author Isaque Pestana
 *
 */

@Controller
@RequestMapping("/api/buscaPaciente")
public class BuscaPacienteRestController {

	@Autowired
	private PacienteService pacienteService;

	private List<PacientePO> pacientesBuscados;

	@RequestMapping(value = "/listarPacientesPorCpf", method = RequestMethod.GET)
	public @ResponseBody List<PacientePO> getPacientesPorCpf(@RequestParam("term") String cpfPaciente) {

		pacientesBuscados = pacienteService.findPacientesByCpfPaciente(cpfPaciente);

		return pacientesBuscados;
	}
	
	
	@RequestMapping(value = "/listarPacientes", method = RequestMethod.GET)
	public @ResponseBody List<PacientePO> getPacientesPorNome(@RequestParam("term") String nomePaciente) {

		pacientesBuscados = pacienteService.findPacientesByNome(nomePaciente);

		return pacientesBuscados;
	}
}
