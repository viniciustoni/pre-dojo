package br.com.predojo.entity;

/**
 * Dados da arma.
 * 
 * @author Vinicius A Gai
 *
 */
public class Arma extends BaseKillsJogo {

	private static final long serialVersionUID = 6151976615489853639L;

	private String nomArma;

	/**
	 * Constructor.
	 * 
	 * @param nomArma
	 *            Nome da arma.
	 */
	public Arma(String nomArma) {
		super();
		this.nomArma = nomArma;
	}

	public String getNomArma() {
		return nomArma;
	}

	/**
	 * Incrementa mais uma morte para a arma.
	 */
	public void incremetaKill() {
		incrementarKill();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomArma == null) ? 0 : nomArma.hashCode());
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
		Arma other = (Arma) obj;
		if (nomArma == null) {
			if (other.nomArma != null)
				return false;
		} else if (!nomArma.equals(other.nomArma))
			return false;
		return true;
	}

}
