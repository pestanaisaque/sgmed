package br.umc.sgmed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.umc.sgmed.dao.UsuarioDAO;
import br.umc.sgmed.po.UsuarioPO;
import br.umc.sgmed.service.interf.UsuarioService;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UsuarioPO findUsuarioByLogin(String login) {
		return usuarioDAO.findByLogin(login);
	}

	@Override
	public void saveUsuario(UsuarioPO usuarioPO) {
		usuarioPO.setSenha(bCryptPasswordEncoder.encode(usuarioPO.getSenha()));
		usuarioPO.setAtivo(1);
		usuarioDAO.save(usuarioPO);
	}

}