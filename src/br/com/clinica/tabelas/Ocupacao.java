package br.com.clinica.tabelas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ocupacao implements Serializable{

  private static final long serialVersionUID = -7898530110461726757L;
  @Id
  @GeneratedValue
  private int id;
  private String nome;
  public String getNome() {
	return nome;
 }
 public void setNome(String nome) {
	this.nome = nome;
 }
  
	
}
