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

public class InscriereGrup {

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

	private JLabel label_2;
	private JLabel label_1;
	private JLabel label;
	private JLabel label_3;
	private JLabel label_4;

	public InscriereGrup(String profil, String nume, String CNP) {
		connection();
		initialize(profil, nume, CNP);
	}

	private void textFields() {

		this.textField = new JTextField();
		this.textField.setColumns(10);
		this.textField.setBounds(75, 46, 139, 30);
		this.panel.add(this.textField);

		this.textField_1 = new JTextField();
		this.textField_1.setColumns(10);
		this.textField_1.setBounds(75, 87, 139, 30);
		this.panel.add(this.textField_1);

		this.textField_2 = new JTextField();
		this.textField_2.setColumns(10);
		this.textField_2.setBounds(75, 128, 139, 30);
		this.panel.add(this.textField_2);

		this.textField_3 = new JTextField();
		this.textField_3.setColumns(10);
		this.textField_3.setBounds(75, 169, 139, 30);
		this.panel.add(this.textField_3);

	}

	private void labels(String profil) {
		this.label_1 = new JLabel("Nume:");
		this.label_1.setBounds(2, 46, 467, 37);
		label_1.setFont(new Font("Times new roman", Font.ITALIC, 14));
		this.panel.add(this.label_1);

		this.label_2 = new JLabel("Prenume:");
		this.label_2.setBounds(2, 94, 464, 23);
		label_2.setFont(new Font("Times new roman", Font.ITALIC, 14));
		this.panel.add(this.label_2);

		this.label_3 = new JLabel("CNP:");
		this.label_3.setBounds(2, 137, 464, 14);
		label_3.setFont(new Font("Times new roman", Font.ITALIC, 14));
		this.panel.add(this.label_3);

		this.label_4 = new JLabel("Nume grup:");
		this.label_4.setBounds(2, 179, 464, 14);
		label_4.setFont(new Font("Times new roman", Font.ITALIC, 14));
		this.panel.add(this.label_4);
		panel.setBackground(new Color(127, 0, 255));

	}

	private void buttons(String profil, String nume, String CNP) {
		// buttons
		JButton button = new JButton("Register");
		button.setBounds(200, 427, 100, 23);
		panel.add(button);
		button.addActionListener(e -> studRegToGroup());

		JButton buttonBack = new JButton("Back");
		buttonBack.setBounds(0, 0, 89, 23);
		buttonBack.addActionListener(e -> {
			frameO.dispose();
			new DupaLoginS(profil, nume, CNP);
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
		labels("Student");

		buttons("Student", nume, CNP);
		frameO.setTitle("Inscriere Grup");
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

	private void studRegToGroup() {
		try {
			int rows = 0;
			int rows1 = 0;
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = s.executeQuery("select * from proiect.relatii_grup");
			while (rs.next())
				rows++;

			CallableStatement statement = con.prepareCall("{call proiect.studentGroup(?,?,?,?)}");

			statement.setString(1, textField.getText().toString());
			statement.setString(2, textField_1.getText().toString());
			statement.setString(3, textField_2.getText().toString());
			statement.setString(4, textField_3.getText().toString());

			try {
				statement.execute();
				statement.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Nu te poti inscrie la acest grup", "ATENTIE",
						JOptionPane.WARNING_MESSAGE);
			}
			rs = s.executeQuery("select * from proiect.relatii_grup");
			while (rs.next())
				rows1++;

			if (rows1 == rows) {
				JOptionPane.showMessageDialog(null, "Nu te poti inscrie la acest grup", "ATENTIE",
						JOptionPane.WARNING_MESSAGE);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
