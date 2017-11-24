package br.umc.sgmed.controller.usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.umc.sgmed.po.PerfilPO;
import br.umc.sgmed.po.UsuarioPO;
import br.umc.sgmed.service.interf.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

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
	
	@RequestMapping(value = { "/", "/sessao/login" }, method = RequestMethod.GET)
	public ModelAndView getLogin() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sessao/login");
		return modelAndView;
	}
	
	@RequestMapping("/sessao/logout")
	public ModelAndView getLogout(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sessao/login");
		session.invalidate();
		return modelAndView;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView getHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	@RequestMapping(value = "/sessao/cadastroUsuario", method = RequestMethod.GET)
	public ModelAndView getCadastro() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("perfisDisponiveis", perfis);
		modelAndView.addObject("usuarioPO", new UsuarioPO());
		modelAndView.setViewName("sessao/cadastroUsuario");
		return modelAndView;
	}
	
	@RequestMapping(value = "/sessao/recuperarSenha", method = RequestMethod.GET)
	public ModelAndView getRecuperarSenha() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("usuarioPO", new UsuarioPO());
		modelAndView.setViewName("sessao/recuperarSenha");
		return modelAndView;
	}
	
	
	
	/**
	 * SETS
	 */
	
//	@RequestMapping(value = "/sessao/cadastroUsuario", method = RequestMethod.POST)
//	public ModelAndView setCadastro(@Valid UsuarioPO usuarioPO, BindingResult bindingResult) {
//		ModelAndView modelAndView = new ModelAndView();
//		UsuarioPO usuarioExistente = usuarioService.findUsuarioByLogin(usuarioPO.getLogin());
//		if (usuarioExistente != null) {
//			bindingResult.rejectValue("login", "error.user",
//					"Já existe um usuário cadastrado com esse Login");
//		}
//		if (bindingResult.hasErrors()) {
//			modelAndView.setViewName("sessao/cadastroUsuario");
//		} else {
//			usuarioService.saveUsuario(usuarioPO);
//			modelAndView.addObject("successMessage", "Usuário cadastrado com sucesso");
//			modelAndView.addObject("usuarioPO", new UsuarioPO());
//			modelAndView.setViewName("sessao/cadastroUsuario");
//		}
//		return modelAndView;
//	}
	
//	@RequestMapping(value = "/recuperarSenha", method = RequestMethod.POST)
//	public ModelAndView setRecuperarSenha(@Valid UsuarioPO usuarioPO, BindingResult bindingResult){
//		ModelAndView modelAndView = new ModelAndView();
//		
//		
//		return modelAndView;
//	}

	

	
}