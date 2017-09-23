package br.com.clinica.tabelas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Endereco implements Serializable{

  private static final long serialVersionUID = 7284591005168333680L;

  @Id
  @GeneratedValue
  private int id;
  
  private String enderecoCompleto;
  
  private Cidade cidade;
  
  private String cpf;
  
  private String bairro;

public String getEndereco() {
	return enderecoCompleto;
}

public void setEndereco(String endereco) {
	this.enderecoCompleto = endereco;
}

public Cidade getCidade() {
	return cidade;
}

public void setCidade(Cidade cidade) {
	this.cidade = cidade;
}

public String getCpf() {
	return cpf;
}

public void setCpf(String cpf) {
	this.cpf = cpf;
}

public String getBairro() {
	return bairro;
}

public void setBairro(String bairro) {
	this.bairro = bairro;
}
  
  
}
