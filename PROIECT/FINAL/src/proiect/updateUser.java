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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class updateUser {

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
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;

	public updateUser(String profil, String nume, String CNP) {
		connection();
		initialize(profil, nume, CNP);
	}

	private void textFields() {
		// testFields
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(90, 39, 139, 30);
		panel.add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(90, 80, 139, 30);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(90, 121, 139, 30);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(90, 162, 139, 30);
		panel.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(90, 203, 139, 30);
		panel.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(90, 244, 139, 30);
		panel.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(90, 285, 139, 30);
		panel.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(90, 326, 139, 30);
		panel.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(90, 367, 139, 30);
		panel.add(textField_8);

		panel.setBackground(new Color(255, 255, 153));
	}

	private void labels(String profil) {

		label = new JLabel("Profil:");
		label.setFont(new Font("times new roman", Font.ITALIC, 14));
		label.setBounds(2, 46, 467, 23);
		panel.add(label);

		label_1 = new JLabel("CNP:");
		label_1.setFont(new Font("Times new roman", Font.ITALIC, 14));
		label_1.setBounds(2, 87, 467, 23);
		panel.add(label_1);

		label_2 = new JLabel("Nume:");
		label_2.setFont(new Font("Times new roman", Font.ITALIC, 14));
		label_2.setBounds(2, 128, 464, 23);
		panel.add(label_2);

		label_3 = new JLabel("Prenume:");
		label_3.setFont(new Font("Times new roman", Font.ITALIC, 14));
		label_3.setBounds(2, 169, 464, 14);
		panel.add(label_3);

		label_4 = new JLabel("Adresa:");
		label_4.setFont(new Font("Times new roman", Font.ITALIC, 14));
		label_4.setBounds(2, 210, 464, 14);
		panel.add(label_4);

		label_5 = new JLabel("Telefon:");
		label_5.setFont(new Font("Times new roman", Font.ITALIC, 14));
		label_5.setBounds(2, 251, 464, 14);
		panel.add(label_5);

		label_6 = new JLabel("Email:");
		label_6.setFont(new Font("Times new roman", Font.ITALIC, 14));
		label_6.setBounds(2, 292, 464, 14);
		panel.add(label_6);

		label_7 = new JLabel("IBAN:");
		label_7.setFont(new Font("Times new roman", Font.ITALIC, 14));
		label_7.setBounds(2, 333, 464, 14);
		panel.add(label_7);

		label_8 = new JLabel("Nr_contract:");
		label_8.setFont(new Font("Times new roman", Font.ITALIC, 14));
		label_8.setBounds(2, 374, 464, 14);
		panel.add(label_8);

	}

	private void buttons(String profil, String nume, String CNP) {
		// buttons
		JButton button = new JButton("Update");
		button.setBounds(200, 427, 89, 23);
		panel.add(button);
		button.addActionListener(e -> update());

		JButton buttonBack = new JButton("Back");

		buttonBack.setBounds(0, 0, 89, 23);
		buttonBack.addActionListener(e -> {
			frameO.dispose();
			new DupaLoginA(profil, nume, CNP);
		});
		panel.add(buttonBack);
	}

	private void initialize(String profil, String nume, String CNP) {
		frameO = new JFrame();
		frameO.setSize(500, 500);
		frameO.setBounds(300, 300, 500, 500);
		frameO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		frameO.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		textFields();
		labels("Administrator");

		buttons("Administrator", nume, CNP);
		frameO.setTitle("Update User");
		frameO.setVisible(true);
	}

	private void update() {
		try {
			String t = textField.getText();
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			if (t.equals("profesor")) {
				CallableStatement statement = con.prepareCall("{call proiect.updateprofesor(?,?,?,?,?,?,?,?)}");

				statement.setString(1, textField_1.getText().toString());
				statement.setString(2, textField_2.getText().toString());
				statement.setString(3, textField_3.getText().toString());
				statement.setString(4, textField_4.getText().toString());
				statement.setString(5, textField_5.getText().toString());
				statement.setString(6, textField_6.getText().toString());
				statement.setString(7, textField_7.getText().toString());
				statement.setString(8, textField_8.getText().toString());
				statement.execute();
				statement.close();
			}

			else if (t.equals("student")) {
				CallableStatement statement = con.prepareCall("{call proiect.updatestudent(?,?,?,?,?,?,?,?)}");

				statement.setString(1, textField_1.getText().toString());
				statement.setString(2, textField_2.getText().toString());
				statement.setString(3, textField_3.getText().toString());
				statement.setString(4, textField_4.getText().toString());
				statement.setString(5, textField_5.getText().toString());
				statement.setString(6, textField_6.getText().toString());
				statement.setString(7, textField_7.getText().toString());
				statement.setString(8, textField_8.getText().toString());
				statement.execute();
				statement.close();
			}

			else if (t.equals("administrator")) {
				CallableStatement statement = con.prepareCall("{call proiect.updateadministrator(?,?,?,?,?,?,?,?)}");

				statement.setString(1, textField_1.getText().toString());
				statement.setString(2, textField_2.getText().toString());
				statement.setString(3, textField_3.getText().toString());
				statement.setString(4, textField_4.getText().toString());
				statement.setString(5, textField_5.getText().toString());
				statement.setString(6, textField_6.getText().toString());
				statement.setString(7, textField_7.getText().toString());
				statement.setString(8, textField_8.getText().toString());
				statement.execute();
				statement.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
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
}
