/**
 * 
 */
package br.umc.sgmed.controller.medicamento;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	private Boolean generico;
	private Boolean naoGenerico;

	@ModelAttribute ("generico")
	public Boolean isGenerico() {
		return generico;
	}
	
	@ModelAttribute ("naoGenerico")
	public Boolean isNaoGenerico() {
		return naoGenerico;
	}

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

		generico = new Boolean(false);
		naoGenerico = new Boolean(false);
		
		
		String nomeComercial = medicamentoPO.getNomeComercial();
		Integer idMedicamento = Integer
				.parseInt(nomeComercial.substring(nomeComercial.indexOf(":") + 2, nomeComercial.length()));

		if (null != idMedicamento && !"".equals(idMedicamento)) {

			medicamentoBuscado = medicamentoService.findMedicamentoById(idMedicamento);
			
			if (1 == medicamentoBuscado.getGenerico()) {
				generico = new Boolean(true);
			} else if (2 == medicamentoBuscado.getGenerico()) {
				naoGenerico = new Boolean(true);
			}
			
			modelAndView.addObject("medicamentoBuscado", medicamentoBuscado);
			modelAndView.addObject("generico", generico);
			modelAndView.addObject("naoGenerico", naoGenerico);
			modelAndView.setViewName("medicamento/resultadoBuscaMedicamento");

		}

		return modelAndView;
	}

}
