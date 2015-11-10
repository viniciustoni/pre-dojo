package br.com.predojo.application;

import java.text.ParseException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.com.predojo.entity.Jogador;
import br.com.predojo.entity.Partida;
import br.com.predojo.utils.PreDojoUtils;

/**
 * Junit para a classe de {@link ProcessaLinha}
 * 
 * @author Vinicis A Gai
 */
public class ProcessaLinhaTest {

	private static final String DAT_INICIO_PARTIDA = "23/04/2013 15:34:22";
	private static final String NOM_PARTIDA = "11348965";
	private static final String LINHA_INICIO_PARTIDA = DAT_INICIO_PARTIDA + " - New match " + NOM_PARTIDA + " has started";
	private static final String NOM_JOGADOR_KILL_JOGADA =  "Roman";
	private static final String NOM_JOGADOR_DEATH_JOGADA =  "Nick";
	private static final String NOM_ARMA_JOGADA =  "Nick";
	private static final String LINHA_JOGADA = "23/04/2013 15:36:04 - "+ NOM_JOGADOR_KILL_JOGADA + " killed "+ NOM_JOGADOR_DEATH_JOGADA + " using " + NOM_ARMA_JOGADA;
	private static final String DAT_FIM_PARTIDA = "23/04/2013 15:35:22";
	private static final String LINHA_FIM_PARTIDA = "23/04/2013 15:35:22" + " - Match 11348965 has ended";
	
	private ProcessaLinha processaLinha = new ProcessaLinha(); 
	
	@Test
	public void testCriaPartidaNova() throws ParseException {
		
		// Executa
		final Partida partida = processaLinha.criaPartidaNova(LINHA_INICIO_PARTIDA);
		
		// Verificaçoes
		Assert.assertNotNull(partida);
		Assert.assertEquals(PreDojoUtils.convertStringToDate(DAT_INICIO_PARTIDA), partida.getDatInicio());
		Assert.assertEquals(NOM_PARTIDA, partida.getNomPartida());
		
	}
	
	@Test
	public void testCriaPartidaNovaLinhaForaDoPadrao() throws ParseException {
		
		// Executa
		final Partida partida = processaLinha.criaPartidaNova(LINHA_JOGADA);
		
		// Verificaçoes
		Assert.assertNull(partida);
		
	}
	
	@Test
	public void testFinalizaPartida() throws ParseException {
		
		// Cria a partida
		final Partida partida = new Partida(NOM_PARTIDA, new Date());
		
		// Executa método
		final boolean isFinalizaPartida = processaLinha.finalizaPartida(LINHA_FIM_PARTIDA, partida);
		
		// Verificações
		Assert.assertTrue(isFinalizaPartida);
		Assert.assertEquals(PreDojoUtils.convertStringToDate(DAT_FIM_PARTIDA), partida.getDatFim());
		
	}
	
	@Test
	public void testFinalizaPartidaLinhaForaDoPadrao() throws ParseException {
		
		// Cria a partida
		final Partida partida = new Partida(NOM_PARTIDA, new Date());
		
		// Executa método
		final boolean isFinalizaPartida = processaLinha.finalizaPartida(LINHA_JOGADA, partida);
		
		// Verificações
		Assert.assertFalse(isFinalizaPartida);
		
	}
	
	@Test
	public void testExecutaJogada() throws ParseException {
		
		// Cria a partida
		final Partida partida = new Partida(NOM_PARTIDA, new Date());
		
		// Executa o método
		processaLinha.executaJogada(LINHA_JOGADA, partida);
		
		// Finaliza para validar jogadores
		partida.encerraPartida(new Date());
		
		// Verificaçoes
		final Jogador jogadorKill = partida.getRanking().get(0);
		Assert.assertEquals(NOM_JOGADOR_KILL_JOGADA, jogadorKill.getNomJogador());
		Assert.assertEquals(Integer.valueOf(1), jogadorKill.getQtdKills());
		Assert.assertEquals(Integer.valueOf(0), jogadorKill.getQtdDeath());
		Assert.assertEquals(NOM_ARMA_JOGADA, jogadorKill.getNomeArmaMaisUtilizada());
		
		final Jogador jogadorDeath = partida.getRanking().get(1);
		Assert.assertEquals(NOM_JOGADOR_DEATH_JOGADA, jogadorDeath.getNomJogador());
		Assert.assertEquals(Integer.valueOf(0), jogadorDeath.getQtdKills());
		Assert.assertEquals(Integer.valueOf(1), jogadorDeath.getQtdDeath());
	}
	
	@Test
	public void testExecutaJogadaLinhaForaDoPadrao() throws ParseException {
		
		// Cria a partida
		final Partida partida = new Partida(NOM_PARTIDA, new Date());
		
		// Executa o método
		processaLinha.executaJogada(LINHA_FIM_PARTIDA, partida);
		
		// Finaliza para validar jogadores
		partida.encerraPartida(new Date());
		
		// Verificaçoes
		Assert.assertNull(partida.getRanking());
	}
	
}
