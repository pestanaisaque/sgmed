package br.umc.sgmed.controller;

import java.util.List;

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
import br.umc.sgmed.service.interf.PerfilService;
import br.umc.sgmed.service.interf.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PerfilService perfilService;

	/**
	 * ATRIBUTOS DE TELA
	 */

	@ModelAttribute("perfisDisponiveis")
	public List<PerfilPO> getPerfis() {
		return perfilService.findAllPerfis();
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

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView getHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public ModelAndView getCadastro() {
		ModelAndView modelAndView = new ModelAndView();
		UsuarioPO usuarioPO = new UsuarioPO();
		modelAndView.addObject("usuarioPO", usuarioPO);
		modelAndView.setViewName("cadastro");
		return modelAndView;
	}
	
	@RequestMapping(value = "/recuperarSenha", method = RequestMethod.GET)
	public ModelAndView getRecuperarSenha() {
		ModelAndView modelAndView = new ModelAndView();
		UsuarioPO usuarioPO = new UsuarioPO();
		modelAndView.addObject("usuarioPO", usuarioPO);
		modelAndView.setViewName("recuperarSenha");
		return modelAndView;
	}
	
	
	
	/**
	 * SETS
	 */
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public ModelAndView setCadastro(@Valid UsuarioPO usuarioPO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		UsuarioPO usuarioExistente = usuarioService.findUsuarioByLogin(usuarioPO.getLogin());
		if (usuarioExistente != null) {
			bindingResult.rejectValue("login", "error.user",
					"Já existe um usuário cadastrado com esse Login");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("cadastro");
		} else {
			usuarioService.saveUsuario(usuarioPO);
			modelAndView.addObject("successMessage", "Usuário cadastrado com sucesso");
			modelAndView.addObject("usuarioPO", new UsuarioPO());
			modelAndView.setViewName("cadastro");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/recuperarSenha", method = RequestMethod.POST)
	public ModelAndView setRecuperarSenha(@Valid UsuarioPO usuarioPO, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		
		
		return modelAndView;
	}

	

	
}