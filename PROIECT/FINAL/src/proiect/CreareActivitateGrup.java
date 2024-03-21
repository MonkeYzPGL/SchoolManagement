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

public class CreareActivitateGrup {

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

	private JLabel label_2;
	private JLabel label_1;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;

	public CreareActivitateGrup(String profil, String nume, String CNP) {
		connection();
		initialize(profil, nume, CNP);
	}

	private void textFields() {
		// testFields
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(130, 46, 139, 30);
		panel.add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(130, 87, 139, 30);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(130, 128, 139, 30);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(130, 169, 139, 30);
		panel.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(130, 210, 139, 30);
		panel.add(textField_4);

	}

	private void labels(String profil) {

		label_1 = new JLabel("Nume grup:");
		label_1.setBounds(10, 53, 467, 14);
		label_1.setFont(new Font("times new roman", Font.ITALIC, 14));
		panel.add(label_1);

		label_2 = new JLabel("Nume activitate:");
		label_2.setBounds(10, 94, 464, 14);
		label_2.setFont(new Font("times new roman", Font.ITALIC, 14));
		panel.add(label_2);

		label_3 = new JLabel("Data desfasurarii:");
		label_3.setBounds(10, 135, 464, 14);
		label_3.setFont(new Font("times new roman", Font.ITALIC, 14));
		panel.add(label_3);

		label_4 = new JLabel("Ora expirarii:");
		label_4.setBounds(10, 176, 464, 14);
		label_4.setFont(new Font("times new roman", Font.ITALIC, 14));
		panel.add(label_4);

		label_5 = new JLabel("Nr minim de elevi:");
		label_5.setBounds(10, 217, 464, 14);
		label_5.setFont(new Font("times new roman", Font.ITALIC, 14));
		panel.add(label_5);
		panel.setBackground(new Color(102, 102, 255));

	}

	private void buttons(String profil, String nume, String CNP) {
		// buttons
		JButton button = new JButton("Creare");
		button.setBounds(200, 427, 100, 23);
		button.setFont(new Font("times new roman", Font.BOLD, 14));
		panel.add(button);
		button.addActionListener(e -> activitate());

		JButton buttonBack = new JButton("Back");

		buttonBack.setBounds(0, 0, 89, 23);
		buttonBack.addActionListener(e -> {
			frameO.dispose();
			new DupaLoginS(profil, nume, CNP);
		});
		buttonBack.setFont(new Font("times new roman", Font.BOLD, 14));
		panel.add(buttonBack);
	}

	private void initialize(String profil, String nume, String CNP) {
		frameO = new JFrame("Creare activitate grup");
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

	private void activitate() {
		try {
			int rows = 0;
			int rows1 = 0;
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = s.executeQuery("select * from proiect.activ_grup");
			while (rs.next())
				rows++;

			CallableStatement statement = con.prepareCall("{call proiect.createActivityGroup(?,?,?,?,?)}");

			statement.setString(1, textField.getText().toString());
			statement.setString(2, textField_1.getText().toString());
			statement.setString(3, textField_2.getText().toString());
			statement.setString(4, textField_3.getText().toString());
			statement.setString(5, textField_4.getText().toString());

			try {
				statement.execute();
				statement.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Nu ai putut sa creezi activititatea!", "ATENTIE",
						JOptionPane.WARNING_MESSAGE);
			}

			rs = s.executeQuery("select * from proiect.activ_grup");
			while (rs.next())
				rows1++;

			if (rows1 == rows) {
				JOptionPane.showMessageDialog(null, "Nu ai putut sa creezi activititatea!", "ATENTIE",
						JOptionPane.WARNING_MESSAGE);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
