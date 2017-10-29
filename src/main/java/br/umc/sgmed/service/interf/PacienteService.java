/**
 * 
 */
package br.umc.sgmed.service.interf;

import java.util.List;

import br.umc.sgmed.po.PacientePO;

/**
 * @author Isaque Pestana
 *
 */
public interface PacienteService {
	public PacientePO findPacienteById(int idPaciente);

	public List<PacientePO> findPacienteByNome(String nomePaciente);

	public List<PacientePO> findPacienteBySobrenome(String sobrenomePaciente);

	public void savePaciente(PacientePO pacientePO);

	public void updatePaciente(PacientePO pacientePO);

	public void deletePaciente(PacientePO pacientePO);
}
