package br.com.clinica.negocio;

import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.capture.event.DPFPErrorListener;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityListener;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusListener;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;

public interface IObseverBiometria {
	
	public void process(DPFPSample sample);
	

	public void addErrorListener(DPFPErrorListener arg0);
		//tela.getTextAreaLog().append("Ocorreu um erro!!"+"\n");	
	

	
	public void addImageQualityListener(DPFPImageQualityListener arg0) ;
		//tela.getTextAreaLog().append("A qualidade da imagem não está boa!"+"\n");
		
    
	

	
	public void addReaderStatusListener(DPFPReaderStatusListener arg0);
	//	tela.getTextAreaLog().append("O leitor de digital está contectado."+"\n");
		
	public void readerConnected(DPFPReaderStatusEvent arg0) ;
	
	public void readerDisconnected(DPFPReaderStatusEvent arg0);


	public void fingerGone(DPFPSensorEvent arg0);

	
	public void fingerTouched(DPFPSensorEvent arg0);

	
	public void imageAcquired(DPFPSensorEvent arg0);
		
	


}
