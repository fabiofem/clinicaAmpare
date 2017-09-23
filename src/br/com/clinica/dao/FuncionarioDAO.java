package br.com.clinica.dao;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import br.com.clinica.tabelas.Funcionario;
import br.com.clinica.tabelas.Impressao;
import br.com.clinica.tabelas.Passagem;
import br.com.clinica.tabelas.Pessoa;
import br.com.clinica.util.JPAUtil;
import br.com.clinica.util.LogUtil;

public class FuncionarioDAO {

	private EntityManager entityManager = JPAUtil.getEntityManager();
	
	public void inserir(Pessoa  funcionario,Impressao impressao){
	     
		  if(!entityManager.getTransaction().isActive()){
		    	entityManager.getTransaction().begin();
		  } 
	     entityManager.persist(impressao);
	     entityManager.persist(funcionario);
         entityManager.getTransaction().commit();	
         LogUtil.Log(FuncionarioDAO.class.getName()+"Cadastrando: "+funcionario,Level.INFO);
		
	}
	public boolean verificarRepeticaoCPF(Pessoa  funcionario){
		TypedQuery<Pessoa> q = entityManager.createQuery("from Pessoa p where p.cpf=:cpf",Pessoa.class);
        q.setParameter("cpf", funcionario.getCpf());
        try{
        	q.getSingleResult();
        }catch(Exception e){
        	e.printStackTrace();
        	if(e instanceof NoResultException){
        	    LogUtil.Log("Não encontrei esse CPF!", Level.SEVERE);
        	    return false;
        	}else if(e instanceof NonUniqueResultException){
        		LogUtil.Log("Existe mais de um CPF! Altere por favor este e os demais!", Level.SEVERE);
        		return true;
        	}
        	
        }
	    return true;
	}
	
	public void atualizar(Pessoa p){
		  if(!entityManager.getTransaction().isActive()){
		    	entityManager.getTransaction().begin();
		  }
		entityManager.merge(p);
        entityManager.getTransaction().commit();	
        LogUtil.Log(FuncionarioDAO.class.getName()+"Atualizando: "+p,Level.INFO);
       
  }
	public boolean remover(Pessoa p){ 
		    TypedQuery<Passagem> query =entityManager.createQuery("select p from Passagem p where p.pessoa=:pessoa",Passagem.class);
	        query.setParameter("pessoa", p);
	        List<Passagem> lista= query.getResultList();
	        if(!entityManager.getTransaction().isActive()){
		    	entityManager.getTransaction().begin();
		    }
	        if(lista != null){
	        
	                     int result = JOptionPane.showConfirmDialog(null, "Atenção a remoção irá excluir as passagens de Entrada e Saída do Funcionário");
			       	     if(result == 0){
							        for (Passagem passagem : lista) {
							        	entityManager.remove(passagem);
								    }
						     	   entityManager.remove(p);
						     	   entityManager.getTransaction().commit();
						     	   JOptionPane.showMessageDialog(null, "Remoção Realizada com sucesso! Para conferir é necessário reinicializar o aplicativo");
						     	   LogUtil.Log(FuncionarioDAO.class.getName()+"Atualizando: "+p,Level.INFO);
			       	               return true;
			       	    }
	        }
	        return false;
	}
	public Funcionario retornaFuncionario(int id) {
	    return entityManager.find(Funcionario.class, id);
	}
	
	
}
