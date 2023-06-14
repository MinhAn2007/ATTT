package DemoATTT;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class PanelAES extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblKey;
	private JTextField textField;
	private JLabel lblOriginalText;
	private JTextArea textAreaOriginal;
	private JTextArea textAreaEncrypted;
	private JLabel lblEncryptedText;
	private JTextArea textAreaDycrypted;
	private JLabel lblDecryptedText;
	private JButton btnEncrypt;
	private JButton buttonDecrypt;
	int key_columns_temp[][] = new int[60][4];

	int[][] galois = { { 0x02, 0x03, 0x01, 0x01 }, { 0x01, 0x02, 0x03, 0x01 }, { 0x01, 0x01, 0x02, 0x03 },
			{ 0x03, 0x01, 0x01, 0x02 } };

	int[][] invgalois = { { 0x0e, 0x0b, 0x0d, 0x09 }, { 0x09, 0x0e, 0x0b, 0x0d }, { 0x0d, 0x09, 0x0e, 0x0b },
			{ 0x0b, 0x0d, 0x09, 0x0e } };

	int s_box[] = { 0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76,
			0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72, 0xC0, 0xB7, 0xFD,
			0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15, 0x04, 0xC7, 0x23, 0xC3,
			0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75, 0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E,
			0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84, 0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC, 0xB1, 0x5B,
			0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF, 0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9,
			0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8, 0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21,
			0x10, 0xFF, 0xF3, 0xD2, 0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E, 0x3D, 0x64, 0x5D,
			0x19, 0x73, 0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB,
			0xE0, 0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95, 0xE4, 0x79, 0xE7, 0xC8,
			0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08, 0xBA, 0x78, 0x25, 0x2E,
			0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A, 0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03,
			0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E, 0xE1, 0xF8, 0x98, 0x11, 0x69, 0xD9, 0x8E, 0x94,
			0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF, 0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41, 0x99,
			0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16, };

	int inv_s_box[] = { 0x52, 0x09, 0x6A, 0xD5, 0x30, 0x36, 0xA5, 0x38, 0xBF, 0x40, 0xA3, 0x9E, 0x81, 0xF3, 0xD7, 0xFB,
			0x7C, 0xE3, 0x39, 0x82, 0x9B, 0x2F, 0xFF, 0x87, 0x34, 0x8E, 0x43, 0x44, 0xC4, 0xDE, 0xE9, 0xCB, 0x54, 0x7B,
			0x94, 0x32, 0xA6, 0xC2, 0x23, 0x3D, 0xEE, 0x4C, 0x95, 0x0B, 0x42, 0xFA, 0xC3, 0x4E, 0x08, 0x2E, 0xA1, 0x66,
			0x28, 0xD9, 0x24, 0xB2, 0x76, 0x5B, 0xA2, 0x49, 0x6D, 0x8B, 0xD1, 0x25, 0x72, 0xF8, 0xF6, 0x64, 0x86, 0x68,
			0x98, 0x16, 0xD4, 0xA4, 0x5C, 0xCC, 0x5D, 0x65, 0xB6, 0x92, 0x6C, 0x70, 0x48, 0x50, 0xFD, 0xED, 0xB9, 0xDA,
			0x5E, 0x15, 0x46, 0x57, 0xA7, 0x8D, 0x9D, 0x84, 0x90, 0xD8, 0xAB, 0x00, 0x8C, 0xBC, 0xD3, 0x0A, 0xF7, 0xE4,
			0x58, 0x05, 0xB8, 0xB3, 0x45, 0x06, 0xD0, 0x2C, 0x1E, 0x8F, 0xCA, 0x3F, 0x0F, 0x02, 0xC1, 0xAF, 0xBD, 0x03,
			0x01, 0x13, 0x8A, 0x6B, 0x3A, 0x91, 0x11, 0x41, 0x4F, 0x67, 0xDC, 0xEA, 0x97, 0xF2, 0xCF, 0xCE, 0xF0, 0xB4,
			0xE6, 0x73, 0x96, 0xAC, 0x74, 0x22, 0xE7, 0xAD, 0x35, 0x85, 0xE2, 0xF9, 0x37, 0xE8, 0x1C, 0x75, 0xDF, 0x6E,
			0x47, 0xF1, 0x1A, 0x71, 0x1D, 0x29, 0xC5, 0x89, 0x6F, 0xB7, 0x62, 0x0E, 0xAA, 0x18, 0xBE, 0x1B, 0xFC, 0x56,
			0x3E, 0x4B, 0xC6, 0xD2, 0x79, 0x20, 0x9A, 0xDB, 0xC0, 0xFE, 0x78, 0xCD, 0x5A, 0xF4, 0x1F, 0xDD, 0xA8, 0x33,
			0x88, 0x07, 0xC7, 0x31, 0xB1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xEC, 0x5F, 0x60, 0x51, 0x7F, 0xA9, 0x19, 0xB5,
			0x4A, 0x0D, 0x2D, 0xE5, 0x7A, 0x9F, 0x93, 0xC9, 0x9C, 0xEF, 0xA0, 0xE0, 0x3B, 0x4D, 0xAE, 0x2A, 0xF5, 0xB0,
			0xC8, 0xEB, 0xBB, 0x3C, 0x83, 0x53, 0x99, 0x61, 0x17, 0x2B, 0x04, 0x7E, 0xBA, 0x77, 0xD6, 0x26, 0xE1, 0x69,
			0x14, 0x63, 0x55, 0x21, 0x0C, 0x7D, };

	int r_con[] = { 0x00, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1B, 0x36, 0x6C, 0xD8, 0xAB, 0x4D, 0x9A,
			0x2F, 0x5E, 0xBC, 0x63, 0xC6, 0x97, 0x35, 0x6A, 0xD4, 0xB3, 0x7D, 0xFA, 0xEF, 0xC5, 0x91, 0x39, };

	public void sub_bytes(int s[][]) {

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				s[i][j] = s_box[s[i][j]];
			}
		}
	}

	public void inv_sub_bytes(int s[][]) {

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				s[i][j] = inv_s_box[s[i][j]];
			}
		}
	}

	public void shift_A_row(int s[][], int amount, int row) {
		for (int j = 0; j < amount; j++) {
			int a = s[row][0];
			int i;
			for (i = 0; i < 3; i++)
				s[row][i] = s[row][i + 1];
			s[row][i] = a;
		}
	}

	public void shift_rows(int s[][]) {
		shift_A_row(s, 1, 1);
		shift_A_row(s, 2, 2);
		shift_A_row(s, 3, 3);
	}

	public void inv_shift_rows(int s[][]) {
		shift_A_row(s, 3, 1);
		shift_A_row(s, 2, 2);
		shift_A_row(s, 1, 3);
	}

	public void mix_columns(int[][] arr) {
		int[][] tarr = new int[4][4];
		for (int i = 0; i < 4; i++) {
			System.arraycopy(arr[i], 0, tarr[i], 0, 4);
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				arr[i][j] = mcHelper(tarr, galois, i, j);
			}
		}
	}

	public int mcHelper(int[][] arr, int[][] g, int i, int j) {
		int mcsum = 0;
		for (int k = 0; k < 4; k++) {
			int a = g[i][k];
			int b = arr[k][j];
			mcsum ^= mcCalc(a, b);
		}
		return mcsum;
	}

	public int mcCalc(int a, int b) {
		if (a == 1) {
			return b;
		} else if (a == 2) {
			return McTables.mc2[b / 16][b % 16];
		} else if (a == 3) {
			return McTables.mc3[b / 16][b % 16];
		}
		return 0;
	}

	public void inv_mix_columns(int[][] arr) {
		int[][] tarr = new int[4][4];
		for (int i = 0; i < 4; i++) {
			System.arraycopy(arr[i], 0, tarr[i], 0, 4);
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				arr[i][j] = invMcHelper(tarr, invgalois, i, j);
			}
		}
	}

	public int invMcHelper(int[][] arr, int[][] igalois, int i, int j) {
		int mcsum = 0;
		for (int k = 0; k < 4; k++) {
			int a = igalois[i][k];
			int b = arr[k][j];
			mcsum ^= invMcCalc(a, b);
		}
		return mcsum;
	}

	public int invMcCalc(int a, int b) {
		if (a == 9) {
			return McTables.mc9[b / 16][b % 16];
		} else if (a == 0xb) {
			return McTables.mc11[b / 16][b % 16];
		} else if (a == 0xd) {
			return McTables.mc13[b / 16][b % 16];
		} else if (a == 0xe) {
			return McTables.mc14[b / 16][b % 16];
		}
		return 0;
	}

	public void add_round_key(int s[][], int k[][], int start_column) {
		int row = 0;
		for (int x = start_column; x < start_column + 4; x++) {
			for (int y = 0; y < 4; y++) {
				s[row][y] ^= k[x][y];
			}
			row++;
		}
	}

	public int[][] bytes2matrix(byte[] text) {
		int rows = text.length / 4;
		int[][] temp = new int[rows][4];
		int z = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < 4; j++) {
				temp[i][j] = text[z] & 0xFF;
				z++;
			}
		}
		return temp;
	}

	public byte[] matrix2bytes(int[][] text) {

		byte[] temp = new byte[16];
		int z = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				temp[z] = (byte) text[i][j];
				z++;
			}
		}
		return temp;
	}

	public int[] xor_ints(int[] a, int[] b) {

		int[] temp = new int[a.length];
		for (int i = 0; i < a.length; i++) {

			temp[i] = (a[i] ^ b[i]);

		}
		return temp;
	}

	public byte[] pad(byte[] plaintext) {
		int padding_len = 0;
		int plainLlength = plaintext.length;
		padding_len = 16 - (plaintext.length % 16);

		int plainPadLen = plainLlength + padding_len;
		byte[] temp = new byte[plainPadLen];

		for (int i = 0; i < plainPadLen; i++) {
			if (i < plainLlength)
				temp[i] = plaintext[i];
			else
				temp[i] = (byte) padding_len;
		}
		String str_plan = new String(temp, StandardCharsets.UTF_8);
		System.out.println(str_plan);
		return temp;
	}

	public byte[] unpad(byte[] plaintext) {
		int plainLlength = plaintext.length;

		int padding_len = plaintext[plainLlength - 1];
		int origPlainLen = plainLlength - padding_len;
		byte[] temp = new byte[origPlainLen];

		for (int i = 0; i < origPlainLen; i++) {
			temp[i] = plaintext[i];
		}
		String str_plan = new String(temp, StandardCharsets.UTF_8);
		System.out.println(str_plan);
		return temp;
	}

	public byte[][] split_blocks(byte[] message) {
		int iterations = message.length / 16;
		byte[][] temp = new byte[iterations][16];
		byte[] block = new byte[16];

		int len = 0;
		for (int j = 0; j < iterations; j++) {
			block = Arrays.copyOfRange(message, len, len + 16);
			for (int i = 0; i < 16; i++) {
				temp[j][i] = block[i];
			}
			len = len + 16;
			block = null;
		}
		return temp;
	}

	public int[][] _expand_key(byte[] master_key) {
		int[][] key_columns = bytes2matrix(master_key);
		int iteration_size = (int) Math.floor(master_key.length / 4);
		int[] word = new int[4];
		int p = 1;
		int xornum = 8;

		for (int x = 0; x < key_columns.length; x++) {
			for (int y = 0; y < key_columns[0].length; y++) {
				key_columns_temp[x][y] = key_columns[x][y];
			}
		}
		while (xornum < 60) {
			int xornum2 = xornum - 8;
			int previousword_index = xornum - 1;
			for (int i = 0; i < 4; i++) {
				word[i] = (int) key_columns_temp[previousword_index][i];
			}
			if ((xornum % iteration_size) == 0) {
				int temp[] = new int[word.length];
				for (int x = 0; x < 4; x++) {
					temp[x] = s_box[word[x]];
				}
				for (int z = 0; z < temp.length; z++)
					word[z] = temp[z];
				word[0] = (word[0] ^ (byte) r_con[p]);
				p += 1;
			} else if (master_key.length == 32 && (xornum % iteration_size) == 4) {
				int temp2[] = new int[4];
				for (int x = 0; x < 4; x++)
					temp2[x] = s_box[word[x]];
				for (int z = 0; z < 4; z++)
					word[z] = temp2[z];
			}
			int[] key_column_word = new int[4];
			for (int x = 0; x < 4; x++) {
				key_column_word[x] = key_columns_temp[xornum2][x];
			}
			word = xor_ints(word, key_column_word);
			for (int x = 0; x < 4; x++) {
				key_columns_temp[xornum][x] = word[x];
			}
			int a = word[0];
			int i;
			for (i = 0; i < 3; i++)
				word[i] = word[i + 1];
			word[i] = a;
			xornum++;
		}
		return key_columns_temp;
	}

	public byte[] encrypt_block(byte[] plaintext) {
		String str_plan = new String(plaintext, StandardCharsets.UTF_8);
		System.out.println(str_plan);
		int[][] plain_state = new int[4][4];
		int num_roundkeyMetrices = 4;

		plain_state = bytes2matrix(plaintext);
		add_round_key(plain_state, key_columns_temp, 0);
		for (int i = 1; i < 14; i++) {
			sub_bytes(plain_state);
			shift_rows(plain_state);
			mix_columns(plain_state);
			add_round_key(plain_state, key_columns_temp, num_roundkeyMetrices);
			num_roundkeyMetrices += 4;
		}

		sub_bytes(plain_state);
		shift_rows(plain_state);
		add_round_key(plain_state, key_columns_temp, 56);
		String str_plan1 = new String(matrix2bytes(plain_state), StandardCharsets.UTF_8);

		System.out.println(str_plan1);
		return matrix2bytes(plain_state);

	}

	public byte[] decrypt_block(byte[] ciphertext) {
		int[][] cipher_state = new int[4][4];
		int num_roundkeyMetrices = 52;

		cipher_state = bytes2matrix(ciphertext);
		add_round_key(cipher_state, key_columns_temp, 56);
		inv_shift_rows(cipher_state);
		inv_sub_bytes(cipher_state);

		for (int i = 13; i > 0; i--) {
			add_round_key(cipher_state, key_columns_temp, num_roundkeyMetrices);
			inv_mix_columns(cipher_state);
			inv_shift_rows(cipher_state);
			inv_sub_bytes(cipher_state);
			num_roundkeyMetrices -= 4;
		}

		add_round_key(cipher_state, key_columns_temp, 0);

		return matrix2bytes(cipher_state);
	}

	public byte[] encrypt(byte[] plaintext) {
		plaintext = pad(plaintext);
		byte[] cipher = new byte[plaintext.length];
		byte[] temp = new byte[16];
		byte[][] split_blocks_temp = split_blocks(plaintext);
		int len_block = split_blocks_temp.length;
		int round = 0;
		for (int i = 0; i < len_block; i++) {
			temp = encrypt_block(split_blocks_temp[i]);
			for (int x = 0; x < 16; x++) {
				cipher[round] = temp[x];
				round++;
			}
		}
		return cipher;
	}

	public byte[] decrypt(byte[] cipher) {

		byte[] plaintext = new byte[cipher.length];
		byte[] temp = new byte[16];
		byte[][] split_blocks_temp = split_blocks(cipher);
		int len_block = split_blocks_temp.length;
		int round = 0;
		for (int i = 0; i < len_block; i++) {
			temp = decrypt_block(split_blocks_temp[i]);
			for (int x = 0; x < 16; x++) {
				plaintext[round] = temp[x];
				round++;
			}
		}
		plaintext = unpad(plaintext);

		return plaintext;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JPanel createPanel() {

		setTitle("AES Encryption - stackjava.com");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 909, 249);
		setResizable(false);
		setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		this.contentPane.setBackground(SystemColor.control);
		this.contentPane.setForeground(Color.WHITE);
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		this.lblKey = new JLabel("Key: ");
		this.lblKey.setBounds(10, 11, 46, 14);
		this.contentPane.add(this.lblKey);

		this.textField = new JTextField();
		this.textField.setBounds(70, 8, 170, 20);
		this.contentPane.add(this.textField);
		this.textField.setColumns(10);
		this.textField.setSize(300, 25);

		this.lblOriginalText = new JLabel("Original text: ");
		this.lblOriginalText.setBounds(10, 43, 76, 14);
		this.contentPane.add(this.lblOriginalText);

		this.textAreaOriginal = new JTextArea();
		this.textAreaOriginal.setLineWrap(true);
		this.textAreaOriginal.setBounds(10, 68, 200, 100);
		this.contentPane.add(this.textAreaOriginal);

		this.textAreaEncrypted = new JTextArea();
		this.textAreaEncrypted.setLineWrap(true);
		this.textAreaEncrypted.setBounds(337, 68, 200, 100);
		this.contentPane.add(this.textAreaEncrypted);

		this.lblEncryptedText = new JLabel("Encrypted text: ");
		this.lblEncryptedText.setBounds(337, 43, 95, 14);
		this.contentPane.add(this.lblEncryptedText);

		this.textAreaDycrypted = new JTextArea();
		this.textAreaDycrypted.setLineWrap(true);
		this.textAreaDycrypted.setBounds(678, 68, 200, 100);
		this.contentPane.add(this.textAreaDycrypted);

		this.lblDecryptedText = new JLabel("Decrypted text: ");
		this.lblDecryptedText.setBounds(678, 43, 105, 14);
		this.contentPane.add(this.lblDecryptedText);

		this.btnEncrypt = new JButton("Encrypt >>");
		this.btnEncrypt.setBounds(220, 80, 107, 23);
		this.buttonDecrypt = new JButton("Decrypt >>");
		this.buttonDecrypt.setBounds(558, 80, 107, 23);
		this.contentPane.add(this.buttonDecrypt);
		this.contentPane.add(this.btnEncrypt);
		this.btnEncrypt.addActionListener(this);
		this.buttonDecrypt.addActionListener(this);

		return this.contentPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PanelAES AES = new PanelAES();
		try {
			this.textField.getText().toString().getBytes("UTF8");
			if (this.textField.getText().toString().length() < 32
					|| this.textField.getText().toString().length() > 32) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập khóa gồm 32 ký tự");
				this.textField.requestFocus();
				return;
			}
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		byte text_arr[] = null;
		try {
			text_arr = this.textAreaOriginal.getText().toString().getBytes("UTF8");
			if (this.textAreaOriginal.getText().toString().length() <= 0) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin cần mã hóa");
				this.textAreaOriginal.requestFocus();
				return;
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		byte[] cipher = AES.encrypt(text_arr);

		byte[] plantext = AES.decrypt(cipher);

		Object o = e.getSource();
		if (o.equals(btnEncrypt)) {
			String str_ciph = new String(cipher, StandardCharsets.UTF_8);
			this.textAreaEncrypted.setText(str_ciph);
		}
		if (o.equals(buttonDecrypt)) {
			String str_plan = new String(plantext, StandardCharsets.UTF_8);
			this.textAreaDycrypted.setText(str_plan);
		}
	}
}