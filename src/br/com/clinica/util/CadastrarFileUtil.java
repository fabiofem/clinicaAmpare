package br.com.clinica.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import br.com.clinica.tabelas.Funcionario;
import br.com.clinica.tabelas.Paciente;

public class CadastrarFileUtil {
	
	
	public static void cadastrarImpressaoEmArquivo(Paciente p){
		String path="./File/Pacientes";
		byte[] b=p.getImpressao().serialize();
		File f = new File(path);
        if(!f.exists()){
			f = new File(path);
			f.mkdirs();
	
		}
        if(p.getCpf() == null ){
        	p.setCpf("SemCPF");
        }else if(p.getCpf().equals("")){
        	p.setCpf("SemCPF");	
        }
        if(p.getEmail() == null){
        	p.setEmail("SemEmail");
        }else if(p.getEmail().equals("")){
        	p.setEmail("SemEmail");
        }
        if(p.getFone()== null){
        	p.setFone("SemFone");
        }else if(p.getFone().equals("")){
        	p.setFone("SemFone");
        	
        }
        
        
		f = new File(path+"//_"+p.getNomeCompleto()+"_" +p.getCpf()+ "_"   +p.getEmail()+"_"+p.getPlano().getTipo()+"_"+p.getFone()+"_.txt");
	    try {
			 FileOutputStream bufferedOutputStream = new FileOutputStream(f);
			 bufferedOutputStream.write(b);
			 bufferedOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			 LogUtil.Log(CadastrarFileUtil.class+e.getMessage(), Level.SEVERE);
			JOptionPane.showMessageDialog(null, "Ocorreu um problema ao criar o file: "+e.getMessage());
		}
			
	}
	public static void cadastrarImpressaoEmArquivo(Funcionario p){
		String path= "./File/Funcionarios";
		byte[] b=p.getImpressao().serialize();
		File f = new File(path);
        if(!f.exists()){
			f = new File(path);
			f.mkdirs();
	
		}
        if(p.getEmail() == null){
        	p.setEmail("SemEmail");
        }else if(p.getEmail().equals("")){
        	p.setEmail("SemEmail");
        }
        if(p.getFone()== null){
        	p.setFone("SemFone");
        }else if(p.getFone().equals("")){
        	p.setFone("SemFone");
        	
        }
        
        
		f = new File(path+"//_"+p.getNomeCompleto()+"_" +p.getCpf()+ "_"   +p.getEmail()+"_"+p.getFone()+"_"+p.getFuncao()+"_.txt");
	    try {
			 FileOutputStream bufferedOutputStream = new FileOutputStream(f);
			 bufferedOutputStream.write(b);
			 bufferedOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			 LogUtil.Log(CadastrarFileUtil.class+e.getMessage(), Level.SEVERE);
			JOptionPane.showMessageDialog(null, "Ocorreu um problema ao criar o file: "+e.getMessage());
		}
	
	}

	

}
