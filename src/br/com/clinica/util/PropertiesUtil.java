package br.com.clinica.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

public class PropertiesUtil {
   
	
	private static Properties props = new Properties();
	
	public static void abrir() {
		try {
			FileInputStream file = new FileInputStream("./File/Properties/texto.properties");
		 	props.load(file);
		} catch (FileNotFoundException e) {
			LogUtil.Log(Properties.class.getName() +e.getMessage() ,Level.SEVERE);
			e.printStackTrace();
		} catch (IOException e) {
			LogUtil.Log(Properties.class.getName() +e.getMessage() ,Level.SEVERE);
			e.printStackTrace();
		}

	}
	public static String getTexto(String key){
		return props.getProperty(key);
	}
	public static void main(String[] args) {
		abrir();
		
	}
	
}
