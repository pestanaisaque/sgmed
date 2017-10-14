package br.umc.sgmed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.umc.sgmed.dao.MedicamentoRepository;
import br.umc.sgmed.po.MedicamentoPO;

@Service("medicamentoService")
public class MedicamentoServiceImpl implements MedicamentoService {

	@Autowired
	private MedicamentoRepository medicamentoRepository;

	@Override
	public MedicamentoPO findMedicamentoById(int idMedicamento) {
		return medicamentoRepository.findOne(idMedicamento);
	}

	@Override
	public MedicamentoPO findMedicamentoByNomeComercial(String nomeComercial) {
		return medicamentoRepository.findByNomeComercial(nomeComercial);
	}

	@Override
	public MedicamentoPO findMedicamentoByPrincipioAtivo(String principioAtivo) {
		return medicamentoRepository.findByPrincipioAtivo(principioAtivo);
	}

	@Override
	public void saveMedicamento(MedicamentoPO medicamentoPO) {
		medicamentoRepository.save(medicamentoPO);
	}

	@Override
	public void updateMedicamento(MedicamentoPO medicamentoPO) {
		saveMedicamento(medicamentoPO);
	}

	@Override
	public void deleteMedicamento(MedicamentoPO medicamentoPO) {
		medicamentoRepository.delete(medicamentoPO);
	}

}
