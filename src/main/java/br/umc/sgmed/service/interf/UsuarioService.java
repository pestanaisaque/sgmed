package br.umc.sgmed.service.interf;

import java.util.List;

import br.umc.sgmed.po.UsuarioPO;

public interface UsuarioService {
	public UsuarioPO findUsuarioByLogin(String email);

	public void saveUsuario(UsuarioPO user);

	public void updateUsuario(UsuarioPO user);

	public void deleteUsuario(UsuarioPO user);

	public List<UsuarioPO> findUsuariosByNome(String nomeUsuario);
}