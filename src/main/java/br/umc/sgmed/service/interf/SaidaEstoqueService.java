package br.umc.sgmed.service.interf;

import br.umc.sgmed.dto.SaidaEstoqueDTO;

public interface SaidaEstoqueService {
	public void salvarSaidaEstoque(SaidaEstoqueDTO saidaEstoqueDTO);
	
	public void atualizarEstoque(SaidaEstoqueDTO saidaEstoqueDTO);
}
