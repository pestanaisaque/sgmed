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
public class ExclusaoMedicamentoEstoqueController {
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

	@RequestMapping(value = { "/estoque/administracao/buscaExclusaoMedicamentoEstoque" }, method = RequestMethod.GET)
	public ModelAndView getExclusaoMedicamentoEstoque() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemEstoquePO", new ItemEstoquePO());
		modelAndView.setViewName("estoque/administracao/buscaExclusaoMedicamentoEstoque");
		return modelAndView;
	}

	@RequestMapping(value = {
			"/estoque/administracao/resultadoBuscaExclusaoMedicamentoEstoque" }, method = RequestMethod.GET)
	public ModelAndView getResultadoAlteracaoMedicamentoEstoque() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemBuscado", itemBuscado);
		modelAndView.setViewName("estoque/administracao/resultadoBuscaExclusaoMedicamentoEstoque");
		return modelAndView;
	}

	/**
	 * POST
	 */

	@RequestMapping(value = "/estoque/administracao/buscaExclusaoMedicamentoEstoque", method = RequestMethod.POST)
	private ModelAndView setAlteracaoMedicamentoEstoque(@Valid ItemEstoquePO itemEstoquePO,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		generico = new Boolean(false);
		naoGenerico = new Boolean(false);

		String nomeComercial = itemEstoquePO.getMedicamentoPO().getNomeComercial();

		Integer lote = Integer
				.parseInt(nomeComercial.substring(nomeComercial.indexOf("d:") + 3, nomeComercial.length()));

		if (null != lote && !"".equals(lote)) {

			itemBuscado = itemEstoqueService.findItemEstoqueById(lote);

			if (1 == itemBuscado.getMedicamentoPO().getGenerico()) {
				generico = new Boolean(true);
			} else if (2 == itemBuscado.getMedicamentoPO().getGenerico()) {
				naoGenerico = new Boolean(true);
			}

			modelAndView.addObject("itemBuscado", itemBuscado);
			modelAndView.addObject("generico", generico);
			modelAndView.addObject("naoGenerico", naoGenerico);
			modelAndView.setViewName("estoque/administracao/resultadoBuscaExclusaoMedicamentoEstoque");

		}

		return modelAndView;
	}

}
