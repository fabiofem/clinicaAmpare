package br.com.clinica.negocio;

import java.awt.Image;
import java.util.List;
import java.util.logging.Level;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.DPFPCapturePriority;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPDataListener;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusListener;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorListener;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;

import br.com.clinica.tabelas.Impressao;
import br.com.clinica.util.LogUtil;

public final class BiometriaCapturaImpressaoModelo implements Runnable, DPFPDataListener,DPFPSensorListener,DPFPReaderStatusListener,ISubjectBiometria{

    private DPFPCapture capturer;
	private DPFPFeatureExtraction extractor;
	private DPFPEnrollment enrollment;
	private IObseverBiometria observer;
	private boolean verificarPause = true;	

    
	public void cadastrarImpressaoDigital(){
	    extractor =DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
	    enrollment = DPFPGlobal.getEnrollmentFactory().createEnrollment();
	    LogUtil.Log(BiometriaCapturaImpressaoModelo.class.getName()+"CRIANDO A FABRICA DE LEITORAS  PARA FAZER O CADASTRO",Level.INFO);
        verificarImpressaoDigital();	
	}

	

	private void verificarImpressaoDigital(){
		if(capturer == null || !capturer.isStarted()){
			LogUtil.Log(BiometriaCapturaImpressaoModelo.class.getName()+"CRIOU A FABRICA DE LEITORAS  ",Level.INFO);
			capturer = DPFPGlobal.getCaptureFactory().createCapture();
		 //capture.setReaderSerialNumber(activeReader);
		 capturer.setPriority(DPFPCapturePriority.CAPTURE_PRIORITY_LOW);
		 capturer.addDataListener(this);
		 capturer.addReaderStatusListener(this);
		 capturer.addSensorListener(this);
		 capturer.addReaderStatusListener((DPFPReaderStatusListener) this);
		 capturer.startCapture();
		}
	}


	@Override
	public void readerConnected(DPFPReaderStatusEvent arg0) {
		  observer.readerConnected(arg0);
    }
	public void stop(){
		capturer.stopCapture();
	}
	public void start(){
		if(capturer!=null && !capturer.isStarted()){
			LogUtil.Log(BiometriaCapturaImpressaoModelo.class.getName()+" STARTOU A LEITORA!",Level.INFO);
		   capturer.startCapture();
		}
	}

	@Override
	public void readerDisconnected(DPFPReaderStatusEvent arg0) {
		   observer.readerDisconnected(arg0);
	}

	@Override
	public void fingerGone(DPFPSensorEvent arg0) {
		   observer.fingerGone(arg0);
		
	}

	@Override
	public void fingerTouched(DPFPSensorEvent arg0) {
		   observer.fingerTouched(arg0);
		
		
	}

	@Override
	public void imageAcquired(DPFPSensorEvent arg0) {
	     observer.imageAcquired(arg0);
	}

	@Override
	public void dataAcquired(DPFPDataEvent e) {
		   observer.process(e.getSample());
	}

	@Override
	public void addObserver(IObseverBiometria observer) {
		 this.observer = observer;
	    
	}

	@Override
	public void removeObserver(IObseverBiometria observer) {
		this.observer=null;
	
		
	}
	public Image convertSampleToBitmap(DPFPSample sample) {
		return DPFPGlobal.getSampleConversionFactory().createImage(sample);
	}
	
	public DPFPFeatureSet extractFeatures(DPFPSample sample, DPFPDataPurpose purpose)
	{
		
		try {
			return extractor.createFeatureSet(sample, purpose);
		} catch (DPFPImageQualityException e) {
			LogUtil.Log("PROBLEMAS NA QUALIDADE DA IMPRESSAO::"+BiometriaCapturaImpressaoModelo.class.getName()+"  "+e.getMessage(),Level.SEVERE);
			return null;
		}
	}


	public DPFPEnrollment getEnrollment() {

		return enrollment;
	}

	public void setEnrollment(DPFPEnrollment enrollment) {
		this.enrollment = enrollment;
	}


	@Override
	public Impressao verificacao(List<Impressao> lista, DPFPSample sample) {
		LogUtil.Log(BiometriaCapturaImpressaoModelo.class.getName()+" Fazendo verifica��o dessa impress�o: "+sample.toString(),Level.INFO);
		try{
			//extractor
		DPFPFeatureExtraction featureExtractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
		DPFPFeatureSet featureSet =  featureExtractor.createFeatureSet(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
        DPFPVerification matcher = DPFPGlobal.getVerificationFactory().createVerification();
		matcher.setFARRequested(DPFPVerification.MEDIUM_SECURITY_FAR);
       
		for (Impressao p: lista) {
			
             DPFPVerificationResult result = matcher.verify(featureSet, p);
				if (result.isVerified()) {		
				  java.awt.Toolkit.getDefaultToolkit().beep();
                  return p;  
                 
				} 
		
			
		}
		
		}catch(Exception e){
		  e.printStackTrace();
		  LogUtil.Log("Problemas na busca pela impressão: "+BiometriaCapturaImpressaoModelo.class.getName()+"  "+e.getMessage(),Level.SEVERE); 
		}

		return null;
	}

	@Override
	public void run() {
		 while(true){
			 
			 if(verificarPause){
	            verificarImpressaoDigital();
			 }
       }
		
	}

	public boolean isVerificarPause() {
		return verificarPause;
	}


	public void setVerificarPause(boolean verificarPause) {
		this.verificarPause = verificarPause;
	}
	
	
}
