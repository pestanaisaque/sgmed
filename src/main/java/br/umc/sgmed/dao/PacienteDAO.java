/**
 * 
 */
package br.umc.sgmed.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.umc.sgmed.po.PacientePO;

/**
 * @author Isaque Pestana
 *
 */
@Repository("pacienteRepository")
public interface PacienteDAO extends JpaRepository<PacientePO, Integer> {
	@Query("SELECT p FROM PacientePO p WHERE UPPER(p.nomePaciente) LIKE CONCAT('%', UPPER(:nomePaciente), '%')")
	List<PacientePO> findAllByNomePaciente(@Param("nomePaciente") String nomePaciente);
	
	@Query("SELECT p FROM PacientePO p WHERE UPPER(p.sobrenomePaciente) LIKE CONCAT('%', UPPER(:sobrenomePaciente), '%')")
	List<PacientePO> findAllBySobrenomePaciente(@Param("sobrenomePaciente") String sobrenomePaciente);
	
}
