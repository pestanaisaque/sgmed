package br.umc.sgmed.controller.sessao.rest;
/**
 * 
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.umc.sgmed.po.UsuarioPO;
import br.umc.sgmed.response.Response;
import br.umc.sgmed.service.interf.SessaoService;

/**
 * @author Isaque Pestana
 *
 */

@Controller
@RequestMapping("/api/cadastroUsuario")
public class SessaoRestController {
	@Autowired
	private SessaoService sessaoService;

	private List<UsuarioPO> usuariosBuscados;

	@RequestMapping(value = "/listarUsuariosPorNome", method = RequestMethod.GET)
	public @ResponseBody List<UsuarioPO> getUsuariosPorNome(@RequestParam("term") String nomeUsuario) {

		usuariosBuscados = sessaoService.findUsuariosByNome(nomeUsuario);

		return usuariosBuscados;
	}

	@RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.POST)
	public @ResponseBody Response cadastrarUsuario(@RequestBody UsuarioPO usuarioPO) {
		
		Response response;

		UsuarioPO usuarioExistente = sessaoService.findUsuarioByLogin(usuarioPO.getLogin());

		if (null != usuarioExistente) {
			response = new Response("NOK", usuarioExistente);
		} else {
			sessaoService.saveUsuario(usuarioPO);
			response = new Response("OK", usuarioPO);
		}

		return response;
	}
}
