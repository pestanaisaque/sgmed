/**
 * 
 */
package br.umc.sgmed.controller.paciente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class CadastroPacienteController {

	private String dtNascimentoStr;
	
	@Autowired
	private PacienteService pacienteService;

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
	
	/**
	 * SETS 
	 */
	
	@RequestMapping(value = "/paciente/cadastroPaciente", method = RequestMethod.POST)
	private ModelAndView setCadastroPaciente(@Valid PacientePO pacientePO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		PacientePO pacienteExistente = pacienteService.findPacienteById(pacientePO.getIdPaciente());
		if (null != pacienteExistente) {
			bindingResult.rejectValue("idPaciente", "error.user",
					"Já existe um Paciente cadastrado com esse ID");
		} 
		
		if (bindingResult.hasErrors()){
			modelAndView.setViewName("paciente/cadastroPaciente");
		} else {
			pacienteService.savePaciente(pacientePO);
			modelAndView.addObject("successMessage", "Paciente cadastrado com sucesso");
			modelAndView.addObject("pacientePO", new PacientePO());
			modelAndView.setViewName("paciente/cadastroPaciente");
		}
		

		return modelAndView;
	}

	public String getDtNascimentoStr() {
		return dtNascimentoStr;
	}

	public void setDtNascimentoStr(String dtNascimentoStr) {
		this.dtNascimentoStr = dtNascimentoStr;
	}
	
}
