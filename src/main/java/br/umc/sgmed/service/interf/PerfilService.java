package br.umc.sgmed.service.interf;

import java.util.List;

import br.umc.sgmed.po.PerfilPO;

public interface PerfilService {
	public PerfilPO findPerfilByPerfil(String perfil);
	
	public List<PerfilPO> findAllPerfis();
}
