package br.com.clinica.tabelas;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.swing.table.AbstractTableModel;

import br.com.clinica.util.ComboboxUtil;
import br.com.clinica.util.ConversorDatasUtil;
import br.com.clinica.util.LogUtil;

public class AgendamentoModeloTabela   extends AbstractTableModel{
    
	
	private static final long serialVersionUID = 2716472947400295049L;

	private List<Agendamento> lista = new ArrayList<Agendamento>();
	
	 private String[] colunas = new String[]{
		        "Paciente","Convénio","Data","hora","Médico","Tem Guia","valor"};
	
    public AgendamentoModeloTabela (){
    	 ComboboxUtil.recuperarAgendamento();
    	 lista=ComboboxUtil.listaAgendamento; 
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
	    Agendamento agendamento = lista.get(rowIndex);
	    if(agendamento != null){
						switch (columnIndex) {
						      case 0:
						      return agendamento.getPaciente().getNomeCompleto();
						      case 1:
							  return agendamento.getPlano().getTipo();
						      case 2:
						      return ConversorDatasUtil.formatoDatas( agendamento.getAgendamento());
						      case 3:
						      return agendamento.getHora(); 
						      case 4:
							  return agendamento.getNomeMedico();
						      case 5:
						      return agendamento.isTemGuia();
						      case 6:
						      return agendamento.getValor();
						default:
							break;
		                   }
			 }else{
				 LogUtil.Log(Agendamento.class.getClass()+"  PROBLEMAS!A  CLASSE AGENDAMENTO CLASSE VEIO VAZIA!!", Level.WARNING);
			 }
		return new Agendamento();
		
	}

	
	 /* Adiciona um registro. */
	    public void addAgendamento(Agendamento m) {
	        // Adiciona o registro.
	        lista.add(m);
	        int ultimoIndice = getRowCount() - 1;
	        fireTableRowsInserted(ultimoIndice, ultimoIndice);
	    }
	    /* Remove a linha especificada. */
	    public void removeAgendamento(int indiceLinha) {
	    	if(lista != null &&  !lista.isEmpty()){
	           lista.remove(indiceLinha);
	           fireTableRowsDeleted(indiceLinha, indiceLinha);
	    	}else{
	    		 LogUtil.Log(Agendamento.class.getClass()+"  PROBLEMAS! A LISTA DE AGENDAMENTOS ESTÁ VAZIA!!", Level.WARNING);
	    	}
	    }
	    /* Adiciona uma lista de Cliente ao final dos registros. */
	    public void addListaDeCliente(List<Agendamento> cliente) {
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

	public Agendamento getAgendamento(int selRow) {
           if(lista != null && !lista.isEmpty()){		
	         return lista.get(selRow);
           }else{
        	 LogUtil.Log(Agendamento.class.getClass()+"  PROBLEMAS! A LISTA DE AGENDAMENTOS ESTÁ VAZIA!!", Level.WARNING);     	   
        	 return new Agendamento();  
           }
           
	}

}

