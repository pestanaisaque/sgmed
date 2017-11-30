/**
 * 
 */
package br.umc.sgmed.service.interf;

import org.springframework.mail.SimpleMailMessage;

/**
 * @author Isaque Pestana
 *
 */
public interface EmailService {
	public void sendEmail(SimpleMailMessage email);
}
