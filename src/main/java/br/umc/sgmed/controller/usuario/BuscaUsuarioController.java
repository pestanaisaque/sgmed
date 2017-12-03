/**
 * 
 */
package br.umc.sgmed.controller.usuario;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.umc.sgmed.po.UsuarioPO;
import br.umc.sgmed.service.interf.UsuarioService;

/**
 * @author Isaque Pestana
 *
 */
@Controller
public class BuscaUsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	private UsuarioPO usuarioBuscado;

	@RequestMapping(value = { "/usuario/buscaUsuario" }, method = RequestMethod.GET)
	public ModelAndView getBuscaUsuario() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("usuarioPO", new UsuarioPO());
		modelAndView.setViewName("usuario/buscaUsuario");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/usuario/resultadoBuscaUsuario" }, method = RequestMethod.GET)
	public ModelAndView getResultadoBuscaUsuario() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("usuario/resultadoBuscaUsuario");
		return modelAndView;
	}

	@RequestMapping(value = "/usuario/buscaUsuario", method = RequestMethod.POST)
	private ModelAndView setBuscaUsuario(@Valid UsuarioPO usuarioPO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		String nomeUsuario = usuarioPO.getNomeUsuario();
		Integer idUsuario = Integer.parseInt(nomeUsuario.substring(nomeUsuario.indexOf("Id:") + 4, nomeUsuario.length()));

		if (null != idUsuario && !"".equals(idUsuario)) {

			usuarioBuscado = usuarioService.findUsuarioById(idUsuario);

			modelAndView.addObject("usuarioBuscado", usuarioBuscado);
			modelAndView.setViewName("usuario/resultadoBuscaUsuario");

		}

		return modelAndView;
	}
}
