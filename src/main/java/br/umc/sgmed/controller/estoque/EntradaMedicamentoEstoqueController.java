/**
 * 
 */
package br.umc.sgmed.controller.estoque;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.umc.sgmed.po.ItemEstoquePO;
import br.umc.sgmed.po.MedicamentoPO;
import br.umc.sgmed.service.interf.MedicamentoService;

/**
 * @author Isaque Pestana
 *
 */

@Controller
public class EntradaMedicamentoEstoqueController {
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
	 * GET
	 */

	@RequestMapping(value = { "/estoque/entrada/consultaMedicamentoParaEntradaEstoque" }, method = RequestMethod.GET)
	public ModelAndView getConsultaMedicamentoParaEntradaEstoque() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("medicamentoPO", new MedicamentoPO());
		modelAndView.setViewName("estoque/entrada/consultaMedicamentoParaEntradaEstoque");
		return modelAndView;
	}

	@RequestMapping(value = {
			"/estoque/entrada/resultadoConsultaMedicamentoParaEntradaEstoque" }, method = RequestMethod.GET)
	public ModelAndView getResultadoConsultaMedicamentoParaEntradaEstoque() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("estoque/entrada/resultadoConsultaMedicamentoParaEntradaEstoque");
		return modelAndView;
	}

	/**
	 * POST
	 */

	@RequestMapping(value = "/estoque/entrada/consultaMedicamentoParaEntradaEstoque", method = RequestMethod.POST)
	private ModelAndView setConsultaMedicamentoParaEntradaEstoque(@Valid MedicamentoPO medicamentoPO,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		generico = new Boolean(false);
		naoGenerico = new Boolean(false);

		try {

			medicamentoBuscado = medicamentoService.findMedicamentoById(medicamentoPO.getIdMedicamento());

			if (1 == medicamentoBuscado.getGenerico()) {
				generico = new Boolean(true);
			} else if (2 == medicamentoBuscado.getGenerico()) {
				naoGenerico = new Boolean(true);
			}

			modelAndView.addObject("medicamentoBuscado", medicamentoBuscado);
			modelAndView.addObject("novoItemEstoque", new ItemEstoquePO());
			modelAndView.addObject("generico", generico);
			modelAndView.addObject("naoGenerico", naoGenerico);
			modelAndView.setViewName("estoque/entrada/resultadoConsultaMedicamentoParaEntradaEstoque");

		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("medicamentoPO", new MedicamentoPO());
			modelAndView.setViewName("estoque/entrada/consultaMedicamentoParaEntradaEstoque");
		}

		return modelAndView;
	}

}
