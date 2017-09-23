package br.com.clinica.apresentacao;

import java.awt.Font;
import java.text.ParseException;
import java.util.logging.Level;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.clinica.tabelas.AgendamentoModeloTabela;
import br.com.clinica.tabelas.Paciente;
import br.com.clinica.tabelas.PlanoDeSaude;
import br.com.clinica.util.ComboboxUtil;
import br.com.clinica.util.LogUtil;

public class TelaAgendamento extends JFrame {

	private static final long serialVersionUID = -4827011917663213615L;
	private JPanel contentPane;
	private JTextField textFieldData;
	private JTextField textFieldMedico;
	private JTable tableAgendamento;
	private JButton btnSalvar;
    private AgendamentoModeloTabela tabelaModelo = new AgendamentoModeloTabela();
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxConvenio;
	private JCheckBox chckbxTemGuia;
	private JButton btnRemover;
	private JLabel lblHora;
	private JTextField textFieldHoras;
	private JLabel lblValor;
	private JTextField textFieldValor;
	public static final String CMD_SALVAR_AGENDA="Agendar";
	public static final String CMD_REMOVER_AGENDA="Remover Agenda";
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TelaAgendamento() {
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAgendamento = new JLabel("Agendar");
		lblAgendamento.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblPaciente = new JLabel("Paciente:");
		
		
		ComboboxUtil.recuperarPacientes();
		
		comboBoxPaciente = new JComboBox();
		
        DefaultComboBoxModel<String> modelPaciente = new DefaultComboBoxModel<String>();
		
		for(Paciente p:ComboboxUtil.listaPacientes){
			 modelPaciente.addElement(p.getNomeCompleto());
		}
	
		comboBoxPaciente.setModel(modelPaciente);
		
		JLabel lblConvnio = new JLabel("Convênio");
		
		comboBoxConvenio = new JComboBox();
		
		ComboboxUtil.inicializarPlanos();
		
		
		
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		
		for(PlanoDeSaude p:ComboboxUtil.listaPlanoDeSaude){
			model.addElement(p.getTipo());
		}
		comboBoxConvenio.setModel(model);
		
		JLabel lblData = new JLabel("Data:");
		
		try {
			textFieldData = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			LogUtil.Log(TelaAgendamento.class.getName()+ "Problemas na Formatação dessa data: "+textFieldData, Level.SEVERE);
			e.printStackTrace();
		}
		textFieldData.setColumns(10);
		
		chckbxTemGuia = new JCheckBox("Tem guia?");
		
		JLabel lblMdicoa = new JLabel("Médico/a:");
		
		textFieldMedico = new JTextField();
		textFieldMedico.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		
	     btnSalvar = new JButton(CMD_SALVAR_AGENDA);
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnRemover = new JButton(CMD_REMOVER_AGENDA);
		
		lblHora = new JLabel("Hora:");
		
		try {
			textFieldHoras = new JFormattedTextField(new MaskFormatter("##:##"));
		} catch (ParseException e) {
			LogUtil.Log(TelaAgendamento.class.getName()+ "Problemas na Formatação dessa data: "+textFieldHoras, Level.SEVERE);
			e.printStackTrace();
		}
		
		
		textFieldHoras.setColumns(10);
		
		lblValor = new JLabel("Valor:");
		
		textFieldValor = new JTextField();
		
		textFieldValor.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(271)
					.addComponent(lblAgendamento, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnRemover)
					.addContainerGap(725, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 714, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(128, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblData, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblPaciente, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(comboBoxPaciente, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblConvnio)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxConvenio, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
							.addComponent(lblMdicoa)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldMedico, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxTemGuia, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addGap(108))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblHora)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldHoras, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblValor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblAgendamento)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPaciente)
						.addComponent(comboBoxPaciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxTemGuia)
						.addComponent(textFieldMedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxConvenio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblConvnio)
						.addComponent(lblMdicoa))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblData)
						.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHora)
						.addComponent(textFieldHoras, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblValor)
						.addComponent(textFieldValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSalvar))
					.addGap(38)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(btnRemover)
					.addContainerGap(133, Short.MAX_VALUE))
		);
		
		tableAgendamento = new JTable();
		
		scrollPane.setViewportView(tableAgendamento);
		
		tableAgendamento.setModel(tabelaModelo);
		
		
		contentPane.setLayout(gl_contentPane);
	}
	
	
	
	

	public JButton getBtnRemover() {
		return btnRemover;
	}



	public void setBtnRemover(JButton btnRemover) {
		this.btnRemover = btnRemover;
	}



	
	public JButton getBtnSalvar() {
		return btnSalvar;
	}
	public void setBtnSalvar(JButton btnSalvar) {
		this.btnSalvar = btnSalvar;
	}
	public JTextField getTextFieldHora() {
		return textFieldHoras;
	}
	public JTextField getTextFieldHoras() {
		return textFieldHoras;
	}



	public void setTextFieldHoras(JTextField textFieldHoras) {
		this.textFieldHoras = textFieldHoras;
	}



	public JTextField getTextFieldValor() {
		return textFieldValor;
	}



	public void setTextFieldValor(JTextField textFieldValor) {
		this.textFieldValor = textFieldValor;
	}



	public AgendamentoModeloTabela getTabelaModelo() {
		return tabelaModelo;
	}



	public void setTabelaModelo(AgendamentoModeloTabela tabelaModelo) {
		this.tabelaModelo = tabelaModelo;
	}


	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxPaciente;
	
	
	public JCheckBox getChckbxTemGuia() {
		return chckbxTemGuia;
	}



	public void setChckbxTemGuia(JCheckBox chckbxTemGuia) {
		this.chckbxTemGuia = chckbxTemGuia;
	}



	public JTextField getTextFieldData() {
		return textFieldData;
	}



	public void setTextFieldData(JTextField textFieldData) {
		this.textFieldData = textFieldData;
	}



	public JTextField getTextFieldMedico() {
		return textFieldMedico;
	}



	public void setTextFieldMedico(JTextField textFieldMedico) {
		this.textFieldMedico = textFieldMedico;
	}



	public JTable getTableAgendamento() {
		return tableAgendamento;
	}



	public void setTableAgendamento(JTable tableAgendamento) {
		this.tableAgendamento = tableAgendamento;
	}







	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxPaciente() {
		return comboBoxPaciente;
	}



	public void setComboBoxPaciente(@SuppressWarnings("rawtypes") JComboBox comboBoxPaciente) {
		this.comboBoxPaciente = comboBoxPaciente;
	}



	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxConvenio() {
		return comboBoxConvenio;
	}



	@SuppressWarnings("rawtypes")
	public void setComboBoxConvenio(JComboBox comboBoxConvenio) {
		this.comboBoxConvenio = comboBoxConvenio;
	}


}
