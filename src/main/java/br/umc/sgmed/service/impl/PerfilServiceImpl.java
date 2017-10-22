package br.umc.sgmed.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.umc.sgmed.dao.PerfilDAO;
import br.umc.sgmed.po.PerfilPO;
import br.umc.sgmed.service.interf.PerfilService;

@Service("perfilService")
public class PerfilServiceImpl implements PerfilService {
	
	@Autowired
	private PerfilDAO perfilDAO;
	
	@Override
	public PerfilPO findPerfilByPerfil(String perfil) {
		return perfilDAO.findByPerfil(perfil);
	}

	@Override
	public List<PerfilPO> findAllPerfis() {
		return perfilDAO.findAll();
	}

}
