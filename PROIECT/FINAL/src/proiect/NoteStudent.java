package proiect;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

public class NoteStudent {

	private String url = "jdbc:mysql://localhost/proiect";
	private String user = "root";
	private String password = "17052002Da";
	private Connection con;

	private JFrame frameNS;
	private JButton button;
	private JTable table;

	public NoteStudent(String profil, String nume, String CNP) {
		connection();
		initialize(profil, nume, CNP);
	}

	private void initialize(String profil, String nume, String CNP) {
		frameNS = new JFrame();
		frameNS.setBounds(300, 300, 500, 500);
		frameNS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameNS.getContentPane().setLayout(null);

		button = new JButton("Back");
		button.setBounds(10, 427, 89, 23);
		button.addActionListener(e -> {
			frameNS.dispose();
			new DupaLoginS(profil, nume, CNP);
		});
		frameNS.getContentPane().add(button);

		// procente("PC");
		// tabel(nume, CNP);

		frameNS.setVisible(true);
	}

	/*
	 * private void tabel(String nume, String CNP) {
	 * 
	 * String[] coloane = { "Materie", "nota_curs", "nota_lab", "nota_sem", "medie"
	 * }; String[][] continut = afisNote(nume, CNP); if (continut != null) { table =
	 * new JTable(continut, coloane) {
	 * 
	 * @Override public boolean isCellEditable(int row, int column) { return false;
	 * } }; table.setBounds(10, 11, 445, 412); JScrollPane js = new
	 * JScrollPane(table); js.setBounds(0, 11, 484, 405);
	 * frameNS.getContentPane().add(js); } else JOptionPane.showMessageDialog(null,
	 * "Nu ai nicio nota pana acum!", "", JOptionPane.INFORMATION_MESSAGE); }
	 */
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

	private void afisNote(String nume, String CNP) {
		String[] np = nume.split(" ");

		try {
			FileWriter file = new FileWriter("D:\\Facultate\\Anul 2\\BD\\PROIECT\\" + nume + "_activitati.txt");

			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = s.executeQuery("call proiect.showNotes('" + np[0] + "','" + np[1] + "','" + CNP + "');");
			ResultSetMetaData rsmd = rs.getMetaData();

			int rows = 0;
			while (rs.next()) {
				rows++;
			}

			if (rows != 0)
				rs.beforeFirst();

			while (rs.next()) {
				// System.out.println(rs.getString("nume_curs"));
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					file.write(rs.getObject(i).toString() + " ");
					if (i % 2 == 0)
						file.write("\n");

				}
				file.write("\n");
			}
			file.write("\n");

			file.close();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
}

/*
 * private String[] procente(String materie) { String[] proc = new String[3];
 * try { Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
 * ResultSet.CONCUR_READ_ONLY); ResultSet rs =
 * s.executeQuery("call proiect.getProcente('" + materie + "');");
 * ResultSetMetaData rsmd = rs.getMetaData();
 * 
 * while (rs.next()) for (int i = 1; i <= rsmd.getColumnCount(); i++) proc[i -
 * 1] = rs.getObject(i).toString();
 * 
 * } catch (SQLException throwables) { throwables.printStackTrace(); } return
 * proc; }
 */
