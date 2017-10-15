package br.umc.sgmed.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.umc.sgmed.po.PerfilPO;

@Repository("roleRepository")
public interface PerfilDAO extends JpaRepository<PerfilPO, Integer> {
	PerfilPO findByPerfil(String role);

}
