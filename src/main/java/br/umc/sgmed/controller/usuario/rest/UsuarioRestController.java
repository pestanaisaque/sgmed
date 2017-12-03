package br.umc.sgmed.controller.usuario.rest;
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

import br.umc.sgmed.dto.ResultadoRecuperarSenhaDTO;
import br.umc.sgmed.po.PasswordResetTokenPO;
import br.umc.sgmed.po.UsuarioPO;
import br.umc.sgmed.response.Response;
import br.umc.sgmed.service.interf.PasswordTokenService;
import br.umc.sgmed.service.interf.UsuarioService;

/**
 * @author Isaque Pestana
 *
 */

@Controller
@RequestMapping("/api/cadastroUsuario")
public class UsuarioRestController {
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PasswordTokenService passwordTokenService;

	private List<UsuarioPO> usuariosBuscados;

	@RequestMapping(value = "/listarUsuariosPorNome", method = RequestMethod.GET)
	public @ResponseBody List<UsuarioPO> getUsuariosPorNome(@RequestParam("term") String nomeUsuario) {

		usuariosBuscados = usuarioService.findUsuariosByNome(nomeUsuario);

		return usuariosBuscados;
	}

	@RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.POST)
	public @ResponseBody Response cadastrarUsuario(@RequestBody UsuarioPO usuarioPO) {

		Response response;

		UsuarioPO usuarioExistente = usuarioService.findUsuarioByLogin(usuarioPO.getLogin());

		if (null != usuarioExistente) {
			response = new Response("NOK", usuarioExistente);
		} else {
			usuarioService.saveUsuario(usuarioPO);
			response = new Response("OK", usuarioPO);
		}

		return response;
	}

	@RequestMapping(value = "/alterarUsuario", method = RequestMethod.POST)
	public @ResponseBody Response alterarUsuario(@RequestBody UsuarioPO usuarioPO) {
		Response response;

		UsuarioPO usuario = usuarioService.findUsuarioByLogin(usuarioPO.getLogin());
		usuarioPO.setIdUsuario(usuario.getIdUsuario());
		usuarioPO.setSenha(usuario.getSenha());
		usuarioPO.setAtivo(usuario.getAtivo());
		usuarioPO.setPerfis(usuario.getPerfis());

		try {
			usuarioService.updateUsuario(usuarioPO);
			response = new Response("OK", usuarioPO);
		} catch (Exception e) {
			response = new Response("NOK", usuarioPO);
		}
		return response;
	}

	@RequestMapping(value = "/deletarUsuario", method = RequestMethod.POST)
	public @ResponseBody Response deletarUsuario(@RequestBody UsuarioPO usuarioPO) {
		Response response;

		UsuarioPO usuario = usuarioService.findUsuarioByLogin(usuarioPO.getLogin());
		usuarioPO.setIdUsuario(usuario.getIdUsuario());
		
		try {
			usuarioService.deleteUsuario(usuarioPO);
			response = new Response("OK", usuarioPO);
		} catch (Exception e) {
			response = new Response("NOK", usuarioPO);
		}
		return response;
	}

	@RequestMapping(value = "/alterarSenhaUsuario", method = RequestMethod.POST)
	public @ResponseBody Response alterarSenhaUsuario(@RequestBody ResultadoRecuperarSenhaDTO recuperarSenhaDTO) {
		Response response;

		try {
			PasswordResetTokenPO passwordResetTokenBuscado = passwordTokenService
					.findPasswordResetTokenPOByToken(recuperarSenhaDTO.getToken());

			if (null != passwordResetTokenBuscado) {

				passwordTokenService.alterarSenhaUsuario(passwordResetTokenBuscado, recuperarSenhaDTO);

				response = new Response("OK", passwordResetTokenBuscado);

			} else {
				response = new Response("NOK_PASS", new PasswordResetTokenPO());
			}

		} catch (Exception e) {
			response = new Response("NOK", new PasswordResetTokenPO());
		}

		return response;
	}

}
