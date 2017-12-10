/**
 * 
 */
package br.umc.sgmed.controller.medicamento;

import java.util.List;

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
public class AlteracaoMedicamentoController {

	@Autowired
	private MedicamentoService medicamentoService;

	private MedicamentoPO medicamentoBuscado;

	private Boolean generico;
	private Boolean naoGenerico;

	@ModelAttribute("generico")
	public Boolean isGenerico() {
		return generico;
	}

	@ModelAttribute("naoGenerico")
	public Boolean isNaoGenerico() {
		return naoGenerico;
	}

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

	/**
	 * SETS
	 */

	@RequestMapping(value = "/medicamento/buscaAlteracaoMedicamento", method = RequestMethod.POST)
	private ModelAndView setBuscaAlteracaoMedicamento(@Valid MedicamentoPO medicamentoPO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		try {

			medicamentoBuscado = medicamentoService.findMedicamentoById(medicamentoPO.getIdMedicamento());

			if (1 == medicamentoBuscado.getGenerico()) {
				naoGenerico = new Boolean(false);
				generico = new Boolean(true);
			} else if (2 == medicamentoBuscado.getGenerico()) {
				generico = new Boolean(false);
				naoGenerico = new Boolean(true);
			}

			modelAndView.addObject("medicamentoBuscado", medicamentoBuscado);
			modelAndView.addObject("generico", generico);
			modelAndView.addObject("naoGenerico", naoGenerico);
			modelAndView.setViewName("medicamento/resultadoBuscaAlteracaoMedicamento");

		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("medicamentoPO", new MedicamentoPO());
			modelAndView.setViewName("medicamento/buscaAlteracaoMedicamento");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/medicamento/resultadoBuscaAlteracaoMedicamento", method = RequestMethod.POST)
	private ModelAndView setResultadoBuscaAlteracaoMedicamento(@Valid MedicamentoPO medicamentoPO,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		medicamentoPO.setIdMedicamento(medicamentoBuscado.getIdMedicamento());

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("medicamento/resultadoBuscaAlteracaoMedicamento");
		} else {
			medicamentoService.updateMedicamento(medicamentoPO);
			modelAndView.addObject("medicamentoBuscado", medicamentoPO);
			modelAndView.setViewName("medicamento/resultadoBuscaAlteracaoMedicamento");
		}

		return modelAndView;
	}

}
