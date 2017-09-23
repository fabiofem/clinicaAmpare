package br.com.clinica.negocio;

import java.util.List;
import java.util.TimerTask;

import br.com.clinica.tabelas.Passagem;
import br.com.clinica.tabelas.PassagemDoDia;
import br.com.clinica.util.ComboboxUtil;
import br.com.clinica.util.GerarRelatorioPDFUtil;

public class MandarEmailNegocio extends TimerTask{

	@Override
	public void run() {
	     
		  List<PassagemDoDia> listaPassagemDodia= ComboboxUtil.listaDias;		 
		 if(listaPassagemDodia != null){
			 for(PassagemDoDia passagem : listaPassagemDodia ){
			  if(passagem.getListaPassagens() != null){
		        List<Passagem> lista = passagem.getListaPassagens();
		        GerarRelatorioPDFUtil.gerarPDFFuncionarios(lista,false);
		        GerarRelatorioPDFUtil.gerarPDFPacientes(lista,false);
			  }
			 }
		 //System.out.println(""+ConversorDatasUtil.formatoDatasHoras( Calendar.getInstance()));
		 }
		 
		 
	}

}
