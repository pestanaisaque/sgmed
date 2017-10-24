/**
 * 
 */
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

/**
 * @author Isaque Pestana
 *
 */

@Controller
public class ExclusaoMedicamentoController {
	
	@Autowired
	private MedicamentoService medicamentoService;
	
	private MedicamentoPO medicamentoBuscado;
	
	private List<MedicamentoPO> medicamentosBuscados;
	
	
	/**
	 * GETS
	 */
	
	@RequestMapping(value = { "/medicamento/buscaExclusaoMedicamento" }, method = RequestMethod.GET)
	public ModelAndView getBuscaExclusaoMedicamento() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("medicamentoPO", new MedicamentoPO());
		modelAndView.setViewName("medicamento/buscaExclusaoMedicamento");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/medicamento/resultadoBuscaExclusaoMedicamento" }, method = RequestMethod.GET)
	public ModelAndView getResultadoBuscaExclusaoMedicamento() {
		ModelAndView modelAndView = new ModelAndView();
		
		if (null == medicamentoBuscado){
			modelAndView.addObject("medicamentoBuscado", new MedicamentoPO());
			modelAndView.setViewName("medicamento/buscaExclusaoMedicamento");
		} 

		return modelAndView;
	}
	
	/**
	 * SETS
	 */
	
	@RequestMapping(value = "/medicamento/buscaExclusaoMedicamento", method = RequestMethod.POST)
	private ModelAndView setBuscaExclusaoMedicamento(@Valid MedicamentoPO medicamentoPO, BindingResult bindingResult) {
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
				modelAndView.setViewName("medicamento/resultadoBuscaExclusaoMedicamento");
			}
			
		} else if (null != nomeComercial && !"".equals(nomeComercial)) {
			
			medicamentosBuscados = medicamentoService.findMedicamentosByNomeComercial(nomeComercial);
			
			// Validar retorno
			if (medicamentosBuscados.isEmpty()) {
				
				bindingResult.rejectValue("idMedicamento", "error.user",
						"Não existe Medicamento cadastrado com esse Nome comercial.");
				
			} else if (medicamentosBuscados.stream().count() > 1) { // Se for maior que 1, retornar tela de Seleção de Medicamento
				
				modelAndView.addObject("medicamentosBuscados", medicamentosBuscados);
				modelAndView.setViewName("medicamento/listaBuscaExclusaoMedicamento");
				
			} else { // Retornar tela de exibição de medicamento
				
				modelAndView.addObject("medicamentoBuscado", medicamentoBuscado);
				modelAndView.setViewName("medicamento/resultadoBuscaExclusaoMedicamento");
				
			}
			
		}
		
		// Se o medicamento não for encontrado em nenhuma etapa
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("medicamento/buscaExclusaoMedicamento");
		} 

		return modelAndView;
	}
	
	@RequestMapping(value = "/medicamento/resultadoBuscaExclusaoMedicamento", method = RequestMethod.POST)
	private ModelAndView setResultadoBuscaExclusaoMedicamento(@Valid MedicamentoPO medicamentoPO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()){
			modelAndView.setViewName("medicamento/resultadoBuscaExclusaoMedicamento");
		} else {
			medicamentoService.deleteMedicamento(medicamentoBuscado);
			modelAndView.addObject("medicamentoPO", new MedicamentoPO());
			modelAndView.setViewName("medicamento/buscaExclusaoMedicamento");
		}

		return modelAndView;
	}
	
	
	
}
