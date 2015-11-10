package br.com.predojo.utils;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import br.com.predojo.entity.Jogador;
import br.com.predojo.entity.Partida;

/**
 * Classe utilitaria para o PreDojo
 * 
 * @author Vinicius A Gai
 *
 */
public class PreDojoUtils {

	private static final String DATE_PATTERN = "dd/MM/yyyy HH:mm:ss";
	private static final String TEMPLATE_CABECALHO = "Partida {0}";
	private static final String CABECALHO_NOM_JOGADOR = "Jogador";
	private static final String CABECALHO_QTD_KILLS = "Kills";
	private static final String CABECALHO_QTD_MORTES = "Death";
	private static final String CABECALHO_QTD_KILLS_MAX = "Max kills";
	private static final String CABECALHO_ARMA_PREFERIDA = "Arma preferida";
	private static final String CABECALHO_MENSAGEM = "Mensagem";
	private static final String SEPARADOR = "|";
	private static final String AWARD_5_MORTES_1_MIN = "5 Em menos de 1 minuto";
	private static final String AWARD_SEM_DEATH = "Não Morreu";
	private static final Integer TAM_NOM_JOGADOR = 100;
	private static final Integer TAM_QTD_KILLS = 5;
	private static final Integer TAM_QTD_MORTES = 5;
	private static final Integer TAM_QTD_KILLS_MAX = 9;
	private static final Integer TAM_ARMA_PREFERIDA = 50;

	/**
	 * Converter de uma campo de String para Date.
	 * 
	 * @param data
	 *            Data a ser convertida
	 * @return Data
	 * @throws ParseException
	 */
	public static Date convertStringToDate(final String data) throws ParseException {
		return DateUtils.parseDate(data, DATE_PATTERN);
	}

	/**
	 * Monta o placar.
	 * 
	 * @param partida Dados da partida.
	 * @return Placar
	 */
	public static String montaPlacar(final Partida partida) {

		final StringBuilder placar = new StringBuilder();

		// Monta o cabecalho.
		placar.append(montaCabecalho(partida.getNomPartida()));
		
		// Dados do jogador
		if (CollectionUtils.isNotEmpty(partida.getRanking())) {
			for(Jogador jogador : partida.getRanking()) {
				placar.append(montaLinhaJogador(jogador));
			}
		}
		
		return placar.toString();

	}

	/**
	 * Monta a linha para o cabecalho
	 * @param nomPartida Nome da partida
	 * @return Cabecalho
	 */
	private static String montaCabecalho(final String nomPartida) {

		final StringBuilder cabecalho = new StringBuilder();
		
		cabecalho.append(MessageFormat.format(TEMPLATE_CABECALHO, nomPartida));
		cabecalho.append(System.lineSeparator());

		cabecalho.append(StringUtils.rightPad(CABECALHO_NOM_JOGADOR, TAM_NOM_JOGADOR));
		cabecalho.append(SEPARADOR);
		cabecalho.append(StringUtils.rightPad(CABECALHO_QTD_KILLS, TAM_QTD_KILLS));
		cabecalho.append(SEPARADOR);
		cabecalho.append(StringUtils.rightPad(CABECALHO_QTD_MORTES, TAM_QTD_MORTES));
		cabecalho.append(SEPARADOR);
		cabecalho.append(StringUtils.rightPad(CABECALHO_QTD_KILLS_MAX, TAM_QTD_KILLS_MAX));
		cabecalho.append(SEPARADOR);
		cabecalho.append(StringUtils.rightPad(CABECALHO_ARMA_PREFERIDA, TAM_ARMA_PREFERIDA));
		cabecalho.append(SEPARADOR);
		cabecalho.append(CABECALHO_MENSAGEM);
		cabecalho.append(System.lineSeparator());

		return cabecalho.toString();

	}

	/**
	 * Monta a linha para o jogador
	 * 
	 * @param jogador Dados do jogador
	 * @return Linha contendo os dados do jogador.
	 */
	private static String montaLinhaJogador(final Jogador jogador) {

		final StringBuilder linhaJogador = new StringBuilder();

		linhaJogador.append(StringUtils.rightPad(jogador.getNomJogador(), TAM_NOM_JOGADOR));
		linhaJogador.append(SEPARADOR);
		linhaJogador.append(StringUtils.rightPad(jogador.getQtdKills().toString(), TAM_QTD_KILLS));
		linhaJogador.append(SEPARADOR);
		linhaJogador.append(StringUtils.rightPad(jogador.getQtdDeath().toString(), TAM_QTD_MORTES));
		linhaJogador.append(SEPARADOR);
		linhaJogador.append(StringUtils.rightPad(jogador.getMaxKillsSeguidas().toString(), TAM_QTD_KILLS_MAX));
		linhaJogador.append(SEPARADOR);
		linhaJogador.append(StringUtils.rightPad(jogador.getNomeArmaMaisUtilizada(), TAM_ARMA_PREFERIDA));
		linhaJogador.append(SEPARADOR);
		
		// 5 mortes em 1 minuto
		if (jogador.isHasAward5Mortes()) {
			linhaJogador.append(AWARD_5_MORTES_1_MIN);
			if (!jogador.hasMorte()) {
				linhaJogador.append(", ");
			}
		}

		// Se não tiver nenhuma morte
		if (!jogador.hasMorte()) {
			linhaJogador.append(AWARD_SEM_DEATH);
		}
		
		linhaJogador.append(System.lineSeparator());

		return linhaJogador.toString();

	}

}
