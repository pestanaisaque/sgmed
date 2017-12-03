package br.umc.sgmed.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;

	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

							http.csrf().disable()
								.authorizeRequests()
									.antMatchers("/", "/login").permitAll()
									.antMatchers("/usuario/**").hasAuthority("ADMIN")
									.antMatchers("/medicamento/**").hasAuthority("ADMIN")
									.antMatchers("/paciente/**").hasAuthority("ADMIN")
									.antMatchers("/estoque/**").hasAuthority("ADMIN")
									.antMatchers("/recuperarSenha").anonymous()
//									.anyRequest().authenticated()
							.and()
								.formLogin()
									.loginPage("/login")
									.defaultSuccessUrl("/home")
									.usernameParameter("login_id")
									.passwordParameter("senha_id")
									.and()
									.logout()
									.permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/imgs/**", "/assets/**");
	}

}