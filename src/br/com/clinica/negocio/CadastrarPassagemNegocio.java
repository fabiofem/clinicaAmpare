package br.com.clinica.negocio;




import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;

import javax.swing.table.DefaultTableModel;

import br.com.clinica.dao.PassagemDoDiaDAO;
import br.com.clinica.tabelas.Funcionario;
import br.com.clinica.tabelas.Passagem;
import br.com.clinica.tabelas.PassagemDoDia;
import br.com.clinica.tabelas.Pessoa;
import br.com.clinica.util.LogUtil;

public class CadastrarPassagemNegocio {
	
	
	private PassagemDoDiaDAO dPassagemDoDiaDAO = new PassagemDoDiaDAO();
	private PassagemDoDia passagemDoDia;
	private DefaultTableModel tabelaEntrada;
	private DefaultTableModel tabelaPacientes;
	private DefaultTableModel tabelaSaida;
	public CadastrarPassagemNegocio() {
		
	}
	public CadastrarPassagemNegocio(DefaultTableModel tabelaPacientes, DefaultTableModel tabelaEntrada, DefaultTableModel tabelaSaida){
		this.tabelaEntrada = tabelaEntrada;
		this.tabelaSaida = tabelaSaida;
		this.tabelaPacientes =tabelaPacientes;
		
		try{
			passagemDoDia = dPassagemDoDiaDAO.retornaPassagemDoDia(Calendar.getInstance());
		}catch(Exception e){
			LogUtil.Log(CadastrarPassagemNegocio.class.getName()+"  "+e.getMessage(),Level.SEVERE); 
			e.printStackTrace();
			passagemDoDia = new PassagemDoDia();
			passagemDoDia.setDataPassagem(Calendar.getInstance());
			dPassagemDoDiaDAO.inserir(passagemDoDia);
			}
		recuperTabelas();
	}


	public PassagemDoDia getPassagemDoDia() {
		return passagemDoDia;
	}
	public void recuperTabelas(){
		List<Passagem> lista = passagemDoDia.getListaPassagens();
		if(lista!=null){
	    for(Passagem passagem: lista){
               Pessoa pessoa =passagem.getPessoa();
               
			@SuppressWarnings("deprecation")
			Object linha[]= new Object[]{pessoa.getNomeCompleto(),passagem.getDataEntrada().getTime().toLocaleString()};
			
			if(passagem.getPessoa() instanceof Funcionario){
				if(passagem.isEntrada()){
					tabelaEntrada.addRow(linha);
				}else{
				    tabelaSaida.addRow(linha);
				}
			}else{
				tabelaPacientes.addRow(linha);
			}
		
	    }
		}
	}
	@SuppressWarnings("deprecation")
	public  boolean cadastroPassagem(Pessoa pessoa){
		   boolean resultado=false;
		   Passagem passagem = new Passagem();
		   passagem.setDataEntrada(Calendar.getInstance());
		   passagem.setPessoa(pessoa);
		   
		   
		   Object linha[]= new Object[]{pessoa.getNomeCompleto(),passagem.getDataEntrada().getTime().toLocaleString()};
		  
		   
		   List<Passagem> listaPassagem = passagemDoDia.getListaPassagens();
		   
		
		   
		   
		   
		   if(pessoa instanceof Funcionario){
		   
				   int repeticoes = 0;
				    for(Passagem passagemU: listaPassagem){
					   if(passagemU.getPessoa().getId() == pessoa.getId()){
						   repeticoes++;
					   }
					   
				    }
		           if((repeticoes%2) != 0){
					  passagem.setEntrada(false);   
					  resultado=false;
					  tabelaSaida.addRow(linha);
					  
				   }else{
					  tabelaEntrada.addRow(linha);
					  passagem.setEntrada(true);
					  resultado=true;
					  
				   }
           
			}else{
				passagem.setEntrada(true);
				tabelaPacientes.addRow(linha);
			}
		   dPassagemDoDiaDAO.inserir(passagemDoDia, passagem);
		   
		   return resultado;
	}
	public void setPassagemDoDia(PassagemDoDia passagemdoDia2) {
		 this.passagemDoDia = passagemdoDia2;
		
	}
	public void setTabelaEntrada(DefaultTableModel modeloTableEntrada) {
		this.tabelaEntrada=modeloTableEntrada;
		
	}
	public void setTabelaSaida(DefaultTableModel modeloTableSaida) {
		this.tabelaSaida =modeloTableSaida;
		
	}
	public void setTalelaPacientes(DefaultTableModel modeloTablePacientes) {
		this.tabelaPacientes =modeloTablePacientes;
		
	}
	

}
