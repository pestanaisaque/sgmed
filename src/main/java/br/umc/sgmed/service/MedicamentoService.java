package br.umc.sgmed.service;

import br.umc.sgmed.po.MedicamentoPO;

public interface MedicamentoService {
	public MedicamentoPO findMedicamentoById(int idMedicamento);
	
	public MedicamentoPO findMedicamentoByNomeComercial(String nomeComercial);

	public MedicamentoPO findMedicamentoByPrincipioAtivo(String principioAtivo);

	public void saveMedicamento(MedicamentoPO medicamentoPO);

	public void updateMedicamento(MedicamentoPO medicamentoPO);

	public void deleteMedicamento(MedicamentoPO medicamentoPO);
}
