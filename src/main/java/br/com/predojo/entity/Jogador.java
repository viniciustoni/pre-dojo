package br.com.predojo.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections4.CollectionUtils;

/**
 * Contém os dados do jogador.
 * 
 * @author Vinicius A Gai
 *
 */
public class Jogador extends Kill {

	private static final long serialVersionUID = 1825462953754342872L;

	private static final long ONE_MINUTE = 60 * 1000;

	private final String nomJogador;
	private Integer qtdDeath;
	private boolean hasAward5Mortes;
	private Integer maxKillsSeguidas;
	private Integer qtdKillsAtual;
	private Date datInicioKill;
	private final Map<String, Arma> armasUtilizadasTO;

	public Jogador(String nomJogador) {
		super();
		this.nomJogador = nomJogador;
		this.qtdDeath = 0;
		this.maxKillsSeguidas = 0;
		this.qtdKillsAtual = 0;
		this.armasUtilizadasTO = new TreeMap<>();
	}

	public String getNomJogador() {
		return nomJogador;
	}

	public Integer getQtdDeath() {
		return qtdDeath;
	}

	public boolean isHasAward5Mortes() {
		return hasAward5Mortes;
	}

	public Integer getMaxKillsSeguidas() {
		return maxKillsSeguidas;
	}

	/**
	 * Efetua as regras para contar a morte do jogador.
	 */
	public void contaMorte() {
		this.qtdDeath++;
		this.qtdKillsAtual = 0;
		this.datInicioKill = null;
	}

	/**
	 * Efetua as regras para contar o kil do jogador.
	 * 
	 * @param nomArma
	 */
	public void incrementaKill(final String nomArma, final Date datMorte) {

		// Quantidade de mortes
		this.qtdKillsAtual++;

		// Valida outros dados do jogador.
		incrementaKill();
		verifica5MortesSemMorrer(datMorte);
		verificaMaxNumeroMortes();
		contaKillArma(nomArma);

	}
	
	/**
	 * Verifica se o jogador ja morreu na partida.
	 * 
	 * @return true caso já morreu.
	 */
	public boolean hasMorte() {
		return this.qtdDeath > 0;
	}

	/**
	 * Carrega a arma mais utilizada.
	 * @return
	 */
	public Arma getArmaMaisUtilizada() {
		
		// Arma mais utilizada
		Arma armaMaisUtilizada = null;
		
		
		// Ordena pela mais utilizada e carrega ela.
		if (CollectionUtils.isNotEmpty(this.armasUtilizadasTO.values())) {
			// Lista de armas utilizadas
			final List<Arma> armasUtilizadas = new ArrayList<>(this.armasUtilizadasTO.values());	
			
			// Ordena a lista
			Collections.sort(armasUtilizadas);
			armaMaisUtilizada = armasUtilizadas.get(0);
		}
		
		return armaMaisUtilizada;
		
	}
	
	/**
	 * Carrega o nome da arma mais utilizada.
	 * @return
	 */
	public String getNomeArmaMaisUtilizada() {
		
		// Arma mais utilizada
		String nomArmaMaisUtilizada = "";
		Arma armaMaisUtilizada = getArmaMaisUtilizada();
		
		if (armaMaisUtilizada != null) {
			nomArmaMaisUtilizada = armaMaisUtilizada.getNomArma();
		}
		
		return nomArmaMaisUtilizada;
		
	}
	
	/**
	 * Verifica se o jogador posui 5 mortes sem morrer nenhuma vez.
	 * 
	 * @param datMorte
	 */
	private void verifica5MortesSemMorrer(final Date datMorte) {
		// Se primeira morte, conta o inicio do tempo
		if (this.datInicioKill == null) {
			this.datInicioKill = datMorte;
			// Caso tempo ja iniciado, verifica se ele tem 5 mortes em um minuto.
		} else if (qtdKillsAtual >= 5 && !this.hasAward5Mortes) {
			final boolean inOneMinute = (datMorte.getTime() - this.datInicioKill.getTime()) <= ONE_MINUTE;
			if (inOneMinute) {
				this.hasAward5Mortes = true;
			}
		}
	}
	
	/**
	 * Verifica a quantidade máxima de mortes.
	 */
	private void verificaMaxNumeroMortes() {
		if (this.qtdKillsAtual > this.maxKillsSeguidas) {
			this.maxKillsSeguidas = this.qtdKillsAtual;
		}
	}
	
	/**
	 * Conta o kill para a arma utilizada.
	 * 
	 * @param nomArma
	 */
	private void contaKillArma(final String nomArma) {
		
		// Cria o objeto de arma
		Arma armaTO = armasUtilizadasTO.get(nomArma);
		
		// Caso a arma não exista ele cria.
		if (armaTO == null) {
			armaTO = new Arma(nomArma);
		}
		
		// Incrementa a morte
		armaTO.incremetaKill();
		this.armasUtilizadasTO.put(nomArma, armaTO);
		
	}
	
}
