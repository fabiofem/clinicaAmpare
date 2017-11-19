package br.com.clinica.apresentacao;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import br.com.clinica.negocio.ISubjectBiometria;
import br.com.clinica.tabelas.Funcao;
import br.com.clinica.util.LogUtil;

public class TelaCadastroMedico extends JFrame{
	
	
	private static final long serialVersionUID = 1L;
	private JTextField textFieldCpf;
    private JTextArea textAreaLog;
    private JLabel labelImpressao;
	private JTextField textFieldPrompt;
    private JTextField textFieldNome;
    private JTextField textFieldEmail;
    private JTextField textFieldFone;

     public TelaCadastroMedico() {
		
		 inicializaComponentes();
		 inicializaEventos();		    
	 }


	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void inicializaComponentes(){
		
        setBounds(100, 100, 770, 455);
		
		JPanel panelBotoes = new JPanel();
		
		JSeparator separator = new JSeparator();
		
		JPanel panelImpressaoImagem = new JPanel();
		
		JPanel panelPrompt = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelBotoes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(266)
							.addComponent(panelImpressaoImagem, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(panelPrompt, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panelBotoes, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(55)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelImpressaoImagem, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
						.addComponent(panelPrompt, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE))
					.addGap(63))
		);
		
		labelImpressao = new JLabel("");
		labelImpressao.setPreferredSize(new Dimension(240, 280));
		labelImpressao.setBorder(BorderFactory.createLoweredBevelBorder());
		
		GroupLayout gl_panelImpressaoImagem = new GroupLayout(panelImpressaoImagem);
		gl_panelImpressaoImagem.setHorizontalGroup(
			gl_panelImpressaoImagem.createParallelGroup(Alignment.LEADING)
				.addComponent(labelImpressao, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
		);
		gl_panelImpressaoImagem.setVerticalGroup(
			gl_panelImpressaoImagem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelImpressaoImagem.createSequentialGroup()
					.addComponent(labelImpressao, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panelImpressaoImagem.setLayout(gl_panelImpressaoImagem);
		JLabel labelPrompt = new JLabel("Prompt:");
		
		textFieldPrompt = new JTextField();
		textFieldPrompt.setText("");
		textFieldPrompt.setMaximumSize(textFieldPrompt.getPreferredSize());
	    textFieldPrompt.setColumns(10);
	    textFieldPrompt.setBorder(
				BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), ""),
					BorderFactory.createLoweredBevelBorder()
				));
	    
	    
		
		JLabel labelStatus = new JLabel("Status:\r\n");
		
	
		
		JScrollPane scrollPaneLog = new JScrollPane();
		GroupLayout gl_panelPrompt = new GroupLayout(panelPrompt);
		gl_panelPrompt.setHorizontalGroup(
			gl_panelPrompt.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrompt.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelPrompt.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneLog, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
						.addComponent(labelPrompt)
						.addComponent(textFieldPrompt, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
						.addComponent(labelStatus))
					.addContainerGap())
		);
		gl_panelPrompt.setVerticalGroup(
			gl_panelPrompt.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrompt.createSequentialGroup()
					.addComponent(labelPrompt)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldPrompt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelStatus)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneLog, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
		);
		
		textAreaLog = new JTextArea();
	    textAreaLog.setEditable(false);
	
	    textAreaLog.setFont(UIManager.getFont("Panel.font"));
	    
		
		scrollPaneLog.setViewportView(textAreaLog);
		
		
		
		
		
		
		panelPrompt.setLayout(gl_panelPrompt);
		
	    	
		
		JLabel labelOrganizaoMilitar = new JLabel("Nome Completo");
		
		
		
		
		
		JLabel labelSaram = new JLabel("CPF");
		
		textFieldCpf = new JTextField();
		textFieldCpf.setColumns(10);
		
		
		
		
		
		JLabel labelNomeDeGuerra = new JLabel("Funcionário");
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		
		JLabel lblFone = new JLabel("Fone");
		
		textFieldFone = new JTextField();
		textFieldFone.setColumns(10);
		GroupLayout gl_panelBotoes = new GroupLayout(panelBotoes);
		gl_panelBotoes.setHorizontalGroup(
			gl_panelBotoes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBotoes.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelOrganizaoMilitar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(labelSaram, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(labelNomeDeGuerra, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(lblEmail)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblFone)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(textFieldFone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panelBotoes.setVerticalGroup(
			gl_panelBotoes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBotoes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelBotoes.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelSaram)
						.addComponent(labelOrganizaoMilitar)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelNomeDeGuerra)
						.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFone)
						.addComponent(textFieldFone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelBotoes.setLayout(gl_panelBotoes);
		getContentPane().setLayout(groupLayout);
		pack();
	    setLocationRelativeTo(null);
		setVisible(true); 
	    
		  	
	}
	
	
	private void inicializaEventos() {
		
	
		
	}
	

	
	
	    
	
}
