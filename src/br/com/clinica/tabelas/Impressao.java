package br.com.clinica.tabelas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;

@Entity
public class Impressao implements Serializable, DPFPSample,DPFPTemplate{
	
	private static final long serialVersionUID = 5685038384837146583L;
	@Id  
	@GeneratedValue
    private int id;
    @Lob
	private byte[] biometria;
    
	@OneToOne
	private Pessoa pessoa;
	
	
	
	@Override
	public void deserialize(byte[] arg0) throws IllegalArgumentException {
		biometria=arg0;
		
	}

	@Override
	public byte[] serialize() {
	  return biometria;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
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
		Impressao other = (Impressao) obj;
		if (id != other.id)
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		return true;
	}
	
	
	
	
}
