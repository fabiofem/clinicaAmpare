package br.com.clinica.apresentacao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.util.Calendar;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.capture.event.DPFPErrorListener;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityListener;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusListener;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;

import br.com.clinica.negocio.CadastrarPassagemNegocio;
import br.com.clinica.negocio.IObseverBiometria;
import br.com.clinica.negocio.ISubjectBiometria;
import br.com.clinica.tabelas.Impressao;
import br.com.clinica.util.ComboboxUtil;
import br.com.clinica.util.ConversorDatasUtil;
import br.com.clinica.util.LogUtil;

public class TelaPrincipal extends JFrame implements IObseverBiometria{

	
	private static final long serialVersionUID = -2757720426291301319L;
	private JPanel contentPane;
	private JTextField textFieldDataAtual;
	private JTextField textFieldNome;
	private JTable tableEntrada;
	private JTable tableSaida;
	private JMenuItem mntmFuncionario;
	private JMenuItem mntmPacientes;
	private JMenuItem mntmPlanosDeSade;
	private JMenu mnAdministrao;
	private JMenu mnRelatrio;
	private JMenu mnSobre;
	private DefaultTableModel modeloTableEntradaPacientes = new DefaultTableModel(new String[][]{}, new Object[] { "Nome Completo", "Data Entrada"});
	private DefaultTableModel modeloTableEntrada = new DefaultTableModel(new String[][]{}, new Object[] { "Nome Completo", "Data Entrada"});
	private DefaultTableModel modeloTableSaida = new DefaultTableModel(new String[][]{}, new Object[] { "Nome Completo", "Data Saída"});
	private CadastrarPassagemNegocio cadastrarPassagemNegocio = new CadastrarPassagemNegocio(modeloTableEntradaPacientes,modeloTableEntrada,modeloTableSaida);
	private JTextField textFieldLeitora;
	private  ISubjectBiometria biometria;
	private JLabel labelFotoUsuario;
	private JLabel lblSada_1;
	private JLabel labelDigital;
	private JTextField textFieldMensagemUsuario;
	private JTable tableEntradaPacientes;
	private JMenuItem mntmEscolhaODia;
	private JTextField textFieldAtualizacao;
	private JMenuItem mntmCadastrarAgendamento;
	private JMenuItem mntmGerenciarPacientes;
    private JMenuItem mntmGerenciarFuncionrios;
	private JLabel lblNewLabel;
	private JMenuItem mntmInformaes;
	private JMenuItem mntmDefinaOsParmetros;
	public final static String CMD_TELA_CADASTRO_FUNC="Cadastrar Funcionário";
	public final static String CMD_TELA_CADASTRO_PAC="Cadastrar Paciente";
	public final static String CMD_TELA_AGENDAMENTO="Cadastrar Agendamento";
	public final static String CMD_TELA_CADASTRO_MEDICOS="Cadastrar Medicos";
	public final static String CMD_TELA_PLANO="Plano de Saúde";
	public final static String CMD_TELA_GERENCIAR_PAC="Gerenciar Pacientes";
	public final static String CMD_TELA_GERENCIAR_FUN="Gerenciar Funcionários";
	public final static String CMD_TELA_ESCOLHA_DIA="Escolha o dia";
	public final static String CMD_TELA_INFORMACOES="Informações";
	public final static String CMD_TELA_SOBRE="Sobre";
	public final static String CMD_TELA_DEFINIR_PARAMETROS_RELATORIOS="Defina os parâmetros";
	private JMenuItem mntmCadastroMedico;

	
	
	
	
	
	
	public JMenuItem getMntmDefinaOsParmetros() {
		return mntmDefinaOsParmetros;
	}
	public void setMntmDefinaOsParmetros(JMenuItem mntmDefinaOsParmetros) {
		this.mntmDefinaOsParmetros = mntmDefinaOsParmetros;
	}
	public JMenuItem getMntmInformaes() {
		return mntmInformaes;
	}
	public void setMntmInformaes(JMenuItem mntmInformaes) {
		this.mntmInformaes = mntmInformaes;
	}
	public TelaPrincipal(ISubjectBiometria biometria) {
		
		this.biometria= biometria;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1150, 900);
		
