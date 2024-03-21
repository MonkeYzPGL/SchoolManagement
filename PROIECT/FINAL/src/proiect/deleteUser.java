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

public class deleteUser {

	private String url = "jdbc:mysql://localhost/proiect";
	private String user = "root";
	private String password = "17052002Da";
	private Connection con;

	private JFrame frameO;

	private JPanel panel;

	private JTextField textField;
	private JTextField textField_1;

	private JLabel label;
	private JLabel label_1;

	public deleteUser(String profil, String nume, String CNP) {
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

		panel.setBackground(new Color(153, 153, 255));
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
	}

	private void buttons(String profil, String nume, String CNP) {
		// buttons
		JButton button = new JButton("Update");
		button.setBounds(200, 427, 89, 23);
		panel.add(button);
		button.addActionListener(e -> delete());

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
		frameO.setTitle("Delete User");
		frameO.setVisible(true);
	}

	private void delete() {
		try {
			String t = textField.getText();
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			if (t.equals("profesor")) {
				CallableStatement statement = con.prepareCall("{call proiect.deleteprofesor(?)}");

				statement.setString(1, textField_1.getText().toString());

				statement.execute();
				statement.close();
			}

			else if (t.equals("student")) {
				CallableStatement statement = con.prepareCall("{call proiect.deletestudent(?)}");

				statement.setString(1, textField_1.getText().toString());

				statement.execute();
				statement.close();
			}

			else if (t.equals("administrator")) {
				CallableStatement statement = con.prepareCall("{call proiect.deletedmini(?)}");

				statement.setString(1, textField_1.getText().toString());

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
