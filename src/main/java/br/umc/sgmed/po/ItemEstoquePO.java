/**
 * 
 */
package br.umc.sgmed.po;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author Isaque Pestana
 *
 */

@Entity
@Table(name = "item_estoque")
public class ItemEstoquePO {

	@Id
	@Column(name = "item_estoque_id")
	private Integer idItemEstoque;

	@OneToOne
	private MedicamentoPO medicamentoPO;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "itemEstoquePO")
	@JsonManagedReference(value = "itemEstoquePO")
	private List<SaidaEstoquePO> saidasEstoquePO;

	@Column(name = "qtd_item_estoque")
	private Long qtdItemEmEstoque;

	@Column(name = "dt_validade_item_estoque")
	private Date dtValidadeItemEstoque;

	/**
	 * @return the idItemEstoque
	 */
	public Integer getIdItemEstoque() {
		return idItemEstoque;
	}

	/**
	 * @param idItemEstoque
	 *            the idItemEstoque to set
	 */
	public void setIdItemEstoque(Integer idItemEstoque) {
		this.idItemEstoque = idItemEstoque;
	}

	/**
	 * @return the medicamentoPO
	 */
	public MedicamentoPO getMedicamentoPO() {
		return medicamentoPO;
	}

	/**
	 * @param medicamentoPO
	 *            the medicamentoPO to set
	 */
	public void setMedicamentoPO(MedicamentoPO medicamentoPO) {
		this.medicamentoPO = medicamentoPO;
	}

	/**
	 * @return the saidasEstoquePO
	 */
	public List<SaidaEstoquePO> getSaidasEstoquePO() {
		return saidasEstoquePO;
	}

	/**
	 * @param saidasEstoquePO
	 *            the saidasEstoquePO to set
	 */
	public void setSaidasEstoquePO(List<SaidaEstoquePO> saidasEstoquePO) {
		this.saidasEstoquePO = saidasEstoquePO;
	}

	/**
	 * @return the qtdItemEmEstoque
	 */
	public Long getQtdItemEmEstoque() {
		return qtdItemEmEstoque;
	}

	/**
	 * @param qtdItemEmEstoque
	 *            the qtdItemEmEstoque to set
	 */
	public void setQtdItemEmEstoque(Long qtdItemEmEstoque) {
		this.qtdItemEmEstoque = qtdItemEmEstoque;
	}

	/**
	 * @return the dtValidadeItemEstoque
	 */
	public Date getDtValidadeItemEstoque() {
		return dtValidadeItemEstoque;
	}

	/**
	 * @param dtValidadeItemEstoque
	 *            the dtValidadeItemEstoque to set
	 */
	public void setDtValidadeItemEstoque(Date dtValidadeItemEstoque) {
		this.dtValidadeItemEstoque = dtValidadeItemEstoque;
	}

}
