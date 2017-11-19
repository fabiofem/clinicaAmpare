package br.com.clinica.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.com.clinica.apresentacao.TelaAgendamento;
import br.com.clinica.apresentacao.TelaCadastroFuncionario;
import br.com.clinica.apresentacao.TelaCadastroMedico;
import br.com.clinica.apresentacao.TelaCadastroPaciente;
import br.com.clinica.apresentacao.TelaCadastroPlano;
import br.com.clinica.apresentacao.TelaDeRelatorios;
import br.com.clinica.apresentacao.TelaGerenciarFuncionarios;
import br.com.clinica.apresentacao.TelaGerenciarPacientes;
import br.com.clinica.apresentacao.TelaPassagens;
import br.com.clinica.apresentacao.TelaPrincipal;
import br.com.clinica.apresentacao.TelaRelatorio;
import br.com.clinica.dao.AgendamentoDAO;
import br.com.clinica.dao.FuncionarioDAO;
import br.com.clinica.dao.PacienteDAO;
import br.com.clinica.dao.PassagemDoDiaDAO;
import br.com.clinica.dao.PlanoDAO;
import br.com.clinica.negocio.BiometriaCapturaImpressaoModelo;
import br.com.clinica.negocio.ISubjectBiometria;
import br.com.clinica.negocio.MandarEmailNegocio;
import br.com.clinica.negocio.SalvarBaseDeDadosNegocio;
import br.com.clinica.relatorio.jasper.GerarRelatorioJarsper;
import br.com.clinica.tabelas.Agendamento;
import br.com.clinica.tabelas.Funcionario;
import br.com.clinica.tabelas.Paciente;
import br.com.clinica.tabelas.PassagemDoDia;
import br.com.clinica.tabelas.PlanoDeSaude;
import br.com.clinica.util.ComboboxUtil;
import br.com.clinica.util.ConversorDatasUtil;
import br.com.clinica.util.ExpressionRUtil;
import br.com.clinica.util.GerarRelatorioPDFUtil;
import br.com.clinica.util.LogUtil;

public class ControllerClinica extends TimerTask   implements ActionListener,WindowListener {
        
	    private   ISubjectBiometria biometria = new BiometriaCapturaImpressaoModelo();
	    private TelaCadastroFuncionario telaCadastroFuncionario;
	    private TelaCadastroPaciente telaCadastroPaciente;
	    private TelaCadastroPlano telaCadastroPlano;
	    private TelaPrincipal principal;
	    private TelaRelatorio relatorio;
	    private TelaAgendamento telaAgendamento;
	    private TelaPassagens passagens;
		private TelaGerenciarFuncionarios telaGerenciarFuncionarios;
		private TelaGerenciarPacientes telaGerenciarPacientes;
	    private Thread biometriaPersonaThread;
		private TelaDeRelatorios deRelatorios;
	    
	    public ControllerClinica(){
	    	
	    	try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
	    	biometriaPersonaThread = new Thread(biometria);
	    	principal = new TelaPrincipal(biometria);
	    	biometria.addObserver(principal);
	    	biometriaPersonaThread.start();
			
	    	ComboboxUtil.iniciar();
	    	
	    	
	    
	    	principal.setVisible(true);
	    	principal.setLocationRelativeTo(null);
	        principal.addWindowListener(this);
			//biometria.addObserver(telaPrincipal);
	        principal.getMntmFuncionario().addActionListener(this);
	        principal.getMntmPlanosDeSade().addActionListener(this);
	        principal.getMntmPacientes().addActionListener(this);
	        principal.getMntmInformaes().addActionListener(this);
	        principal.getMntmEscolhaODia().addActionListener(this);
	        principal.getMntmCadastrarAgendamento().addActionListener(this);
	        principal.getTextFieldAtualizacao().setText(ConversorDatasUtil.formatoDatasHoras( Calendar.getInstance()));
	        principal.getMntmGerenciarFuncionrios().addActionListener(this);
	        principal.getMntmGerenciarPacientes().addActionListener(this);
	        principal.getGetMntmGerenciarMedicos().addActionListener(this);
	        principal.getMntmDefinaOsParmetros().addActionListener(this);
	      
	    }
	
