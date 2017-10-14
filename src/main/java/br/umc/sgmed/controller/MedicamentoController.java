package br.umc.sgmed.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.umc.sgmed.po.MedicamentoPO;
import br.umc.sgmed.service.MedicamentoService;

@Controller
public class MedicamentoController {

	@Autowired
	private MedicamentoService medicamentoService;

	@RequestMapping(value = "/medicamento/cadastroMedicamento", method = RequestMethod.POST)
	private ModelAndView cadastrarMedicamento(@Valid MedicamentoPO medicamentoPO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		MedicamentoPO medicamentoExistente = medicamentoService.findMedicamentoById(medicamentoPO.getIdMedicamento());
		if (null != medicamentoExistente) {
			bindingResult.rejectValue("idMedicamento", "error.medicamentoPO",
					"Já existe um Medicamento cadastrado com esse ID");
		} 
		
		if (bindingResult.hasErrors()){
			modelAndView.setViewName("cadastroMedicamento");
		} else {
			medicamentoService.saveMedicamento(medicamentoPO);
			modelAndView.addObject("medicamentoPO", new MedicamentoPO());
			modelAndView.setViewName("cadastroMedicamento");
		}
		

		return modelAndView;
	}
	
	

}
