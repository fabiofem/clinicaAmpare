package br.com.clinica.negocio;

import java.awt.Image;
import java.util.List;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;

import br.com.clinica.tabelas.Impressao;

public interface ISubjectBiometria extends Runnable{
	 public void addObserver(IObseverBiometria observer);
	 public void removeObserver(IObseverBiometria observer);
	 public Impressao verificacao(List<Impressao> lista,DPFPSample sample);
	 public void stop();
	 public void start();
	 public DPFPEnrollment getEnrollment();
	 public Image convertSampleToBitmap(DPFPSample sample);
	 public DPFPFeatureSet extractFeatures(DPFPSample sample, DPFPDataPurpose purpose);
	 public void setVerificarPause(boolean b);
     public boolean isVerificarPause();
     public void cadastrarImpressaoDigital();
	 

}
