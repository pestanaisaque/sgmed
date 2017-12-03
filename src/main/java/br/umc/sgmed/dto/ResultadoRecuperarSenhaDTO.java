/**
 * 
 */
package br.umc.sgmed.dto;

/**
 * @author Isaque Pestana
 *
 */
public class ResultadoRecuperarSenhaDTO {
	private String novaSenha;
	private String confirmacaoNovaSenha;
	private String token;

	/**
	 * 
	 */
	public ResultadoRecuperarSenhaDTO() {
	}

	/**
	 * @param novaSenha
	 * @param confirmacaoNovaSenha
	 */
	public ResultadoRecuperarSenhaDTO(String novaSenha, String confirmacaoNovaSenha, String token) {
		this.novaSenha = novaSenha;
		this.confirmacaoNovaSenha = confirmacaoNovaSenha;
		this.token = token;
	}

	/**
	 * @return the novaSenha
	 */
	public String getNovaSenha() {
		return novaSenha;
	}

	/**
	 * @param novaSenha
	 *            the novaSenha to set
	 */
	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	/**
	 * @return the confirmacaoNovaSenha
	 */
	public String getConfirmacaoNovaSenha() {
		return confirmacaoNovaSenha;
	}

	/**
	 * @param confirmacaoNovaSenha
	 *            the confirmacaoNovaSenha to set
	 */
	public void setConfirmacaoNovaSenha(String confirmacaoNovaSenha) {
		this.confirmacaoNovaSenha = confirmacaoNovaSenha;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

}
