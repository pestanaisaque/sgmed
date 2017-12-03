/**
 * 
 */
package br.umc.sgmed.service.interf;

import br.umc.sgmed.dto.ResultadoRecuperarSenhaDTO;
import br.umc.sgmed.po.PasswordResetTokenPO;

/**
 * @author Isaque Pestana
 *
 */
public interface PasswordTokenService {
	public PasswordResetTokenPO findPasswordResetTokenPOByToken(String token);
	
	public void alterarSenhaUsuario(PasswordResetTokenPO passwordResetTokenPO, ResultadoRecuperarSenhaDTO recuperarSenhaDTO);
}
