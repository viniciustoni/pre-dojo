package br.com.predojo.entity;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.com.predojo.entity.Arma;
import br.com.predojo.entity.Jogador;
import br.com.predojo.util.BaseTest;

/**
 * Junit para a classe de {@link Jogador}
 * 
 * @author Vinicius A Gai
 *
 */
public class JogadorTest {

	private static final String NOM_JOGADOR = "JUnit1";
	private static final String NOM_ARMA_AK47 = "AK-47";
	private static final String NOM_ARMA_M16 = "M16";
	
	/**
	 * Junit para o seguinte cenário: <br>
	 * <ul>
	 *   <li>3 kills com a AK 47</li>
	 *   <li>2 Kills com a M16</li>
	 *   <li>Award de 5 kills em menos de um minuto</li>
	 *   <li>Award sem nenhum morte</li>
	 * </ul>
	 * 
	 */
	@Test
	public void testIncrementa3KillsAk47E2KillsM16Award5EmUmMinutoAwardSemMorte() {
		
		// Cria o jogador
		final Jogador jogadorTO = new Jogador(NOM_JOGADOR);
		
		// 3 mortes com AK47
		jogadorTO.incrementaKill(NOM_ARMA_AK47, new Date());
		jogadorTO.incrementaKill(NOM_ARMA_AK47, new Date());
		jogadorTO.incrementaKill(NOM_ARMA_AK47, new Date());
		
		// 2 mortes com M16
		jogadorTO.incrementaKill(NOM_ARMA_M16, new Date());
		jogadorTO.incrementaKill(NOM_ARMA_M16, new Date());
		
		// Valida os dados
		Assert.assertEquals(NOM_JOGADOR, jogadorTO.getNomJogador());
		Assert.assertEquals(Integer.valueOf(5), jogadorTO.getQtdKills());
		Assert.assertEquals(Integer.valueOf(5), jogadorTO.getMaxKillsSeguidas());
		Assert.assertEquals(Integer.valueOf(0), jogadorTO.getQtdDeath());
		Assert.assertTrue(jogadorTO.isHasAward5Mortes());
		Assert.assertFalse(jogadorTO.hasMorte());
		
		// Testa a arma mais usada
		final Arma armaMaisUsada = jogadorTO.getArmaMaisUtilizada();
		Assert.assertEquals(NOM_ARMA_AK47, armaMaisUsada.getNomArma());
		
	}
	
	/**
	 * Junit para o seguinte cenário: <br>
	 * <ul>
	 *   <li>2 kills com a AK 47</li>
	 *   <li>3 Kills com a M16</li>
	 *   <li>Uma Morte</li>
	 * </ul>
	 * 
	 */
	@Test
	public void testIncrementa2KillsAk47UmaMorteE3KillsM16Award5EmUmMinutoAwardSemMorte() {
		
		// Cria o jogador
		final Jogador jogadorTO = new Jogador(NOM_JOGADOR);
		
		// 3 mortes com AK47
		jogadorTO.incrementaKill(NOM_ARMA_AK47, new Date());
		jogadorTO.incrementaKill(NOM_ARMA_AK47, new Date());
		
		// Conta uma morte
		jogadorTO.contaMorte();
		
		// 2 mortes com M16
		jogadorTO.incrementaKill(NOM_ARMA_M16, new Date());
		jogadorTO.incrementaKill(NOM_ARMA_M16, new Date());
		jogadorTO.incrementaKill(NOM_ARMA_M16, new Date());
		
		// Valida os dados
		Assert.assertEquals(NOM_JOGADOR, jogadorTO.getNomJogador());
		Assert.assertEquals(Integer.valueOf(5), jogadorTO.getQtdKills());
		Assert.assertEquals(Integer.valueOf(3), jogadorTO.getMaxKillsSeguidas());
		Assert.assertEquals(Integer.valueOf(1), jogadorTO.getQtdDeath());
		Assert.assertFalse(jogadorTO.isHasAward5Mortes());
		Assert.assertTrue(jogadorTO.hasMorte());
		
		// Testa a arma mais usada
		final Arma armaMaisUsada = jogadorTO.getArmaMaisUtilizada();
		Assert.assertEquals(NOM_ARMA_M16, armaMaisUsada.getNomArma());
		
	}
	
	
	/**
	 * Junit para o seguinte cenário: <br>
	 * <ul>
	 *   <li>2 kills com a AK 47</li>
	 *   <li>3 Kills com a M16</li>
	 *   <li>NÃO Award de 5 kills em menos de um minuto</li>
	 *   <li>Award sem nenhum morte</li>
	 * </ul>
	 * 
	 */
	@Test
	public void testIncrementa2KillsAk47E3KillsM16Award5EmMaisDeUmMinutoAwardSemMorte() {
		
		// Cria o jogador
		final Jogador jogadorTO = new Jogador(NOM_JOGADOR);
		
		// 3 mortes com AK47
		jogadorTO.incrementaKill(NOM_ARMA_AK47, BaseTest.createDate(9, 0, 1));
		jogadorTO.incrementaKill(NOM_ARMA_AK47, BaseTest.createDate(9, 0, 20));
		
		// 2 mortes com M16
		jogadorTO.incrementaKill(NOM_ARMA_M16, BaseTest.createDate(9, 0, 30));
		jogadorTO.incrementaKill(NOM_ARMA_M16, BaseTest.createDate(9, 0, 50));
		jogadorTO.incrementaKill(NOM_ARMA_M16, BaseTest.createDate(9, 0, 61));
		
		// Valida os dados
		Assert.assertEquals(NOM_JOGADOR, jogadorTO.getNomJogador());
		Assert.assertEquals(Integer.valueOf(5), jogadorTO.getQtdKills());
		Assert.assertEquals(Integer.valueOf(5), jogadorTO.getMaxKillsSeguidas());
		Assert.assertEquals(Integer.valueOf(0), jogadorTO.getQtdDeath());
		Assert.assertFalse(jogadorTO.isHasAward5Mortes());
		Assert.assertFalse(jogadorTO.hasMorte());
		
		// Testa a arma mais usada
		final Arma armaMaisUsada = jogadorTO.getArmaMaisUtilizada();
		Assert.assertEquals(NOM_ARMA_M16, armaMaisUsada.getNomArma());
		
	}
}
