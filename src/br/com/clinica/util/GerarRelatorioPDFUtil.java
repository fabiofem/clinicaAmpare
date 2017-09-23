package br.com.clinica.util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.clinica.tabelas.Funcionario;
import br.com.clinica.tabelas.Paciente;
import br.com.clinica.tabelas.Passagem;

public class GerarRelatorioPDFUtil {

	



public static void gerarPDFFuncionarios(List<Passagem> lista,boolean mostrar){
	String path = ConversorDatasUtil.formatoDatasHoras(Calendar.getInstance()).toString().replaceAll("/","_").replaceAll(":", "_");
	String nome="C:/CLINICA/File/Relatorios/Relatorio" + path+  ".pdf";
	Document doc = new Document();
	
	try{
		
		File f= new File(nome);
		FileOutputStream file = new FileOutputStream(f);
	    PdfWriter.getInstance(doc, file);
		Paragraph paragraph = new Paragraph(" Relatório de passagens de Entradas");
		Paragraph paragraph2 = new Paragraph(" ");
		paragraph.setAlignment(1);
		doc.open();
		
		
		
		doc.add(paragraph);
		doc.add(paragraph2);
		
		
		PdfPTable pdfPTable = new PdfPTable(2);
	
		PdfPCell cel3 = new  PdfPCell(new Paragraph("Nome Completo"));
		PdfPCell cel4 = new  PdfPCell(new Paragraph("Hora de Entrada"));
		pdfPTable.addCell(cel3);pdfPTable.addCell(cel4);
		
		
		for(Passagem p: lista){
			if(p.getPessoa() instanceof Funcionario){
					if(p.isEntrada()){
					 cel3 = new  PdfPCell(new Paragraph(p.getPessoa().getNomeCompleto()));
					 cel4 = new  PdfPCell(new Paragraph(ConversorDatasUtil.formatoDatasHoras( p.getDataEntrada())));
                     pdfPTable.addCell(cel3);pdfPTable.addCell(cel4);
				    }
			}
		}
		
		doc.add(pdfPTable);
		
		////////////////////////////////////////////////////////////////////////////////////////////
		
		Paragraph paragraph3 = new Paragraph(" Relatório de passagens de Saídas");
		Paragraph paragraph4 = new Paragraph(" ");
		paragraph3.setAlignment(1);
	
		doc.add(paragraph3);
		doc.add(paragraph4);
		
		
		PdfPTable pdfPTable2 = new PdfPTable(2);
		PdfPCell cel32 = new  PdfPCell(new Paragraph("Nome Completo"));
		PdfPCell cel42 = new  PdfPCell(new Paragraph("Hora de Saídas"));
		pdfPTable2.addCell(cel32);pdfPTable2.addCell(cel42);
		
		
		for(Passagem p: lista){
			
			  if ( p.getPessoa() instanceof Funcionario){
			        if(!p.isEntrada()){
		
					cel32 = new  PdfPCell(new Paragraph(p.getPessoa().getNomeCompleto()));
				    cel42 = new  PdfPCell(new Paragraph(ConversorDatasUtil.formatoDatasHoras( p.getDataEntrada())));
					pdfPTable2.addCell(cel32);pdfPTable2.addCell(cel42);
                  	}
			  }
		    
		}
		
		doc.add(pdfPTable2);
		
		
		doc.close();
		File fileEmail =new File(nome);
		
		if(mostrar){
		 Desktop.getDesktop().open(new File(nome));
		 //String cmds[] = new String[] {"cmd", "/c",  nome};  
	       // Runtime.getRuntime().exec(cmds); 	
		
		}
		
     
		 Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					SendEmailUtil.sendEmail(fileEmail,"clinicampare@gmail.com");
					LogUtil.Log(GerarRelatorioPDFUtil.class.getName()+"Relatório encaminhado  clinicampare@gmail.com ",Level.INFO); 
					SendEmailUtil.sendEmail(fileEmail,"fabio.eduardo.moreira@gmail.com");
					LogUtil.Log(GerarRelatorioPDFUtil.class.getName()+"Relatório encaminhado  fabio.eduardo.moreira@gmail.com ",Level.INFO);	
				}
			  });
			   t.start();
		
		
		
		
	}catch(Exception e){
		LogUtil.Log(GerarRelatorioPDFUtil.class.getName()+e.getMessage(),Level.SEVERE);
		e.printStackTrace();
		
	}
 }

public static void gerarPDFPacientes(List<Passagem> lista,boolean mostrar){
	String path = ConversorDatasUtil.formatoDatasHoras(Calendar.getInstance()).replaceAll("/","_").replaceAll(":", "_");
	String nome= "C:/CLINICA/File/Relatorios/Relatorio" + path+  ".pdf";
	Document doc = new Document();
	
	try{
		
		File f= new File(nome);
		FileOutputStream file = new FileOutputStream(f);
		
	    PdfWriter.getInstance(doc, file);
		
		
		
		Paragraph paragraph = new Paragraph(" Relatório de passagens de Entradas dos Pacientes");
		Paragraph paragraph2 = new Paragraph(" ");
		paragraph.setAlignment(1);
		
		
		
		doc.open();
		
		
		
		doc.add(paragraph);
		doc.add(paragraph2);
		
		
		PdfPTable pdfPTable = new PdfPTable(2);
	
		PdfPCell cel3 = new  PdfPCell(new Paragraph("Nome Completo"));
		PdfPCell cel4 = new  PdfPCell(new Paragraph("Hora de Entrada"));
		pdfPTable.addCell(cel3);pdfPTable.addCell(cel4);
		
		
		for(Passagem p: lista){
			if(p.getPessoa() instanceof Paciente){
					if(p.isEntrada()){
					 cel3 = new  PdfPCell(new Paragraph(p.getPessoa().getNomeCompleto()));
					 cel4 = new  PdfPCell(new Paragraph(ConversorDatasUtil.formatoDatasHoras( p.getDataEntrada())));
                     pdfPTable.addCell(cel3);pdfPTable.addCell(cel4);
				    }
			}
		}
		
		doc.add(pdfPTable);
		
		////////////////////////////////////////////////////////////////////////////////////////////
		

		
		
	
		
		File fileEmail =new File(nome);
		doc.close();
	
		
		if(mostrar){
			 Desktop.getDesktop().open(new File(nome));
	   }
	   Thread t = new Thread(new Runnable() {
		
		@Override
		public void run() {
			SendEmailUtil.sendEmail(fileEmail,"clinicampare@gmail.com");
			LogUtil.Log(GerarRelatorioPDFUtil.class.getName()+"Relatório encaminhado  clinicampare@gmail.com ",Level.INFO); 
			SendEmailUtil.sendEmail(fileEmail,"fabio.eduardo.moreira@gmail.com");
			LogUtil.Log(GerarRelatorioPDFUtil.class.getName()+"Relatório encaminhado  fabio.eduardo.moreira@gmail.com ",Level.INFO);	
		}
	  });
	   t.start();
		
		
	}catch(Exception e){
		e.printStackTrace();
		LogUtil.Log(GerarRelatorioPDFUtil.class.getName()+e.getMessage(),Level.SEVERE);
		e.printStackTrace();
	}
 }





}
