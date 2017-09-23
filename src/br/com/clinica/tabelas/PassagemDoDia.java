package br.com.clinica.tabelas;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PassagemDoDia {
	@Id
	@GeneratedValue
	private int id;
	@Temporal(TemporalType.DATE)
	@Column(unique=true)
	private Calendar dataPassagem;
    
	
	@OneToMany(mappedBy="passagemDoDia")
    private List<Passagem> listaPassagens;


	public List<Passagem> getListaPassagens() {
		return listaPassagens;
	}

	public void addPassagens(Passagem passagens) {
		passagens.setPassagemDoDia(this);
		this.listaPassagens.add(passagens);
	
		
	}


	public Calendar getDataPassagem() {
		return dataPassagem;
	}

	public void setDataPassagem(Calendar dataPassagem) {
		this.dataPassagem = dataPassagem;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataPassagem == null) ? 0 : dataPassagem.hashCode());
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
		PassagemDoDia other = (PassagemDoDia) obj;
		if (dataPassagem == null) {
			if (other.dataPassagem != null)
				return false;
		} else if (!dataPassagem.equals(other.dataPassagem))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
   

	
}
