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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CautaCurs {

	private String url = "jdbc:mysql://127.0.0.1:3306/proiect";
	private String user = "root";
	private String password = "17052002Da";
	private Connection con;

	JComboBox comboBox;
	JButton butonSend;
	JButton butonBack;
	JButton butonSearch;
	JTextArea textArea;
	JTextArea textAreaMesaj;
	private JFrame frameMA;
	JTextField textField;
	JTextField textField_1;
	private JPanel panel;

	public CautaCurs(String profil, String nume, String CNP) {
		connection();
		initialize(profil, nume, CNP);
	}

	private void initialize(String profil, String nume, String CNP) {
		frameMA = new JFrame("Cauta un curs");
		frameMA.getContentPane().setLayout(null);

		buttons(profil, nume, CNP);
		textFields(profil, nume, CNP);

		butonBack = new JButton("Back");
		butonBack.setFont(new Font("Times new roman", Font.BOLD, 14));
		butonBack.setBounds(0, 0, 70, 25);
		butonBack.addActionListener(e -> {
			if (profil == "Student") {
				frameMA.dispose();
				new DupaLoginS(profil, nume, CNP);
			} else if (profil == "Profesor") {
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
		sp.setBounds(10, 45, 464, 222);
		// frameMA.add(textArea);
		frameMA.add(sp);

		frameMA.getContentPane().add(sp);

		frameMA.setSize(500, 500);
		frameMA.setBounds(300, 300, 500, 500);
		frameMA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMA.setVisible(true);
	}

	private void buttons(String profil, String nume, String CNP) {
		butonSend = new JButton("Afiseaza Profesorul");
		butonSend.setFont(new Font("times new roman", Font.BOLD, 14));
		butonSend.setBounds(2, 360, 160, 25);
		butonSend.addActionListener(e -> {
			cautaCursul();

		});
		frameMA.getContentPane().add(butonSend);

		butonSearch = new JButton("Afiseaza Studentii");
		butonSearch.setFont(new Font("Times new roman", Font.BOLD, 14));
		butonSearch.setBounds(2, 400, 160, 25);
		butonSearch.addActionListener(e -> {
			seeStudent();

		});
		frameMA.getContentPane().add(butonSearch);
	}

	private void textFields(String profil, String nume, String CNP) {

		JLabel label = new JLabel("Curs:");
		label.setFont(new Font("times new roman", Font.ITALIC, 14));
		label.setBounds(2, 320, 130, 25);
		frameMA.getContentPane().add(label);
		textField = new JTextField("");
		textField.setColumns(10);
		textField.setBounds(50, 320, 130, 25);
		frameMA.getContentPane().add(textField);

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

	private void cautaCursul() {

		String[] det = new String[1000];
		try {
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = s.executeQuery(
					"SELECT  nume_curs, nume, prenume  from cursuri, profesor, informatii_profesor where informatii_profesor.idprofesor=profesor.idprofesor and cursuri.idcurs=informatii_profesor.idcurs and cursuri.nume_curs='"
							+ textField.getText() + "';");
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
			int contor = 0;
			for (String z : det)

				if (z != null) {
					text = text + " " + z + " ";
					contor++;
				}
			if (contor == 2) {
				text = text + "\n";
			}

			textArea.setText(text);

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}

	private void seeStudent() {

		String[] det = new String[1000];
		try {
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = s.executeQuery("call proiect.showStudentToACourse('" + textField.getText() + "');");
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
			int contor = 0;
			for (String z : det) {

				if (z != null) {
					text = text + " " + z + " ";
					contor++;

				}
				if (contor % 2 == 0) {
					text = text + "\n";
				}
			}

			textArea.setText(text);

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}
}
