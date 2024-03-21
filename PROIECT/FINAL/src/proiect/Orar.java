package proiect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Orar {

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

	public Orar(String profil, String nume, String CNP) {
		connection();
		initialize(profil, nume, CNP);
	}

	private void labels() {

		JLabel label_1 = new JLabel("Curs");
		label_1.setFont(new Font("Times new Roman", Font.ITALIC, 14));
		// label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(62, 182, 91, 40);
		panel.add(label_1);

		JLabel label_2 = new JLabel("Seminar");
		// label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Times new Roman", Font.ITALIC, 14));
		label_2.setBounds(211, 182, 91, 40);
		panel.add(label_2);

		JLabel label_3 = new JLabel("Laborator");
		// label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Times new roman", Font.ITALIC, 14));
		label_3.setBounds(383, 182, 91, 40);
		panel.add(label_3);

		JLabel label_4 = new JLabel("Ziua Saptamanii:");
		label_4.setFont(new Font("Times new roman", Font.ITALIC, 14));
		label_4.setBounds(80, 77, 110, 40);
		panel.add(label_4);
	}

	private void comboBoxes() {
		comboBox = new JComboBox();
		comboBox.setBounds(36, 233, 91, 22);
		panel.add(comboBox);

		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(193, 233, 91, 22);
		panel.add(comboBox_1);

		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(369, 233, 91, 22);
		panel.add(comboBox_2);
	}

	private void buttons(String profil, String nume, String CNP) {
		JButton buttonAfiseaza = new JButton("SHOW");
		buttonAfiseaza.setFont(new Font("Times new roman", Font.BOLD, 16));
		buttonAfiseaza.setBounds(200, 427, 90, 23);
		buttonAfiseaza.addActionListener(e -> {
			if (textField.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Nu ai o introdus o zi", "ATENTIE!", JOptionPane.WARNING_MESSAGE);
			else {
				if (profil.equals("Student"))
					orarStud(nume, CNP, textField.getText());
				else
					orarProf(nume, CNP, textField.getText());
			}
		});
		panel.add(buttonAfiseaza);

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

	private void orarStud(String nume, String CNP, String zi) {
		String[] nupr = nume.split(" ");
		String[] ore = new String[7];
		int contor = 0;
		try {
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery(
					"call proiect.showOrarStudCurs('" + nupr[0] + "','" + nupr[1] + "','" + CNP + "','" + zi + "');");
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				ore[contor++] = rs.getObject(1).toString() + ": " + rs.getObject(2).toString();
			}
			contor = 0;
			comboBox.setModel(new DefaultComboBoxModel(ore));
			ore = new String[7];

			rs = s.executeQuery(
					"call proiect.showOrarStudSem('" + nupr[0] + "','" + nupr[1] + "','" + CNP + "','" + zi + "');");
			rsmd = rs.getMetaData();
			while (rs.next()) {
				ore[contor++] = rs.getObject(1).toString() + ": " + rs.getObject(2).toString();
			}
			contor = 0;
			comboBox_1.setModel(new DefaultComboBoxModel(ore));
			ore = new String[7];
			rs = s.executeQuery(
					"call proiect.showOrarStudLab('" + nupr[0] + "','" + nupr[1] + "','" + CNP + "','" + zi + "');");
			rsmd = rs.getMetaData();
			while (rs.next()) {
				ore[contor++] = rs.getObject(1).toString() + ": " + rs.getObject(2).toString();
			}
			comboBox_2.setModel(new DefaultComboBoxModel(ore));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void orarProf(String nume, String CNP, String zi) {
		String[] nupr = nume.split(" ");
		String[] ore = new String[7];
		int contor = 0;
		try {
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery(
					"call proiect.showOrarProfCurs('" + nupr[0] + "','" + nupr[1] + "','" + CNP + "','" + zi + "');");
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				ore[contor++] = rs.getObject(1).toString();
			}
			contor = 0;
			comboBox.setModel(new DefaultComboBoxModel(ore));
			ore = new String[7];

			rs = s.executeQuery(
					"call proiect.showOrarProfSem('" + nupr[0] + "','" + nupr[1] + "','" + CNP + "','" + zi + "');");
			rsmd = rs.getMetaData();
			while (rs.next()) {
				ore[contor++] = rs.getObject(1).toString();
			}
			contor = 0;
			comboBox_1.setModel(new DefaultComboBoxModel(ore));
			ore = new String[7];
			rs = s.executeQuery(
					"call proiect.showOrarProfLab('" + nupr[0] + "','" + nupr[1] + "','" + CNP + "','" + zi + "');");
			rsmd = rs.getMetaData();
			while (rs.next()) {
				ore[contor++] = rs.getObject(1).toString();
			}
			comboBox_2.setModel(new DefaultComboBoxModel(ore));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void initialize(String profil, String nume, String CNP) {
		frameO = new JFrame("Orar");
		frameO.setSize(500, 500);
		frameO.setBounds(300, 300, 500, 500);
		frameO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		frameO.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		textField = new JTextField("");
		textField.setFont(new Font("Times new roamn", Font.BOLD, 12));
		textField.setBounds(190, 83, 70, 25);
		panel.add(textField);
		textField.setColumns(10);
		panel.setBackground(new Color(255, 139, 139));
		labels();
		comboBoxes();
		buttons(profil, nume, CNP);

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

}
