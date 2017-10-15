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
import br.umc.sgmed.service.PerfilService;
import br.umc.sgmed.service.UsuarioService;

@Controller
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PerfilService perfilService;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView();
		UsuarioPO usuarioPO = new UsuarioPO();
		modelAndView.addObject("usuarioPO", usuarioPO);
		modelAndView.setViewName("cadastro");
		return modelAndView;
	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid UsuarioPO usuarioPO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		UsuarioPO usuarioExistente = usuarioService.findUsuarioByLogin(usuarioPO.getEmail());
		if (usuarioExistente != null) {
			bindingResult.rejectValue("login", "error.usuarioPO",
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

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@ModelAttribute("perfisDisponiveis")
	public List<PerfilPO> getPerfis() {
		return perfilService.findAllPerfis();
	}
}