		JMenuBar menuBarPrincipal = new JMenuBar();
		setJMenuBar(menuBarPrincipal);
		
		JMenu menuCadastro = new JMenu("Cadastro");
		menuBarPrincipal.add(menuCadastro);
		
		mntmCadastroMedico = new JMenuItem(CMD_TELA_CADASTRO_MEDICOS);
		menuCadastro.add(mntmCadastroMedico);
		
		mntmFuncionario = new JMenuItem(CMD_TELA_CADASTRO_FUNC);
		menuCadastro.add(mntmFuncionario);
		
		mntmPacientes = new JMenuItem(CMD_TELA_CADASTRO_PAC);
		menuCadastro.add(mntmPacientes);
		
		mntmCadastrarAgendamento = new JMenuItem(CMD_TELA_AGENDAMENTO);
		menuCadastro.add(mntmCadastrarAgendamento);
		
		mntmPlanosDeSade = new JMenuItem(CMD_TELA_PLANO);
		menuCadastro.add(mntmPlanosDeSade);
		
		mnAdministrao = new JMenu("Administração");
		menuBarPrincipal.add(mnAdministrao);
		
		mntmGerenciarPacientes = new JMenuItem(CMD_TELA_GERENCIAR_PAC);
		mnAdministrao.add(mntmGerenciarPacientes);
		
		mntmGerenciarFuncionrios = new JMenuItem(CMD_TELA_GERENCIAR_FUN);
		mnAdministrao.add(mntmGerenciarFuncionrios);
		
		mnRelatrio = new JMenu("Relatório");
		menuBarPrincipal.add(mnRelatrio);
		
		mntmEscolhaODia = new JMenuItem(CMD_TELA_ESCOLHA_DIA);
		mnRelatrio.add(mntmEscolhaODia);
		
		mntmDefinaOsParmetros = new JMenuItem(CMD_TELA_DEFINIR_PARAMETROS_RELATORIOS);
		mnRelatrio.add(mntmDefinaOsParmetros);
		
		mnSobre = new JMenu("Sobre");
		menuBarPrincipal.add(mnSobre);
		
		mntmInformaes = new JMenuItem(CMD_TELA_INFORMACOES);
		mnSobre.add(mntmInformaes);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		
		
