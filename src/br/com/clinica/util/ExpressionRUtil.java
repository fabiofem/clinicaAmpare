package br.com.clinica.util;

import java.util.regex.Pattern;

public class ExpressionRUtil {
	
	public static boolean verificar(String texto){
		String m="[^a-z0-9A-Z()\\.]";
		if(!Pattern.compile(m).matcher(texto).find()){
			return true;
		}
		return false;
	}

	public static boolean verificarNumero(String valor) {
		String m="[0-9]";
		if(Pattern.compile(m).matcher(valor).find()){
			return true;
		}
		return false;
	}

}
