package br.com.clinica.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;

import br.com.clinica.dao.FuncionarioDAO;
import br.com.clinica.dao.PacienteDAO;
import br.com.clinica.dao.PassagemDoDiaDAO;
import br.com.clinica.tabelas.Funcao;
import br.com.clinica.tabelas.Funcionario;
import br.com.clinica.tabelas.Impressao;
import br.com.clinica.tabelas.Paciente;
import br.com.clinica.tabelas.Passagem;
import br.com.clinica.tabelas.PassagemDoDia;
import br.com.clinica.tabelas.PlanoDeSaude;

public class RestaurarBD {
	public static void main(String[] args) {
		 // restaurarImpressoesPacientes();
		 //restaurarImpressoesFuncionarios();
		restaurarPlanos();
	//criarPassagensPac();
		

		
		
		
	}
	
	public static void criarPassagensPac(){
	    PassagemDoDiaDAO dao = new PassagemDoDiaDAO();
		PassagemDoDia dia = dao.retornaPassagemDoDia(Calendar.getInstance());
		PacienteDAO pacienteDAO = new PacienteDAO();
		
		Paciente paciente = pacienteDAO.retornaPaciente(36); 
		Passagem passagem =new Passagem();
		passagem.setDataEntrada(Calendar.getInstance());
		Passagem passagem2 =new Passagem();
		passagem2.setDataEntrada(Calendar.getInstance());
		Passagem passagem3 =new Passagem();
		passagem3.setDataEntrada(Calendar.getInstance());
		passagem.setPessoa(paciente);
		passagem2.setPessoa(paciente);
		passagem3.setPessoa(paciente);
		
		dao.inserir(dia, passagem);
		dao.inserir(dia, passagem2);
		dao.inserir(dia, passagem3);
		
	}
	public static void criarPassagensFunc(){
		PassagemDoDiaDAO dao = new PassagemDoDiaDAO();
		PassagemDoDia dia = dao.retornaPassagemDoDia(Calendar.getInstance());
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		
		Funcionario paciente = funcionarioDAO.retornaFuncionario(48); 
		Passagem passagem =new Passagem();
		passagem.setDataEntrada(Calendar.getInstance());
		Passagem passagem2 =new Passagem();
		passagem2.setDataEntrada(Calendar.getInstance());
		Passagem passagem3 =new Passagem();
		passagem3.setDataEntrada(Calendar.getInstance());
		passagem.setPessoa(paciente);
		passagem2.setPessoa(paciente);
		passagem3.setPessoa(paciente);
		
		dao.inserir(dia, passagem);
		dao.inserir(dia, passagem2);
		dao.inserir(dia, passagem3);
		
	}
	
	
	public static void restaurarImpressoesPacientes(){
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<PlanoDeSaude> lista =(List) ComboboxUtil.retornarTodos("from PlanoDeSaude");
		final String path="C:/CLINICA/File/Pacientes";
		try{
		   File f = new File(path);
		   File[] nomes = f.listFiles();
		   for (int i = 0; i < nomes.length; i++) {
			    String token[] = nomes[i].getPath().split("_");
			    	Paciente p = new Paciente();
			    	p.setNomeCompleto(token[1]);
			    	p.setCpf(token[2]);
			    	p.setEmail(token[3]);
			    	for(PlanoDeSaude plano:lista){
			    	   if(plano.getTipo().equals(token[4])){
			    		   p.setPlano(plano);
			    		   break;
			    	   }
			    		
			    	}
				    @SuppressWarnings("resource")
					FileInputStream fileInputStream = new FileInputStream((nomes[i])); 
				    int valor=0;
				    List<Byte> listaBytes = new ArrayList<Byte>();
				    while( (valor= fileInputStream.read()) != -1){
				         listaBytes.add((byte)valor); 
				    }
				    byte b[] =new byte[listaBytes.size()];
				    for (int j = 0; j < b.length; j++) {
						b[j] = listaBytes.get(j);
					}
				    Impressao impressao = new Impressao();
				    impressao.deserialize(b);
				    EntityManager m = JPAUtil.getEntityManager();
                    m.getTransaction().begin();
                    m.persist(impressao);
                    p.setImpressao(impressao);
                    m.persist(p);
                    m.getTransaction().commit();
                   
				   
			    
		   }
		}catch(Exception e){
			LogUtil.Log(RestaurarBD.class+ "Ocorreu um problema na recuperação "+e.getMessage(),Level.SEVERE);
			e.printStackTrace();
			
		}
		
		
		
	}
	
