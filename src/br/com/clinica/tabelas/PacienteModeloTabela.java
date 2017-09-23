package br.com.clinica.tabelas;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.swing.table.AbstractTableModel;

import br.com.clinica.dao.PacienteDAO;
import br.com.clinica.util.ComboboxUtil;
import br.com.clinica.util.LogUtil;

public class PacienteModeloTabela extends AbstractTableModel{
	
	
    private static final long serialVersionUID = -9170590686771575639L;

	private List<Paciente> lista = new ArrayList<Paciente>();
	
	 private String[] colunas = new String[]{
		        "Nome","Cpf","Plano","Email"};

	private Paciente paciente;
	private PacienteDAO dao = new PacienteDAO();
	
	
	

	public PacienteModeloTabela (){
    	 ComboboxUtil.recuperarPacientes();
    	 lista=ComboboxUtil.listaPacientes; 
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
	    Paciente paciente = lista.get(rowIndex);
	    if(!lista.isEmpty()){
					switch (columnIndex) { 
					      case 0:
					      return paciente.getNomeCompleto();
					      case 1:
						  return paciente.getCpf();
					      case 2:
					    	  if(paciente.getPlano()!=null)
					      return paciente.getPlano().getTipo();
					      case 3:
					      return paciente.getEmail(); 
					      case 4:
						  return paciente.getPathImagem();
					default:
						break;
					}
	    }else{
	      LogUtil.Log(PacienteModeloTabela.class.getName()+" Problemas lista de Pacientes está vazia!", Level.SEVERE);
	        
	    }
		return new Paciente();
		
	}
	
	public void setValueTable(Object aValue, int rowIndex, int columnIndex)throws Exception{
		        this.setValueAt(aValue, rowIndex, columnIndex);
		        if(paciente.getCpf().equals("CPF_REPETIDO")){
		        	paciente.setCpf("");
		        	throw new Exception("Esse CPF já existe! Coloque outro");
		        }
	}
	
	
	 @Override
	 //modifica na linha e coluna especificada
	 public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		 Paciente paciente = lista.get(rowIndex);
		 if(!lista.isEmpty()){
							 switch (columnIndex) { // Seta o valor do campo respectivo
							      case 0:
							      paciente.setNomeCompleto((String)aValue);
							      break;
							      case 1:
								  paciente.setCpf((String)aValue);
						    	  boolean r =  dao.verificarRepeticaoCPF(paciente);  
						    	   if(r){
						    		  paciente.setCpf("CPF_REPETIDO");
						    	   }else{
						    		  paciente.setCpf((String)aValue);
							       }
						    		break;
							      case 2:
							      List<PlanoDeSaude> lista = ComboboxUtil.retornarTodos("from PlanoDeSaude");	  
							      for(PlanoDeSaude p:lista){
							    	  if(p != null && p.getTipo().equals(aValue)){
							    		  paciente.setPlano(p);
									      break; 
							    	  }
							      
							      }
							      break;	  
					              case 3:
							      paciente.setEmail((String)aValue); 
							      break;
							      case 4:
								  paciente.setPathImagem( (String)aValue);
								  break;
							 }
						     fireTableCellUpdated(rowIndex, columnIndex);
		 }else{
			 LogUtil.Log(PacienteModeloTabela.class.getName()+" Problemas lista de Pacientes está vazia!", Level.SEVERE);
		 }
	 }
	
	
	
	

	
	 /* Adiciona um registro. */
	    public void addPaciente(Paciente m) {
	        // Adiciona o registro.
	        lista.add(m);
	        int ultimoIndice = getRowCount() - 1;
	        fireTableRowsInserted(ultimoIndice, ultimoIndice);
	    }
	    /* Remove a linha especificada. */
	    public void removePaciente(int indiceLinha) {
	        lista.remove(indiceLinha);
	        fireTableRowsDeleted(indiceLinha, indiceLinha);
	    }
	    /* Adiciona uma lista de Cliente ao final dos registros. */
	    public void addListaPaciente(List<Paciente> cliente) {
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

	public Paciente getPaciente(int selRow) {
		   paciente = lista.get(selRow);
	       return paciente;
	}

	public Paciente getPaciente() {
	  return paciente;
	}





}

