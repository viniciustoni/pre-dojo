package br.com.predojo.view;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * View para a tela de PreDojoView
 * 
 * @author Vinicius A Gai
 */
public class PreDojoView {

	private JFrame frmApplication = null;  //  @jve:decl-index=0:visual-constraint="10,346"
	private JPanel ctpPainel = null;
	private JMenuBar mnbMenuBar = null;
	private JMenu fmnArquivo = null;
	private JMenuItem exitMenuItem = null;
	private JMenuItem abrirArquivo = null;
	private JButton btnFileChoser = null;
	private JLabel lblPartida = null;
	private JScrollPane scpScroll = null;
	private JTable dtbRanking = null;

	/**
	 * This method initializes jFrame
	 *
	 * @return javax.swing.JFrame
	 */
	public JFrame getFrmApplication() {
		if (frmApplication == null) {
			frmApplication = new JFrame();
			frmApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frmApplication.setJMenuBar(getMnbMenuBar());
			frmApplication.setSize(785, 473);
			frmApplication.setContentPane(getCtpPainel());
			frmApplication.setTitle("PreDojo");
		}
		return frmApplication;
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getCtpPainel() {
		if (ctpPainel == null) {
			lblPartida = new JLabel();
			lblPartida.setBounds(new Rectangle(18, 58, 533, 21));
			lblPartida.setText("");
			ctpPainel = new JPanel();
			ctpPainel.setLayout(null);
			ctpPainel.add(getBtnFileChoser());
			ctpPainel.add(lblPartida, null);
			ctpPainel.add(getScpScroll(), null);
		}
		return ctpPainel;
	}

	/**
	 * This method initializes jJMenuBar
	 *
	 * @return javax.swing.JMenuBar
	 */
	private JMenuBar getMnbMenuBar() {
		if (mnbMenuBar == null) {
			mnbMenuBar = new JMenuBar();
			mnbMenuBar.add(getFmnArquivo());
		}
		return mnbMenuBar;
	}

	/**
	 * This method initializes jMenu
	 *
	 * @return javax.swing.JMenu
	 */
	private JMenu getFmnArquivo() {
		if (fmnArquivo == null) {
			fmnArquivo = new JMenu();
			fmnArquivo.setText("Arquivo");
			fmnArquivo.add(getAbrirArquivo());
			fmnArquivo.add(getExitMenuItem());
		}
		return fmnArquivo;
	}

	/**
	 * This method initializes jMenuItem
	 *
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Exit");
			exitMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitMenuItem;
	}

	/**
	 * This method initializes jMenuItem
	 *
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getAbrirArquivo() {
		if (abrirArquivo == null) {
			abrirArquivo = new JMenuItem();
			abrirArquivo.setText("Processar Arquivo");
			abrirArquivo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					carregaArquivo();
				}
			});
		}
		return abrirArquivo;
	}

	/**
	 * This method initializes btnFileChoser
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBtnFileChoser() {
		if (btnFileChoser == null) {
			btnFileChoser = new JButton();
			btnFileChoser.setBounds(new Rectangle(54, 8, 174, 37));
			btnFileChoser.setText("Processar arquivo");
			btnFileChoser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					carregaArquivo();
				}
			});
		}
		return btnFileChoser;
	}

	/**
	 * This method initializes scpScroll
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getScpScroll() {
		if (scpScroll == null) {
			scpScroll = new JScrollPane();
			scpScroll.setBounds(new Rectangle(15, 87, 736, 307));
			scpScroll.setViewportView(getDtbRanking());
		}
		return scpScroll;
	}

	/**
	 * This method initializes dtbRanking
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getDtbRanking() {
		if (dtbRanking == null) {
			dtbRanking = new JTable();
		}
		return dtbRanking;
	}

	/**
	 * Escolhe e processa o arquivo
	 */
	private void carregaArquivo() {

		// Cria o fileChosser.
		final JFileChooser flcSelecionaArquivo = new JFileChooser();
		flcSelecionaArquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);

		// Cria os filter para o filechosser
		final FileFilter filterTxt = new FileNameExtensionFilter("TXT File","txt");
		final FileFilter filterLog = new FileNameExtensionFilter("Log Files","log");
		
		flcSelecionaArquivo.addChoosableFileFilter(filterTxt);
		flcSelecionaArquivo.addChoosableFileFilter(filterLog);

		// Abre a tela
		int returnVal = flcSelecionaArquivo.showOpenDialog(frmApplication);

		// Valida o arquivo e executa.
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			final File file = flcSelecionaArquivo.getSelectedFile();
			// This is where a real application would open the file.
			System.out.println("Abrindo arquivo: " + file.getPath());
			System.out.println("Abrindo arquivo: " + file.length());
		}
	}

	/**
	 * Processa o arquivo.
	 * 
	 * @param file
	 */
	private void processaArquivo(final File file) {
		
		
	}
}
