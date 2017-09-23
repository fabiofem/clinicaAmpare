package br.com.clinica.tabelas;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Paciente  extends Pessoa{
    
   
	private static final long serialVersionUID = 3525659820229804948L;
	private int numeroProntuario;
    private String cns;
    
    
    @OneToOne
    private PlanoDeSaude plano;
    
    @ManyToOne
    private Agendamento agendamento;
	
    private boolean assinouGuia;
    
    public boolean isAssinouGuia(){
    	return assinouGuia;
    }
    
	public int getNumeroProntuario() {
		return numeroProntuario;
	}
	public void setNumeroProntuario(int numeroProntuario) {
		this.numeroProntuario = numeroProntuario;
	}
	
	
	public String getCns() {
		return cns;
	}
	public void setCns(String cns) {
		this.cns = cns;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
	       return id;
	}
	public PlanoDeSaude getPlano() {
		return plano;
	}
	public void setPlano(PlanoDeSaude listaPlanos) {
		this.plano = listaPlanos;
	}
	public void setAssinouGuia(boolean b) {
		this.assinouGuia=b;
		
	}
	
	
	
	


    
    
}
