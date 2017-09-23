package br.com.clinica.dao;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import br.com.clinica.tabelas.Agendamento;
import br.com.clinica.tabelas.Impressao;
import br.com.clinica.tabelas.Paciente;
import br.com.clinica.tabelas.Passagem;
import br.com.clinica.tabelas.Pessoa;
import br.com.clinica.tabelas.PlanoDeSaude;
import br.com.clinica.util.JPAUtil;
import br.com.clinica.util.LogUtil;

public class PacienteDAO {
	
    private EntityManager entityManager = JPAUtil.getEntityManager();
	
	public void inserir(Pessoa  funcionario,PlanoDeSaude plano,Impressao impressao){
		 if(!entityManager.getTransaction().isActive()){
		    	entityManager.getTransaction().begin();
		 }
		 entityManager.persist(plano);
	     entityManager.persist(impressao);
	     entityManager.persist(funcionario);
         entityManager.getTransaction().commit();	
         LogUtil.Log(PacienteDAO.class.getName()+"Cadastrando: "+funcionario,Level.INFO);
		
	}
	public boolean verificarRepeticaoCPF(Pessoa  funcionario){
		
		TypedQuery<Paciente> q= entityManager.createQuery("from Paciente p where p.cpf=:cpf",Paciente.class);
        q.setParameter("cpf", funcionario.getCpf());
        try{
        	Paciente p = q.getSingleResult();
        	System.out.println(p.getId()  + "  "+p.getNomeCompleto());
        	if(p.getId()==0){
        		
        	}
        }catch(Exception e){
        	if(e instanceof NoResultException){
        	    LogUtil.Log("Não encontrei esse CPF!", Level.SEVERE);
        	    return false;
        	}else if(e instanceof NonUniqueResultException){
        		LogUtil.Log("Existe mais de um CPF! Altere por favor este e os demais!", Level.SEVERE);
        		return true;
        	}
        	return false;
        }
	    return true;
	}
	
	public void atualizar(Pessoa p){
		 if(!entityManager.getTransaction().isActive()){
		    	entityManager.getTransaction().begin();
		  }
		 
          entityManager.merge(p);
          entityManager.getTransaction().commit();	
          LogUtil.Log(PacienteDAO.class.getName()+"Atualizando: "+p,Level.INFO);
		
       
  }
	public boolean remover(Paciente paciente) {
		    if(!entityManager.getTransaction().isActive()){
		    	entityManager.getTransaction().begin();
		    }
		    TypedQuery<Passagem> q= entityManager.createQuery("select p from Passagem p where p.pessoa=:pessoa",Passagem.class);
            q.setParameter("pessoa", paciente);
	        List<Passagem> lista= q.getResultList();
	        
	        if(lista != null){
	           int result = JOptionPane.showConfirmDialog(null, "Atenção a remoção irá excluir as passagens de Entrada do Paciente");
				           if(result == 0){
							   	         
				   	                     TypedQuery<Agendamento> query= entityManager.createQuery("select ag from Agendamento ag where ag.paciente=:paciente",Agendamento.class);
					                     query.setParameter("paciente", paciente);
					                      List<Agendamento> listaAge=  query.getResultList();
							             if(listaAge!=null){
							            	  int result2 = JOptionPane.showConfirmDialog(null, "Atenção a remoção irá excluir os agendamentos desse Paciente");
								        	  if(result == 0 && result2 == 0){
							            	     for (Passagem passagem : lista) {
										   	        	 entityManager.remove(passagem);
										   		 }
							            	     for (Agendamento a : listaAge) {
							        	              entityManager.remove(a);
									             }
							                      entityManager.remove(paciente);
							                      entityManager.getTransaction().commit();
							                      return true;
								        	 }
							            }
			                  }
	             }
	        return false;
	     }
	public Paciente retornaPaciente(int id) {
	   return entityManager.find(Paciente.class, id);
	}


}
