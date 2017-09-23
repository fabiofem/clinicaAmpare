package br.com.clinica.apresentacao;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.com.clinica.tabelas.PlanoDeSaudeAbstractModelTabel;

public class TelaCadastroPlano extends JFrame {

	
	private static final long serialVersionUID = 1586051983434014503L;
	private JPanel contentPane;
	private JTable tablePlano;
	private JButton buttonCadastro;
	private JButton buttonRemover;
	private JTextField textFieldNomePlano;
	private PlanoDeSaudeAbstractModelTabel defaultTableModel = new PlanoDeSaudeAbstractModelTabel();
    public final static String CMD_CADASTRAR_PLANO="Cadastro Plano";
    public final static String CMD_REMOVER_PLANO="Remover Plano";
	
	public TelaCadastroPlano() {
	
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNomePlano = new JLabel("Nome do Plano");
		
		textFieldNomePlano = new JTextField();
		textFieldNomePlano.setColumns(10);
		
		buttonCadastro = new JButton(CMD_CADASTRAR_PLANO);
		
		JScrollPane scrollPanePlano = new JScrollPane();
		
		buttonRemover = new JButton(CMD_REMOVER_PLANO);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNomePlano)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textFieldNomePlano, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
							.addComponent(buttonCadastro)
							.addComponent(scrollPanePlano, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
						.addComponent(buttonRemover))
					.addContainerGap(219, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNomePlano)
						.addComponent(textFieldNomePlano, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(buttonCadastro)
					.addGap(18)
					.addComponent(scrollPanePlano, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(buttonRemover)
					.addContainerGap(43, Short.MAX_VALUE))
		);
		
		tablePlano = new JTable();		
		
		tablePlano.setModel(defaultTableModel);
		scrollPanePlano.setViewportView(tablePlano);
		contentPane.setLayout(gl_contentPane);
	}
    
	
	
	public JTable getTablePlano() {
		return tablePlano;
	}

	public void setTablePlano(JTable tablePlano) {
		this.tablePlano = tablePlano;
	}

	public JButton getButtonCadastro() {
		return buttonCadastro;
	}

	public void setButtonCadastro(JButton buttonCadastro) {
		this.buttonCadastro = buttonCadastro;
	}

	public JButton getButtonRemover() {
		return buttonRemover;
	}

	public void setButtonRemover(JButton buttonRemover) {
		this.buttonRemover = buttonRemover;
	}

	public JTextField getTextFieldNomePlano() {
		return textFieldNomePlano;
	}

	public void setTextFieldNomePlano(JTextField textFieldNomePlano) {
		this.textFieldNomePlano = textFieldNomePlano;
	}

	public PlanoDeSaudeAbstractModelTabel getDefaultTableModel() {
		return defaultTableModel;
	}


	

}
