package br.com.predojo;

import javax.swing.SwingUtilities;

import br.com.predojo.view.PreDojoView;

/**
 * Classe main da alicação.
 * 
 * @author Vinicius A Gai
 *
 */
public class PreDojoApplication {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				final PreDojoView preDojoView = new PreDojoView();
				preDojoView.getFrmApplication().setVisible(true);
			}
		});

	}

}
