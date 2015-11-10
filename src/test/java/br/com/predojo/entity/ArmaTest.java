package br.com.predojo.to;

import org.junit.Assert;
import org.junit.Test;

import br.com.predojo.to.Arma;

/**
 * Junits para os métodos presentes na classe de {@link Arma}
 * 
 * @author Vinicius A Gai
 *
 */
public class ArmaTest {

	private static final String NOM_ARMA = "AK-47";
	private static final Integer QTD_MORTES_TEST = 1;
	
	@Test
	public void testIncremetaMorte() {
		
		// Monta o objeto de arma
		final Arma armaTO = new Arma(NOM_ARMA);
		
		// Incrementa a morte
		armaTO.incremetaKill();
		
		// Testa se incrementou.
		Assert.assertEquals(QTD_MORTES_TEST, armaTO.getQtdKills());
		
	}
	
}
