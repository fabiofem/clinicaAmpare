package br.com.clinica.apresentacao;

import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.com.clinica.tabelas.PassagemDoDiaModeloTabela;

public class TelaRelatorio extends JFrame{
	

	private static final long serialVersionUID = -3637504961857614755L;
	private JButton buttonMostrar;
	private PassagemDoDiaModeloTabela tabelaPassagemDoDia = new PassagemDoDiaModeloTabela();
	private JTable tabela;
	public static String CMD_MOSTRAR="Mostrar";


	public TelaRelatorio() {
		   inicializaComponentes();
         
    }
	private void inicializaComponentes(){
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonMostrar = new JButton(CMD_MOSTRAR);
		panel.add(buttonMostrar);
		
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 581, GroupLayout.PREFERRED_SIZE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))
		);
		
		tabela = new JTable();
		
		tabelaPassagemDoDia = new PassagemDoDiaModeloTabela();
	
        tabela.setModel(tabelaPassagemDoDia);
		scrollPane.setViewportView(tabela);
		getContentPane().setLayout(groupLayout);
		pack();
   		setLocationRelativeTo(null);
   		
		
	}
		public AbstractButton getButtonMostrar() {
		return buttonMostrar;
	}

	public PassagemDoDiaModeloTabela getTabelaPassagemDoDia() {
		return tabelaPassagemDoDia;
	}
	public void setTabelaPassagemDoDia(PassagemDoDiaModeloTabela tabelaPassagemDoDia) {
		this.tabelaPassagemDoDia = tabelaPassagemDoDia;
	}
	public JTable getTabela() {
		return tabela;
	}
	public void setTabela(JTable tabela) {
		this.tabela = tabela;
	}



}