    public static void restaurarImpressoesFuncionarios(){
		final String path= "C:/CLINICA/File/Funcionarios";
		try{
		   File f = new File(path);
		   File[] nomes = f.listFiles();
		   for (int i = 0; i < nomes.length; i++) {
			    String token[] = nomes[i].getPath().split("_");
			    System.out.println(token[i]);
			    Funcionario funcionario = new Funcionario();
			    funcionario.setNomeCompleto(token[1]);
			    funcionario.setCpf(token[2]);
			    funcionario.setEmail(token[3]);
			    funcionario.setFone(token[4]);
			    funcionario.setFuncao( Funcao.valueOf(token[5]) ); 
			    @SuppressWarnings("resource")
				final FileInputStream fileInputStream = new FileInputStream((nomes[i])); 
				    int valor=0;
				    List<Byte> listaBytes = new ArrayList<Byte>();
				    while( (valor= fileInputStream.read()) != -1){
				         listaBytes.add((byte)valor); 
				    }
				    byte b[] =new byte[listaBytes.size()];
				    for (int j = 0; j < b.length; j++) {
						b[j] = listaBytes.get(j);
					}
				    Impressao impressao = new Impressao();
				    impressao.deserialize(b);
				    EntityManager m = JPAUtil.getEntityManager();
                    m.getTransaction().begin();
                    m.persist(impressao);
                    funcionario.setImpressao(impressao);
                    m.persist(funcionario);
                    m.getTransaction().commit();
                   
				   
			    
		   }
		}catch(Exception e){
			LogUtil.Log(RestaurarBD.class+ "Ocorreu um problema na recuperação "+e.getMessage(),Level.SEVERE);
			e.printStackTrace();
			
		}
		
		
		
	}
	
	
	
	
	
	public static void restaurarPlanos(){
	    EntityManager manager  = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		PlanoDeSaude deSaude = new PlanoDeSaude();
		deSaude.setTipo("ALFAMED CARTÃO DE DESCONTO");
		PlanoDeSaude deSaude2 = new PlanoDeSaude();
		deSaude2.setTipo("AMIL MEDIAL");
		PlanoDeSaude deSaude3 = new PlanoDeSaude();
		deSaude3.setTipo("BEM ESTAR");
		PlanoDeSaude deSaude4 = new PlanoDeSaude();
		deSaude4.setTipo("BRADESCO SAÚDE");
		PlanoDeSaude deSaude5 = new PlanoDeSaude();
		deSaude5.setTipo("CLINICA TAUBATÉ");
		PlanoDeSaude deSaude6 = new PlanoDeSaude();
		deSaude6.setTipo("ECONLIFE");
		
		
		PlanoDeSaude deSaude8 = new PlanoDeSaude();
		deSaude8.setTipo("FUSEX SAÚDE");
		PlanoDeSaude deSaude9 = new PlanoDeSaude();
		deSaude9.setTipo("GAMA SAUDE");
		PlanoDeSaude deSaude10 = new PlanoDeSaude();
		deSaude10.setTipo("GLOBO ASSISTÊNCIA FAMILIAR");
		PlanoDeSaude deSaude12 = new PlanoDeSaude();
		deSaude12.setTipo("MEDSERVICE");
		PlanoDeSaude deSaude13 = new PlanoDeSaude();
		deSaude13.setTipo("PARTICULAR");
		PlanoDeSaude deSaude14 = new PlanoDeSaude();
		deSaude14.setTipo("PASSE");
		PlanoDeSaude deSaude15 = new PlanoDeSaude();
		deSaude15.setTipo("PETROBRAS");
		PlanoDeSaude deSaude16 = new PlanoDeSaude();
		deSaude16.setTipo("PLANO FIDELIDADE");
		PlanoDeSaude deSaude17 = new PlanoDeSaude();
		deSaude17.setTipo("ECONLIFE DESCONTO");
		PlanoDeSaude deSaude18 = new PlanoDeSaude();
		deSaude18.setTipo("PLANO PAS");
		PlanoDeSaude deSaude19 = new PlanoDeSaude();
		deSaude19.setTipo("POLICLIN SAUDE");
		PlanoDeSaude deSaude20 = new PlanoDeSaude();
		deSaude20.setTipo("PORTO SEGURO");
		PlanoDeSaude deSaude21 = new PlanoDeSaude();
		deSaude21.setTipo("POSTAL SAUDE");
		PlanoDeSaude deSaude22 = new PlanoDeSaude();
		deSaude22.setTipo("SABESPREV");
		PlanoDeSaude deSaude23 = new PlanoDeSaude();
		deSaude23.setTipo("SANTA CASA SAUDE");
		PlanoDeSaude deSaude24 = new PlanoDeSaude();
		deSaude24.setTipo("SA�DE CAIXA");
		PlanoDeSaude deSaude25 = new PlanoDeSaude();
		deSaude25.setTipo("SINDICATO DOS BANCÁRIOS E FINANCIERIOS");
		PlanoDeSaude deSaude26 = new PlanoDeSaude();
		deSaude26.setTipo("TEM ADMINISTRADORA DE CARTÕES");
		PlanoDeSaude deSaude27 = new PlanoDeSaude();
		deSaude27.setTipo("USE SAÚDE");		   
	    manager.persist(deSaude);manager.persist(deSaude2);manager.persist(deSaude3);	
		manager.persist(deSaude4);manager.persist(deSaude5);manager.persist(deSaude6);
		
		manager.persist(deSaude8);manager.persist(deSaude9);manager.persist(deSaude10);	
		manager.persist(deSaude12);manager.persist(deSaude13);manager.persist(deSaude14);
		manager.persist(deSaude15);
		manager.persist(deSaude16);manager.persist(deSaude17);manager.persist(deSaude18);	
		manager.persist(deSaude19);manager.persist(deSaude20);manager.persist(deSaude21);
		manager.persist(deSaude22);
		manager.persist(deSaude23);manager.persist(deSaude24);manager.persist(deSaude25);	
		manager.persist(deSaude26);manager.persist(deSaude27);
		
		
		manager.getTransaction().commit();
		
	}

}
