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
public class ExclusaoUsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	private UsuarioPO usuarioBuscado;

	/**
	 * GET
	 */

	@RequestMapping(value = { "/usuario/buscaExclusaoUsuario" }, method = RequestMethod.GET)
	public ModelAndView getBuscaAlteracaoUsuario() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("usuarioPO", new UsuarioPO());
		modelAndView.setViewName("usuario/buscaExclusaoUsuario");
		return modelAndView;
	}

	@RequestMapping(value = { "/usuario/resultadoBuscaExclusaoUsuario" }, method = RequestMethod.GET)
	public ModelAndView getResultadoBuscaAlteracaoUsuario() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("usuario/resultadoBuscaExclusaoUsuario");
		return modelAndView;
	}

	/**
	 * POST
	 */

	@RequestMapping(value = "/usuario/buscaExclusaoUsuario", method = RequestMethod.POST)
	private ModelAndView setBuscaAlteracaoUsuario(@Valid UsuarioPO usuarioPO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		String nomeUsuario = usuarioPO.getNomeUsuario();
		Integer idUsuario = Integer
				.parseInt(nomeUsuario.substring(nomeUsuario.indexOf("Id:") + 4, nomeUsuario.length()));

		if (null != idUsuario && !"".equals(idUsuario)) {

			usuarioBuscado = usuarioService.findUsuarioById(idUsuario);

			modelAndView.addObject("usuarioBuscado", usuarioBuscado);
			modelAndView.setViewName("usuario/resultadoBuscaExclusaoUsuario");

		}

		return modelAndView;
	}

}
