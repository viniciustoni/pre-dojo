package br.com.predojo.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Classe basica de teste para ser utilizada nos Junits.
 * 
 * @author Vinicius A Gai
 *
 */
public abstract class BaseTest {

	/**
	 * Cria uma data com os dados informados.
	 * 
	 * @param hourOfDay hora a ser criada, no formato 24h
	 * @param minuto Minuto a ser criado
	 * @param segundo Segundo a ser criado
	 * @return Data criada
	 */
	public static Date createDate(final Integer hourOfDay, final Integer minuto, final Integer segundo) {
		
		final Calendar calendar = GregorianCalendar.getInstance();
		
		// seta os dados
		calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		calendar.set(Calendar.MINUTE, minuto);
		calendar.set(Calendar.SECOND, segundo);
		
		// retorna a data
		return calendar.getTime();
		
	}
	
}
