package br.umc.sgmed.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.umc.sgmed.po.UsuarioPO;

@Repository("usuarioRepository")
public interface UsuarioDAO extends JpaRepository<UsuarioPO, Long> {
	UsuarioPO findByLogin(String email);
	
	@Query("SELECT u FROM UsuarioPO u WHERE UPPER(u.nomeUsuario) LIKE CONCAT(UPPER(:nomeUsuario), '%')")
	List<UsuarioPO> findUsuariosByNome(@Param("nomeUsuario") String nomeUsuario);
}
