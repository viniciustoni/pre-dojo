package br.com.predojo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections4.CollectionUtils;

/**
 * Contém os dados da partida.
 * 
 * @author Vinicius A Gai
 */
public class Partida implements Serializable {

	private static final long serialVersionUID = -5543391133544497219L;
	private static final String WORLD = "<WORLD>";

	private String nomPartida;
	private Date datInicio;
	private Date datFim;
	private final Map<String, Jogador> jogadoresTO;
	private List<Jogador> ranking;

	public Partida(String nomPartida, Date datInicio) {
		super();
		this.nomPartida = nomPartida;
		this.datInicio = datInicio;
		this.jogadoresTO = new TreeMap<>();
	}

	public Date getDatFim() {
		return datFim;
	}

	public void setDatFim(Date datFim) {
		this.datFim = datFim;
	}

	public String getNomPartida() {
		return nomPartida;
	}

	public Date getDatInicio() {
		return datInicio;
	}

	public List<Jogador> getRanking() {
		return ranking;
	}

	/**
	 * Encerra a partida. Seta a data de fim da partida e cria o ranking.
	 */
	public void encerraPartida(final Date datFim) {

		// Data de finalização da partda.
		this.datFim = datFim;

		// Ordena pela mais utilizada e carrega ela.
		if (CollectionUtils.isNotEmpty(this.jogadoresTO.values())) {
			// Lista de armas utilizadas
			ranking = new ArrayList<>(this.jogadoresTO.values());

			// Ordena a lista
			Collections.sort(ranking);
		}

	}

	/**
	 * Processa a morte no jogo.
	 * 
	 * @param nomJogadorKill
	 *            Nome do jogador que efetuou a morte.
	 * @param nomJogadorDeath
	 *            Nome do jogador que morreu
	 * @param nomArma
	 *            Nome da arma.
	 * @param datMorte
	 *            Data da morte
	 */
	public void processaMorte(final String nomJogadorKill, final String nomJogadorDeath, final String nomArma,
			final Date datMorte) {

		// Carrega o jogador que efetuou a morte
		final Jogador jogadorMatou = getJogador(nomJogadorKill);

		// Processa o kill, caso o jogador existir
		if (jogadorMatou != null) {
			jogadorMatou.incrementaKill(nomArma, datMorte);
			atualizaJogador(jogadorMatou);
		}

		// Conta a morte do jogador.
		final Jogador jogadorMorreu = getJogador(nomJogadorDeath);
		if (jogadorMorreu != null) {
			jogadorMorreu.contaMorte();
			atualizaJogador(jogadorMorreu);
		}

	}

	/**
	 * Carrega o jogador, caso não exista na lista de jogadores ele cria um
	 * novo, caso exista retorna. Null em caso de jogador WORLD.
	 * 
	 * @param nomJogador
	 *            Nome do jogador.
	 * @return Dados do jogador
	 */
	private Jogador getJogador(final String nomJogador) {

		Jogador jogadorTO = jogadoresTO.get(nomJogador);

		if (!WORLD.equals(nomJogador) && jogadorTO == null) {
			jogadorTO = new Jogador(nomJogador);
		}

		return jogadorTO;

	}

	/**
	 * Atualiza os dados do Jogador.
	 * 
	 * @param jogadorTO
	 *            Dados do jogador.
	 */
	private void atualizaJogador(final Jogador jogadorTO) {

		if (jogadorTO != null) {
			this.jogadoresTO.put(jogadorTO.getNomJogador(), jogadorTO);
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomPartida == null) ? 0 : nomPartida.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partida other = (Partida) obj;
		if (nomPartida == null) {
			if (other.nomPartida != null)
				return false;
		} else if (!nomPartida.equals(other.nomPartida))
			return false;
		return true;
	}

}
