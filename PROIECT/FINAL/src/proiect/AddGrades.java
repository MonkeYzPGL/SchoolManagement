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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddGrades {

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

	public AddGrades(String profil, String nume, String CNP) {
		connection();
		initialize(profil, nume, CNP);
	}

	private void textFields() {
		// testFields
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(120, 89, 139, 30);
		panel.add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(120, 130, 139, 30);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(120, 171, 139, 30);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(120, 212, 139, 30);
		panel.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(120, 253, 139, 30);
		panel.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(120, 294, 139, 30);
		panel.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(120, 335, 139, 30);
		panel.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(120, 376, 139, 30);
		panel.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(120, 417, 139, 30);
		panel.add(textField_8);

	}

	private void labels(String profil) {
		label_1 = new JLabel("Nume student:");
		label_1.setBounds(0, 89, 467, 37);
		label_1.setFont(new Font("Times new roman", Font.ITALIC, 14));
		panel.add(label_1);

		label_2 = new JLabel("Prenume student:");
		label_2.setBounds(0, 130, 464, 23);
		label_2.setFont(new Font("Times new roman", Font.ITALIC, 14));
		panel.add(label_2);

		label_3 = new JLabel("CNP:");
		label_3.setBounds(0, 171, 464, 14);
		label_3.setFont(new Font("Times new roman", Font.ITALIC, 14));
		panel.add(label_3);

		label_4 = new JLabel("Nume curs:");
		label_4.setBounds(0, 212, 464, 14);
		label_4.setFont(new Font("Times new roman", Font.ITALIC, 14));
		panel.add(label_4);

		label_5 = new JLabel("Nume profesor:");
		label_5.setBounds(0, 253, 464, 14);
		label_5.setFont(new Font("Times new roman", Font.ITALIC, 14));
		panel.add(label_5);

		label_6 = new JLabel("Prenume profesor:");
		label_6.setBounds(0, 294, 464, 14);
		label_6.setFont(new Font("Times new roman", Font.ITALIC, 14));
		panel.add(label_6);

		label_7 = new JLabel("Nota laborator:");
		label_7.setBounds(0, 335, 464, 14);
		label_7.setFont(new Font("Times new roman", Font.ITALIC, 14));
		panel.add(label_7);

		label_8 = new JLabel("Nota seminar:");
		label_8.setBounds(0, 376, 464, 14);
		label_8.setFont(new Font("Times new roman", Font.ITALIC, 14));
		panel.add(label_8);

		label_9 = new JLabel("Nota curs:");
		label_9.setBounds(0, 417, 464, 14);
		label_9.setFont(new Font("Times new roman", Font.ITALIC, 14));
		panel.add(label_9);

	}

	private void buttons(String profil, String nume, String CNP) {
		// buttons
		JButton button = new JButton("Adauga nota");
		button.setBounds(300, 250, 120, 23);
		panel.add(button);
		button.addActionListener(e -> grades());

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
		frameO = new JFrame("Adaugare nota ");
		frameO.setSize(500, 500);
		frameO.setBounds(300, 300, 500, 500);
		frameO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		frameO.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		// textField = new JTextField("");
		// textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		// textField.setBounds(164, 77, 169, 33);
		// panel.add(textField);
		// textField.setColumns(10);

		textFields();
		labels("Profesor");

		buttons("Profesor", nume, CNP);

		panel.setBackground(new Color(7, 149, 2));

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

	private void grades() {
		try {
			int rows = 0;
			int rows1 = 0;
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = s.executeQuery("select * from proiect.note_student");
			while (rs.next())
				rows++;

			CallableStatement statement = con.prepareCall("{call proiect.addGrades(?,?,?,?,?,?,?,?,?)}");

			statement.setString(1, textField.getText().toString());
			statement.setString(2, textField_1.getText().toString());
			statement.setString(3, textField_2.getText().toString());
			statement.setString(4, textField_3.getText().toString());
			statement.setString(5, textField_4.getText().toString());
			statement.setString(6, textField_5.getText().toString());
			statement.setInt(7, Integer.parseInt(textField_6.getText().toString()));
			statement.setInt(8, Integer.parseInt(textField_7.getText().toString()));
			statement.setInt(9, Integer.parseInt(textField_8.getText().toString()));

			statement.execute();
			statement.close();
			rs = s.executeQuery("select * from proiect.note_student");
			while (rs.next())
				rows1++;

			if (rows1 == rows) {
				JOptionPane.showMessageDialog(null, "Studentul nu este inscris la curs!", "ATENTIE",
						JOptionPane.WARNING_MESSAGE);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Nu s-a putut efectua operatiunea!", "WARNING",
					JOptionPane.PLAIN_MESSAGE);
			ex.printStackTrace();
		}
	}

}
