/**
 * 
 */
package br.umc.sgmed.controller.medicamento.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.umc.sgmed.po.MedicamentoPO;
import br.umc.sgmed.service.interf.MedicamentoService;

/**
 * @author Isaque Pestana
 *
 */

@Controller
@RequestMapping("/api/buscaMedicamento")
public class BuscaMedicamentoRestController {
	@Autowired
	private MedicamentoService medicamentoService;

	private List<MedicamentoPO> medicamentosBuscados;

	@RequestMapping(value = "/listarNomeComercial", method = RequestMethod.GET)
	public @ResponseBody List<MedicamentoPO> getMedicamentosPorNomeComercial(
			@RequestParam("term") String nomeComercial) {

		medicamentosBuscados = medicamentoService.findMedicamentosByNomeComercial(nomeComercial);

		return medicamentosBuscados;
	}

}
