package br.com.clinica.dao;



import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.clinica.tabelas.Passagem;
import br.com.clinica.tabelas.PassagemDoDia;
import br.com.clinica.tabelas.Pessoa;
import br.com.clinica.util.JPAUtil;
import br.com.clinica.util.LogUtil;

public class PassagemDoDiaDAO {

    private EntityManager manager =JPAUtil.getEntityManager();
	
	
	public void inserir(PassagemDoDia p){
		if(!manager.getTransaction().isActive()){
	 	  manager.getTransaction().begin();
		}
		manager.persist(p);
		manager.getTransaction().commit();
		LogUtil.Log(PassagemDoDiaDAO.class.getName()+"Passagem do Dia "+p.getDataPassagem().getTime().toString(),Level.INFO);
	
	}
	public List<Passagem> retornarPassagensDoDia(PassagemDoDia passagemDoDia, Pessoa pessoa){
		TypedQuery<Passagem> query = manager.createQuery("from Passagem data where data.passagemDoDia=:passagemDoDia and data.pessoa=:pessoa",Passagem.class);
		query.setParameter("passagemDoDia", passagemDoDia);
		query.setParameter("pessoa", pessoa);
		return query.getResultList();
	}
	
	public PassagemDoDia retornaPassagemDoDia(int id){
		   return manager.find(PassagemDoDia.class, id); 
	}
	public PassagemDoDia retornaPassagemDoDia(Calendar data){
		 TypedQuery<PassagemDoDia> query = manager.createQuery("from PassagemDoDia d where d.dataPassagem=:data",PassagemDoDia.class);
	     query.setParameter("data", data);
	     return query.getSingleResult();
	}
	
	public void inserir(PassagemDoDia passagem, Passagem entrada){
		        try{
		        	
		           if(!manager.getTransaction().isActive()){
		        		 manager.getTransaction().begin();
		 		    }
				   passagem.addPassagens(entrada);
				   manager.persist(passagem);
				   manager.persist(entrada);
				   manager.getTransaction().commit();
				   LogUtil.Log(PassagemDoDiaDAO.class.getName()+"Passagem  "+entrada.getDataEntrada().getTime().toString(),Level.INFO);
				}catch(Exception e){
				   LogUtil.Log(PassagemDoDiaDAO.class.getName()+"Passagem do Dia "+e.getMessage(),Level.SEVERE);
				   e.printStackTrace();
			   }
		
		}
	
	public void remover(PassagemDoDia p){
		if(!manager.getTransaction().isActive()){
   		  manager.getTransaction().begin();
	    }
		manager.remove(p);
		manager.getTransaction().commit();
	}
	public List<PassagemDoDia> buscarTodos(){
		TypedQuery<PassagemDoDia> q = manager.createQuery("from PassagemDoDia ",PassagemDoDia.class);
		return q.getResultList();
	}

	
}
