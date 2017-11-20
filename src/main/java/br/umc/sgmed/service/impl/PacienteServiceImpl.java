/**
 * 
 */
package br.umc.sgmed.service.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.umc.sgmed.dao.PacienteDAO;
import br.umc.sgmed.po.PacientePO;
import br.umc.sgmed.service.interf.PacienteService;

/**
 * @author Isaque Pestana
 *
 */

@Service("pacienteService")
public class PacienteServiceImpl implements PacienteService {

	@Autowired
	private PacienteDAO pacienteDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.umc.sgmed.service.interf.PacienteService#findPacienteByIdPaciente(java
	 * .lang. Integer)
	 */
	@Override
	public PacientePO findPacienteByIdPaciente(Integer idPaciente) {
		return pacienteDAO.findOne(idPaciente);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.umc.sgmed.service.interf.PacienteService#findPacienteByCpf(java.lang.
	 * String)
	 */
	@Override
	public PacientePO findPacienteByCpfPaciente(String cpfPaciente) {
		return pacienteDAO.findPacienteByCpfPaciente(cpfPaciente);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.umc.sgmed.service.interf.PacientesService#findPacienteByCpf(java.lang.
	 * String)
	 */
	@Override
	public List<PacientePO> findPacientesByCpfPaciente(String cpfPaciente) {
		return pacienteDAO.findAllByCpfPaciente(cpfPaciente);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.umc.sgmed.service.interf.PacienteService#findPacienteByNome(java.lang.
	 * String)
	 */
	@Override
	public List<PacientePO> findPacientesByNome(String nomePaciente) {
		return pacienteDAO.findAllByNomePaciente(nomePaciente);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.umc.sgmed.service.interf.PacienteService#savePaciente(br.umc.sgmed.po.
	 * PacientePO)
	 */
	@Override
	public void savePaciente(PacientePO pacientePO) {
		pacienteDAO.save(pacientePO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.umc.sgmed.service.interf.PacienteService#updatePaciente(br.umc.sgmed.
	 * po.PacientePO)
	 */
	@Override
	public void updatePaciente(PacientePO pacientePO) {
		savePaciente(pacientePO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.umc.sgmed.service.interf.PacienteService#deletePaciente(br.umc.sgmed.
	 * po.PacientePO)
	 */
	@Override
	public void deletePaciente(PacientePO pacientePO) {
		pacienteDAO.delete(pacientePO);
	}

}
