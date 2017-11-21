/**
 * 
 */
package br.umc.sgmed.controller.relatorio;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.umc.sgmed.po.PacientePO;
import br.umc.sgmed.po.SaidaEstoquePO;
import br.umc.sgmed.service.interf.PacienteService;
import br.umc.sgmed.service.interf.SaidaEstoqueService;

/**
 * @author Isaque Pestana
 *
 */
@Controller
public class RelatorioController {

	@Autowired
	private SaidaEstoqueService saidaEstoqueService;

	@Autowired
	private PacienteService pacienteService;

	private List<SaidaEstoquePO> saidaBuscada;

	private PacientePO pacienteBuscado;

	/**
	 * GET
	 */

	@RequestMapping(value = { "/relatorio/buscaRelatorioPaciente" }, method = RequestMethod.GET)
	public ModelAndView getCadastroPaciente() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pacientePO", new PacientePO());
		modelAndView.setViewName("relatorio/buscaRelatorioPaciente");
		return modelAndView;
	}

	/**
	 * POST
	 */

	@RequestMapping(value = "/relatorio/buscaRelatorioPaciente", method = RequestMethod.POST)
	private ModelAndView setBuscaPaciente(@Valid PacientePO pacientePO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		String nomePaciente = (pacientePO.getNomePaciente() != null) ? pacientePO.getNomePaciente()
				: pacientePO.getCpfPaciente();

		Integer idPaciente = Integer
				.parseInt(nomePaciente.substring(nomePaciente.indexOf(":") + 2, nomePaciente.length()));

		if (null != idPaciente && !"".equals(idPaciente)) {

			pacienteBuscado = pacienteService.findPacienteByIdPaciente(idPaciente);
			
			saidaBuscada = saidaEstoqueService.findSaidasByIdPaciente(pacienteBuscado.getIdPaciente());

			modelAndView.addObject("saidaBuscada", saidaBuscada);
			modelAndView.addObject("pacienteBuscado", pacienteBuscado);
			modelAndView.setViewName("relatorio/resultadoBuscaRelatorioPaciente");
		}

		return modelAndView;
	}
}
