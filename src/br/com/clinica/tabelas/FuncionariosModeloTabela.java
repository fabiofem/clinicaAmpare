package br.com.clinica.tabelas;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.swing.table.AbstractTableModel;

import br.com.clinica.dao.FuncionarioDAO;
import br.com.clinica.util.ComboboxUtil;
import br.com.clinica.util.LogUtil;

public class FuncionariosModeloTabela extends AbstractTableModel{
	
	
	    private static final long serialVersionUID = -9170590686771575639L;

		private List<Funcionario> lista = new ArrayList<Funcionario>();
		
		 private String[] colunas = new String[]{
			        "Nome","Cpf","Função","Email","fone"};

		private Funcionario funcionario;
		
		private FuncionarioDAO dao = new FuncionarioDAO();


		public FuncionariosModeloTabela (){
	    	 ComboboxUtil.recuperarFuncionarios();
	    	 lista=ComboboxUtil.listaFuncionarios; 
	    }
		
		@Override
		public int getColumnCount() {
		       return colunas.length;
		}

		@Override
		public int getRowCount() {
			   return lista.size();
		}
		@Override
		    public String getColumnName(int columnIndex) {
		       
		        return colunas[columnIndex];
		    }

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
		    Funcionario funcionario = lista.get(rowIndex);
				       if(!lista.isEmpty()){
				    
							switch (columnIndex) { 
							      case 0:
							      return funcionario.getNomeCompleto();
							      case 1:
								  return funcionario.getCpf();
							      case 2:
							      return funcionario.getFuncao();
							      case 3:
							      return funcionario.getEmail(); 
							      case 4:
								  return funcionario.getFone();
							      case 5:
								  return funcionario.getPathImagem();
							default:
								break;
							}
		             }else{
		    	          LogUtil.Log(FuncionariosModeloTabela.class.getName()+" Não tem nada na lista de funcionarios ", Level.SEVERE);
			             
		             }
				       return new Funcionario();
		}
		
		
		public void setValueTable(Object aValue, int rowIndex, int columnIndex)throws Exception{
			        this.setValueAt(aValue, rowIndex, columnIndex);
			        if(funcionario.getCpf().equals("CPF_REPETIDO")){
			        	funcionario.setCpf("");
			        	throw new Exception("Esse CPF já existe! Coloque outro");
			        }
		}
		 @Override
		 //modifica na linha e coluna especificada
		 public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		     Funcionario funcionario = lista.get(rowIndex);
		              if(!lista.isEmpty()){
								 switch (columnIndex) { // Seta o valor do campo respectivo
								      case 0:
								      funcionario.setNomeCompleto((String)aValue);
								      break;
								      case 1:
								    	  funcionario.setCpf((String)aValue);
								    	  boolean r =  dao.verificarRepeticaoCPF(funcionario);  
								    	if(r){
								    	   	funcionario.setCpf("CPF_REPETIDO");
								    	   	break;
								    	}else{
									     funcionario.setCpf((String)aValue);
									     break;
								    	}
								      case 2:
								      funcionario.setFuncao((Funcao)aValue);
								      break;
								      case 3:
								      funcionario.setEmail((String)aValue); 
								      break;
								      case 4:
									  funcionario.setFone( (String)aValue);
									  break;
								      case 5:
									  funcionario.setPathImagem( (String)aValue);
									  break;
								 }
							     fireTableCellUpdated(rowIndex, columnIndex);
		              }else{
		            	  LogUtil.Log(FuncionariosModeloTabela.class.getName()+" Não tem nada na lista de funcionarios ", Level.SEVERE);
		              }
		              
		       
		          }
		
		
		
		

		
		 /* Adiciona um registro. */
		    public void addFuncionario(Funcionario m) {
		        // Adiciona o registro.
		        lista.add(m);
		        int ultimoIndice = getRowCount() - 1;
		        fireTableRowsInserted(ultimoIndice, ultimoIndice);
		    }
		    /* Remove a linha especificada. */
		    public void removeFuncionario(int indiceLinha) {
		        lista.remove(indiceLinha);
		        fireTableRowsDeleted(indiceLinha, indiceLinha);
		    }
		    /* Adiciona uma lista de Cliente ao final dos registros. */
		    public void addListaFuncionario(List<Funcionario> cliente) {
		        // Pega o tamanho antigo da tabela.
		        int tamanhoAntigo = getRowCount();
		        // Adiciona os registros.
		        lista.addAll(cliente);
		        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
		    }
		
		
		   /* Verifica se este table model esta vazio. */
	    public boolean isEmpty() {
	        return lista.isEmpty();
	    }

		public Funcionario getFuncionario(int selRow) {
			   funcionario = lista.get(selRow);
		       return funcionario;
		}

		public Funcionario getFuncionario() {
		  return funcionario;
		}

	



}