	    public static void main(String[] args) {
	    	
	    	new ControllerClinica();
	    	
	        	
			Timer t2 =new Timer();
			
			MandarEmailNegocio m = new MandarEmailNegocio();
			t2.schedule(m,0, 43200000);
		
			
			
			Timer t3 =new Timer();
			
	        SalvarBaseDeDadosNegocio salvarBaseDeDadosNegocio = new SalvarBaseDeDadosNegocio();
			t3.schedule(salvarBaseDeDadosNegocio,0, 43400000);
			
			
			
			LogUtil.Log(ControllerClinica.class+" Aplicativo foi iniciado ", Level.INFO);
			
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd  = e.getActionCommand();
		   	if(cmd.equals(TelaPrincipal.CMD_TELA_PLANO)){
		   		LogUtil.Log("Controller: clicou em Plano de Saude", Level.INFO);
		   		telaCadastroPlano = new TelaCadastroPlano();
		   		telaCadastroPlano.setVisible(true);
		   		telaCadastroPlano.addWindowListener(this);
		   		telaCadastroPlano.getButtonCadastro().addActionListener(this);
		   		telaCadastroPlano.getButtonRemover().addActionListener(this);
			}else if(cmd.equals(TelaCadastroPlano.CMD_CADASTRAR_PLANO)){
				LogUtil.Log("Controller: Cadastro de Plano", Level.INFO);
				PlanoDeSaude planoDeSaude = new PlanoDeSaude();
				if(telaCadastroPlano.getTextFieldNomePlano().getText().indexOf("\\")!= -1 || telaCadastroPlano.getTextFieldNomePlano().getText().equals("")){
				  JOptionPane.showMessageDialog(null," Caracteres inválido!!"+telaCadastroPlano.getTextFieldNomePlano().getText());
				  telaCadastroPlano.getTextFieldNomePlano().setText("");
				}else if(telaCadastroPlano.getTextFieldNomePlano().getText().equals("")){
					JOptionPane.showMessageDialog(null,"É Necassário um nome!");
				}else{
				
							
						   if(!telaCadastroPlano.getDefaultTableModel().ehNovo(planoDeSaude)){
							   planoDeSaude.setTipo(telaCadastroPlano.getTextFieldNomePlano().getText());  
							   PlanoDAO dao = new PlanoDAO();
							   dao.cadastrar(planoDeSaude);
						       telaCadastroPlano.getDefaultTableModel().addPlanoDeSaude(planoDeSaude);
						     
						   }
				}						
			}else if(cmd.equals(TelaCadastroPlano.CMD_REMOVER_PLANO)){
				LogUtil.Log("Controller: clicou Remover Plano", Level.INFO);
				int row = telaCadastroPlano.getTablePlano().getSelectedRow();	
			    PlanoDeSaude p = telaCadastroPlano.getDefaultTableModel().getPlano(row);
			    PlanoDAO dao = new PlanoDAO();
			    boolean r = dao.remover(p);
			    if(r){
			      telaCadastroPlano.getDefaultTableModel().remove(row);
			    }
		   }else if(cmd.equals(TelaPrincipal.CMD_TELA_CADASTRO_FUNC)){
				 LogUtil.Log("Controller: clicou  Cadastro  Funcionário", Level.INFO);
				 telaCadastroFuncionario = new TelaCadastroFuncionario(biometria);
				 telaCadastroFuncionario.setVisible(true);
				 telaCadastroFuncionario.addWindowListener(this);
				 biometria.stop();
				 biometria.setVerificarPause(false);
				 biometria.cadastrarImpressaoDigital();
				 biometria.addObserver(telaCadastroFuncionario);
		   }else if(cmd.equals(TelaPrincipal.CMD_TELA_CADASTRO_MEDICOS)){
				 LogUtil.Log("Controller: clicou  Cadastro  Médicos", Level.INFO);
				 TelaCadastroMedico telaCadastroMedico = new TelaCadastroMedico();
				 System.out.println("xxxxxxxxxxxxxxxx"); 
			
		   
		    }else if(cmd.equals(TelaPrincipal.CMD_TELA_CADASTRO_PAC)){
				LogUtil.Log("Controller: clicou  Cadastro  Pacientes", Level.INFO); 
				telaCadastroPaciente = new TelaCadastroPaciente(biometria);
				telaCadastroPaciente.setVisible(true);
				telaCadastroPaciente.addWindowListener(this);
				biometria.setVerificarPause(false);
				biometria.stop();
				biometria.cadastrarImpressaoDigital();
			    biometria.addObserver(telaCadastroPaciente);
			}else if(cmd.equals(TelaPrincipal.CMD_TELA_ESCOLHA_DIA)){
				LogUtil.Log("Controller: Escolha o dia", Level.INFO);
				relatorio = new TelaRelatorio();
			    relatorio.setVisible(true);
				relatorio.getButtonMostrar().addActionListener(this);
		   }else if(e.getActionCommand().equals(TelaRelatorio.CMD_MOSTRAR)){
			     LogUtil.Log("Controller: clicou  Mostrar o dia", Level.INFO);
         	     int selRow = relatorio.getTabela().getSelectedRow();
	             if(selRow != -1) {
	           	    if(!relatorio.getTabelaPassagemDoDia().isEmpty()){
	           	      PassagemDoDia passagemdoDia = relatorio.getTabelaPassagemDoDia().getDia(selRow);
	           	      relatorio.dispose();
	           	      passagens = new TelaPassagens();
	           	      passagens.setPassagemDoDia(passagemdoDia);
	           	      passagens.inicializaAsTabelas();
	           	      passagens.getButtonGerarRelatorio().addActionListener(this);
	           	      passagens.getBtnGerarRelatrioPacientes().addActionListener(this);
	           	      
	           	     }
	             }
         }else if(e.getActionCommand().equals(TelaPassagens.CMD_MOSTRAR_RELATORIOS_FUNCIONARIO)){
        	 LogUtil.Log("Controller: clicou  Gerar Relatorio Func", Level.INFO);
             GerarRelatorioPDFUtil.gerarPDFFuncionarios(passagens.getListaPassagens(),true);
        	 
         }else if(e.getActionCommand().equals(TelaPassagens.CMD_MOSTRAR_RELATORIOS_PACIENTE)){
        	 LogUtil.Log("Controller: clicou  Gerar Relatorio Pacientes", Level.INFO);
        	 GerarRelatorioPDFUtil.gerarPDFPacientes(passagens.getListaPassagens(),true);
        	
        	 
         }else if(e.getActionCommand().equals(TelaPrincipal.CMD_TELA_DEFINIR_PARAMETROS_RELATORIOS)){
        	
        	 deRelatorios =	new TelaDeRelatorios();
        	 deRelatorios.getBtnButtonRelaFunc().addActionListener(this);
        	 deRelatorios.getBtnButtonRelatorioPacientes().addActionListener(this);
        	 deRelatorios.setVisible(true);
        
			 
         
         }else if(e.getActionCommand().equals(TelaDeRelatorios.CMD_MOSTRAR_RELATORIO_FUNC)){
        	 LogUtil.Log("Controller:  clicou  Gerar Relatorio Funcionários pela Tela Definir Parâmetros", Level.INFO);
        	        try { 
        	 
        	            String nomePaciente  = deRelatorios.getComboBoxFuncionarios().getSelectedItem().toString();
			        	 List<Funcionario> lista = ComboboxUtil.listaFuncionarios;
						 Funcionario funcionario = null;
								for (Funcionario f : lista) {
									if(f.getNomeCompleto().equals(nomePaciente)){
										funcionario = f;
										break;
									}
								  }
					
		
						Date data = ConversorDatasUtil.formatoDatas(deRelatorios.getComboBoxDiasPac().getSelectedItem().toString() );
					    PassagemDoDiaDAO dao = new PassagemDoDiaDAO();
					    Calendar c = Calendar.getInstance();
					    c.setTime(data);
				        PassagemDoDia passagemDoDia = dao.retornaPassagemDoDia(c);
					    GerarRelatorioJarsper.imprimir(funcionario, passagemDoDia, true);					        
					    			  
					} catch (Exception e1) {
						e1.printStackTrace();
						LogUtil.Log("Controller: Problemas no momento de imprimir o relatorio no: "+e1.getMessage(), Level.SEVERE);
						JOptionPane.showMessageDialog(null, "A pasta no Diretório C:\\CLINICA e o seu counteúdo parece que foi removida! "+e1.getMessage());
					}
		}else if(e.getActionCommand().equals(TelaDeRelatorios.CMD_MOSTRAR_RELATORIO_PAC)){
        	    LogUtil.Log("Controller: clicou  Gerar Relatorio Pacientes pela Tela Definir Parâmetros ", Level.INFO);
        	       try { 
        	        String nomePaciente  = deRelatorios.getComboBoxPacientes().getSelectedItem().toString();
		        	    List<Paciente> lista = ComboboxUtil.listaPacientes;
						Paciente paciente = null;
						for (Paciente p : lista) {
							if(p.getNomeCompleto().equals(nomePaciente)){
								paciente = p;
								break;
							}
						}
				
					Date data = ConversorDatasUtil.formatoDatas(deRelatorios.getComboBoxDiasPac().getSelectedItem().toString() );
				    PassagemDoDiaDAO dao = new PassagemDoDiaDAO();
				    Calendar c = Calendar.getInstance();
				    c.setTime(data);
				    PassagemDoDia passagemDoDia = dao.retornaPassagemDoDia(c);
				    GerarRelatorioJarsper.imprimir(paciente, passagemDoDia, false);	
				    				    			  
				} catch (Exception e1) {
					LogUtil.Log("Controller: Problemas no momento de imprimir o relatorio no: "+e1.getMessage(), Level.SEVERE);
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "A pasta no Diretório C:\\CLINICA e o seu counteúdo parece que foi removida! "+e1.getMessage());
				}	
        	     	 
         }else if(e.getActionCommand().equals(TelaPrincipal.CMD_TELA_AGENDAMENTO)){
        	 LogUtil.Log("Controller: clicou  Cadastro  Agendamento", Level.INFO);
        	 telaAgendamento = new TelaAgendamento();
        	 telaAgendamento.getBtnSalvar().addActionListener(this);
        	 telaAgendamento.getBtnRemover().addActionListener(this);
        	 telaAgendamento.setVisible(true);
         }else if(e.getActionCommand().equals(TelaAgendamento.CMD_SALVAR_AGENDA)){
        	 LogUtil.Log("Controller: clicou  Agendar", Level.INFO);
        		String nomePaciente = telaAgendamento.getComboBoxPaciente().getSelectedItem().toString();
				List<Paciente> lista = ComboboxUtil.listaPacientes;
				Paciente plano=null;
				for (Paciente p : lista) {
					if(p.getNomeCompleto().equals(nomePaciente)){
						plano=p;
						break;
					}
				}
				/////////////////////////////
				String nomePlano2 = telaAgendamento.getComboBoxConvenio().getSelectedItem().toString();
				List<PlanoDeSaude> lista2 = ComboboxUtil.listaPlanoDeSaude;
				PlanoDeSaude plano2=null;
				for (PlanoDeSaude planoDeSaude : lista2) {
					if(planoDeSaude.getTipo().equals(nomePlano2)){
						plano2=planoDeSaude;
						break;
					}
				}
        	 
        	 Agendamento agendamento = new Agendamento();
        	 agendamento.setPaciente(plano);
        	 agendamento.setPlano(plano2);
        	 agendamento.setNomeMedico(telaAgendamento.getTextFieldMedico().getText());
        	 agendamento.setTemGuia(telaAgendamento.getChckbxTemGuia().isSelected());
        	 
        	 GregorianCalendar c= new GregorianCalendar();
        	 try {
				c.setTime( ConversorDatasUtil.formatoDatas( telaAgendamento.getTextFieldData().getText()));
			} catch (ParseException e1) {
				LogUtil.Log(ControllerClinica.class.getName()+"  "+e1.getMessage(),Level.SEVERE);
				e1.printStackTrace();
			}
        	 agendamento.setAgendamento( c);
        	 agendamento.setHora( telaAgendamento.getTextFieldHora().getText() );
        	 String valor = telaAgendamento.getTextFieldValor().getText();
        	 
        	 if(ExpressionRUtil.verificarNumero(valor)){
        	    agendamento.setValor(Double.parseDouble(valor));
        	    telaAgendamento.getTabelaModelo().addAgendamento(agendamento);
        	    AgendamentoDAO agendamentoDAO = new AgendamentoDAO();  
       	    	agendamentoDAO.cadastrar(agendamento);
        	 
        	 }else{
        		 JOptionPane.showMessageDialog(null, "Digitou letras ao invez de números!");
        		 telaAgendamento.getTextFieldValor().setText("");
        		 LogUtil.Log("Controller na opção em Cadastrar Agendamento: Vc digitou letras ao invez de números!", Level.WARNING);
        		 
        	 }
        	 
         }else if(cmd.equals(TelaAgendamento.CMD_REMOVER_AGENDA)   ){
        	 LogUtil.Log("Controller: clicou  Remover agenda", Level.INFO);
        	 int selRow = telaAgendamento.getTableAgendamento().getSelectedRow();
             if(selRow != -1) {
           	    if(!telaAgendamento.getTabelaModelo().isEmpty()){
           	      Agendamento agendamento = telaAgendamento.getTabelaModelo().getAgendamento(selRow);
           	      telaAgendamento.getTabelaModelo().removeAgendamento(selRow);
           	      try {
           	    	AgendamentoDAO agendamentoDAO = new AgendamentoDAO();  
           	    	agendamentoDAO.remover(agendamento);
				  } catch (Exception e1) {
					  LogUtil.Log(ControllerClinica.class.getName()+"  "+e1.getMessage(),Level.SEVERE);
					  e1.printStackTrace();
				  }
 
           	    }
           	  }         
             
         }else if(cmd.equals(TelaPrincipal.CMD_TELA_GERENCIAR_FUN)){
        	 LogUtil.Log("Controller: Gerenciar Func", Level.INFO);
        	 telaGerenciarFuncionarios=	new TelaGerenciarFuncionarios();
        	 telaGerenciarFuncionarios.getBtnRemover().addActionListener(this);
        	 telaGerenciarFuncionarios.getButtonAlterar().addActionListener(this);
        	
        	 telaGerenciarFuncionarios.setVisible(true);
        	
         }else if(cmd.equals(telaGerenciarFuncionarios.CMD_REMOVER_FUNCIONARIO)){ 
        	 LogUtil.Log("Controller: clicou Remover Func", Level.INFO);
        	 if(! telaGerenciarFuncionarios.getModeloTabelaFuncionarios().isEmpty()){
        		   int selRow = telaGerenciarFuncionarios.getTableFuncionarios().getSelectedRow();
						             if(selRow != -1) {
						           	        Funcionario funcionario  = telaGerenciarFuncionarios.getModeloTabelaFuncionarios().getFuncionario(selRow);
						           	        FuncionarioDAO dao = new FuncionarioDAO();
						           	        boolean b = dao.remover(funcionario);
						           	          if(b){
						           	            telaGerenciarFuncionarios.getModeloTabelaFuncionarios().removeFuncionario(selRow);
						           	         }
						           	       }
				   }
        	        	 
         }else if(cmd.equals(TelaGerenciarFuncionarios.CMD_ALTERAR_FUNCIONARIO)){ 	 
        	 LogUtil.Log("Controller: clicou em Alterar", Level.INFO);
        	 if( !telaGerenciarFuncionarios.getModeloTabelaFuncionarios().isEmpty()){	
                  try{
        		   telaGerenciarFuncionarios.getModeloTabelaFuncionarios().setValueTable(telaGerenciarFuncionarios.getTextFieldNome().getText(),telaGerenciarFuncionarios.getIndex(), 0);
                   telaGerenciarFuncionarios.getModeloTabelaFuncionarios().setValueTable(telaGerenciarFuncionarios.getTextFieldCpf().getText(), telaGerenciarFuncionarios.getIndex(), 1);
                   telaGerenciarFuncionarios.getModeloTabelaFuncionarios().setValueTable(telaGerenciarFuncionarios.getComboBoxFuncao().getSelectedItem(),telaGerenciarFuncionarios.getIndex(), 2);
                   telaGerenciarFuncionarios.getModeloTabelaFuncionarios().setValueTable(telaGerenciarFuncionarios.getTextFieldEmail().getText(), telaGerenciarFuncionarios.getIndex(), 3);
                   telaGerenciarFuncionarios.getModeloTabelaFuncionarios().setValueTable(telaGerenciarFuncionarios.getTextFieldFone().getText(),telaGerenciarFuncionarios.getIndex(), 4);
                   telaGerenciarFuncionarios.getModeloTabelaFuncionarios().setValueTable(telaGerenciarFuncionarios.getPathImagem(), telaGerenciarFuncionarios.getIndex(), 5);
                   FuncionarioDAO dao = new FuncionarioDAO();
                   dao.atualizar( telaGerenciarFuncionarios.getModeloTabelaFuncionarios().getFuncionario());
                   JOptionPane.showMessageDialog(null, "Funcionário alterado com sucesso");
                  
                  }catch(Exception erro){
                	  erro.printStackTrace();
                	  JOptionPane.showMessageDialog(null, erro.getMessage());
                	  
                  }
                  
        	 }else{
        		 JOptionPane.showMessageDialog(null, "Cadastre um Funcionário!");
        	 }
         }else if(cmd.equals(TelaGerenciarPacientes.CMD_ALTERAR_PACIENTES)){ 	 
              
             
        	 if( !telaGerenciarPacientes.getModeloTabelaPacientes().isEmpty()){
        	 
        		     if(!telaGerenciarPacientes.getTextFieldNome().getText().equals("")){
        		    	 try{
	                         telaGerenciarPacientes.getModeloTabelaPacientes().setValueTable(telaGerenciarPacientes.getTextFieldNome().getText(),telaGerenciarPacientes.getIndex(), 0);
			                 telaGerenciarPacientes.getModeloTabelaPacientes().setValueTable(telaGerenciarPacientes.getTextFieldCpf().getText(), telaGerenciarPacientes.getIndex(), 1);
			                 telaGerenciarPacientes.getModeloTabelaPacientes().setValueTable(telaGerenciarPacientes.getComboBoxPlanoDeSaude().getSelectedItem(),telaGerenciarPacientes.getIndex(), 2);
			                 telaGerenciarPacientes.getModeloTabelaPacientes().setValueTable(telaGerenciarPacientes.getTextFieldEmail().getText(), telaGerenciarPacientes.getIndex(), 3);
			                 telaGerenciarPacientes.getModeloTabelaPacientes().setValueTable(telaGerenciarPacientes.getPathImagem(),telaGerenciarPacientes.getIndex(), 4);
			                 PacienteDAO dao =new PacienteDAO();
			                 dao.atualizar( telaGerenciarPacientes.getModeloTabelaPacientes().getPaciente());
			                 JOptionPane.showMessageDialog(null, "Paciente alterado com sucesso");
        		    	 }catch(Exception erro){
        		    		 erro.printStackTrace();
                       	     JOptionPane.showMessageDialog(null, erro.getMessage());
        		    	 }
        		     }else{
        		    	 JOptionPane.showMessageDialog(null, "É obrigatório um nome para o Paciente!");
        		     }
        	 }else{
        		         JOptionPane.showMessageDialog(null, "Cadastre um Paciente!");

        	 }
                   
                   
          }else if(cmd.equals(TelaPrincipal.CMD_TELA_GERENCIAR_PAC)){
         	 telaGerenciarPacientes  =	new TelaGerenciarPacientes();
        	 telaGerenciarPacientes.getBtnRemover().addActionListener(this);
        	 telaGerenciarPacientes.getButtonAlterar().addActionListener(this);
             telaGerenciarPacientes.setVisible(true);
             
          }else if(cmd.equals(TelaPrincipal.CMD_TELA_INFORMACOES)){   
        	  JOptionPane.showMessageDialog(null,"Desenvolvido por Fabio Eduardo Moreira "+"\n "+"Para maiores informações: fabio.eduardo.moreira@gmail.com \n"+"Telefone: 11 983553643");
          }else if(cmd.equals(TelaGerenciarPacientes.CMD_REMOVER_PACIENTES)){
        	
        	  
        	                   if( !telaGerenciarPacientes.getModeloTabelaPacientes().isEmpty()){
        	  
								         	 
								 			        	 int selRow = 	 telaGerenciarPacientes.getTabelaPacientes().getSelectedRow();
								 			             if(selRow != -1) {
								 			           	    
								 			           	    	Paciente paciente  = telaGerenciarPacientes.getModeloTabelaPacientes().getPaciente(selRow);
								 			            	    PacienteDAO pacienteDAO =new PacienteDAO();
								 			            	    
								 			            	    boolean r=pacienteDAO.remover(paciente);
								 			            	    if(r){
								 			            	     telaGerenciarPacientes.getModeloTabelaPacientes().removePaciente(selRow);
								 			            	    
								 			            	    
								 			            	     String msg  ="Remoção Realizada com sucesso! Para conferir é necessário reinicializar o aplicativo";
								 			            	     JOptionPane.showMessageDialog(null, msg);
								 			            	     LogUtil.Log(ControllerClinica.class+" Remoção Realizada com sucesso! "+paciente.getNomeCompleto()+ msg, Level.INFO); 
								 			            	    }
								 			            	}
								 			              
			         	 
			                     }else{
			                    	 JOptionPane.showMessageDialog(null, "Cadastre um Paciente!");
			                     }
       			
          }
		
		}

	


		@Override
		public void run() {
		   /*
			if( telaPrincipal == null){
				//telaPrincipal = new TelaPrincipal();
				ComboboxUtil.iniciar();
				telaPrincipal.setVisible(true);
				telaPrincipal.setLocationRelativeTo(null);
				telaPrincipal.addWindowFocusListener(this);
				//biometria.addObserver(telaPrincipal);
				telaPrincipal.getMntmFuncionario().addActionListener(this);
				telaPrincipal.getMntmPlanosDeSade().addActionListener(this);
				telaPrincipal.getMntmPacientes().addActionListener(this);
				telaPrincipal.getMntmInformaes().addActionListener(this);
				telaPrincipal.getMntmEscolhaODia().addActionListener(this);
				telaPrincipal.getMntmCadastrarAgendamento().addActionListener(this);
				telaPrincipal.getTextFieldAtualizacao().setText(ConversorDatasUtil.formatoDatasHoras( Calendar.getInstance()));
				telaPrincipal.getMntmGerenciarFuncionrios().addActionListener(this);
				telaPrincipal.getMntmGerenciarPacientes().addActionListener(this);
			}else if(telaPrincipal.isShowing()){
				LogUtil.Log(ControllerClinica.class+" Aplicativo foi reiniciado �s "+Calendar.getInstance().getTime().toString(), Level.INFO);
				telaPrincipal.dispose();
				ComboboxUtil.iniciar();
				
				telaPrincipal = new TelaPrincipal();
				telaPrincipal.setVisible(true);
				telaPrincipal.setLocationRelativeTo(null);
				telaPrincipal.addWindowFocusListener(this);
				
				//biometria.addObserver(telaPrincipal);
				telaPrincipal.getMntmPlanosDeSade().addActionListener(this);
				telaPrincipal.getMntmFuncionario().addActionListener(this);
				telaPrincipal.getMntmPacientes().addActionListener(this);
				telaPrincipal.getMnRelatrio().addActionListener(this);    
				telaPrincipal.getMntmCadastrarAgendamento().addActionListener(this);
				telaPrincipal.getTextFieldAtualizacao().setText(ConversorDatasUtil.formatoDatasHoras( Calendar.getInstance()));
				telaPrincipal.getMntmGerenciarFuncionrios().addActionListener(this);
				telaPrincipal.getMntmGerenciarPacientes().addActionListener(this);
				telaPrincipal.getMntmInformaes().addActionListener(this);
			}
			*/
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
	

			
			
			
			
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			if(e.getComponent() instanceof TelaCadastroPaciente){
				ComboboxUtil.recuperarImpressao();
				biometria.removeObserver(telaCadastroPaciente);
				biometria.setVerificarPause(true);
				biometria.addObserver(principal);
				
			}else if(e.getComponent() instanceof TelaCadastroFuncionario){
				 ComboboxUtil.recuperarImpressao();
				 biometria.setVerificarPause(true);
			     biometria.removeObserver(telaCadastroFuncionario);
				 biometria.setVerificarPause(true);
				 biometria.addObserver(principal);
				
			}else if(e.getComponent() instanceof TelaPrincipal){
				
							
			
			}	
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
   
    	            
			
			
			
 

			
		


}
