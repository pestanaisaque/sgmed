package br.umc.sgmed.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.umc.sgmed.po.UsuarioPO;

@Repository("userRepository")
public interface UsuarioDAO extends JpaRepository<UsuarioPO, Long> {
	UsuarioPO findByLogin(String email);
}
