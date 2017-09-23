package br.com.clinica.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mysql.jdbc.Connection;

public class JPAUtil {
	
	private  static EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("clinica");
    private  static EntityManager manager;
    public  static void fecharFabrica(){
    	LogUtil.Log(JPAUtil.class.getName()+" FECHANDO A FÁBRICA: ",Level.INFO);
    	fabrica.close();
    	
    }
    public static Connection getConnection() throws SQLException {
        
     return (Connection) DriverManager.getConnection ("jdbc:mysql://localhost/clinica", "root", "23272008");   
    
    }
    
	public static EntityManager getEntityManager(){
		   if(manager == null){
		      manager = fabrica.createEntityManager();
		      LogUtil.Log(JPAUtil.class.getName()+"  Criado um Entity Manager com Sucesso: "+manager.toString(),Level.INFO);
		   }else{
			  LogUtil.Log(JPAUtil.class.getName()+"  Chamando um Entity Manager com Sucesso: "+manager.toString(),Level.INFO);
		   }
		return manager;
	}

	public static void abrir() {
		fabrica =Persistence.createEntityManagerFactory("clinica");
		manager = fabrica.createEntityManager();
	}


		

}
