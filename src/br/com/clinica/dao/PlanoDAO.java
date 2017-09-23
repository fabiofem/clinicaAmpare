package br.com.clinica.dao;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import br.com.clinica.tabelas.Paciente;
import br.com.clinica.tabelas.PlanoDeSaude;
import br.com.clinica.util.JPAUtil;
import br.com.clinica.util.LogUtil;

public class PlanoDAO {
	
	private EntityManager entityManager = JPAUtil.getEntityManager();
	
	public  void cadastrar(PlanoDeSaude saude){
		 if(!entityManager.getTransaction().isActive()){
			entityManager.getTransaction().begin();
	      }
		 entityManager.persist(saude);
         entityManager.getTransaction().commit();	
 		 LogUtil.Log(PlanoDeSaude.class.getName()+"Cadastrando: "+saude,Level.INFO);
    }
	public  void atualizar(PlanoDeSaude saude){
		 if(!entityManager.getTransaction().isActive()){
		    entityManager.getTransaction().begin();
		 }
		 entityManager.merge(saude);
         entityManager.getTransaction().commit();	
         LogUtil.Log(PlanoDeSaude.class.getName()+"Atualizando: "+saude,Level.INFO);
        
   }
	@SuppressWarnings("unchecked")
	public  List<PlanoDeSaude> retornarTodos(){
		   return entityManager.createQuery("from PlanoDeSaude").getResultList();
	}

	public boolean remover(PlanoDeSaude p){ 
	       TypedQuery<Paciente> q= entityManager.createQuery("select p from Paciente p where p.plano=:plano",Paciente.class);
           q.setParameter("plano", p);
           List<Paciente> lista= q.getResultList();
           if(lista!=null && !lista.isEmpty()){
        	   JOptionPane.showMessageDialog(null,"Não pode excluir esse plano já existe um paciente associado a ele, por favor tire o plano desse paciente");
        	   return false;
           }else{
        	   if(!entityManager.getTransaction().isActive()){
       			entityManager.getTransaction().begin();
       	      }
        	   entityManager.remove(p);
        	   entityManager.getTransaction().commit();
        	   LogUtil.Log(PlanoDeSaude.class.getName()+"Removendo: "+p,Level.INFO);
           }
           return true;
	}


}
