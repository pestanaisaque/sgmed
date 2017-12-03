/**
 * 
 */
package br.umc.sgmed.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.umc.sgmed.po.PasswordResetTokenPO;

/**
 * @author Isaque Pestana
 *
 */
@Repository("passwordTokenRepository")
public interface PasswordTokenDAO extends JpaRepository<PasswordResetTokenPO, Long> {
	PasswordResetTokenPO findByToken(String token);
}
