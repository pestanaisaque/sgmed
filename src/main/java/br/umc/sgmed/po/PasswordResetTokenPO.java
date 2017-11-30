/**
 * 
 */
package br.umc.sgmed.po;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Isaque Pestana
 *
 */

@Entity
@Table(name = "password_reset_token")
public class PasswordResetTokenPO {

	private static final int EXPIRATION = 60 * 24; // 1 DIA

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String token;

	@OneToOne(targetEntity = UsuarioPO.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "usuario_id")
	private UsuarioPO usuarioPO;

	private Date expiryDate;

	/**
	 * 
	 */
	public PasswordResetTokenPO() {
	}

	/**
	 * @param token
	 * @param usuarioPO
	 */
	public PasswordResetTokenPO(String token, UsuarioPO usuarioPO) {
		this.token = token;
		this.usuarioPO = usuarioPO;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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

	/**
	 * @return the usuarioPO
	 */
	public UsuarioPO getUsuarioPO() {
		return usuarioPO;
	}

	/**
	 * @param usuarioPO
	 *            the usuarioPO to set
	 */
	public void setUsuarioPO(UsuarioPO usuarioPO) {
		this.usuarioPO = usuarioPO;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

}
