package br.com.clinica.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConversorDatasUtil {
	
	public final static String formatoDatas(Calendar data){
		SimpleDateFormat parse = new SimpleDateFormat("dd/MM/yyyy");
		return parse.format( data.getTime() );
	}
	public final static Date formatoDatas(String data) throws ParseException{
		SimpleDateFormat parse = new SimpleDateFormat("dd/MM/yyyy");
		return parse.parse(data);
	}
	public final static String formatoDatasHoras(Calendar data){
		SimpleDateFormat parse = new SimpleDateFormat("HH:mm:ss");
		return parse.format( data.getTime() );
	}

}
