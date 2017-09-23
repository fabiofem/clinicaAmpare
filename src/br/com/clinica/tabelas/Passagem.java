package br.com.clinica.tabelas;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Passagem {
	
	@Id
	@GeneratedValue
	private int id;
	
    @OneToOne
    private Pessoa pessoa;
	
	
	@Temporal(TemporalType.TIMESTAMP)
    private Calendar dataEntrada;
    
	private boolean entrada;
    
    @ManyToOne
    private PassagemDoDia passagemDoDia;
    
    
    public void setPassagemDoDia(PassagemDoDia passagemDoDia){
    	this.passagemDoDia =passagemDoDia;
    }
    
    
    public Calendar getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(Calendar dataSaida) {
		this.dataEntrada = dataSaida;
	}

	public void setPessoa(Pessoa u) {
		pessoa=u;
		
	}
	public boolean isEntrada() {
		return entrada;
	}
	public void setEntrada(boolean entrada) {
		this.entrada = entrada;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	

}
