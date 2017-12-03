/**
 * 
 */
package br.umc.sgmed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.umc.sgmed.dao.PasswordTokenDAO;
import br.umc.sgmed.dto.ResultadoRecuperarSenhaDTO;
import br.umc.sgmed.po.PasswordResetTokenPO;
import br.umc.sgmed.po.UsuarioPO;
import br.umc.sgmed.service.interf.PasswordTokenService;
import br.umc.sgmed.service.interf.UsuarioService;

/**
 * @author Isaque Pestana
 *
 */

@Service("passwordTokenService")
public class PasswordTokenServiceImpl implements PasswordTokenService {
	@Autowired
	private PasswordTokenDAO passwordTokenDAO;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public PasswordResetTokenPO findPasswordResetTokenPOByToken(String token) {
		return passwordTokenDAO.findByToken(token);
	}

	@Override
	public void alterarSenhaUsuario(PasswordResetTokenPO passwordResetTokenPO, ResultadoRecuperarSenhaDTO recuperarSenhaDTO) {
		UsuarioPO usuario = passwordResetTokenPO.getUsuarioPO();

		// ATUALIZAR SENHA
		usuario.setSenha(bCryptPasswordEncoder.encode(recuperarSenhaDTO.getNovaSenha()));
		usuarioService.updateUsuario(usuario);
		
		// DELETAR TOKEN
		passwordTokenDAO.delete(passwordResetTokenPO);
		
	}
	
	

	

}
