package br.com.clinica.relatorio.jasper;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

import br.com.clinica.tabelas.PassagemDoDia;
import br.com.clinica.tabelas.Pessoa;
import br.com.clinica.util.JPAUtil;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class GerarRelatorioJarsper {
	
	@SuppressWarnings("deprecation")
	public static void imprimir(Pessoa pessoa, PassagemDoDia passagemDoDia,boolean tipo) throws Exception{

		HashMap<String , Object> paramentros =new HashMap<>();
		paramentros.put("PESSOA_ID", pessoa.getId());
		paramentros.put("PASSAGEM_DO_DIA_ID", passagemDoDia.getId());
	    FileInputStream inputStream = null;
			  String path = "";
			 
			 if(tipo){
				   path += "C:/CLINICA/File/Relatorios/Jasper/RelatoriosFuncionarios";
				
				  inputStream = new FileInputStream(new File(path+".jasper"));
			 }else{
				  path += "C:/CLINICA/File/Relatorios/Jasper/RelatoriosPacientes";
				  
				  inputStream = new FileInputStream(new File(path+".jasper"));
			 }
			  JasperPrint	impressao = JasperFillManager.fillReport(inputStream, paramentros,JPAUtil.getConnection());
			 
			  
			  
			  JasperViewer viewer = new JasperViewer(impressao, false);
			  viewer.setVisible(true);
			  viewer.show();

	}

}
