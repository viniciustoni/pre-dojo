package br.com.predojo.application;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;

import br.com.predojo.entity.Partida;
import br.com.predojo.exception.ArquivoNaoExistenteException;

/**
 * Le o arquivo de log.
 * 
 * @author Vinicius A Gai
 *
 */
public class LeitorArquivo {

	private ProcessaLinha processaLinha = new ProcessaLinha();

	/**
	 * Processa o Arquivo
	 * 
	 * @param file
	 *            Arquivo.
	 * @return {@link List<Partida>}
	 * @throws IOException
	 * @throws ParseException 
	 */
	public List<Partida> processaArquivo(final File file) throws ArquivoNaoExistenteException, IOException, ParseException {

		List<Partida> partidas = null;

		// Verifica a existencia do arquivo
		if (file == null || !file.exists()) {
			throw new ArquivoNaoExistenteException("Nenhum arquivo encontrado.");
		}

		// Le o arquivo
		final List<String> linhas = FileUtils.readLines(file);

		// Processa
		if (CollectionUtils.isNotEmpty(linhas)) {
			partidas = new ArrayList<>();
			int indexPartida = 0;
			boolean hasPartidaIniciada = false;

			// Percorre as linhas
			for (String linha : linhas) {

				// Verifica se é comeco de jogo
				if (!hasPartidaIniciada) {
					partidas.add(indexPartida, processaLinha.criaPartidaNova(linha));
					hasPartidaIniciada = true;

					// Verifica se é final de jogo
				} else if (processaLinha.finalizaPartida(linha, partidas.get(indexPartida))) {
					hasPartidaIniciada = false;
					indexPartida++;
				} else {
					// Evento de jogada
					processaLinha.executaJogada(linha, partidas.get(indexPartida));
				}

			}
		}

		return partidas;
	}

}
