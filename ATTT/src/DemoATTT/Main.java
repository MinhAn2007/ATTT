
package DemoATTT;

import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Main extends JFrame {
	/**
	 * qưe123123123123123
	 */
	private static final long serialVersionUID = 1L;

	public Main() throws UnsupportedEncodingException {
		setTitle("Encrypt and Decrypt");
		setTitle("Encrypt and Decrypt");

		setSize(900, 350);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTabbedPane tabPane = new JTabbedPane();

		tabPane.add("AES", tabbedPaneAES());
		tabPane.add("Transposition", tabbedPaneTranspositon());
		this.add(tabPane);
	}

	public JTabbedPane tabbedPaneTranspositon() {
		JTabbedPane MainContainer = new JTabbedPane();
		MainContainer.add("Transposition Encrypt and Decrypt", new PanelTranspostion().createPanel());
		return MainContainer;
	}

	public JTabbedPane tabbedPaneAES() throws UnsupportedEncodingException {
		JTabbedPane MainContainer = new JTabbedPane();
		MainContainer.add("AES Encrypt and Decrypt", new PanelAES().createPanel());
		return MainContainer;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		new Main();
	}

}
