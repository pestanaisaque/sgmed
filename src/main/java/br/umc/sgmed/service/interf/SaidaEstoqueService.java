package br.umc.sgmed.service.interf;

import java.util.List;

import br.umc.sgmed.dto.SaidaEstoqueDTO;
import br.umc.sgmed.po.PacientePO;
import br.umc.sgmed.po.SaidaEstoquePO;

public interface SaidaEstoqueService {
	public void salvarSaidaEstoque(SaidaEstoqueDTO saidaEstoqueDTO);
	
	public void atualizarEstoque(SaidaEstoqueDTO saidaEstoqueDTO);
	
	public List<SaidaEstoquePO> findSaidasByIdPaciente(Integer idPaciente);
}
