/**
 * 
 */
package br.umc.sgmed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.umc.sgmed.dao.ItemEstoqueDAO;
import br.umc.sgmed.dao.PacienteDAO;
import br.umc.sgmed.dao.SaidaEstoqueDAO;
import br.umc.sgmed.dto.SaidaEstoqueDTO;
import br.umc.sgmed.po.ItemEstoquePO;
import br.umc.sgmed.po.SaidaEstoquePO;
import br.umc.sgmed.service.interf.SaidaEstoqueService;
import br.umc.sgmed.utils.DateUtils;

/**
 * @author Isaque Pestana
 *
 */

@Service("saidaEstoqueService")
public class SaidaEstoqueServiceImpl implements SaidaEstoqueService {
	@Autowired
	private ItemEstoqueDAO itemEstoqueDAO;

	@Autowired
	private PacienteDAO pacienteDAO;

	@Autowired
	private SaidaEstoqueDAO saidaEstoqueDAO;

	@Override
	public void salvarSaidaEstoque(SaidaEstoqueDTO saidaEstoqueDTO) {
		SaidaEstoquePO saidaEstoquePO = new SaidaEstoquePO();

		// item estoque
		saidaEstoquePO.setItemEstoquePO(itemEstoqueDAO.findOne(saidaEstoqueDTO.getItemEstoquePO().getIdItemEstoque()));

		// paciente
		saidaEstoquePO
				.setPacientePO(pacienteDAO.findPacienteByCpfPaciente(saidaEstoqueDTO.getPacientePO().getCpfPaciente()));

		// qtd anterior
		saidaEstoquePO.setQtdAnterior(saidaEstoquePO.getItemEstoquePO().getQtdItemEmEstoque());

		// qtd retirada
		saidaEstoquePO.setQtdRetirada(saidaEstoqueDTO.getQtdSaida());

		// dt retirada
		saidaEstoquePO.setDataDaRetirada(DateUtils.getDataAtual());
		
		saidaEstoqueDAO.save(saidaEstoquePO);
	}

	@Override
	public void atualizarEstoque(SaidaEstoqueDTO saidaEstoqueDTO) {
		ItemEstoquePO itemEstoquePO = itemEstoqueDAO.findOne(saidaEstoqueDTO.getItemEstoquePO().getIdItemEstoque());

		Long novaQtdEstoque = itemEstoquePO.getQtdItemEmEstoque() - saidaEstoqueDTO.getQtdSaida();

		itemEstoquePO.setQtdItemEmEstoque(novaQtdEstoque);

		itemEstoqueDAO.save(itemEstoquePO);

	}

}