		textFieldNome.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), ""),
						BorderFactory.createLoweredBevelBorder()
					));
			
			
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		JSeparator separatorBotoesSuperior1 = new JSeparator();
		separatorBotoesSuperior1.setForeground(Color.BLUE);
		
		JScrollPane scrollPaneEntrada = new JScrollPane();
		scrollPaneEntrada.setBorder(new LineBorder(Color.BLUE));
		
		JScrollPane scrollPaneSaida = new JScrollPane();
		scrollPaneSaida.setBorder(new LineBorder(Color.BLUE));
		
		JLabel lblEntrada = new JLabel("Entrada Pacientes");
		
		JLabel lblSada = new JLabel("Entrada Funcionários");
		
		JLabel lblEstatusLeitora = new JLabel("Estatus Leitora:");
		
		textFieldLeitora = new JTextField();
		textFieldLeitora.setColumns(10);
		
		
		textFieldLeitora.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), ""),
						BorderFactory.createLoweredBevelBorder()
					));
			
			
		
		
		
		
		lblSada_1 = new JLabel("Saída Funcionários");
		
		JScrollPane scrollPanePacientes = new JScrollPane();
		scrollPanePacientes.setBorder(new LineBorder(Color.BLUE));
		
		JScrollPane scrollPaneDigital = new JScrollPane();
		
		JSeparator separatorSuperiorBotoes2 = new JSeparator();
		separatorSuperiorBotoes2.setForeground(Color.BLUE);
		
		textFieldMensagemUsuario = new JTextField();
		textFieldMensagemUsuario.setForeground(Color.RED);
		textFieldMensagemUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldMensagemUsuario.setColumns(10);
		textFieldMensagemUsuario.setBorder(
				BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), ""),
					BorderFactory.createLoweredBevelBorder()
				));
		
		
		textFieldAtualizacao = new JTextField();
		textFieldAtualizacao.setColumns(10);
		textFieldAtualizacao.setBorder(
				BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), ""),
					BorderFactory.createLoweredBevelBorder()
				));
		
		lblNewLabel = new JLabel("Atualizações:");
		
		JSeparator separatorVertical = new JSeparator();
		separatorVertical.setForeground(Color.BLUE);
		separatorVertical.setOrientation(SwingConstants.VERTICAL);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrollPaneDigital)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
							.addGap(43)
							.addComponent(separatorVertical, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(39)
									.addComponent(scrollPanePacientes, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(scrollPaneSaida, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
												.addComponent(scrollPaneEntrada, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE))
											.addGap(52))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblSada_1, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
											.addGap(154))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(107)
									.addComponent(lblEntrada, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 250, Short.MAX_VALUE)
									.addComponent(lblSada, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
									.addGap(182))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblEstatusLeitora, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldLeitora, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 244, Short.MAX_VALUE)
							.addComponent(textFieldMensagemUsuario, GroupLayout.PREFERRED_SIZE, 483, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addComponent(separatorBotoesSuperior1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1132, Short.MAX_VALUE)
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1132, Short.MAX_VALUE)
				.addComponent(separatorSuperiorBotoes2, GroupLayout.DEFAULT_SIZE, 1132, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldAtualizacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(975, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separatorBotoesSuperior1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEstatusLeitora)
						.addComponent(textFieldLeitora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldMensagemUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(separatorVertical, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(separatorSuperiorBotoes2, GroupLayout.PREFERRED_SIZE, 3, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEntrada)
								.addComponent(lblSada))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(scrollPaneSaida, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblSada_1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(scrollPaneEntrada, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(scrollPaneDigital, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPanePacientes, GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE))))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textFieldAtualizacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(60))
		);
		
		tableEntradaPacientes = new JTable();
		tableEntradaPacientes.setModel(modeloTableEntradaPacientes);
		
		
		scrollPanePacientes.setViewportView(tableEntradaPacientes);
		
		labelDigital = new JLabel("");
		//labelDigital.setBorder(new LineBorder(Color.BLUE));
		labelDigital.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), ""),
						BorderFactory.createLoweredBevelBorder()
					));
		
		scrollPaneDigital.setViewportView(labelDigital);
			
			
		
		
		
		
		
		
		labelFotoUsuario = new JLabel("");
		//labelFotoUsuario.setBorder(new LineBorder(Color.BLUE));
		labelFotoUsuario.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), ""),
						BorderFactory.createLoweredBevelBorder()
					));
			
		
		scrollPane.setViewportView(labelFotoUsuario);
			
			
		
		
		
		
		
		
		tableSaida = new JTable();
		tableSaida.setModel(modeloTableSaida);
		
		scrollPaneEntrada.setViewportView(tableSaida);
		
		tableEntrada = new JTable();
		tableEntrada.setModel(modeloTableEntrada);
		
		
		scrollPaneSaida.setViewportView(tableEntrada);
		
		JLabel lblSistemaDeControle = new JLabel("Sistema de Controle por Biometria");
		lblSistemaDeControle.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		textFieldDataAtual = new JTextField();
		textFieldDataAtual.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldDataAtual.setColumns(10);
		textFieldDataAtual.setText( ConversorDatasUtil.formatoDatas( Calendar.getInstance())+"   "+           ConversorDatasUtil.formatoDatasHoras( Calendar.getInstance()) );
		textFieldDataAtual.setEditable(false);
		textFieldDataAtual.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), ""),
						BorderFactory.createLoweredBevelBorder()
					));
			
			
		
		
		
		
		JLabel lblVersao = new JLabel("Versão 2.1");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(textFieldDataAtual, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(lblSistemaDeControle)
					.addPreferredGap(ComponentPlacement.RELATED, 435, Short.MAX_VALUE)
					.addComponent(lblVersao)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSistemaDeControle)
						.addComponent(lblVersao))
					.addContainerGap(8, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(13, Short.MAX_VALUE)
					.addComponent(textFieldDataAtual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
	}
	
	@Override
	public void process(DPFPSample sample) {
		
		
		Impressao imp = biometria.verificacao(ComboboxUtil.listaImpressao, sample);
		
		Image img = biometria.convertSampleToBitmap(sample);
        drawPicture(img);
		
		
		if(imp != null){
			
			textFieldNome.setText(imp.getPessoa().getNomeCompleto());
			
			
			
			if(imp.getPessoa().getPathImagem()!=null){
			    
		      desenhaUsuario(new File(imp.getPessoa().getPathImagem()));
			
			}
			cadastrarPassagemNegocio.cadastroPassagem(imp.getPessoa());
			textFieldMensagemUsuario.setText("Impressão foi encontrada!");		
		}else{
			textFieldMensagemUsuario.setText("Impressão não foi encontrada!");
			textFieldNome.setText("");
			labelFotoUsuario.setIcon(null);
		}
		
	}
	public void drawPicture(Image image) {
		labelDigital.setIcon(null);
		labelDigital.setIcon(new ImageIcon(image.getScaledInstance(labelDigital.getWidth(), labelDigital.getHeight(), Image.SCALE_DEFAULT)));
		
	
	}

	private void desenhaUsuario(File file){
		labelFotoUsuario.setIcon(null);
		Image imagem=null;
		ImageIcon imageUsuario =null;
		 try{
			 imagem = ImageIO.read(file);
			 imageUsuario =  new ImageIcon(imagem);
			 imageUsuario.setImage( imageUsuario.getImage().getScaledInstance(labelFotoUsuario.getWidth(), labelFotoUsuario.getHeight(), Image.SCALE_SMOOTH));
			 labelFotoUsuario.setIcon(imageUsuario); 
		 }catch(Exception pane){
			 pane.printStackTrace();
			 textFieldMensagemUsuario.setText( "Verifique se os arquivos com as fotos estão no computador:"+file.getAbsolutePath());
			 
		 }
		  
	}

	@Override
	public void addErrorListener(DPFPErrorListener arg0) {
		LogUtil.Log(TelaPrincipal.class.getName()+"Ocorreu um problema com a leitora.",Level.SEVERE);
		textFieldLeitora.setText("Ocorreu um problema com a leitora.");
				
	}

	@Override
	public void addImageQualityListener(DPFPImageQualityListener arg0) {
		textFieldLeitora.setText("Problemas na Imagem!");
		
	}

	@Override
	public void addReaderStatusListener(DPFPReaderStatusListener arg0) {
		textFieldLeitora.setText("addReaderStatusListener");	
	}

	@Override
	public void readerDisconnected(DPFPReaderStatusEvent arg0) {
		textFieldLeitora.setText("A Impressora está desconectada!");
		
	}

	@Override
	public void fingerGone(DPFPSensorEvent arg0) {
		textFieldLeitora.setText("O dedo foi  retirado!");
		
	}

	@Override
	public void fingerTouched(DPFPSensorEvent arg0) {
		textFieldLeitora.setText("A leitora foi tocada!");
		
	}

	@Override
	public void imageAcquired(DPFPSensorEvent arg0) {
		textFieldLeitora.setText("A Impressão digital foi capturada!");
	}

	@Override
	public void readerConnected(DPFPReaderStatusEvent arg0) {
		textFieldLeitora.setText("A leitora está funcionando!");
		
	}

	public JTextField getTextFieldDataAtual() {
		return textFieldDataAtual;
	}



	public void setTextFieldDataAtual(JTextField textFieldDataAtual) {
		this.textFieldDataAtual = textFieldDataAtual;
	}



	public JTextField getTextFieldNome() {
		return textFieldNome;
	}



	public void setTextFieldNome(JTextField textFieldNome) {
		this.textFieldNome = textFieldNome;
	}



	public JTable getTableEntrada() {
		return tableEntrada;
	}



	public void setTableEntrada(JTable tableEntrada) {
		this.tableEntrada = tableEntrada;
	}



	public JTable getTableSaida() {
		return tableSaida;
	}



	public void setTableSaida(JTable tableSaida) {
		this.tableSaida = tableSaida;
	}



	public JMenuItem getMntmFuncionario() {
		return mntmFuncionario;
	}



	public void setMntmFuncionario(JMenuItem mntmFuncionario) {
		this.mntmFuncionario = mntmFuncionario;
	}



	public JMenuItem getMntmPacientes() {
		return mntmPacientes;
	}



	public void setMntmPacientes(JMenuItem mntmPacientes) {
		this.mntmPacientes = mntmPacientes;
	}



	public JMenuItem getMntmPlanosDeSade() {
		return mntmPlanosDeSade;
	}



	public void setMntmPlanosDeSade(JMenuItem mntmPlanosDeSade) {
		this.mntmPlanosDeSade = mntmPlanosDeSade;
	}



	public JMenu getMnAdministrao() {
		return mnAdministrao;
	}



	public void setMnAdministrao(JMenu mnAdministrao) {
		this.mnAdministrao = mnAdministrao;
	}



	public JMenu getMnRelatrio() {
		return mnRelatrio;
	}



	public void setMnRelatrio(JMenu mnRelatrio) {
		this.mnRelatrio = mnRelatrio;
	}



	public JMenu getMnSobre() {
		return mnSobre;
	}



	public void setMnSobre(JMenu mnSobre) {
		this.mnSobre = mnSobre;
	}
	
	public JMenuItem getMntmGerenciarPacientes() {
		return mntmGerenciarPacientes;
	}
	public void setMntmGerenciarPacientes(JMenuItem mntmGerenciarPacientes) {
		this.mntmGerenciarPacientes = mntmGerenciarPacientes;
	}
	public JMenuItem getMntmGerenciarFuncionrios() {
		return mntmGerenciarFuncionrios;
	}
	public void setMntmGerenciarFuncionrios(JMenuItem mntmGerenciarFuncionrios) {
		this.mntmGerenciarFuncionrios = mntmGerenciarFuncionrios;
	}

	public JMenuItem getMntmCadastrarAgendamento() {
		return mntmCadastrarAgendamento;
	}
	public void setMntmCadastrarAgendamento(JMenuItem mntmCadastrarAgendamento) {
		this.mntmCadastrarAgendamento = mntmCadastrarAgendamento;
	}
	public JTextField getTextFieldAtualizacao() {
		return textFieldAtualizacao;
	}
	public void setTextFieldAtualizacao(JTextField textFieldAtualizacao) {
		this.textFieldAtualizacao = textFieldAtualizacao;
	}
	public JMenuItem getMntmEscolhaODia() {
		return mntmEscolhaODia;
	}
	public void setMntmEscolhaODia(JMenuItem mntmEscolhaODia) {
		this.mntmEscolhaODia = mntmEscolhaODia;
	}
	public JMenuItem getGetMntmGerenciarMedicos() {
		return mntmCadastroMedico;
	}
	public void setGetMntmGerenciarMedicos(JMenuItem getMntmGerenciarMedicos) {
		this.mntmCadastroMedico = getMntmGerenciarMedicos;
	}
}
