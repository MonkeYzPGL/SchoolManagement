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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FerestreGrupStudiu {

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
	private JPanel panel;

	public FerestreGrupStudiu(String profil, String nume, String CNP) {
		connection();
		initialize(profil, nume, CNP);
	}

	private void initialize(String profil, String nume, String CNP) {
		frameMA = new JFrame();
		frameMA.getContentPane().setLayout(null);

		buttons(profil, nume, CNP);

		butonBack = new JButton("Back");
		butonBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		butonBack.setBounds(10, 405, 117, 45);
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
		// frameMA.add(textArea);
		frameMA.add(sp);

		frameMA.getContentPane().add(sp);

		frameMA.setSize(500, 500);
		frameMA.setBounds(300, 300, 500, 500);
		frameMA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMA.setVisible(true);
	}

	private void buttons(String profil, String nume, String CNP) {
		butonSend = new JButton("Afiseaza");
		butonSend.setFont(new Font("Tahoma", Font.PLAIN, 14));
		butonSend.setBounds(357, 405, 117, 45);
		butonSend.addActionListener(e -> {
			ferestre();

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

	private void ferestre() {

		String[] det = new String[1000];
		try {
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = s.executeQuery(
					"select nume_curs,zi_curs, ora_curs, zi_seminar, ora_sem, zi_lab, ora_lab from cursuri,activitati where activitati.idcurs=cursuri.idcurs;");
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

			for (String z : det) {
				if (z != null) {
					text = text + " " + z + "\n ";

				}

			}
			text = text + "\n" + "Orice activitate programata ar trebui sa nu se suprapuna cu activitatile de mai sus";

			textArea.setText(text);

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}
}
