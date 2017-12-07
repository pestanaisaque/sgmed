package br.umc.sgmed.controller.usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.umc.sgmed.dto.ResultadoRecuperarSenhaDTO;
import br.umc.sgmed.po.PerfilPO;
import br.umc.sgmed.po.UsuarioPO;
import br.umc.sgmed.service.interf.EmailService;
import br.umc.sgmed.service.interf.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EmailService emailService;

	List<PerfilPO> perfis;

	/**
	 * ATRIBUTOS DE TELA
	 */

	@ModelAttribute("perfisDisponiveis")
	public List<PerfilPO> getPerfis() {

		PerfilPO admin = new PerfilPO();
		admin.setIdPerfil(1);
		admin.setPerfil("ADMIN");

		PerfilPO medico = new PerfilPO();
		medico.setIdPerfil(2);
		medico.setPerfil("MEDICO");

		PerfilPO usuario = new PerfilPO();
		usuario.setIdPerfil(3);
		usuario.setPerfil("USUARIO");

		perfis = new ArrayList<>(Arrays.asList(admin, medico, usuario));

		return perfis;
	}

	/**
	 * GETS
	 */

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView getLogin() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/acessoNaoAutorizado" }, method = RequestMethod.GET)
	public ModelAndView getAcessoNaoAutorizado() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("acessoNaoAutorizado");
		return modelAndView;
	}

	@RequestMapping("/logout")
	public ModelAndView getLogout(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		session.invalidate();
		return modelAndView;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView getHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@RequestMapping(value = "/usuario/cadastroUsuario", method = RequestMethod.GET)
	public ModelAndView getCadastro() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("perfisDisponiveis", perfis);
		modelAndView.addObject("usuarioPO", new UsuarioPO());
		modelAndView.setViewName("usuario/cadastroUsuario");
		return modelAndView;
	}

	@RequestMapping(value = "/recuperarSenha", method = RequestMethod.GET)
	public ModelAndView getRecuperarSenha() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("usuarioPO", new UsuarioPO());
		modelAndView.setViewName("recuperarSenha");
		return modelAndView;
	}

	@RequestMapping(value = "/resultadoEnvioToken", method = RequestMethod.GET)
	public ModelAndView getResultadoEnvioToken() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("recuperarSenha");
		return modelAndView;
	}
	
	@RequestMapping(value = "/resultadoRecuperarSenha", method = RequestMethod.GET)
	public ModelAndView getResultadoRecuperarSenha() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("recuperacaoSenhaDTO", new ResultadoRecuperarSenhaDTO());
		modelAndView.setViewName("resultadoRecuperarSenha");
		return modelAndView;
	}

	/**
	 * SETS
	 */

	// @RequestMapping(value = "/usuario/cadastroUsuario", method =
	// RequestMethod.POST)
	// public ModelAndView setCadastro(@Valid UsuarioPO usuarioPO, BindingResult
	// bindingResult) {
	// ModelAndView modelAndView = new ModelAndView();
	// UsuarioPO usuarioExistente =
	// usuarioService.findUsuarioByLogin(usuarioPO.getLogin());
	// if (usuarioExistente != null) {
	// bindingResult.rejectValue("login", "error.user",
	// "Já existe um usuário cadastrado com esse Login");
	// }
	// if (bindingResult.hasErrors()) {
	// modelAndView.setViewName("usuario/cadastroUsuario");
	// } else {
	// usuarioService.saveUsuario(usuarioPO);
	// modelAndView.addObject("successMessage", "Usuário cadastrado com
	// sucesso");
	// modelAndView.addObject("usuarioPO", new UsuarioPO());
	// modelAndView.setViewName("usuario/cadastroUsuario");
	// }
	// return modelAndView;
	// }

	@RequestMapping(value = "/recuperarSenha", method = RequestMethod.POST)
	public ModelAndView setRecuperarSenha(@Valid UsuarioPO usuarioPO, HttpServletRequest request,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		String nomeUsuario = usuarioPO.getNomeUsuario();

		Integer idUsuario = Integer
				.parseInt(nomeUsuario.substring(nomeUsuario.indexOf("Id:") + 4, nomeUsuario.length()));

		UsuarioPO usuarioExistente = usuarioService.findUsuarioById(idUsuario);

		String token = UUID.randomUUID().toString();
		usuarioService.createPasswordResetTokenForUsuarioPO(usuarioExistente, token);

		// Endereço da aplicação para recuperação de senha
		String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort();

		// Mensagem de E-mail
		SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
		passwordResetEmail.setTo(usuarioExistente.getEmail());
		passwordResetEmail.setSubject("Reset de senha SGMED.");
		passwordResetEmail.setText("Para o reset de sua senha, clique no seguinte link:\n" + appUrl
				+ "/resultadoRecuperarSenha?token=" + token);

		emailService.sendEmail(passwordResetEmail);

		String email = usuarioExistente.getEmail();
		Integer tamanhoEmail = email.substring(0, email.indexOf("@")).length();
		
		StringBuffer asteriscos = new StringBuffer();
		
		while (asteriscos.length() < tamanhoEmail - 2){
			asteriscos.append("*");
		}
		
		modelAndView.addObject("email", asteriscos + email.substring(email.indexOf("@") - 2, email.length()));
		modelAndView.setViewName("resultadoEnvioToken");

		return modelAndView;
	}

}