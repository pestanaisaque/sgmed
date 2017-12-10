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
public class BuscaPacienteController {

	@Autowired
	private PacienteService pacienteService;

	private PacientePO pacienteBuscado;

	/**
	 * GET
	 */

	@RequestMapping(value = { "/paciente/buscaPaciente" }, method = RequestMethod.GET)
	public ModelAndView getBuscaPaciente() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pacientePO", new PacientePO());
		modelAndView.setViewName("paciente/buscaPaciente");
		return modelAndView;
	}

	@RequestMapping(value = { "/paciente/resultadoBuscaPaciente" }, method = RequestMethod.GET)
	public ModelAndView getResultadoBuscaPaciente() {
		ModelAndView modelAndView = new ModelAndView();

		if (null == pacienteBuscado) {
			modelAndView.addObject("pacienteBuscado", new PacientePO());
			modelAndView.setViewName("paciente/buscaPaciente");
		}

		return modelAndView;
	}

	/**
	 * POST
	 */

	@RequestMapping(value = "/paciente/buscaPaciente", method = RequestMethod.POST)
	private ModelAndView setBuscaPaciente(@Valid PacientePO pacientePO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		try {

			pacienteBuscado = pacienteService.findPacienteByCpfPaciente(pacientePO.getCpfPaciente());

			modelAndView.addObject("pacienteBuscado", pacienteBuscado);
			modelAndView.setViewName("paciente/resultadoBuscaPaciente");

		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("pacientePO", new PacientePO());
			modelAndView.setViewName("paciente/buscaPaciente");
		}
		return modelAndView;
	}
}
