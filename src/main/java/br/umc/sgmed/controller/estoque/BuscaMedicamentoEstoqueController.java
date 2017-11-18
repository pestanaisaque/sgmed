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
import br.umc.sgmed.service.interf.ItemEstoqueService;

/**
 * @author Isaque Pestana
 *
 */

@Controller
public class BuscaMedicamentoEstoqueController {
	@Autowired
	private ItemEstoqueService itemEstoqueService;

	private ItemEstoquePO itemBuscado;

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

	@RequestMapping(value = { "/estoque/consulta/consultaMedicamentoEstoque" }, method = RequestMethod.GET)
	public ModelAndView getConsultaMedicamentoEstoque() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemEstoquePO", new ItemEstoquePO());
		modelAndView.setViewName("estoque/consulta/consultaMedicamentoEstoque");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/estoque/consulta/resultadoConsultaMedicamentoEstoque" }, method = RequestMethod.GET)
	public ModelAndView getResultadoConsultaMedicamentoEstoque() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("estoque/consulta/resultadoConsultaMedicamentoEstoque");
		return modelAndView;
	}

	/**
	 * POST
	 */

	@RequestMapping(value = "/estoque/consulta/consultaMedicamentoEstoque", method = RequestMethod.POST)
	private ModelAndView setConsultaMedicamentoEstoque(@Valid ItemEstoquePO itemEstoquePO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		generico = new Boolean(false);
		naoGenerico = new Boolean(false);

		String nomeComercial = itemEstoquePO.getMedicamentoPO().getNomeComercial();
		Integer idItemEstoque = Integer
				.parseInt(nomeComercial.substring(nomeComercial.indexOf("d:") + 3, nomeComercial.length()));

		if (null != idItemEstoque && !"".equals(idItemEstoque)) {

			itemBuscado = itemEstoqueService.findItemEstoqueById(idItemEstoque);

			if (1 == itemBuscado.getMedicamentoPO().getGenerico()) {
				generico = new Boolean(true);
			} else if (2 == itemBuscado.getMedicamentoPO().getGenerico()) {
				naoGenerico = new Boolean(true);
			}

			modelAndView.addObject("itemEstoqueBuscado", itemBuscado);
			modelAndView.addObject("generico", generico);
			modelAndView.addObject("naoGenerico", naoGenerico);
			modelAndView.setViewName("estoque/consulta/resultadoConsultaMedicamentoEstoque");

		}

		return modelAndView;
	}

}
