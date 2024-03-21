package proiect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AsignareProfesor {

	private String url = "jdbc:mysql://localhost/proiect";
	private String user = "root";
	private String password = "17052002Da";
	private Connection con;

	private JFrame frameO;

	private JPanel panel;

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	private JLabel label_2;
	private JLabel label_1;
	private JLabel label;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;

	public AsignareProfesor(String profil, String nume, String CNP) {
		connection();
		initialize(profil, nume, CNP);
	}

	private void textFields() {
		// testFields
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(117, 46, 139, 30);
		panel.add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(117, 87, 139, 30);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(117, 128, 139, 30);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(117, 169, 139, 30);
		panel.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(117, 210, 139, 30);
		panel.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(117, 251, 139, 30);
		panel.add(textField_5);

	}

	private void labels(String profil) {

		label_1 = new JLabel("Nume curs:");
		label_1.setBounds(2, 46, 467, 37);
		label_1.setFont(new Font("times new roman", Font.ITALIC, 14));
		panel.add(label_1);

		label_2 = new JLabel("Nume profesor:");
		label_2.setBounds(2, 87, 464, 23);
		label_2.setFont(new Font("times new roman", Font.ITALIC, 14));
		panel.add(label_2);

		label_3 = new JLabel("Prenume profesor:");
		label_3.setBounds(2, 135, 464, 14);
		label_3.setFont(new Font("times new roman", Font.ITALIC, 14));
		panel.add(label_3);

		label_4 = new JLabel("Nr. minim de elevi:");
		label_4.setBounds(2, 176, 464, 14);
		label_4.setFont(new Font("times new roman", Font.ITALIC, 14));
		panel.add(label_4);

		label_5 = new JLabel("Nr. maxim de elevi:");
		label_5.setBounds(2, 217, 464, 14);
		label_5.setFont(new Font("times new roman", Font.ITALIC, 14));
		panel.add(label_5);

		label_6 = new JLabel("Departament:");
		label_6.setBounds(2, 258, 464, 14);
		label_6.setFont(new Font("times new roman", Font.ITALIC, 14));
		panel.add(label_6);
		panel.setBackground(new Color(255, 153, 51));

	}

	private void buttons(String profil, String nume, String CNP) {
		// buttons
		JButton button = new JButton("Asignare");
		button.setBounds(180, 400, 89, 23);
		button.setFont(new Font("times new roman", Font.BOLD, 14));
		panel.add(button);
		button.addActionListener(e -> profreg());

		JButton buttonBack = new JButton("Back");
		buttonBack.setFont(new Font("times new roman", Font.BOLD, 14));
		buttonBack.setBounds(0, 0, 89, 23);
		buttonBack.addActionListener(e -> {
			frameO.dispose();
			new DupaLoginA(profil, nume, CNP);
		});
		panel.add(buttonBack);
	}

	private void initialize(String profil, String nume, String CNP) {
		frameO = new JFrame("Asigneaza un profesor");
		frameO.setSize(500, 500);
		frameO.setBounds(300, 300, 500, 500);
		frameO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		frameO.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		textFields();
		labels("Student");

		buttons("Student", nume, CNP);

		frameO.setVisible(true);
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

	private void profreg() {
		try {
			int rows = 0;
			int rows1 = 0;
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = s.executeQuery("select * from proiect.informatii_profesor");
			while (rs.next())
				rows++;

			CallableStatement statement = con
					.prepareCall("{call proiect.insertinformatii_profesor(?,?,?,?,?,?,?,?,?,?)}");

			statement.setString(1, textField.getText().toString());
			statement.setString(2, textField_1.getText().toString());
			statement.setString(3, textField_2.getText().toString());
			statement.setString(4, textField_3.getText().toString());
			statement.setString(5, textField_4.getText().toString());
			statement.setString(6, textField_5.getText().toString());
			statement.setString(7, "");
			statement.setString(8, "");
			statement.setString(9, "");
			statement.setString(10, "");

			try {
				statement.execute();
				statement.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Nu s-a putut assigna profesorul la curs", "ATENTIE",
						JOptionPane.WARNING_MESSAGE);
			}

			rs = s.executeQuery("select * from proiect.informatii_profesor");
			while (rs.next())
				rows1++;

			if (rows1 == rows) {
				JOptionPane.showMessageDialog(null, "Nu s-a putut assigna profesorul la curs", "ATENTIE",
						JOptionPane.WARNING_MESSAGE);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
