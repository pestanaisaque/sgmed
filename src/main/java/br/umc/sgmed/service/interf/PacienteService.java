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

	public PacientePO findPacienteByIdPaciente(Integer idPaciente);

	public PacientePO findPacienteByCpfPaciente(String cpfPaciente);

	public List<PacientePO> findPacientesByCpfPaciente(String cpfPaciente);

	public List<PacientePO> findPacientesByNome(String nomePaciente);

	public void savePaciente(PacientePO pacientePO);

	public void updatePaciente(PacientePO pacientePO);

	public void deletePaciente(PacientePO pacientePO);
}
