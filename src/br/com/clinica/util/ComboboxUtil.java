package br.com.clinica.util;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;

import br.com.clinica.tabelas.Agendamento;
import br.com.clinica.tabelas.Funcionario;
import br.com.clinica.tabelas.Impressao;
import br.com.clinica.tabelas.Paciente;
import br.com.clinica.tabelas.PassagemDoDia;
import br.com.clinica.tabelas.PlanoDeSaude;

public class ComboboxUtil {
	
	private static EntityManager entityManager =JPAUtil.getEntityManager();
	public static List<Funcionario> listaFuncionarios;
	public static List<Impressao> listaImpressao;
	public static List<PlanoDeSaude> listaPlanoDeSaude;
	public static List<PassagemDoDia> listaDias;
	public static List<Paciente> listaPacientes;
	public static List<Agendamento> listaAgendamento;
	public static List<Agendamento> listaAgendamentoDoDia;
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> retornarTodos(String query){
		LogUtil.Log(ComboboxUtil.class.getName()+" RetornarTodos ",Level.INFO);
		if(!entityManager.isOpen()){
			JPAUtil.fecharFabrica();
			JPAUtil.abrir();
			entityManager =JPAUtil.getEntityManager();
		}		
	    return entityManager.createQuery(query).getResultList();
	}

	public static void iniciar() {
		listaDias = retornarTodos("from PassagemDoDia");
		listaFuncionarios  =retornarTodos("from Funcionario");
		listaImpressao  =retornarTodos("from Impressao");
		listaPlanoDeSaude  =retornarTodos("from PlanoDeSaude");
		listaPacientes = retornarTodos("from Paciente"); 
        listaAgendamento = retornarTodos(" from Agendamento");
        LogUtil.Log(ComboboxUtil.class.getName()+" As listas foram alimentadas com sucesso, veja: Lista Plano de Saude: "+listaPlanoDeSaude.size()
        +"Lista Agendamento:  "+listaAgendamento.size()+" Lista Passagem dos Dias: "+listaDias.size()+"  Lista Pacientes: "+listaPacientes.size()+"  Lista Impress�o: "+listaImpressao.size()+" Lista Funcionarios: "+listaFuncionarios.size()
        ,Level.INFO);
        
   
	}
	public static void recuperarFuncionarios() {
		listaFuncionarios = retornarTodos("from Funcionario");
		
	}
	public static void recuperarImpressao() {
		listaImpressao = retornarTodos("from Impressao");
		
	}

	public static void inicializarPlanos() {
		listaPlanoDeSaude = retornarTodos("from PlanoDeSaude");
		
	}

	public static void recuperarDiasParaRelatorio() {
		listaDias  = retornarTodos("from PassagemDoDia");
	}

	public static void recuperarPacientes() {
		listaPacientes = retornarTodos("from Paciente");
		
	}

	public static void recuperarAgendamento() {
		listaAgendamento = retornarTodos("from Agendamento");
		
	}

}
