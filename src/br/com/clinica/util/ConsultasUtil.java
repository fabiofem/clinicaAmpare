package br.com.clinica.util;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.clinica.tabelas.Agendamento;
import br.com.clinica.tabelas.Paciente;

public class ConsultasUtil {
	
	public static List<Agendamento> recuperarAgendamentosDoDia(EntityManager manager,Calendar data){
		 TypedQuery<Agendamento> q = manager.createQuery("from Agendamento agend where agend.data=:data",Agendamento.class);
	     q.setParameter("data", data);
	     return q.getResultList();
	}
	public static List<Agendamento> retornarOsAgendamentos(EntityManager manager, Paciente paciente) {
		TypedQuery<Agendamento> q= manager.createQuery("select ag from Agendamento ag where ag.paciente=:paciente",Agendamento.class);
        q.setParameter("paciente", paciente);
        return q.getResultList();  
	}

}
