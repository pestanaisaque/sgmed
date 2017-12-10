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
public class AlteracaoUsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	private UsuarioPO usuarioBuscado;

	/**
	 * GET
	 */

	@RequestMapping(value = { "/usuario/buscaAlteracaoUsuario" }, method = RequestMethod.GET)
	public ModelAndView getBuscaAlteracaoUsuario() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("usuarioPO", new UsuarioPO());
		modelAndView.setViewName("usuario/buscaAlteracaoUsuario");
		return modelAndView;
	}

	@RequestMapping(value = { "/usuario/resultadoBuscaAlteracaoUsuario" }, method = RequestMethod.GET)
	public ModelAndView getResultadoBuscaAlteracaoUsuario() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("usuario/resultadoBuscaAlteracaoUsuario");
		return modelAndView;
	}

	/**
	 * POST
	 */

	@RequestMapping(value = "/usuario/buscaAlteracaoUsuario", method = RequestMethod.POST)
	private ModelAndView setBuscaAlteracaoUsuario(@Valid UsuarioPO usuarioPO, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			usuarioBuscado = usuarioService.findUsuarioByLogin(usuarioPO.getLogin());

			modelAndView.addObject("usuarioBuscado", usuarioBuscado);
			modelAndView.setViewName("usuario/resultadoBuscaAlteracaoUsuario");

		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("usuarioPO", new UsuarioPO());
			modelAndView.setViewName("usuario/buscaAlteracaoUsuario");
		}

		return modelAndView;
	}

}
