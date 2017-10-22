package br.umc.sgmed.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.umc.sgmed.dao.MedicamentoDAO;
import br.umc.sgmed.po.MedicamentoPO;
import br.umc.sgmed.service.interf.MedicamentoService;

@Service("medicamentoService")
public class MedicamentoServiceImpl implements MedicamentoService {

	@Autowired
	private MedicamentoDAO medicamentoRepository;

	@Override
	public MedicamentoPO findMedicamentoById(int idMedicamento) {
		return medicamentoRepository.findOne(idMedicamento);
	}

	@Override
	public List<MedicamentoPO> findMedicamentosByNomeComercial(String nomeComercial) {
		return medicamentoRepository.findAllByNomeComercial(nomeComercial);
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
