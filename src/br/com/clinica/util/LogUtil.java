package br.com.clinica.util;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogUtil {
      
	private static Logger logger = Logger.getLogger(LogUtil.class.getName());
	private static FileHandler fh;
	  public static void Log(String message,Level info){
		  
			      try {
			    	if( fh == null){
			    		String dia= "C:/CLINICA/File/Log/log_do_dia_"+ ConversorDatasUtil.formatoDatasHoras( Calendar.getInstance() ).toString().replaceAll("/", "_").replaceAll(":", "_")+ ".log";  	            
			    		File file  =new File(dia);
			    		if(!file.exists()){
			    	     	fh = new FileHandler(dia);
			    		}
			            
			            
			            logger.addHandler(fh);
				        SimpleFormatter formatter = new SimpleFormatter();  
				        fh.setFormatter(formatter);
			    	}
			          
		              if(Level.WARNING == info){  
			            logger.warning(message); 
		              }else if(Level.SEVERE == info){
		            	  logger.severe(message);
		              }else if(Level.INFO == info){
		            	  logger.info(message);  
		              } 
		
			    } catch (SecurityException e) {  
			        e.printStackTrace();  
			    } catch (IOException e) {  
			        e.printStackTrace();  
			    }
	  }
	

	
	
	
}
