package br.com.clinica.apresentacao;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;
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

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.capture.event.DPFPErrorListener;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityListener;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusListener;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;

import br.com.clinica.dao.PacienteDAO;
import br.com.clinica.negocio.IObseverBiometria;
import br.com.clinica.negocio.ISubjectBiometria;
import br.com.clinica.tabelas.Impressao;
import br.com.clinica.tabelas.Paciente;
import br.com.clinica.tabelas.Pessoa;
import br.com.clinica.tabelas.PlanoDeSaude;
import br.com.clinica.util.CadastrarFileUtil;
import br.com.clinica.util.ComboboxUtil;
import br.com.clinica.util.LogUtil;

public class TelaCadastroPaciente extends JFrame implements IObseverBiometria{
	
	
	
	private static final long serialVersionUID = 1L;
	private JTextField textFieldCpf;
    private JTextArea textAreaLog;
    private JLabel labelImpressao;
    private JLabel labelFotoUsuario;
	private JButton buttonFoto;
	private String pathImagemUsuario;
	private JTextField textFieldPrompt;
	private JButton buttonResetar;
    private ISubjectBiometria iSubjectBiometria;
    private JTextField textFieldNome;
    private JTextField textFieldEmail;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxConvenio;
    
 
	
     
	
	public TelaCadastroPaciente(ISubjectBiometria modelo) {
         this.iSubjectBiometria =modelo;
		 inicializaComponentes();
		 inicializaEventos();		    
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void inicializaComponentes(){
		
        setBounds(100, 100, 770, 455);
		
		JPanel panelBotoes = new JPanel();
		
		JPanel panelAdicionarFotos = new JPanel();
		
		JSeparator separator = new JSeparator();
		
		JPanel panelImpressaoImagem = new JPanel();
		
		JPanel panelPrompt = new JPanel();
		
		JPanel panelFoto = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelBotoes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
						.addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
						.addComponent(panelAdicionarFotos, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelFoto, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panelImpressaoImagem, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(panelPrompt, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panelBotoes, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelAdicionarFotos, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelFoto, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
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
		
		
		labelFotoUsuario = new JLabel("");
		labelFotoUsuario.setPreferredSize(new Dimension(240, 280));
		labelFotoUsuario.setBorder(BorderFactory.createLoweredBevelBorder());	
		
		
		GroupLayout gl_panelFoto = new GroupLayout(panelFoto);
		gl_panelFoto.setHorizontalGroup(
			gl_panelFoto.createParallelGroup(Alignment.LEADING)
				.addComponent(labelFotoUsuario, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
		);
		gl_panelFoto.setVerticalGroup(
			gl_panelFoto.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFoto.createSequentialGroup()
					.addComponent(labelFotoUsuario, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		
		panelFoto.setLayout(gl_panelFoto);	
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
		
		
		
		
		
		JLabel labelNomeDeGuerra = new JLabel("Plano");
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		
		comboBoxConvenio = new JComboBox();
		
		ComboboxUtil.inicializarPlanos();
		
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		
		for(PlanoDeSaude p:ComboboxUtil.listaPlanoDeSaude){
			model.addElement(p.getTipo());
		}
		
		comboBoxConvenio.setModel(model);
		
		JLabel lblFone = new JLabel("Email");
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
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
					.addComponent(labelNomeDeGuerra, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBoxConvenio, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(lblFone, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
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
						.addComponent(comboBoxConvenio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFone)
						.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelBotoes.setLayout(gl_panelBotoes);
		
		buttonFoto = new JButton("Foto");

		
		JSeparator separator_1 = new JSeparator();
		
		buttonResetar = new JButton("Resetar");
		
		
		GroupLayout gl_panelAdicionarFotos = new GroupLayout(panelAdicionarFotos);
		gl_panelAdicionarFotos.setHorizontalGroup(
			gl_panelAdicionarFotos.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelAdicionarFotos.createSequentialGroup()
					.addGap(22)
					.addComponent(buttonFoto)
					.addGap(18)
					.addComponent(buttonResetar)
					.addContainerGap(552, Short.MAX_VALUE))
				.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
		);
		gl_panelAdicionarFotos.setVerticalGroup(
			gl_panelAdicionarFotos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAdicionarFotos.createSequentialGroup()
					.addGap(2)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addGroup(gl_panelAdicionarFotos.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonFoto)
						.addComponent(buttonResetar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
				
		panelAdicionarFotos.setLayout(gl_panelAdicionarFotos);
		getContentPane().setLayout(groupLayout);
		pack();
	    setLocationRelativeTo(null);
		setVisible(false); 
	    
		  	
	}
	
	
	private void inicializaEventos() {
        
		buttonFoto.addActionListener(new ActionListener() {
			
			    public void actionPerformed(ActionEvent e) {
			
				  JFileChooser file = new JFileChooser(); 
				  file.showOpenDialog(null);
		          file.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        	 if(file.getSelectedFile()!=null){ 
		        		 pathImagemUsuario = file.getSelectedFile().getAbsolutePath();
		        		 try{
		        			  BufferedImage imagem = ImageIO.read(file.getSelectedFile());
		        			  ImageIcon imageUsuario =  new ImageIcon(imagem);
			        		  imageUsuario.setImage( imageUsuario.getImage().getScaledInstance(labelFotoUsuario.getWidth(), labelFotoUsuario.getHeight(), Image.SCALE_SMOOTH));
		        			  labelFotoUsuario.setIcon(imageUsuario);
		        		 }catch(Exception pane){
		        			pane.printStackTrace();
		        			JOptionPane.showMessageDialog(null, "A imagem apresenta problemas por favor converte-a para png!");
		        			LogUtil.Log(TelaCadastroPaciente.class.getName()+"  "+pane.getMessage(),Level.SEVERE); 
		        		 }
		        		 

			         }
		   }
		});
		
		buttonResetar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				limpaOsCampos();
			
			}
		});
		
	
		
	}
	private void limpaOsCampos(){
		pathImagemUsuario =null;
		labelFotoUsuario.setIcon(null);
		labelImpressao.setIcon(null);
		textFieldNome.setText("");
		textFieldCpf.setText("");
		textFieldEmail.setText("");
		textFieldPrompt.setText("");
		textAreaLog.selectAll();
		textAreaLog.replaceSelection("");
	}
	
	public void drawPicture(Image image) {
		labelImpressao.setIcon(new ImageIcon(image.getScaledInstance(labelImpressao.getWidth(), labelImpressao.getHeight(), Image.SCALE_DEFAULT)));
	}
	private void updateStatus()
	{
    textFieldPrompt.setText("Ainda são necessárias:" + iSubjectBiometria.getEnrollment().getFeaturesNeeded()+" de leituras do dedo.");
	}
	
   private void mandaMensagemUsuario(String texto,String path) {
		LogUtil.Log(TelaCadastroPaciente.class.getName()+ " MENSAGEM AO USUÁRIO: "+texto, Level.INFO);
		if(path != null){
			ImageIcon imagem = new ImageIcon(path);
			imagem.setImage( imagem.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
			JOptionPane.showMessageDialog(null,texto,"", JOptionPane.INFORMATION_MESSAGE,imagem);
		}else{
		    JOptionPane.showMessageDialog(TelaCadastroPaciente.this, texto);
		}
	}
	
	

	@Override
	public void process(DPFPSample sample) {
		
		if(textFieldNome.getText().equals("")){
			mandaMensagemUsuario("Falta o nome do usuário.", null);
		}else if(textFieldCpf.getText().equals("")){
			mandaMensagemUsuario("Falta o cpf do usuário.", null);
		}else{	
		
		    textAreaLog.append("A impressão foi capturada."+"\n");
		    textFieldPrompt.setText("Passe o dedo novamente.");
		    updateStatus();
		    

					Image img = iSubjectBiometria.convertSampleToBitmap(sample);
			        drawPicture(img);
					try{
					// Cria um pedido de inscri��o.
					DPFPFeatureSet features = iSubjectBiometria. extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
						// verifica a qualidade e adiciona se for boa
					if (features != null){ 
										try{
											//enrollment.addFeatures(features); 
											iSubjectBiometria.getEnrollment().addFeatures(features);
									    } catch (DPFPImageQualityException ex) { 
									    	textFieldPrompt.setText("Ocorreu um erro com a imagem!");
									    	LogUtil.Log(TelaCadastroPaciente.class.getName()+ " PROBLEMAS:  "+ex.getMessage(), Level.SEVERE);
									    	ex.printStackTrace();
									    }
								      finally {
								    	  updateStatus();
									// verifica se o template foi criado.
													    switch(   iSubjectBiometria.getEnrollment().getTemplateStatus()) {
																	      case TEMPLATE_STATUS_READY:	// finalizado e para a leitura
																		            iSubjectBiometria.stop();
																					textFieldPrompt.setText("Ok, preparando para salvar...");
																			        
																					ComboboxUtil.recuperarImpressao();
																					
																					Impressao imp = iSubjectBiometria.verificacao(ComboboxUtil.listaImpressao, sample);
																					
																					
																					if(imp != null){
																						Pessoa p= imp.getPessoa();
																						
																						  mandaMensagemUsuario("Essa impress�o digital j� existe! "+p.getNomeCompleto(),p.getPathImagem());
																						  textFieldPrompt.setText("Essa impress�o digital j� existe! e pertence ao usu�rio: "+p.getNomeCompleto());
																						  
																						  
																						  iSubjectBiometria.getEnrollment().clear();
																						  iSubjectBiometria.start();
																					}else{
																					
																					        Impressao impressao = new Impressao();
																					        impressao.deserialize(iSubjectBiometria.getEnrollment().getTemplate().serialize());
																							Paciente paciente = new Paciente();
																							
																							
																							
																							
																							paciente.setNomeCompleto(textFieldNome.getText());
																							paciente.setCpf(textFieldCpf.getText());
																							
																							String nomePlano = comboBoxConvenio.getSelectedItem().toString();
																							List<PlanoDeSaude> lista = ComboboxUtil.listaPlanoDeSaude;
																							PlanoDeSaude plano=null;
																							for (PlanoDeSaude planoDeSaude : lista) {
																								if(planoDeSaude.getTipo().equals(nomePlano)){
																									plano=planoDeSaude;
																									break;
																								}
																							}
																							
																							
																							paciente.setPlano( plano);
																							paciente.setCpf(textFieldCpf.getText());	
																							paciente.setEmail(textFieldEmail.getText());
																							paciente.setPathImagem(pathImagemUsuario);
																							paciente.setAssinouGuia(false);
																						    paciente.setImpressao(impressao);
																						    PacienteDAO dao = new PacienteDAO();
																						    
																						    
																						    if(!dao.verificarRepeticaoCPF(paciente)){
																						    
																									dao.inserir(paciente, plano, impressao);
																							 	    CadastrarFileUtil.cadastrarImpressaoEmArquivo(paciente);
																							 	    mandaMensagemUsuario("Paciente criado com sucesso!",null);
																							 	    textFieldPrompt.setText("Ok, Paciente criado com sucesso.");
																							 	   
																						    }else{
																						    	String msg="Esse CPF já existe! Cadastre outro por favor!";
																						    	JOptionPane.showMessageDialog(null, msg);
																						    	limpaOsCampos();
																						        
																						    }
																						    iSubjectBiometria.getEnrollment().clear();
																					 	    iSubjectBiometria.start();
																					 	 
																							
																					}
																					
																					 limpaOsCampos();
																        break;
																	 case TEMPLATE_STATUS_FAILED:	// em caso de pane
																		   iSubjectBiometria.getEnrollment().clear();
																		   iSubjectBiometria.stop();
																		   mandaMensagemUsuario("A leitura não é válida. Repita a operação.",null);
																		   iSubjectBiometria.start();
																		 limpaOsCampos();
																	 break;
																default:
																	break;
													     }
								                    }
										
					                               }else{
					                            	   LogUtil.Log(TelaCadastroPaciente.class.getName()+" O pedido de inscrição veio vazio! (DPFPFeatureSet)",Level.SEVERE);   
					                               }
										
					
												}catch(Exception e){
													LogUtil.Log(TelaCadastroPaciente.class.getName()+"  "+e.getMessage(),Level.SEVERE); 
													e.printStackTrace();
												}

		                      }
	}
    
	@Override
	public void readerConnected(DPFPReaderStatusEvent arg0) {
		textFieldPrompt.setText("A Leitora está contectada!");
		
	}
	

	@Override
	public void addErrorListener(DPFPErrorListener arg0) {
		textAreaLog.append("Ocorreu um erro!!"+"\n");
		
	}


	@Override
	public void addImageQualityListener(DPFPImageQualityListener arg0) {
		textAreaLog.append("A qualidade da imagem não está boa!"+"\n");
		
	}


	@Override
	public void addReaderStatusListener(DPFPReaderStatusListener arg0) {
         textAreaLog.append("O leitor de digital está contectado."+"\n");

	}


	@Override
	public void readerDisconnected(DPFPReaderStatusEvent arg0) {
		textFieldPrompt.setText("A leitora está desconectada!");
		
	}


	@Override
	public void fingerGone(DPFPSensorEvent arg0) {
		textAreaLog.append("O dedo foi retirado da leitora."+"\n");
		
	}


	@Override
	public void fingerTouched(DPFPSensorEvent arg0) {
		textAreaLog.append("O dedo foi colocado na leitora."+"\n");
		
	}


	@Override
	public void imageAcquired(DPFPSensorEvent arg0) {
		textAreaLog.append("Uma impressão digital foi tirada."+"\n");
		
	}
}
