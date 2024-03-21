package proiect;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Filtrare {

	private String url = "jdbc:mysql://localhost/proiect";
	private String user = "root";
	private String password = "17052002Da";
	private Connection con;

	JButton butonSend;
	JButton butonBack;
	JTextArea textArea;
	JTextArea textAreaMesaj;
	private JFrame frameMA;
	JTextField textField;
	JTextField textField_1;

	public Filtrare(String profil, String nume, String CNP) {
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
		butonBack.setBounds(0, 0, 80, 28);
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
		textArea.setName("Show");
		JScrollPane sp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(10, 45, 464, 262);
		// frameMA.add(textArea);
		frameMA.add(sp);

		frameMA.getContentPane().add(sp);

		frameMA.setSize(500, 500);
		frameMA.setBounds(300, 300, 500, 500);
		frameMA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMA.setVisible(true);
	}

	private void buttons(String profil, String nume, String CNP) {
		butonSend = new JButton("Show");
		butonSend.setFont(new Font("Times new ROman", Font.BOLD, 14));
		butonSend.setBounds(25, 380, 100, 28);
		butonSend.addActionListener(e -> {
			detaliiUseri();

		});
		frameMA.getContentPane().add(butonSend);
	}

	private void textFields(String profil, String nume, String CNP) {

		JLabel label = new JLabel("Tip user");
		label.setBounds(25, 320, 100, 28);
		label.setFont(new Font("times new roman", Font.ITALIC, 14));
		frameMA.getContentPane().add(label);
		textField = new JTextField("");
		textField.setColumns(10);
		textField.setBounds(25, 350, 100, 28);
		frameMA.getContentPane().add(textField);
		frameMA.setTitle("Cautare dupa tip user");

	}

	private void connection() {
		try { // Load driver class
			Class.forName("com.mysql.jdbc.Driver");
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

	private void detaliiUseri() {

		String[] det = new String[1000];
		try {
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = s.executeQuery(
					"SELECT nume,prenume,CNP,email,adresa,telefon,IBAN FROM proiect." + textField.getText());
			ResultSetMetaData rsmd = rs.getMetaData();
			int rows = 0;
			while (rs.next())
				rows++;
			if (rows == 0)
				JOptionPane.showMessageDialog(null, "Nu exista date!", "Atentie", JOptionPane.WARNING_MESSAGE);
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
				if (cont % 7 == 0) {
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
