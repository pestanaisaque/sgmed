package br.umc.sgmed.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.umc.sgmed.po.MedicamentoPO;

@Repository("medicamentoRepository")
public interface MedicamentoDAO extends JpaRepository<MedicamentoPO, Integer> {
	MedicamentoPO findByNomeComercial(String nomeComercial);

	MedicamentoPO findByPrincipioAtivo(String principioAtivo);
}
