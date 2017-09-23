package br.com.clinica.tabelas;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Agendamento {
	
	@Id
	@GeneratedValue
    private int id;
	@Temporal(TemporalType.DATE)
	private Calendar data;
	

	private String hora;
	
	private double valor;
	
	
	private boolean temGuia;
	
	private String nomeMedico;
	@OneToOne
	private PlanoDeSaude plano;
	
	@OneToOne
	private Paciente paciente;
	
	private boolean pacienteVeio;
	
	
	public void setPaciente(Paciente p){
		paciente = p;
	}
	public Paciente getPaciente(){
		return paciente;
	}
	

	public boolean isTemGuia() {
		return temGuia;
	}
	public void setTemGuia(boolean temGuia) {
		this.temGuia = temGuia;
	}
	public String getNomeMedico() {
		return nomeMedico;
	}
	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}
	public PlanoDeSaude getPlano() {
		return plano;
	}
	public void setPlano(PlanoDeSaude plano) {
		this.plano = plano;
	}

	public boolean isPacienteVeio() {
		return pacienteVeio;
	}

	public void setPacienteVeio(boolean pacienteVeio) {
		this.pacienteVeio = pacienteVeio;
	}

	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Calendar getAgendamento() {
		return data;
	}
	public void setAgendamento(Calendar agendamento) {
		this.data = agendamento;
	}
	
	
	
}
