package br.com.clinica.tabelas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Especialidades implements Serializable{

	private static final long serialVersionUID = -3714544827322116958L;
	@Id
	@GeneratedValue
	private int id;
	private String tipo;
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
	
	
}
