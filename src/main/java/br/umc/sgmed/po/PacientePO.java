/**
 * 
 */
package br.umc.sgmed.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Isaque Pestana
 *
 */

@Entity
@Table(name = "paciente")
public class PacientePO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "paciente_id")
	private Integer idPaciente;

	@Column(name = "nome_paciente")
	private String nomePaciente;

	@Column(name = "sobrenome_paciente")
	private String sobrenomePaciente;

	@Column(name = "dt_nascimento_paciente")
	private Date dtNascimentoPaciente;

	@Column(name = "cpf_paciente")
	private String cpfPaciente;

	@Column(name = "telefone_paciente")
	private String telefonePaciente;

	@Column(name = "celular_paciente")
	private String celularPaciente;

	@Column(name = "email_paciente")
	private String emailPaciente;

	@Column(name = "endereco_paciente")
	private String enderecoPaciente;

	@Column(name = "numero_endereco_paciente")
	private Long numeroEnderecoPaciente;

	@Column(name = "cidade_paciente")
	private String cidadePaciente;

	@Column(name = "estado_paciente")
	private String estadoPaciente;

	@Column(name = "cep_paciente")
	private String cepPaciente;

	/**
	 * @return the idPaciente
	 */
	public Integer getIdPaciente() {
		return idPaciente;
	}

	/**
	 * @param idPaciente
	 *            the idPaciente to set
	 */
	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	/**
	 * @return the nomePaciente
	 */
	public String getNomePaciente() {
		return nomePaciente;
	}

	/**
	 * @param nomePaciente
	 *            the nomePaciente to set
	 */
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	/**
	 * @return the sobrenomePaciente
	 */
	public String getSobrenomePaciente() {
		return sobrenomePaciente;
	}

	/**
	 * @param sobrenomePaciente
	 *            the sobrenomePaciente to set
	 */
	public void setSobrenomePaciente(String sobrenomePaciente) {
		this.sobrenomePaciente = sobrenomePaciente;
	}

	/**
	 * @return the dtNascimentoPaciente
	 */
	public Date getDtNascimentoPaciente() {
		return dtNascimentoPaciente;
	}

	/**
	 * @param dtNascimentoPaciente
	 *            the dtNascimentoPaciente to set
	 */
	public void setDtNascimentoPaciente(Date dtNascimentoPaciente) {
		this.dtNascimentoPaciente = dtNascimentoPaciente;
	}

	/**
	 * @return the cpfPaciente
	 */
	public String getCpfPaciente() {
		return cpfPaciente;
	}

	/**
	 * @param cpfPaciente
	 *            the cpfPaciente to set
	 */
	public void setCpfPaciente(String cpfPaciente) {
		this.cpfPaciente = cpfPaciente;
	}

	/**
	 * @return the telefonePaciente
	 */
	public String getTelefonePaciente() {
		return telefonePaciente;
	}

	/**
	 * @param telefonePaciente
	 *            the telefonePaciente to set
	 */
	public void setTelefonePaciente(String telefonePaciente) {
		this.telefonePaciente = telefonePaciente;
	}

	/**
	 * @return the celularPaciente
	 */
	public String getCelularPaciente() {
		return celularPaciente;
	}

	/**
	 * @param celularPaciente
	 *            the celularPaciente to set
	 */
	public void setCelularPaciente(String celularPaciente) {
		this.celularPaciente = celularPaciente;
	}

	/**
	 * @return the emailPaciente
	 */
	public String getEmailPaciente() {
		return emailPaciente;
	}

	/**
	 * @param emailPaciente
	 *            the emailPaciente to set
	 */
	public void setEmailPaciente(String emailPaciente) {
		this.emailPaciente = emailPaciente;
	}

	/**
	 * @return the enderecoPaciente
	 */
	public String getEnderecoPaciente() {
		return enderecoPaciente;
	}

	/**
	 * @param enderecoPaciente
	 *            the enderecoPaciente to set
	 */
	public void setEnderecoPaciente(String enderecoPaciente) {
		this.enderecoPaciente = enderecoPaciente;
	}

	/**
	 * @return the numeroEnderecoPaciente
	 */
	public Long getNumeroEnderecoPaciente() {
		return numeroEnderecoPaciente;
	}

	/**
	 * @param numeroEnderecoPaciente
	 *            the numeroEnderecoPaciente to set
	 */
	public void setNumeroEnderecoPaciente(Long numeroEnderecoPaciente) {
		this.numeroEnderecoPaciente = numeroEnderecoPaciente;
	}

	/**
	 * @return the cidadePaciente
	 */
	public String getCidadePaciente() {
		return cidadePaciente;
	}

	/**
	 * @param cidadePaciente
	 *            the cidadePaciente to set
	 */
	public void setCidadePaciente(String cidadePaciente) {
		this.cidadePaciente = cidadePaciente;
	}

	/**
	 * @return the estadoPaciente
	 */
	public String getEstadoPaciente() {
		return estadoPaciente;
	}

	/**
	 * @param estadoPaciente
	 *            the estadoPaciente to set
	 */
	public void setEstadoPaciente(String estadoPaciente) {
		this.estadoPaciente = estadoPaciente;
	}

	/**
	 * @return the cepPaciente
	 */
	public String getCepPaciente() {
		return cepPaciente;
	}

	/**
	 * @param cepPaciente
	 *            the cepPaciente to set
	 */
	public void setCepPaciente(String cepPaciente) {
		this.cepPaciente = cepPaciente;
	}

}
