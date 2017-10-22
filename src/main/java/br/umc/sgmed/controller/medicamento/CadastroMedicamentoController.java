package br.umc.sgmed.controller.medicamento;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.umc.sgmed.po.MedicamentoPO;
import br.umc.sgmed.service.interf.MedicamentoService;

@Controller
public class CadastroMedicamentoController {

	@Autowired
	private MedicamentoService medicamentoService;

	/**
	 * ATRIBUTOS DE TELA
	 */

	
	/**
	 * GETS
	 */
	
	@RequestMapping(value={"/medicamento/cadastroMedicamento"}, method = RequestMethod.GET)
	public ModelAndView getCadastroMedicamento(){
		ModelAndView modelAndView = new ModelAndView();
		MedicamentoPO medicamentoPO = new MedicamentoPO();
		modelAndView.addObject("medicamentoPO", medicamentoPO);
		modelAndView.setViewName("medicamento/cadastroMedicamento");
		return modelAndView;
	}
	
	/**
	 * SETS 
	 */
	
	@RequestMapping(value = "/medicamento/cadastroMedicamento", method = RequestMethod.POST)
	private ModelAndView setCadastroMedicamento(@Valid MedicamentoPO medicamentoPO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		MedicamentoPO medicamentoExistente = medicamentoService.findMedicamentoById(medicamentoPO.getIdMedicamento());
		if (null != medicamentoExistente) {
			bindingResult.rejectValue("idMedicamento", "error.user",
					"Já existe um Medicamento cadastrado com esse ID");
		} 
		
		if (bindingResult.hasErrors()){
			modelAndView.setViewName("medicamento/cadastroMedicamento");
		} else {
			medicamentoService.saveMedicamento(medicamentoPO);
			modelAndView.addObject("successMessage", "Medicamento cadastrado com sucesso");
			modelAndView.addObject("medicamentoPO", new MedicamentoPO());
			modelAndView.setViewName("medicamento/cadastroMedicamento");
		}
		

		return modelAndView;
	}
	
	
	
	

}
