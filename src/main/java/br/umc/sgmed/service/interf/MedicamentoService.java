package br.umc.sgmed.service.interf;

import java.util.List;

import br.umc.sgmed.po.MedicamentoPO;

public interface MedicamentoService {
	public MedicamentoPO findMedicamentoById(int idMedicamento);
	
	public List<MedicamentoPO> findMedicamentosByNomeComercial(String nomeComercial);

	public List<MedicamentoPO> findAllMedicamentos();
	
	public MedicamentoPO findMedicamentoByPrincipioAtivo(String principioAtivo);

	public void saveMedicamento(MedicamentoPO medicamentoPO);

	public void updateMedicamento(MedicamentoPO medicamentoPO);

	public void deleteMedicamento(MedicamentoPO medicamentoPO);
}
