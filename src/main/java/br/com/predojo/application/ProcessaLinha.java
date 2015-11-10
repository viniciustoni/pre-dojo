package br.com.predojo.application;

import java.text.ParseException;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;

import br.com.predojo.entity.Partida;
import br.com.predojo.utils.PreDojoUtils;

/**
 * Processa a linha
 * 
 * @author Vinicius A Gai
 *
 */
public class ProcessaLinha {
	
	private static final String SPACE_DELIM = " ";
	private static final String TIME_SEPARATOR = "-";
	private static final String STARTED_WORD = "has started";
	private static final String ENDED_WORD = "has ended";
	private static final String KILLED_WORD = "killed";
	

	/**
	 * Cria uma nova partida.
	 * 
	 * @param linha Linha para criar partida
	 * @return Partida
	 * @throws ParseException 
	 */
	public Partida criaPartidaNova(final String linha) throws ParseException {
		
		Partida partida = null;
		
		// Verifica se comeco de jogo
		if (StringUtils.containsIgnoreCase(linha, STARTED_WORD)) {
			
			// carrega a data
			final StringTokenizer tokenData = new StringTokenizer(linha, TIME_SEPARATOR);
			final Date datInicio = PreDojoUtils.convertStringToDate(tokenData.nextToken().trim());
			
			// carrega o nome da partida
			tokenData.nextToken(SPACE_DELIM);
			tokenData.nextToken(SPACE_DELIM);
			tokenData.nextToken(SPACE_DELIM);
			
			final String nomPartida = tokenData.nextToken(SPACE_DELIM).trim();
			
			// Cria a partida
			partida = new Partida(nomPartida, datInicio);
		}
		
		return partida;
	}
	
	/**
	 * Finaliza partida.
	 * 
	 * @param linha Linha a ser processada.
	 * @param partida Partida
	 * @return true caso partida finalizada.
	 * @throws ParseException 
	 */
	public boolean finalizaPartida(final String linha, final Partida partida) throws ParseException {
		
		boolean isFinalizaPartida = false;
		
		// Verifica se comeco de jogo
		if (StringUtils.containsIgnoreCase(linha, ENDED_WORD)) {
			
			// carrega a data
			final StringTokenizer tokenData = new StringTokenizer(linha, TIME_SEPARATOR);
			final Date datFim = PreDojoUtils.convertStringToDate(tokenData.nextToken().trim());
			
			// Cria a partida
			partida.encerraPartida(datFim);
			
			// encerra partida
			isFinalizaPartida = true;
		}
		
		return isFinalizaPartida;
		
	}
	
	/**
	 * Processa execução de jogada.
	 * 
	 * @param linha Linha a ser processada.
	 * @param partida Partida.
	 * @throws ParseException 
	 */
	public void executaJogada(final String linha, final Partida partida) throws ParseException {
		
		// Verifica se comeco de jogo
		if (StringUtils.containsIgnoreCase(linha, KILLED_WORD)) {
			
			// carrega a data
			final StringTokenizer tokenData = new StringTokenizer(linha, TIME_SEPARATOR);
			final Date datEvento = PreDojoUtils.convertStringToDate(tokenData.nextToken().trim());
			
			// Carrega nome jogador matou
			tokenData.nextToken(SPACE_DELIM);
			final String nomJogadorKill = tokenData.nextToken(SPACE_DELIM).trim();
			// Não importa
			tokenData.nextToken(SPACE_DELIM);
			final String nomJogadorDeath = tokenData.nextToken(SPACE_DELIM).trim();
			// Nao importa
			tokenData.nextToken(SPACE_DELIM);
			final String nomArma = tokenData.nextToken(SPACE_DELIM).trim();
			
			// Cria a partida
			partida.processaMorte(nomJogadorKill, nomJogadorDeath, nomArma, datEvento);
		}
		
	}
	
}
