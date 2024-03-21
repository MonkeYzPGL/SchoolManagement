package proiect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddProcente {

	private String url = "jdbc:mysql://localhost/proiect";
	private String user = "root";
	private String password = "17052002Da";
	private Connection con;

	private JFrame frameO;

	private JPanel panel;

	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	private JLabel label_2;
	private JLabel label_1;
	private JLabel label;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;

	public AddProcente(String profil, String nume, String CNP) {
		connection();
		initialize(profil, nume, CNP);
	}

	private void textFields() {
		// testFields
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(150, 51, 139, 30);
		panel.add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(150, 92, 139, 30);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(150, 133, 139, 30);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(150, 174, 139, 30);
		panel.add(textField_3);

	}

	private void labels(String profil) {

		label_1 = new JLabel("Numele cursului:");
		label_1.setBounds(0, 50, 467, 37);
		panel.add(label_1);

		label_2 = new JLabel("Procent pentru curs:");
		label_2.setBounds(0, 91, 464, 23);
		panel.add(label_2);

		label_3 = new JLabel("Procent pentru seminar:");
		label_3.setBounds(0, 132, 464, 14);
		panel.add(label_3);

		label_4 = new JLabel("Procent pentru laborator:");
		label_4.setBounds(0, 173, 464, 14);
		panel.add(label_4);

	}

	private void buttons(String profil, String nume, String CNP) {
		// buttons
		JButton button = new JButton("Adauga procentele");
		button.setBounds(120, 220, 150, 23);
		panel.add(button);
		button.addActionListener(e -> procente());

		JButton buttonBack = new JButton("Back");
		buttonBack.setFont(new Font("Times new Roman", Font.BOLD, 16));
		buttonBack.setBounds(0, 0, 80, 23);
		buttonBack.addActionListener(e -> {
			frameO.dispose();
			if (profil == "Student")
				new DupaLoginS(profil, nume, CNP);
			else
				new DupaLoginP(profil, nume, CNP);
		});
		panel.add(buttonBack);
	}

	private void initialize(String profil, String nume, String CNP) {
		frameO = new JFrame("Procente pentru activitati");
		frameO.setSize(500, 500);
		frameO.setBounds(300, 300, 500, 500);
		frameO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		frameO.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		textFields();
		labels("Profesor");

		buttons("Profesor", nume, CNP);
		panel.setBackground(new Color(155, 155, 59));

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

	private void procente() {
		try {

			CallableStatement statement = con.prepareCall("{call proiect.updateactivitatiproc(?,?,?,?)}");

			statement.setString(1, textField.getText().toString());
			statement.setInt(2, Integer.parseInt(textField_1.getText()));
			statement.setInt(3, Integer.parseInt(textField_2.getText()));
			statement.setInt(4, Integer.parseInt(textField_3.getText()));

			try {
				statement.execute();
				statement.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Nu s-au putut adauga procentele la aceasta materie!", "ATENTIE",
						JOptionPane.WARNING_MESSAGE);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Nu s-au putut adauga procentele la aceasta materie!", "ATENTIE",
					JOptionPane.WARNING_MESSAGE);
		}

	}

}
