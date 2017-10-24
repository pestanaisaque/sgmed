package br.umc.sgmed.controller.medicamento;

import java.util.List;

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

public class AlteracaoMedicamentoController {

	@Autowired
	private MedicamentoService medicamentoService;
	
	private MedicamentoPO medicamentoBuscado;
	
	private List<MedicamentoPO> medicamentosBuscados;
	
	
	/**
	 * GETS
	 */
	
	@RequestMapping(value = { "/medicamento/buscaAlteracaoMedicamento" }, method = RequestMethod.GET)
	public ModelAndView getBuscaAlteracaoMedicamento() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("medicamentoPO", new MedicamentoPO());
		modelAndView.setViewName("medicamento/buscaAlteracaoMedicamento");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/medicamento/resultadoBuscaAlteracaoMedicamento" }, method = RequestMethod.GET)
	public ModelAndView getResultadoBuscaAlteracaoMedicamento() {
		ModelAndView modelAndView = new ModelAndView();
		
		if (null == medicamentoBuscado){
			modelAndView.addObject("medicamentoBuscado", new MedicamentoPO());
			modelAndView.setViewName("medicamento/buscaAlteracaoMedicamento");
		} 

		return modelAndView;
	}
	
	/**
	 * SETS
	 */
	
	@RequestMapping(value = "/medicamento/buscaAlteracaoMedicamento", method = RequestMethod.POST)
	private ModelAndView setBuscaAlteracaoMedicamento(@Valid MedicamentoPO medicamentoPO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		Integer idMedicamento = medicamentoPO.getIdMedicamento();
		String nomeComercial = medicamentoPO.getNomeComercial();

		// Se o idMedicamento for digitado, buscar por id. Senão, buscar por
		// nome comercial
		if (null != idMedicamento && !"".equals(idMedicamento)) {
			
			medicamentoBuscado = medicamentoService.findMedicamentoById(idMedicamento);
			
			// Validar retorno
			if (null == medicamentoBuscado || "".equals(medicamentoBuscado.getNomeComercial())) {
				bindingResult.rejectValue("idMedicamento", "error.user",
						"Não existe Medicamento cadastrado com esse ID.");
			} else { // Retornar tela de alteração de medicamento
				modelAndView.addObject("medicamentoBuscado", medicamentoBuscado);
				modelAndView.setViewName("medicamento/resultadoBuscaAlteracaoMedicamento");
			}
			
		} else if (null != nomeComercial && !"".equals(nomeComercial)) {
			
			medicamentosBuscados = medicamentoService.findMedicamentosByNomeComercial(nomeComercial);
			
			// Validar retorno
			if (medicamentosBuscados.isEmpty()) {
				
				bindingResult.rejectValue("idMedicamento", "error.user",
						"Não existe Medicamento cadastrado com esse Nome comercial.");
				
			} else if (medicamentosBuscados.stream().count() > 1) { // Se for maior que 1, retornar tela de Seleção de Medicamento
				
				modelAndView.addObject("medicamentosBuscados", medicamentosBuscados);
				modelAndView.setViewName("medicamento/listaBuscaAlteracaoMedicamento");
				
			} else { // Retornar tela de exibição de medicamento
				
				modelAndView.addObject("medicamentoBuscado", medicamentoBuscado);
				modelAndView.setViewName("medicamento/resultadoBuscaAlteracaoMedicamento");
				
			}
			
		}
		
		// Se o medicamento não for encontrado em nenhuma etapa
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("medicamento/buscaAlteracaoMedicamento");
		} 

		return modelAndView;
	}
	
	@RequestMapping(value = "/medicamento/resultadoBuscaAlteracaoMedicamento", method = RequestMethod.POST)
	private ModelAndView setResultadoBuscaAlteracaoMedicamento(@Valid MedicamentoPO medicamentoPO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		medicamentoPO.setIdMedicamento(medicamentoBuscado.getIdMedicamento());
		
		if (bindingResult.hasErrors()){
			modelAndView.setViewName("medicamento/resultadoBuscaAlteracaoMedicamento");
		} else {
			medicamentoService.updateMedicamento(medicamentoPO);
			modelAndView.addObject("medicamentoBuscado", medicamentoPO);
			modelAndView.setViewName("medicamento/resultadoBuscaAlteracaoMedicamento");
		}
		

		return modelAndView;
	}
	
}
