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
public class ExclusaoMedicamentoController {

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

	@RequestMapping(value = { "/medicamento/buscaExclusaoMedicamento" }, method = RequestMethod.GET)
	public ModelAndView getBuscaExclusaoMedicamento() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("medicamentoPO", new MedicamentoPO());
		modelAndView.setViewName("medicamento/buscaExclusaoMedicamento");
		return modelAndView;
	}

	/**
	 * SETS
	 */

	@RequestMapping(value = "/medicamento/buscaExclusaoMedicamento", method = RequestMethod.POST)
	private ModelAndView setBuscaExclusaoMedicamento(@Valid MedicamentoPO medicamentoPO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			String nomeComercial = medicamentoPO.getNomeComercial();
			Integer idMedicamento = Integer.parseInt(nomeComercial);
			// nomeComercial.substring(nomeComercial.indexOf(":") + 2,
			// nomeComercial.length()));

			if (null != idMedicamento && !"".equals(idMedicamento)) {

				medicamentoBuscado = medicamentoService.findMedicamentoById(idMedicamento);

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
				modelAndView.setViewName("medicamento/resultadoBuscaExclusaoMedicamento");

			}
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("medicamentoPO", new MedicamentoPO());
			modelAndView.setViewName("medicamento/buscaExclusaoMedicamento");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/medicamento/resultadoBuscaExclusaoMedicamento", method = RequestMethod.POST)
	private ModelAndView setResultadoBuscaExclusaoMedicamento(@Valid MedicamentoPO medicamentoPO,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("medicamento/resultadoBuscaExclusaoMedicamento");
		} else {
			medicamentoService.deleteMedicamento(medicamentoBuscado);
			modelAndView.addObject("medicamentoPO", new MedicamentoPO());
			modelAndView.setViewName("medicamento/buscaExclusaoMedicamento");
		}

		return modelAndView;
	}

}
