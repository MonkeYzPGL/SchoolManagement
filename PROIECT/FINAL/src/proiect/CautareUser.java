package proiect;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CautareUser {

	private String url = "jdbc:mysql://localhost/proiect";
	private String user = "root";
	private String password = "17052002Da";
	private Connection con;

	JComboBox comboBox;
	JButton butonSend;
	JButton butonBack;
	JTextArea textArea;
	JTextArea textAreaMesaj;
	private JFrame frameMA;
	JTextField textField;
	JTextField textField_1;
	JTextField textField_2;
	private JPanel panel;

	public CautareUser(String profil, String nume, String CNP) {
		connection();
		initialize(profil, nume, CNP);
	}

	private void initialize(String profil, String nume, String CNP) {
		frameMA = new JFrame();
		frameMA.getContentPane().setLayout(null);

		buttons(profil, nume, CNP);
		textFields(profil, nume, CNP);

		butonBack = new JButton("Back");
		butonBack.setFont(new Font("Times new roman", Font.BOLD, 14));
		butonBack.setBounds(0, 0, 80, 25);
		butonBack.addActionListener(e -> {
			if (profil.equals("Student")) {
				frameMA.dispose();
				new DupaLoginS(profil, nume, CNP);
			} else if (profil.equals("Profesor")) {
				frameMA.dispose();
				new DupaLoginP(profil, nume, CNP);
			} else {
				frameMA.dispose();
				new DupaLoginA(profil, nume, CNP);
			}
		});
		frameMA.getContentPane().add(butonBack);

		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane sp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(10, 45, 464, 262);
		frameMA.add(sp);

		frameMA.getContentPane().add(sp);

		frameMA.setSize(500, 500);
		frameMA.setBounds(300, 300, 500, 500);
		frameMA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMA.setVisible(true);
	}

	private void buttons(String profil, String nume, String CNP) {
		butonSend = new JButton("Search");
		butonSend.setFont(new Font("Times new ROman", Font.BOLD, 14));
		butonSend.setBounds(190, 410, 100, 25);
		butonSend.addActionListener(e -> {
			cautareUseri();

		});
		frameMA.getContentPane().add(butonSend);
	}

	private void textFields(String profil, String nume, String CNP) {

		JLabel label = new JLabel("Tip user: ");
		label.setBounds(20, 320, 130, 28);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		frameMA.getContentPane().add(label);

		textField = new JTextField("");
		textField.setColumns(10);
		textField.setBounds(30, 360, 130, 28);
		frameMA.getContentPane().add(textField);

		JLabel label1 = new JLabel("Nume");
		label1.setBounds(170, 320, 130, 28);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		frameMA.getContentPane().add(label1);

		textField_1 = new JTextField("");
		textField_1.setColumns(10);
		textField_1.setBounds(175, 360, 130, 28);
		frameMA.getContentPane().add(textField_1);

		JLabel label2 = new JLabel("Prenume: ");
		label2.setBounds(320, 320, 130, 28);
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		frameMA.getContentPane().add(label2);

		textField_2 = new JTextField("");
		textField_2.setColumns(10);
		textField_2.setBounds(320, 360, 130, 28);
		frameMA.getContentPane().add(textField_2);

	}

	private void connection() {
		try { // Load driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println(e);
		}

		con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex);
			System.exit(1);
		}
	}

	private void cautareUseri() {

		String[] det = new String[1000];
		try {
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String aux1 = textField_1.getText();
			String aux2 = textField_2.getText();
			ResultSet rs = s.executeQuery(
					"SELECT CNP,nume,prenume,adresa,telefon,email,IBAN,nr_contract FROM proiect." + textField.getText()
							+ " WHERE nume = '" + aux1 + "' AND prenume = '" + aux2 + "' AND nume is not NULL " + ";");
			ResultSetMetaData rsmd = rs.getMetaData();
			int rows = 0;
			while (rs.next())
				rows++;
			if (rows == 0)
				JOptionPane.showMessageDialog(null, "Nu exista date", "Atentie", JOptionPane.WARNING_MESSAGE);
			else {
				int contor = -1;
				rs.beforeFirst();
				while (rs.next())
					for (int i = 1; i <= rsmd.getColumnCount(); i++)
						det[++contor] = rs.getObject(i).toString();
			}
			String text = new String();
			Integer cont = 0;
			for (String z : det) {
				if (cont % 8 == 0) {
					text = text + " \n";
					cont = 0;
				}
				cont++;
				if (z != null)
					text = text + " " + z + " ";
			}

			textArea.setText(text);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
