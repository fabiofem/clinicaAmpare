package br.com.clinica.negocio;

import java.io.File;
import java.util.TimerTask;

import br.com.clinica.util.SendEmailUtil;

public class SalvarBaseDeDadosNegocio extends TimerTask{

	@Override
	public void run() {
		String path= "C:/CLINICA/File/Funcionarios";
		File file = new File(path);
		 File[] nomes = file.listFiles();
		   for (int i = 0; i < nomes.length; i++) {
			    SendEmailUtil.sendEmailArquivo(nomes[i], "clinicampare@gmail.com");
		   }
	       //////////////////////////////////////////////////////////
		   String path2= "C:/CLINICA/File/Pacientes";
			File file2 = new File(path2);
			 File[] nomes2 = file2.listFiles();
			   for (int i = 0; i < nomes2.length; i++) {
				    SendEmailUtil.sendEmailArquivo(nomes2[i], "clinicampare@gmail.com");
			   }   
		   
		   
		
		
	}

}
