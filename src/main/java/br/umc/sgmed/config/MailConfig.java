/**
 * 
 */
package br.umc.sgmed.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author Isaque Pestana
 *
 */

@Configuration
public class MailConfig {
	@Bean
	public JavaMailSender javaMailService() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

		javaMailSender.setJavaMailProperties(getMailProperties());
		javaMailSender.setPassword("sisgmed2017");
		
		return javaMailSender;
	}

	private Properties getMailProperties() {
		Properties properties = new Properties();
		
		properties.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP
		properties.put("mail.smtp.starttls.enable","true"); 
		properties.put("mail.smtp.host", "smtp.gmail.com"); //server SMTP do GMAIL
		properties.put("mail.smtp.auth", "true"); //ativa autenticacao
		properties.put("mail.smtp.user", "sisgmed@gmail.com"); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)
		properties.put("mail.debug", "true");
		properties.put("mail.smtp.port", "465"); //porta
		properties.put("mail.smtp.socketFactory.port", "465"); //mesma porta para o socket
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallback", "false");
		
		return properties;
	}
}
