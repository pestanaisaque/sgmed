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
public class ExclusaoPacienteController {

	@Autowired
	private PacienteService pacienteService;

	private PacientePO pacienteBuscado;

	/**
	 * GETS
	 */

	@RequestMapping(value = { "/paciente/buscaExclusaoPaciente" }, method = RequestMethod.GET)
	public ModelAndView getBuscaExclusaoPaciente() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pacientePO", new PacientePO());
		modelAndView.setViewName("paciente/buscaExclusaoPaciente");
		return modelAndView;
	}

	/**
	 * POST
	 */

	@RequestMapping(value = "/paciente/buscaExclusaoPaciente", method = RequestMethod.POST)
	private ModelAndView setBuscaExclusaoPaciente(@Valid PacientePO pacientePO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			
			pacienteBuscado = pacienteService.findPacienteByCpfPaciente(pacientePO.getCpfPaciente());

			modelAndView.addObject("pacienteBuscado", pacienteBuscado);
			modelAndView.setViewName("paciente/resultadoBuscaExclusaoPaciente");
			
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("pacientePO", new PacientePO());
			modelAndView.setViewName("paciente/buscaExclusaoPaciente");
		}
		return modelAndView;
	}
}
