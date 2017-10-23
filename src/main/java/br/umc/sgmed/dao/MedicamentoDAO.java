package br.umc.sgmed.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.umc.sgmed.po.MedicamentoPO;

@Repository("medicamentoRepository")
public interface MedicamentoDAO extends JpaRepository<MedicamentoPO, Integer> {
	
	@Query("SELECT m FROM MedicamentoPO m WHERE UPPER(m.nomeComercial) LIKE CONCAT('%', UPPER(:nomeComercial), '%')")
	List<MedicamentoPO> findAllByNomeComercial(@Param("nomeComercial") String nomeComercial);

	MedicamentoPO findByPrincipioAtivo(String principioAtivo);
}
