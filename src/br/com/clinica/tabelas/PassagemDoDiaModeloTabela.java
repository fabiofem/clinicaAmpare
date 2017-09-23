package br.com.clinica.tabelas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.clinica.util.ComboboxUtil;
import br.com.clinica.util.ConversorDatasUtil;

public class PassagemDoDiaModeloTabela  extends AbstractTableModel{
    
	
	private static final long serialVersionUID = 2716472947400295049L;

	private List<PassagemDoDia> lista = new ArrayList<PassagemDoDia>();
	
	
	public PassagemDoDiaModeloTabela(){
		lista = ComboboxUtil.retornarTodos("from PassagemDoDia");
	}
	
	
	 private String[] colunas = new String[]{
		        "Datas das Leituras"};
	
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
	    PassagemDoDia passagem = lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			 return ConversorDatasUtil.formatoDatas(passagem.getDataPassagem());
		default:
			break;
		}
		return null;
	}
	   /* Verifica se este table model esta vazio. */
    public boolean isEmpty() {
        return lista.isEmpty();
    }

	public PassagemDoDia getDia(int selRow) {
	       return lista.get(selRow);
	}

}
