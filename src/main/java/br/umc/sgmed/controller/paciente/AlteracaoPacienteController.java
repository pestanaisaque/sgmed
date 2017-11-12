/**
 * 
 */
package br.umc.sgmed.controller.paciente;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.umc.sgmed.po.PacientePO;
import br.umc.sgmed.service.interf.PacienteService;

/**
 * @author Isaque Pestana
 *
 */

@Controller
public class AlteracaoPacienteController {

	@Autowired
	private PacienteService pacienteService;

	private PacientePO pacienteBuscado;
	
	/**
	 * GET
	 */

	@RequestMapping(value = { "/paciente/buscaAlteracaoPaciente" }, method = RequestMethod.GET)
	public ModelAndView getBuscaAlteracaoPaciente() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pacientePO", new PacientePO());
		modelAndView.setViewName("paciente/buscaAlteracaoPaciente");
		return modelAndView;
	}
	
	/**
	 * POST
	 */
	
	@RequestMapping(value = "/paciente/buscaAlteracaoPaciente", method = RequestMethod.POST)
	private ModelAndView setBuscaPaciente(@Valid PacientePO pacientePO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		String nomePaciente = (pacientePO.getNomePaciente() != null) ? pacientePO.getNomePaciente() : pacientePO.getCpfPaciente();
		
		Integer idPaciente = Integer
				.parseInt(nomePaciente.substring(nomePaciente.indexOf(":") + 2, nomePaciente.length()));

		if (null != idPaciente && !"".equals(idPaciente)) {

			pacienteBuscado = pacienteService.findPacienteByIdPaciente(idPaciente);

			modelAndView.addObject("pacienteBuscado", pacienteBuscado);
			modelAndView.setViewName("paciente/resultadoBuscaAlteracaoPaciente");
		}

		return modelAndView;
	}
	
}
