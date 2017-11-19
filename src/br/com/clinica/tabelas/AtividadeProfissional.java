package br.com.clinica.tabelas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AtividadeProfissional implements Serializable{
	
	private static final long serialVersionUID = 8167536474441729058L;
	@Id
	@GeneratedValue
	private int id;
	
	private ConselhoProfissional conselhoProfissional;
	private Ocupacao ocupacao;
	private String numeroConselho;
	
	
	public ConselhoProfissional getConselhoProfissional() {
		return conselhoProfissional;
	}
	public void setConselhoProfissional(ConselhoProfissional conselhoProfissional) {
		this.conselhoProfissional = conselhoProfissional;
	}
	public Ocupacao getOcupacao() {
		return ocupacao;
	}
	public void setOcupacao(Ocupacao ocupacao) {
		this.ocupacao = ocupacao;
	}
	public String getNumeroConselho() {
		return numeroConselho;
	}
	public void setNumeroConselho(String numeroConselho) {
		this.numeroConselho = numeroConselho;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtividadeProfissional other = (AtividadeProfissional) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	

}
