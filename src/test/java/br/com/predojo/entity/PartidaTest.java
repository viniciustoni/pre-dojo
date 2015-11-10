package br.com.predojo.entity;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Junit para o TO de Partida.
 * 
 * @author Vinicius A Gai
 */
public class PartidaTest {

	private static final String NOM_JOGADOR_1 = "JUnit1";
	private static final String NOM_JOGADOR_2 = "JUnit2";
	private static final String NOM_JOGADOR_3 = "JUnit3";
	private static final String WORLD = "<WORLD>";
	private static final String NOM_ARMA_AK47 = "AK-47";
	private static final String NOM_PARTIDA = "12342";

	@Test
	public void testPartidaListaRanking132WorldMata2() {

		// Cria a partida
		final Partida partida = new Partida(NOM_PARTIDA, new Date());

		// Efetua as mortes
		partida.processaMorte(NOM_JOGADOR_1, NOM_JOGADOR_2, NOM_ARMA_AK47, new Date());
		partida.processaMorte(NOM_JOGADOR_1, NOM_JOGADOR_3, NOM_ARMA_AK47, new Date());
		partida.processaMorte(NOM_JOGADOR_1, NOM_JOGADOR_3, NOM_ARMA_AK47, new Date());
		partida.processaMorte(NOM_JOGADOR_2, NOM_JOGADOR_3, NOM_ARMA_AK47, new Date());
		partida.processaMorte(NOM_JOGADOR_3, NOM_JOGADOR_1, NOM_ARMA_AK47, new Date());
		partida.processaMorte(NOM_JOGADOR_3, NOM_JOGADOR_2, NOM_ARMA_AK47, new Date());
		partida.processaMorte(WORLD, NOM_JOGADOR_3, null, new Date());

		// Efetua a finalização da partida
		partida.encerraPartida(new Date());

		// Efetua as validaçoes
		final List<Jogador> jogadores = partida.getRanking();

		// Primeiro jogador
		final Jogador jogadorVencedor = jogadores.get(0);
		Assert.assertEquals(NOM_JOGADOR_1, jogadorVencedor.getNomJogador());
		Assert.assertEquals(Integer.valueOf(3), jogadorVencedor.getQtdKills());
		Assert.assertEquals(Integer.valueOf(1), jogadorVencedor.getQtdDeath());

		// Segundo jogador
		final Jogador jogadorSegundo = jogadores.get(1);
		Assert.assertEquals(NOM_JOGADOR_3, jogadorSegundo.getNomJogador());
		Assert.assertEquals(Integer.valueOf(2), jogadorSegundo.getQtdKills());
		Assert.assertEquals(Integer.valueOf(4), jogadorSegundo.getQtdDeath());

		// Terceiro jogador
		final Jogador jogadorTerceiro = jogadores.get(2);
		Assert.assertEquals(NOM_JOGADOR_2, jogadorTerceiro.getNomJogador());
		Assert.assertEquals(Integer.valueOf(1), jogadorTerceiro.getQtdKills());
		Assert.assertEquals(Integer.valueOf(2), jogadorTerceiro.getQtdDeath());
	}

}
