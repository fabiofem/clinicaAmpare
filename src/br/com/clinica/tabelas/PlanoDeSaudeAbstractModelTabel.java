package br.com.clinica.tabelas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.clinica.util.ComboboxUtil;

public class PlanoDeSaudeAbstractModelTabel extends AbstractTableModel{


	private static final long serialVersionUID = 1L;
	private List<PlanoDeSaude> lista = new ArrayList<PlanoDeSaude>();
	
	private String[] colunas = new String[]{"Plano de Saúde"};
	
	
	public PlanoDeSaudeAbstractModelTabel(){
		lista = ComboboxUtil.retornarTodos("from PlanoDeSaude");
	}
	
	
	@Override
	public int getColumnCount() {
	return colunas.length;
	}
	@Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

	@Override
	public int getRowCount() {
    return lista.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int ColumnIndex) {
		return ((PlanoDeSaude)lista.get(rowIndex)).getTipo();
	}
	public PlanoDeSaude getPlano(int row){
		return lista.get(row);
	}
	   @Override
	 public String getColumnName(int columnIndex) {
	        return colunas[columnIndex];
	    }
	 @Override
	 //modifica na linha e coluna especificada
	 public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	      PlanoDeSaude plano = lista.get(rowIndex); // Carrega o item da linha que deve ser modificado
	     switch (columnIndex) { // Seta o valor do campo respectivo
	         case 0:
	        	 plano.setTipo(aValue.toString());
	                      
	     }
	     fireTableCellUpdated(rowIndex, columnIndex);
	 }
	 /* Adiciona um registro. */
	    public void addPlanoDeSaude(PlanoDeSaude m) {
	        // Adiciona o registro.
	        lista.add(m);
	        int ultimoIndice = getRowCount() - 1;
	        fireTableRowsInserted(ultimoIndice, ultimoIndice);
	    }
	    /* Remove a linha especificada. */
	    public void remove(int indiceLinha) {
	        if(!lista.isEmpty()){
	    	 lista.remove(indiceLinha);
	         fireTableRowsDeleted(indiceLinha, indiceLinha);
	        }
	    }
		public boolean ehNovo(PlanoDeSaude plano2) {
			for (PlanoDeSaude p:lista) {
				if( p.getTipo().equals(plano2.getTipo()))   {
					return true;
				}
			}
			return false;
		}

}
