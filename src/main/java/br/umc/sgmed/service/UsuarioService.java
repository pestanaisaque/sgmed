package br.umc.sgmed.service;

import br.umc.sgmed.po.UsuarioPO;

public interface UsuarioService {
	public UsuarioPO findUsuarioByLogin(String email);

	public void saveUsuario(UsuarioPO user);
}