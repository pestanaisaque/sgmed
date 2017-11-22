/**
 * 
 */
package br.umc.sgmed.controller.relatorio.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.umc.sgmed.po.ItemEstoquePO;
import br.umc.sgmed.po.PacientePO;
import br.umc.sgmed.service.interf.ItemEstoqueService;
import br.umc.sgmed.service.interf.PacienteService;

/**
 * @author Isaque Pestana
 *
 */
@Controller
@RequestMapping("/api/relatorioPaciente")
public class RelatorioRestController {
	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private ItemEstoqueService itemEstoqueService;

	private List<PacientePO> pacientesBuscados;

	private List<ItemEstoquePO> itensBuscados;

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

	@RequestMapping(value = "/listarDistinctItensPorNomeComercial", method = RequestMethod.GET)
	public @ResponseBody List<ItemEstoquePO> getItensPorNomeComercial(@RequestParam("term") String nomeComercial) {

		itensBuscados = itemEstoqueService.findItensByNomeComercial(nomeComercial);

		return itensBuscados;
	}
}
