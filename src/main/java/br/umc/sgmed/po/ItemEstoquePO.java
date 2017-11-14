/**
 * 
 */
package br.umc.sgmed.po;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Isaque Pestana
 *
 */

@Entity
@Table(name = "item_estoque")
public class ItemEstoquePO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_estoque_id")
	private Integer idItemEstoque;

	@OneToMany(mappedBy = "itemEstoquePO", targetEntity = MedicamentoPO.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MedicamentoPO> medicamentosPO;

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
	 * @return the medicamentosPO
	 */
	public List<MedicamentoPO> getMedicamentosPO() {
		return medicamentosPO;
	}

	/**
	 * @param medicamentosPO
	 *            the medicamentosPO to set
	 */
	public void setMedicamentosPO(List<MedicamentoPO> medicamentosPO) {
		this.medicamentosPO = medicamentosPO;
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
