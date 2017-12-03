/**
 * 
 */
package br.umc.sgmed.controller.relatorio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.umc.sgmed.dto.RelatorioMedicamentoDTO;
import br.umc.sgmed.po.ItemEstoquePO;
import br.umc.sgmed.po.MedicamentoPO;
import br.umc.sgmed.po.PacientePO;
import br.umc.sgmed.po.SaidaEstoquePO;
import br.umc.sgmed.service.interf.ItemEstoqueService;
import br.umc.sgmed.service.interf.PacienteService;
import br.umc.sgmed.service.interf.SaidaEstoqueService;

/**
 * @author Isaque Pestana
 *
 */
@Controller
public class RelatorioController {

	@Autowired
	private SaidaEstoqueService saidaEstoqueService;

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private ItemEstoqueService itemEstoqueService;

	private List<SaidaEstoquePO> saidaBuscada;

	private PacientePO pacienteBuscado;

	private List<RelatorioMedicamentoDTO> relatorioDTO;

	/**
	 * GET
	 */

	@RequestMapping(value = { "/relatorio/buscaRelatorioPaciente" }, method = RequestMethod.GET)
	public ModelAndView getRelatorioPaciente() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pacientePO", new PacientePO());
		modelAndView.setViewName("relatorio/buscaRelatorioPaciente");
		return modelAndView;
	}

	@RequestMapping(value = { "/relatorio/buscaRelatorioMedicamento" }, method = RequestMethod.GET)
	public ModelAndView getRelatorioMedicamento() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemEstoquePO", new ItemEstoquePO());
		modelAndView.setViewName("relatorio/buscaRelatorioMedicamento");
		return modelAndView;
	}

	/**
	 * POST
	 */

	@RequestMapping(value = "/relatorio/buscaRelatorioPaciente", method = RequestMethod.POST)
	private ModelAndView setRelatorioPaciente(@Valid PacientePO pacientePO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		String nomePaciente = (pacientePO.getNomePaciente() != null) ? pacientePO.getNomePaciente()
				: pacientePO.getCpfPaciente();

		Integer idPaciente = Integer
				.parseInt(nomePaciente.substring(nomePaciente.indexOf(":") + 2, nomePaciente.length()));

		if (null != idPaciente && !"".equals(idPaciente)) {

			pacienteBuscado = pacienteService.findPacienteByIdPaciente(idPaciente);

			saidaBuscada = saidaEstoqueService.findSaidasByIdPaciente(pacienteBuscado.getIdPaciente());

			modelAndView.addObject("saidaBuscada", saidaBuscada);
			modelAndView.addObject("pacienteBuscado", pacienteBuscado);
			modelAndView.setViewName("relatorio/resultadoBuscaRelatorioPaciente");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/relatorio/buscaRelatorioMedicamento", method = RequestMethod.POST)
	private ModelAndView setRelatorioMedicamento(@Valid ItemEstoquePO itemEstoquePO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		List<ItemEstoquePO> itensBuscados = new ArrayList<>();

		// BUSCAR POR NOME COMERCIAL
		if (null != itemEstoquePO.getMedicamentoPO().getNomeComercial()
				&& !"".equals(itemEstoquePO.getMedicamentoPO().getNomeComercial())) {

			String nomeComercial = itemEstoquePO.getMedicamentoPO().getNomeComercial();
			Integer idItemEstoque = Integer
					.parseInt(nomeComercial.substring(nomeComercial.indexOf("d:") + 3, nomeComercial.length()));

			if (null != idItemEstoque && !"".equals(idItemEstoque)) {

				ItemEstoquePO itemBuscado = itemEstoqueService.findItemEstoqueById(idItemEstoque);

				MedicamentoPO medicamentoBuscado = itemBuscado.getMedicamentoPO();

				Integer idMedicamento = medicamentoBuscado.getIdMedicamento();

				itensBuscados = itemEstoqueService.findItensByIdMedicamento(idMedicamento);

				// montar objeto de relatorio para cada item buscado
				relatorioDTO = itensBuscados.stream().map(item -> new RelatorioMedicamentoDTO(item))
						.collect(Collectors.toList());

				modelAndView.addObject("medicamentoBuscado", medicamentoBuscado);
				modelAndView.addObject("relatorioDTO", relatorioDTO);
				modelAndView.setViewName("relatorio/resultadoBuscaRelatorioMedicamento");

			}
		} else { // BUSCAR TODOS MEDICAMENTOS

			itensBuscados = itemEstoqueService.findAllItens();

			// montar objeto de relatorio para cada item buscado
			relatorioDTO = itensBuscados.stream().map(item -> new RelatorioMedicamentoDTO(item))
					.collect(Collectors.toList());

			modelAndView.addObject("relatorioDTO", relatorioDTO);
			modelAndView.setViewName("relatorio/resultadoBuscaRelatorioMedicamento");
		}
		return modelAndView;
	}
}
