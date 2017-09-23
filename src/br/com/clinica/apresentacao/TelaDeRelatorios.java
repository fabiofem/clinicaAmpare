package br.com.clinica.apresentacao;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.com.clinica.tabelas.Funcionario;
import br.com.clinica.tabelas.Paciente;
import br.com.clinica.tabelas.PassagemDoDia;
import br.com.clinica.util.ComboboxUtil;
import br.com.clinica.util.ConversorDatasUtil;

public class TelaDeRelatorios extends JFrame {

	
	private static final long serialVersionUID = -5938297171992539646L;
	private JPanel contentPane;
	private JButton btnButtonRelatorioPacientes;
	private JButton btnButtonRelaFunc;
	private JComboBox<String> comboBoxPacientes;
	private JComboBox<String> comboBoxFuncionarios;
    public static final String CMD_MOSTRAR_RELATORIO_FUNC="Mostrar Relatorio Func";
	public static final String CMD_MOSTRAR_RELATORIO_PAC="Mostrar Relatório Pac";
	private JComboBox<String> comboBoxDiasPac;
	private JComboBox<String> comboBoxDiasFunc;
	
	

	public JButton getBtnButtonRelaFunc() {
		return btnButtonRelaFunc;
	}

	public void setBtnButtonRelaFunc(JButton btnButtonRelaFunc) {
		this.btnButtonRelaFunc = btnButtonRelaFunc;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeRelatorios frame = new TelaDeRelatorios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TelaDeRelatorios() {
	
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblRelatrioDePacientes = new JLabel("Relatório de Pacientes:");
		
		JLabel lblDia = new JLabel("Dia:");
		

		JLabel lblNome = new JLabel("Nome:");
		
		btnButtonRelatorioPacientes = new JButton(CMD_MOSTRAR_RELATORIO_PAC);
		
		JSeparator separator = new JSeparator();
		
		JLabel lblRelatrioDeFuncionrios = new JLabel("Relatório de Funcionários");
		
		JLabel lblDia_1 = new JLabel("Dia:");
		
		
		
		JLabel lblNome_1 = new JLabel("Nome:");
		
		btnButtonRelaFunc = new JButton(CMD_MOSTRAR_RELATORIO_FUNC);
		
	    comboBoxPacientes = new JComboBox<String>();
		ComboboxUtil.recuperarPacientes();
		DefaultComboBoxModel<String> modelPaciente = new DefaultComboBoxModel<String>();
	    for(Paciente p:ComboboxUtil.listaPacientes){
			 modelPaciente.addElement(p.getNomeCompleto());
		}
	    comboBoxPacientes.setModel(modelPaciente);
	    
	    comboBoxFuncionarios = new JComboBox<String>();
		
	    ComboboxUtil.recuperarFuncionarios();
		DefaultComboBoxModel<String> modelFunc = new DefaultComboBoxModel<String>();
	    for(Funcionario f:ComboboxUtil.listaFuncionarios){
			 modelFunc.addElement(f.getNomeCompleto());
		}
	    comboBoxFuncionarios.setModel( modelFunc);
	    
	    comboBoxDiasPac = new JComboBox<String>();
	    DefaultComboBoxModel<String> modelDias = new DefaultComboBoxModel<String>();
	    DefaultComboBoxModel<String> modelDias2 = new DefaultComboBoxModel<String>();
	    
	    for(PassagemDoDia dias: ComboboxUtil.listaDias){
	    	String d = ConversorDatasUtil.formatoDatas( dias.getDataPassagem() );
	    	modelDias.addElement(d);
	    	modelDias2.addElement(d);
	    }
        comboBoxDiasFunc = new JComboBox<String>();
	    comboBoxDiasFunc.setModel(modelDias2); 
	    comboBoxDiasPac.setModel(modelDias);
	    
	    
	    
	    GroupLayout gl_contentPane = new GroupLayout(contentPane);
	    gl_contentPane.setHorizontalGroup(
	    	gl_contentPane.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_contentPane.createSequentialGroup()
	    			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	    				.addComponent(lblRelatrioDePacientes)
	    				.addGroup(gl_contentPane.createSequentialGroup()
	    					.addComponent(lblDia)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(comboBoxDiasPac, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(lblNome)
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addComponent(comboBoxPacientes, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(btnButtonRelatorioPacientes, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
	    				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 505, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblRelatrioDeFuncionrios)
	    				.addGroup(gl_contentPane.createSequentialGroup()
	    					.addComponent(lblDia_1)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(comboBoxDiasFunc, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
	    					.addGap(10)
	    					.addComponent(lblNome_1)
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addComponent(comboBoxFuncionarios, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(btnButtonRelaFunc)))
	    			.addContainerGap())
	    );
	    gl_contentPane.setVerticalGroup(
	    	gl_contentPane.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_contentPane.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(lblRelatrioDePacientes)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblDia)
	    				.addComponent(btnButtonRelatorioPacientes)
	    				.addComponent(comboBoxPacientes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(comboBoxDiasPac, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblNome))
	    			.addGap(18)
	    			.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addComponent(lblRelatrioDeFuncionrios)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblDia_1)
	    				.addComponent(btnButtonRelaFunc)
	    				.addComponent(comboBoxDiasFunc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(comboBoxFuncionarios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblNome_1))
	    			.addContainerGap(118, Short.MAX_VALUE))
	    );
		contentPane.setLayout(gl_contentPane);
	}
	public JButton getBtnButtonRelatorioPacientes() {
		return btnButtonRelatorioPacientes;
	}

	public void setBtnButtonRelatorioPacientes(JButton btnButtonRelatorioPacientes) {
		this.btnButtonRelatorioPacientes = btnButtonRelatorioPacientes;
	}



	public JComboBox<String> getComboBoxPacientes() {
		return comboBoxPacientes;
	}

	public void setComboBoxPacientes(JComboBox<String> comboBoxPacientes) {
		this.comboBoxPacientes = comboBoxPacientes;
	}

	public JComboBox<String> getComboBoxFuncionarios() {
		return comboBoxFuncionarios;
	}

	public void setComboBoxFuncionarios(JComboBox<String> comboBoxFuncionarios) {
		this.comboBoxFuncionarios = comboBoxFuncionarios;
	}

	public JComboBox<String> getComboBoxDiasPac() {
		return comboBoxDiasPac;
	}

	public void setComboBoxDiasPac(JComboBox<String> comboBoxDiasPac) {
		this.comboBoxDiasPac = comboBoxDiasPac;
	}

	public JComboBox<String> getComboBoxDiasFunc() {
		return comboBoxDiasFunc;
	}

	public void setComboBoxDiasFunc(JComboBox<String> comboBoxDiasFunc) {
		this.comboBoxDiasFunc = comboBoxDiasFunc;
	}
	


}
