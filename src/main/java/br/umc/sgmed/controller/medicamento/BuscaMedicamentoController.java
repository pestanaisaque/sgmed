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
public class BuscaMedicamentoController {
	@Autowired
	private MedicamentoService medicamentoService;
	
	private MedicamentoPO medicamentoBuscado;
	
	private List<MedicamentoPO> medicamentosBuscados;

	/**
	 * ATRIBUTOS DE TELA
	 */

	/**
	 * GETS
	 */

	@RequestMapping(value = { "/medicamento/buscaMedicamento" }, method = RequestMethod.GET)
	public ModelAndView getBuscaMedicamento() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("medicamentoPO", new MedicamentoPO());
		modelAndView.setViewName("medicamento/buscaMedicamento");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/medicamento/resultadoBuscaMedicamento" }, method = RequestMethod.GET)
	public ModelAndView getResultadoBuscaMedicamento() {
		ModelAndView modelAndView = new ModelAndView();
		
		if (null == medicamentoBuscado){
			modelAndView.addObject("medicamentoBuscado", new MedicamentoPO());
			modelAndView.setViewName("medicamento/buscaMedicamento");
		} 

		return modelAndView;
	}
	

	/**
	 * SETS
	 */

	@RequestMapping(value = "/medicamento/buscaMedicamento", method = RequestMethod.POST)
	private ModelAndView setBuscaMedicamento(@Valid MedicamentoPO medicamentoPO, BindingResult bindingResult) {
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
			} else { // Retornar tela de exibição de medicamento
				modelAndView.addObject("medicamentoBuscado", medicamentoBuscado);
				modelAndView.setViewName("medicamento/resultadoBuscaMedicamento");
			}
			
		} else if (null != nomeComercial && !"".equals(nomeComercial)) {
			
			medicamentosBuscados = medicamentoService.findMedicamentosByNomeComercial(nomeComercial);
			
			// Validar retorno
			if (medicamentosBuscados.isEmpty()) {
				
				bindingResult.rejectValue("idMedicamento", "error.user",
						"Não existe Medicamento cadastrado com esse Nome comercial.");
				
			} else if (medicamentosBuscados.stream().count() > 1) { // Se for maior que 1, retornar tela de Seleção de Medicamento
				
				modelAndView.addObject("medicamentosBuscados", medicamentosBuscados);
				modelAndView.setViewName("medicamento/listaBuscaMedicamento");
				
			} else { // Retornar tela de exibição de medicamento
				
				modelAndView.addObject("medicamentoBuscado", medicamentoBuscado);
				modelAndView.setViewName("medicamento/resultadoBuscaMedicamento");
				
			}
			
		}
		
		// Se o medicamento não for encontrado em nenhuma etapa
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("medicamento/buscaMedicamento");
		} 

		return modelAndView;
	}
	
	
	
}
