/**
 * 
 */
package br.umc.sgmed.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.umc.sgmed.po.SaidaEstoquePO;

/**
 * @author Isaque Pestana
 *
 */

@Repository("saidaEstoqueRepository")
public interface SaidaEstoqueDAO extends JpaRepository<SaidaEstoquePO, Integer> {

}
