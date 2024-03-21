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

public class addProf {

	private String url = "jdbc:mysql://localhost/proiect";
	private String user = "root";
	private String password = "17052002Da";
	private Connection con;

	private JFrame frameO;

	private JTextField textField;
	private JLabel label_1;

	private JPanel panel;

	public addProf(String profil, String nume, String CNP) {
		connection();
		initialize(profil, nume, CNP);
	}

	public void fields() {
		this.textField = new JTextField();
		this.textField.setColumns(10);
		this.textField.setBounds(75, 46, 139, 28);
		this.panel.add(this.textField);

		this.label_1 = new JLabel("Nume prof:");
		this.label_1.setBounds(2, 46, 467, 28);
		label_1.setFont(new Font("Times new roman", Font.ITALIC, 14));
		this.panel.add(this.label_1);
	}

	private void buttons(String profil, String nume, String CNP) {
		// buttons
		JButton button = new JButton("Adauga");
		button.setBounds(200, 427, 100, 23);
		panel.add(button);
		button.addActionListener(e -> addprof());

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
		panel.setBackground(new Color(102, 102, 255));

		fields();

		buttons("Student", nume, CNP);
		frameO.setTitle("Adauga un prof");
		frameO.setVisible(true);
	}

	private void addprof() {
		try {
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			CallableStatement statement = con.prepareCall("{call proiect.addPRof(?)}");
			statement.setString(1, textField.getText().toString());

			try {
				statement.execute();
				statement.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Nu poti adauga profesorul", "!!!!!", JOptionPane.WARNING_MESSAGE);
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
