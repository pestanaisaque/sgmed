/**
 * 
 */
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

/**
 * @author Isaque Pestana
 *
 */

@Controller
public class BuscaMedicamentoController {
	@Autowired
	private MedicamentoService medicamentoService;

	private MedicamentoPO medicamentoBuscado;

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

		if (null == medicamentoBuscado) {
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
		
		String nomeComercial = medicamentoPO.getNomeComercial();
		Integer idMedicamento = Integer.parseInt(nomeComercial.substring(nomeComercial.indexOf(":")+2, nomeComercial.length()));
		

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

		} 

		// Se o medicamento não for encontrado em nenhuma etapa
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("medicamento/buscaMedicamento");
		}

		return modelAndView;
	}

}
