package br.com.predojo.entity;

import java.io.Serializable;

/**
 * Classe abstrata para contar as kills que ele efetuou no jogo
 * 
 * @author Vinicius A Gai
 *
 */
public abstract class BaseKillsJogo implements Serializable, Comparable<BaseKillsJogo> {

	private static final long serialVersionUID = 6521090313502384003L;

	private Integer qtdKills;

	public BaseKillsJogo() {
		super();
		this.qtdKills = 0;
	}

	public Integer getQtdKills() {
		return qtdKills;
	}

	/**
	 * Incrementa mais uma morte.
	 */
	protected void incrementarKill() {
		this.qtdKills++;
	}

	@Override
	public int compareTo(BaseKillsJogo baseKillsJogoTOOther) {
		if (this.qtdKills > baseKillsJogoTOOther.qtdKills) {
            return -1;
        }
        if (this.qtdKills < baseKillsJogoTOOther.qtdKills) {
            return 1;
        }
		return 0;
	}

	
}
