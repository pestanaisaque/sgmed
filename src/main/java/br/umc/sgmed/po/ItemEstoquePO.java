/**
 * 
 */
package br.umc.sgmed.po;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
