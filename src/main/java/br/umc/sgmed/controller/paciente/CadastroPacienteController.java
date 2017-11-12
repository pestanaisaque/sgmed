/**
 * 
 */
package br.umc.sgmed.controller.paciente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.umc.sgmed.po.PacientePO;

/**
 * @author Isaque Pestana
 *
 */

@Controller
public class CadastroPacienteController {

	/**
	 * ATRIBUTOS DE TELA
	 */

	@ModelAttribute("ufs")
	public List<String> getUfs() {
		List<String> ufs = new ArrayList<>(Arrays.asList("AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA",
				"MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RS", "SC", "SE", "SP", "TO"));

		return ufs;
	}

	/**
	 * GETS
	 */

	@RequestMapping(value = { "/paciente/cadastroPaciente" }, method = RequestMethod.GET)
	public ModelAndView getCadastroPaciente() {
		ModelAndView modelAndView = new ModelAndView();
		PacientePO pacientePO = new PacientePO();
		modelAndView.addObject("pacientePO", pacientePO);
		modelAndView.setViewName("paciente/cadastroPaciente");
		return modelAndView;
	}

}
