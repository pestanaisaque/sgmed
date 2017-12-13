package br.umc.sgmed.controller.estoque.rest;
/**
 * 
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.umc.sgmed.dto.SaidaEstoqueDTO;
import br.umc.sgmed.po.ItemEstoquePO;
import br.umc.sgmed.po.PacientePO;
import br.umc.sgmed.response.Response;
import br.umc.sgmed.service.interf.ItemEstoqueService;
import br.umc.sgmed.service.interf.PacienteService;
import br.umc.sgmed.service.interf.SaidaEstoqueService;

/**
 * @author Isaque Pestana
 *
 */

@Controller
// @RequestMapping("/api/saidaMedicamentoEstoque")
public class SaidaMedicamentoEstoqueRestController {

	@Autowired
	private ItemEstoqueService itemEstoqueService;

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private SaidaEstoqueService saidaEstoqueService;

	private SaidaEstoqueDTO saidaDTO;
	private Boolean generico;
	private Boolean naoGenerico;

	@ModelAttribute("saidaDTO")
	public SaidaEstoqueDTO getSaidaDTO() {
		return saidaDTO;
	}

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

	@RequestMapping(value = { "/estoque/saida/buscaParaSaidaEstoque" }, method = RequestMethod.GET)
	public ModelAndView getBuscaParaSaidaEstoque() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("saidaEstoqueDTO", new SaidaEstoqueDTO());
		modelAndView.setViewName("estoque/saida/buscaParaSaidaEstoque");
		return modelAndView;
	}

	@RequestMapping(value = { "/estoque/saida/resultadoBuscaParaSaidaEstoque" }, method = RequestMethod.GET)
	public ModelAndView getResultadoConsultaMedicamentoEstoque() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("saidaDTO", saidaDTO);
		modelAndView.setViewName("estoque/saida/resultadoBuscaParaSaidaEstoque");
		return modelAndView;
	}

	/**
	 * POST
	 */

	@RequestMapping(value = "/api/saidaMedicamentoEstoque/retirarMedicamentoEstoque", method = RequestMethod.POST)
	public @ResponseBody Response retirarMedicamentoEstoque(@RequestBody SaidaEstoqueDTO saidaEstoqueDTO) {

		Response response;

		try {

			saidaEstoqueService.salvarSaidaEstoque(saidaEstoqueDTO);

			saidaEstoqueService.atualizarEstoque(saidaEstoqueDTO);

			response = new Response("OK", saidaEstoqueDTO);
		} catch (Exception e) {
			response = new Response("NOK", saidaEstoqueDTO);
		}

		return response;
	}

	@RequestMapping(value = "/api/saidaMedicamentoEstoque/buscarParaSaidaEstoque", method = RequestMethod.POST)
	public @ResponseBody Response buscarParaSaidaEstoque(@RequestBody SaidaEstoqueDTO saidaEstoqueDTO) {

		Response response;

		try {

			generico = new Boolean(false);
			naoGenerico = new Boolean(false);

			// Capturar id do item
			Integer lote = saidaEstoqueDTO.getItemEstoquePO().getIdItemEstoque();

			// Capturar cpf do paciente
			String cpfPaciente = saidaEstoqueDTO.getPacientePO().getCpfPaciente();

			ItemEstoquePO itemBuscado = itemEstoqueService.findItemEstoqueById(lote);
			PacientePO pacienteBuscado = pacienteService.findPacienteByCpfPaciente(cpfPaciente);

			// Validar quantidade do medicamento em lote
			Long qtdMedicamento = itemBuscado.getQtdItemEmEstoque();

			if (qtdMedicamento < 1) {
				response = new Response("NOK_QTD", pacienteBuscado);
			} else {
				if (1 == itemBuscado.getMedicamentoPO().getGenerico()) {
					generico = new Boolean(true);
				} else if (2 == itemBuscado.getMedicamentoPO().getGenerico()) {
					naoGenerico = new Boolean(true);
				}

				saidaDTO = new SaidaEstoqueDTO(itemBuscado, pacienteBuscado);
				response = new Response("OK", saidaDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response = new Response("NOK", saidaEstoqueDTO);
		}

		return response;
	}
}
