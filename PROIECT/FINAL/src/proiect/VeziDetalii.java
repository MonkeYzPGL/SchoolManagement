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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VeziDetalii {

	private String url = "jdbc:mysql://localhost/proiect";
	private String user = "root";
	private String password = "17052002Da";
	private Connection con;

	JButton butonSend;
	JButton butonBack;
	JTextArea textArea;
	JTextArea textAreaMesaj;
	private JFrame frameMA;

	public VeziDetalii(String profil, String nume, String CNP) {
		connection();
		initialize(profil, nume, CNP);
	}

	private void initialize(String profil, String nume, String CNP) {
		frameMA = new JFrame("Date personale");
		frameMA.getContentPane().setLayout(null);

		buttons(profil, nume, CNP);

		butonBack = new JButton("Back");
		butonBack.setFont(new Font("Times new Roman", Font.BOLD, 14));
		butonBack.setBounds(0, 0, 80, 23);
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
		textArea = new JTextArea("INFO");
		textArea.setFont(new Font("Times new roman", Font.ITALIC, 14));
		textArea.setEditable(false);
		JScrollPane sp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(10, 45, 464, 150);
		// frameMA.add(textArea);
		frameMA.add(sp);

		frameMA.getContentPane().add(sp);

		frameMA.setSize(500, 500);
		frameMA.setBounds(300, 300, 500, 500);
		frameMA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMA.setVisible(true);
	}

	private void buttons(String profil, String nume, String CNP) {
		butonSend = new JButton("SHOW");
		butonSend.setFont(new Font("Times new roman", Font.BOLD, 14));
		butonSend.setBounds(200, 427, 80, 23);
		butonSend.addActionListener(e -> {
			detalii(profil, nume, CNP);

		});
		frameMA.getContentPane().add(butonSend);
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

	private void detalii(String tabela, String nume, String CNP) {
		String[] np = nume.split(" ");
		String[] det = new String[1000];
		try {
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			if (tabela.equals("Super-administrator"))
				tabela = "super_administrator";
			ResultSet rs = s.executeQuery("SELECT CNP,nume,prenume,adresa,telefon,email,IBAN,nr_contract FROM proiect."
					+ tabela.toLowerCase() + " WHERE nume = '" + np[0] + "' AND prenume = '" + np[1] + "' AND CNP = '"
					+ CNP + "' AND nume is not NULL " + ";");
			ResultSetMetaData rsmd = rs.getMetaData();
			int rows = 0;
			while (rs.next())
				rows++;
			if (rows == 0)
				System.out.println("Nu exista date");
			else {
				int contor = -1;
				rs.beforeFirst();
				while (rs.next())
					for (int i = 1; i <= rsmd.getColumnCount(); i++)
						det[++contor] = rs.getObject(i).toString();
			}
			String text = new String();
			text = text + "Nume: " + det[1] + " " + det[2] + "\n";
			text = text + "CNP: " + det[0] + "\n";
			text = text + "Adresa: " + det[3] + "\n";
			text = text + "Telefon: " + det[4] + "\n";
			text = text + "Email: " + det[5] + "\n";
			text = text + "IBAN: " + det[6] + "\n";
			text = text + "Numar_contract: " + det[7] + "\n";
			textArea.setText(text);

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}
}
