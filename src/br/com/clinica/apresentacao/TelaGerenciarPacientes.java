package br.com.clinica.apresentacao;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.logging.Level;

import javax.imageio.ImageIO;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.clinica.tabelas.Paciente;
import br.com.clinica.tabelas.PacienteModeloTabela;
import br.com.clinica.tabelas.PlanoDeSaude;
import br.com.clinica.util.ComboboxUtil;
import br.com.clinica.util.LogUtil;

public class TelaGerenciarPacientes extends JFrame {


	private static final long serialVersionUID = -4433287151939168827L;
	private JPanel contentPane;
	private PacienteModeloTabela tabelaModelo = new  PacienteModeloTabela();
	private JButton buttonRemover;
	private JTextField textFieldNome;
	private JTextField textFieldCpf;
	private JTextField textFieldEmail;
	private JButton buttonAlterar;
	private JButton buttonFoto;
	private JLabel labelFotoUsuario;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxPlanoDeSaude;
	private String pathImagemUsuario;
	private int indexRow;
	private JTable tablePacientes;
	private DefaultComboBoxModel<String> modelConvenios;
	public static final String CMD_ALTERAR_PACIENTES="Alterar Pacientes";
	public static final String CMD_REMOVER_PACIENTES="Remover Pacientes";


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TelaGerenciarPacientes() {
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAgendamento = new JLabel("Gerenciar Pacientes");
		lblAgendamento.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JSeparator separator = new JSeparator();
		
		
		JSeparator separator_1 = new JSeparator();
		
		JScrollPane scrollPane = new JScrollPane();
		
		buttonRemover = new JButton(CMD_REMOVER_PACIENTES);
		
		buttonAlterar = new JButton(CMD_ALTERAR_PACIENTES);
		
		JLabel lblNome = new JLabel("Nome:");
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		
		textFieldCpf = new JTextField();
		textFieldCpf.setColumns(10);
		
		JLabel lblCpf = new JLabel("Cpf:");
		
		JLabel lblFuno = new JLabel("Convênio:");
		
		comboBoxPlanoDeSaude = new JComboBox();
		modelConvenios = new DefaultComboBoxModel<String>();
		List<PlanoDeSaude> lista =ComboboxUtil.retornarTodos("from PlanoDeSaude");
		for(PlanoDeSaude p:lista){
			modelConvenios.addElement(p.getTipo());
		}
		
		comboBoxPlanoDeSaude.setModel(modelConvenios);
		
		JLabel lblEmail = new JLabel("Email:");
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		
	
		
		
		buttonFoto = new JButton("Foto");
		
		JPanel panel = new JPanel();
		
		JSeparator separator_2 = new JSeparator();
		
	
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(271)
					.addComponent(lblAgendamento, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(324, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 714, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonRemover)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblCpf, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textFieldCpf, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
										.addComponent(textFieldEmail, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblFuno, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBoxPlanoDeSaude, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
								.addComponent(buttonFoto, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addGap(246))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 847, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(buttonAlterar, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(728, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblAgendamento)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(buttonRemover)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNome)
								.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxPlanoDeSaude, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFuno))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail)
								.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCpf)
								.addComponent(buttonFoto)))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonAlterar)
					.addContainerGap(94, Short.MAX_VALUE))
		);
		
		tablePacientes = new JTable();
		tablePacientes.setModel(tabelaModelo);
		
		
		scrollPane.setViewportView(tablePacientes);
		
		labelFotoUsuario = new JLabel("");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(labelFotoUsuario, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addGap(44))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(labelFotoUsuario, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		
		
		
		
	
		
		
		contentPane.setLayout(gl_contentPane);
		inicializaEventos();
	}
	private void inicializaEventos() {
        
		buttonFoto.addActionListener(new ActionListener() {
			
			   

				public void actionPerformed(ActionEvent e) {
			
				  JFileChooser file = new JFileChooser(); 
				  file.showOpenDialog(null);
		          file.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        	 if(file.getSelectedFile()!=null){ 
		        		 pathImagemUsuario = file.getSelectedFile().getAbsolutePath();
		        		 desenharUsuario(pathImagemUsuario);
			         }
		   }
		});
	    
		tablePacientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
                 int selRow=  tablePacientes.getSelectedRow();				
				if(selRow != -1) {
	           	    if(! tabelaModelo.isEmpty()){
	           	    	Paciente paciente  = tabelaModelo.getPaciente(selRow);
	                    textFieldNome.setText(paciente.getNomeCompleto());
	                    textFieldCpf.setText(paciente.getCpf());
	                    textFieldEmail.setText(paciente.getEmail());
	                    if(paciente.getPlano()!=null){
	                      comboBoxPlanoDeSaude.setSelectedItem(paciente.getPlano().getTipo());
	                    }else{
	                    	comboBoxPlanoDeSaude.setSelectedItem("");
	                    	
	                    }
	                    desenharUsuario(paciente.getPathImagem());
	                    setIndexRow(selRow);	
	           	    }
	             }

				
				
				
				
			}
		});
		
		
		
		
		
		
	}	

	
	public void desenharUsuario(String path){
		pathImagemUsuario= path;
		ImageIcon imageUsuario = null;
		BufferedImage imagem = null;
		if(path != null){
			
		
				try{
					 imagem = ImageIO.read(new File(path));
					 imageUsuario =  new ImageIcon(imagem);
					 imageUsuario.setImage( imageUsuario.getImage().getScaledInstance(labelFotoUsuario.getWidth(), labelFotoUsuario.getHeight(), Image.SCALE_SMOOTH));
					 labelFotoUsuario.setIcon(imageUsuario);
		        }catch(Exception pane){
					 
		        	pane.printStackTrace();
        			JOptionPane.showMessageDialog(null, "A imagem apresenta problemas por favor converte-a para png!");
        			LogUtil.Log(TelaCadastroPaciente.class.getName()+"  "+pane.getMessage(),Level.SEVERE); 
      
		        
		        }
				  
		}else{
			labelFotoUsuario.setIcon(null);
		}
		
		
	}
	
	
	
	
	
	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxPlanoDeSaude() {
		return comboBoxPlanoDeSaude;
	}
	public void setComboBoxPlanoDeSaude(@SuppressWarnings("rawtypes") JComboBox comboBoxPlanoDeSaude) {
		this.comboBoxPlanoDeSaude = comboBoxPlanoDeSaude;
	}
	public JButton getButtonRemover() {
		return buttonRemover;
	}
	public void setButtonRemover(JButton buttonRemover) {
		this.buttonRemover = buttonRemover;
	}
	public JTextField getTextFieldNome() {
		return textFieldNome;
	}
	public void setTextFieldNome(JTextField textFieldNome) {
		this.textFieldNome = textFieldNome;
	}
	public JTextField getTextFieldCpf() {
		return textFieldCpf;
	}
	public void setTextFieldCpf(JTextField textFieldCpf) {
		this.textFieldCpf = textFieldCpf;
	}
	public JTextField getTextFieldEmail() {
		return textFieldEmail;
	}
	public void setTextFieldEmail(JTextField textFieldEmail) {
		this.textFieldEmail = textFieldEmail;
	}
	
	public JButton getButtonAlterar() {
		return buttonAlterar;
	}
	public void setButtonAlterar(JButton buttonAlterar) {
		this.buttonAlterar = buttonAlterar;
	}
	
	public static void main(String[] args) {
		TelaGerenciarFuncionarios t=	new TelaGerenciarFuncionarios();
		t.setVisible(true);
	}
	public JButton getBtnRemover() {
		return buttonRemover;
	}



	public void setBtnRemover(JButton btnRemover) {
		this.buttonRemover = btnRemover;
	}
	
	
	public String getPathImagem() {
		return pathImagemUsuario;
	}
	public int getIndex() {
		return indexRow;
	}
	public void setIndexRow(int selRow) {
		this.indexRow =selRow;
		
	}
	public JTable getTabelaPacientes() {
		return tablePacientes;
	}
	public PacienteModeloTabela getModeloTabelaPacientes() {
		return tabelaModelo;
	}
	
}
