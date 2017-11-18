/**
 * 
 */
package br.umc.sgmed.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.umc.sgmed.po.ItemEstoquePO;

/**
 * @author Isaque Pestana
 *
 */

@Repository("itemEstoqueRepository")
public interface ItemEstoqueDAO extends JpaRepository<ItemEstoquePO, Integer> {
	@Query("SELECT i FROM ItemEstoquePO i WHERE UPPER(i.medicamentoPO.nomeComercial) LIKE CONCAT(UPPER(:nomeComercial), '%') ORDER BY i.dtValidadeItemEstoque")
	List<ItemEstoquePO> findAllItensByNomeComercial(@Param("nomeComercial") String nomeComercial);
	
	@Query("SELECT i FROM ItemEstoquePO i WHERE UPPER(i.idItemEstoque) LIKE CONCAT(UPPER(:lote), '%') ORDER BY i.dtValidadeItemEstoque")
	List<ItemEstoquePO> findAllItensByLote(@Param("lote") String lote);
}
