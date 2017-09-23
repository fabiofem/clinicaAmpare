package br.com.clinica.tabelas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cidade implements Serializable {
	

	private static final long serialVersionUID = 8793093305309569795L;
	@Id
	@GeneratedValue
	int id;
	

}
