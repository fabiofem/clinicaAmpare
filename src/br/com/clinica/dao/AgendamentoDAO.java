package br.com.clinica.dao;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;

import br.com.clinica.tabelas.Agendamento;
import br.com.clinica.util.JPAUtil;
import br.com.clinica.util.LogUtil;

public class AgendamentoDAO {
	
	private EntityManager entityManager = JPAUtil.getEntityManager();
	
	public  void cadastrar(Agendamento agendamento){
		 if(!entityManager.getTransaction().isActive()){
		    	entityManager.getTransaction().begin();
		  }
		 entityManager.persist(agendamento);
         entityManager.getTransaction().commit();
 		 LogUtil.Log(Agendamento.class.getName()+"Cadastrando: "+agendamento,Level.INFO);
    }
	public  void atualizar(Agendamento agendamento){
		 if(!entityManager.getTransaction().isActive()){
		    	entityManager.getTransaction().begin();
		  }
		 entityManager.merge(agendamento);
         entityManager.getTransaction().commit();	
         LogUtil.Log(Agendamento.class.getName()+"Atualizando: "+agendamento,Level.INFO);
        
   }
	@SuppressWarnings("unchecked")
	public  List<Agendamento> retornarTodos(){
		   return entityManager.createQuery("from Agendamento").getResultList();
	}

	public void remover(Agendamento p) throws Exception { 
		 if(!entityManager.getTransaction().isActive()){
		    	entityManager.getTransaction().begin();
		  }
	       entityManager.remove(p);
           entityManager.getTransaction().commit();
           LogUtil.Log(Agendamento.class.getName()+"Removendo: "+p,Level.INFO);
		   		  
	}

}
