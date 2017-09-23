	package br.com.clinica.negocio;

import java.util.concurrent.LinkedBlockingQueue;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.DPFPCapturePriority;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPDataListener;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;

public final class BiometriaPersonaNegocio implements Runnable{
	 private boolean verificarPause = true;
	 public static void cadastrarDigitais(){
		
		System.out.println("Cadastrando digital...");
		   try {
				
				
			    
			    DPFPFeatureExtraction featureExtractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
	            DPFPEnrollment enrollment = DPFPGlobal.getEnrollmentFactory().createEnrollment();
				
	            while (enrollment.getFeaturesNeeded() > 0) {
					DPFPSample sample = getSample(null,
							String.format("Registre a digital do indicador da m�o direita (Faltam %d leituras)\n",enrollment.getFeaturesNeeded()));
					if (sample == null)
						continue;
					DPFPFeatureSet featureSet;
					try {
						featureSet = featureExtractor.createFeatureSet(sample,
								DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
					} catch (DPFPImageQualityException e) {
						System.out.printf(
								"M� qualidade: \"%s\". Tente novamente. \n", e
										.getCaptureFeedback().toString());
						continue;
					}

					enrollment.addFeatures(featureSet);
				}
	            //listaTemplates.add(enrollment.getTemplate());
	            
	            //String nome = JOptionPane.showInputDialog("Digite o nome:");
	           // DB.inserirUsuario(nome, enrollment.getTemplate().serialize());
	            
	              
				System.out.print("Cadastrado com sucesso!\n");
			} catch (DPFPImageQualityException e) {
				System.out.printf("Falha ao cadastrar digital.\n\n");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} 
		   /*
		   catch (SQLException e) {
			
				System.out.printf("Erro na base de dados.\n\n");
				
				e.printStackTrace();
			}
			*/
		   
			// Fim do cadastro da digital

	}
	
	
	public static void verificarDigitais(){
	
		try{
		
		Boolean encontrou = false;

		while (!encontrou) {

			
				DPFPSample sample = getSample(null,"Registre a digital do indicador da m�o direita para verifica��o.\n");
				if (sample == null)
					throw new Exception();

				//DPFPFeatureExtraction featureExtractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
				//DPFPFeatureSet featureSet = featureExtractor.createFeatureSet(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
                DPFPVerification matcher = DPFPGlobal.getVerificationFactory().createVerification();
				matcher.setFARRequested(DPFPVerification.MEDIUM_SECURITY_FAR);
               /*
				for (UsuarioBean u: lista) {
					
                     DPFPVerificationResult result = matcher.verify(featureSet, u);
						if (result.isVerified()) {
							System.out.print("Ok!!! Digital v�lida! " + u.getNome());							
                            encontrou = true;
                            break;
						} //else {
							//System.out.println("Falha.. Digital n�o registrada, tente novamente.");
						//}
						
					
				}
*/
		

		}

		}catch(Exception e){
			e.printStackTrace();
		}
	

		
	}
	
	public static void main(String[] args) {
		//DB.conexao();
		//cadastrarDigitais();
		verificarDigitais();
		
		
	}

	private static DPFPSample getSample(String activeReader, String prompt)
			throws InterruptedException {
		final LinkedBlockingQueue<DPFPSample> samples = new LinkedBlockingQueue<DPFPSample>();
		DPFPCapture capture = DPFPGlobal.getCaptureFactory().createCapture();
		capture.setReaderSerialNumber(activeReader);
		capture.setPriority(DPFPCapturePriority.CAPTURE_PRIORITY_LOW);
		capture.addDataListener(new DPFPDataListener() {
			public void dataAcquired(DPFPDataEvent e) {
				if (e != null && e.getSample() != null) {
					try {
						samples.put(e.getSample());
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		capture.addReaderStatusListener(new DPFPReaderStatusAdapter() {
			int lastStatus = DPFPReaderStatusEvent.READER_CONNECTED;

			public void readerConnected(DPFPReaderStatusEvent e) {
				if (lastStatus != e.getReaderStatus())
					System.out.println("Leitor conectado!");
				lastStatus = e.getReaderStatus();
			}

			public void readerDisconnected(DPFPReaderStatusEvent e) {
				if (lastStatus != e.getReaderStatus())
					System.out.println("O leitor esta desconectado!");
				lastStatus = e.getReaderStatus();
			}

		});
		try {
			capture.startCapture();
			System.out.print(prompt);
			return samples.take();
		} catch (RuntimeException e) {
			System.out
					.printf("Falha ao iniciar captura. Verifique se o leitor n�o esta sendo usado por outra applica��o.\n");
			throw e;
		} finally {
			capture.stopCapture();
		}
	}

	@Override
	public void run() {
		 while(verificarPause){
			          verificarDigitais();
	     }
   }

}
