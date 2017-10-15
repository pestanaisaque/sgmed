package br.umc.sgmed.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "usuario")
public class UsuarioPO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "usuario_id")
	private int idUsuario;

	@Column(name = "nome")
	@NotEmpty(message = "*Por favor, digite seu Nome")
	private String nome;

	@Column(name = "sobrenome")
	@NotEmpty(message = "*Por favor, digite seu Sobrenome")
	private String sobrenome;

	@Column(name = "login")
	@NotEmpty(message = "*Por favor, forneça um Login")
	private String login;

	@Column(name = "email")
	@Email(message = "*Por favor, forneça um E-mail válido")
	@NotEmpty(message = "*Por favor, forneça um E-mail")
	private String email;

	@Column(name = "senha")
	@Length(min = 5, message = "*Sua senha deve ter no mínimo 5 caracteres")
	@NotEmpty(message = "*Por favor, forneça uma senha")
	@Transient
	private String senha;

	@Column(name = "ativo")
	private int ativo;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_perfil", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "perfil_id"))
	private List<PerfilPO> perfis;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public List<PerfilPO> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<PerfilPO> perfis) {
		this.perfis = perfis;
	}

}
