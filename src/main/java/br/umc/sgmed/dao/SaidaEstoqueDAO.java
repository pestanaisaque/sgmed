/**
 * 
 */
package br.umc.sgmed.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.umc.sgmed.po.SaidaEstoquePO;

/**
 * @author Isaque Pestana
 *
 */

@Repository("saidaEstoqueRepository")
public interface SaidaEstoqueDAO extends JpaRepository<SaidaEstoquePO, Integer> {
	@Query("SELECT s FROM SaidaEstoquePO s, PacientePO p WHERE s.pacientePO.idPaciente = p.idPaciente AND s.pacientePO.idPaciente = :idPaciente ORDER BY s.dataDaRetirada")
	List<SaidaEstoquePO> findByIdPaciente(@Param("idPaciente") Integer idPaciente);
}
