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

	PacientePO findPacienteByCpfPaciente(String cpfPaciente);

	@Query("SELECT p FROM PacientePO p WHERE UPPER(p.nomePaciente) LIKE CONCAT('%', UPPER(:nomePaciente), '%')")
	List<PacientePO> findAllByNomePaciente(@Param("nomePaciente") String nomePaciente);

	@Query("SELECT p FROM PacientePO p WHERE UPPER(p.cpfPaciente) LIKE CONCAT('%', UPPER(:cpfPaciente), '%')")
	List<PacientePO> findAllByCpfPaciente(@Param("cpfPaciente") String cpfPaciente);
}
