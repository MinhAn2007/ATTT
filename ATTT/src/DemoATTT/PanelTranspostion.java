package DemoATTT;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelTranspostion {
	private JTextField encryption_PlainText = new JTextField(10);
	private JTextField encryption_Key = new JTextField(10);
	private JTextField encryption_Cipher = new JTextField(10);
	private JButton encryption_btn = new JButton("Encrypt");

	private JTextField decryption_PlainText = new JTextField(10);
	private JTextField decryption_Key = new JTextField(10);
	private JTextField decryption_Cipher = new JTextField(10);
	private JButton decryption_btn = new JButton("Decrypt");

	private String alphapetic = "abcdefghijklmnopqrstuvwxyz";

	public boolean checkValEn() {
		if (this.encryption_PlainText.getText().toString().length() <= 0) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin cần mã hóa");
			this.encryption_PlainText.requestFocus();
			return false;
		} else if (this.encryption_Key.getText().toString().length() <= 0) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập Key mã hóa");
			this.encryption_Key.requestFocus();
			return false;
		}

		return true;
	}

	public boolean checkValDe() {
		if (this.decryption_Cipher.getText().toString().length() <= 0) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin cần giải mã");
			this.decryption_Cipher.requestFocus();
			return false;
		} else if (this.decryption_Key.getText().toString().length() <= 0) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập Key giải mã");
			this.decryption_Key.requestFocus();
			return false;
		}
		return true;
	}

	public PanelTranspostion() {
		encryption_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (checkValEn()) {
					String text = Encryption(encryption_PlainText.getText().toString(),
							encryption_Key.getText().toString());
					encryption_Cipher.setText(text);
				}

			}
		});

		decryption_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (checkValDe()) {
					String text = Decryption(decryption_Cipher.getText().toString(),
							decryption_Key.getText().toString());
					decryption_PlainText.setText(text);
				}
			}
		});
	}

	public JPanel createPanel() {
		JPanel Main = new JPanel(new BorderLayout());
		TitledBorder encryption = BorderFactory.createTitledBorder("Encryption");
		JPanel encryption_panel = new JPanel(new GridLayout(4, 2));
		encryption_panel.setBorder(encryption);
		encryption_panel.add(new JLabel("Plain Text"));
		encryption_panel.add(encryption_PlainText);
		encryption_panel.add(new JLabel("Key"));
		encryption_panel.add(encryption_Key);
		encryption_panel.add(new JLabel("Cipher Text"));
		encryption_panel.add(encryption_Cipher);
		encryption_panel.add(new JLabel(""));
		encryption_panel.add(encryption_btn);

		TitledBorder decryption = BorderFactory.createTitledBorder("Decryption");
		JPanel decryption_panel = new JPanel(new GridLayout(4, 2));
		decryption_panel.setBorder(decryption);
		decryption_panel.add(new JLabel("Cipher Text"));
		decryption_panel.add(decryption_Cipher);
		decryption_panel.add(new JLabel("Key"));
		decryption_panel.add(decryption_Key);
		decryption_panel.add(new JLabel("Plain Text"));
		decryption_panel.add(decryption_PlainText);
		decryption_panel.add(new JLabel(""));
		decryption_panel.add(decryption_btn);
		Main.add(encryption_panel, BorderLayout.NORTH);
		Main.add(decryption_panel, BorderLayout.SOUTH);
		return Main;
	}

	public String Encryption(String Plain, String Key) {
		Plain = Plain.toLowerCase();
		Plain = Plain.replace(" ", "");
		Plain.trim();
		Key = Key.toLowerCase();
		Key = Key.replace(" ", "");
		String Cipher = "";
		char[] key_array = Key.toCharArray();
		int row = (Plain.length() / Key.length());
		int column = Key.length();
		int empty_field = 0;
		int increment = 0;
		char[][] Matrix = new char[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (increment < Plain.length()) {
					Matrix[i][j] = Plain.charAt(increment);
					increment++;
				} else {
					Matrix[i][j] = alphapetic.charAt(empty_field);
					empty_field++;
				}
			}
		}
		int position = 0;
		char[] Sorted_key = new char[key_array.length];
		for (int i = 0; i < key_array.length; i++) {
			Sorted_key[i] = key_array[i];
		}
		Arrays.sort(Sorted_key);
		for (int i = 0; i < column; i++) {
			position = Key.indexOf(Sorted_key[i]);
			for (int j = 0; j < row; j++) {
				Cipher += Matrix[j][position];
			}
		}
		return Cipher;
	}

	public String Decryption(String Cipher, String Key) {
		Cipher = Cipher.toLowerCase();
		Cipher = Cipher.trim();
		Key = Key.toLowerCase();
		String Plain = "";
		int row = Math.round(Cipher.length() / Key.length());
		int column = Key.length();
		int position;
		int increment = 0;
		char[][] Matrix = new char[row][column];
		char[] cipher_array = Cipher.toCharArray();
		char[] sorted_key = Key.toCharArray();
		Arrays.sort(sorted_key);
		for (int i = 0; i < column; i++) {
			position = Key.indexOf(sorted_key[i]);
			for (int j = 0; j < row; j++) {
				Matrix[j][position] = cipher_array[increment];
				increment++;
			}
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				Plain += Matrix[i][j];
			}
		}
		return Plain;
	}

}
