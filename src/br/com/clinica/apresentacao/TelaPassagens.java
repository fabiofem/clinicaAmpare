package br.com.clinica.apresentacao;

import java.awt.Color;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import br.com.clinica.negocio.CadastrarPassagemNegocio;
import br.com.clinica.tabelas.Passagem;
import br.com.clinica.tabelas.PassagemDoDia;
import br.com.clinica.util.ConversorDatasUtil;

public class TelaPassagens extends JFrame{
	
	private static final long serialVersionUID = 141476826726506794L;
	private JTextField textFieldData;
	private DefaultTableModel modeloTableEntrada = new DefaultTableModel(new String[][]{}, new Object[] { "Nome Completo", "Data Entrada"});
	private DefaultTableModel modeloTableSaida = new DefaultTableModel(new String[][]{}, new Object[] { "Nome Completo", "Data Saída"});
	private DefaultTableModel modeloTablePacientes = new DefaultTableModel(new String[][]{}, new Object[] { "Nome Completo", "Data Entrada"});
	
	private CadastrarPassagemNegocio cadastrarPassagemNegocio =  new CadastrarPassagemNegocio();
	private JButton buttonGerarRelatorio;
	private JTable tablePacientes;
	private JButton btnGerarRelatrioPacientes;
	public final static String CMD_MOSTRAR_RELATORIOS_PACIENTE="Mostrar Relatórios Pacientes";
	public final static String CMD_MOSTRAR_RELATORIOS_FUNCIONARIO="Mostrar Relatórios Funcionários";
	
	
	public TelaPassagens() {
		
		inicializaComponentes();	
	}
	
	



	public void inicializaAsTabelas(){
		
		cadastrarPassagemNegocio.setTabelaEntrada(modeloTableEntrada);
		cadastrarPassagemNegocio.setTabelaSaida(modeloTableSaida);
		cadastrarPassagemNegocio.setTalelaPacientes(modeloTablePacientes);
		cadastrarPassagemNegocio.recuperTabelas();
		textFieldData.setText(ConversorDatasUtil.formatoDatas( cadastrarPassagemNegocio.getPassagemDoDia().getDataPassagem()));
		
		
	}
	
	
	private void inicializaComponentes(){
		
		JSeparator separator = new JSeparator();
		
		separator.setBackground(Color.WHITE);
		
		JScrollPane scrollPaneEntrada = new JScrollPane();
		
		JScrollPane scrollPaneSaida = new JScrollPane();
		
		JLabel lblNome = new JLabel("Data:");
		
		textFieldData = new JTextField();
		textFieldData.setColumns(10);
		
		textFieldData.setText(ConversorDatasUtil.formatoDatas( Calendar.getInstance()));
		textFieldData.setColumns(10);
		textFieldData.setEditable(false);
		textFieldData.setMaximumSize(textFieldData.getPreferredSize());
		textFieldData.setColumns(10);
		textFieldData.setBorder(
				BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), ""),
					BorderFactory.createLoweredBevelBorder()
				));
		
		buttonGerarRelatorio = new JButton(CMD_MOSTRAR_RELATORIOS_FUNCIONARIO);
		
		JScrollPane scrollPanePacientes = new JScrollPane();
		
		JLabel lblPacientes = new JLabel("Pacientes");
		
		JLabel lblEntradaFuncionarios = new JLabel("Entrada Funcionários");
		
		JLabel lblSadaFuncionrios = new JLabel("Saída Funcionários");
		
		btnGerarRelatrioPacientes = new JButton(CMD_MOSTRAR_RELATORIOS_PACIENTE);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(lblNome)
					.addGap(18)
					.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(1026))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 1246, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGerarRelatrioPacientes, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPacientes, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPanePacientes, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE))
							.addGap(55)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPaneEntrada, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEntradaFuncionarios, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
									.addGap(50)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPaneSaida, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblSadaFuncionrios, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)))
								.addComponent(buttonGerarRelatorio, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(101, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNome))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 4, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPacientes)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblEntradaFuncionarios)
							.addComponent(lblSadaFuncionrios)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(scrollPanePacientes, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(scrollPaneEntrada, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(buttonGerarRelatorio)))
						.addComponent(scrollPaneSaida, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnGerarRelatrioPacientes))
		);
		
		JTable tableSaida = new JTable();
		scrollPaneSaida.setViewportView(tableSaida);
		tableSaida.setModel(modeloTableSaida);
		tablePacientes = new JTable();
		tablePacientes.setModel(modeloTablePacientes);
		
		
		scrollPanePacientes.setViewportView(tablePacientes);
		
		
		
		
		JTable tableEntrada = new JTable();
		//tableEntrada.setBorder(new LineBorder(Color.RED, 3, true));
	
		tableEntrada.setModel(modeloTableEntrada);
		scrollPaneEntrada.setViewportView(tableEntrada);
		
		
		
			
		getContentPane().setLayout(groupLayout);
		pack();
	    setLocationRelativeTo(null);		    
	    setVisible(true);
	    

		
	}  

		
    
	
	



	public void setPassagemDoDia(PassagemDoDia passagemdoDia) {
		cadastrarPassagemNegocio.setPassagemDoDia(passagemdoDia);
		
	}
	public JButton getBtnGerarRelatrioPacientes() {
		return btnGerarRelatrioPacientes;
	}


	public void setBtnGerarRelatrioPacientes(JButton btnGerarRelatrioPacientes) {
		this.btnGerarRelatrioPacientes = btnGerarRelatrioPacientes;
	}


	public List<Passagem> getListaPassagens(){
		return cadastrarPassagemNegocio.getPassagemDoDia().getListaPassagens();
	}
	
	
	public JButton getButtonGerarRelatorio() {
		return buttonGerarRelatorio;
	}
	
	public JTextField getTextFieldData() {
		return textFieldData;
	}
	public void setTextFieldData(JTextField textFieldData) {
		this.textFieldData = textFieldData;
	}


	public void setButtonGerarRelatorio(JButton buttonGerarRelatorio) {
		this.buttonGerarRelatorio = buttonGerarRelatorio;
	}
	

}

