package proiect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login {

	private String url = "jdbc:mysql://localhost/proiect";
	private String user = "root";
	private String password = "17052002Da";
	private Connection con;

	private JFrame frameL;

	private JPanel panel;

	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;

	private JPasswordField passwordField;
	private JTextField textField;
	private JTextField textField_1;

	private JButton buttonLog;
	private JButton buttonBack;

	public Login(String x) {
		connection();
		initialize(x);
	}

	private void labels(String x) {
		// labels
		label = new JLabel(x);
		label.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(150, 45, 185, 60);
		label.setForeground(Color.black);
		panel.add(label);

		label_1 = new JLabel("Nume:");
		label_1.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		label_1.setBounds(20, 141, 69, 36);
		panel.add(label_1);

		label_2 = new JLabel("Prenume:");
		label_2.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		label_2.setBounds(20, 177, 69, 36);
		panel.add(label_2);

		label_3 = new JLabel("Parola:");
		label_3.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		label_3.setBounds(20, 213, 69, 36);
		panel.add(label_3);

	}

	private void textFields() {
		// text/pass fields
		passwordField = new JPasswordField("");
		passwordField.setBounds(80, 218, 86, 23);
		panel.add(passwordField);

		textField = new JTextField("");
		textField.setColumns(10);
		textField.setBounds(80, 146, 86, 23);
		panel.add(textField);

		textField_1 = new JTextField("");
		textField_1.setBounds(80, 182, 86, 23);
		textField_1.setColumns(10);
		panel.add(textField_1);
	}

	private void buttons() {
		// buttons
		buttonBack = new JButton("Back");
		buttonBack.addActionListener(e -> {
			frameL.dispose();
			new PrimaPagina();
		});
		buttonBack.setBounds(0, 0, 89, 23);
		buttonBack.setFocusable(false);
		panel.add(buttonBack);

		buttonLog = new JButton("Login");
		buttonLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nume = textField.getText();
				String prenume = textField_1.getText();
				String parola = new String(passwordField.getPassword());

				if (nume.equals("") || prenume.equals("") || parola.equals("")) {
					JOptionPane.showMessageDialog(null, "Te rugam sa completezi toate campurile!", "title",
							JOptionPane.WARNING_MESSAGE);
				} else if (parola.length() != 13 || !parola.chars().allMatch(Character::isDigit)) {
					JOptionPane.showMessageDialog(null, "CNP-ul nu este introdus corect !", " title ",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int rasp = doQuery(label.getText(), nume, prenume, parola);
					if (rasp == 1) {
						switch (label.getText()) {
						case "Super-administrator", "Administrator" -> {
							frameL.dispose();
							new DupaLoginA(label.getText(), nume + " " + prenume, parola);
						}
						case "Profesor" -> {
							frameL.dispose();
							new DupaLoginP(label.getText(), nume + " " + prenume, parola);
						}
						case "Student" -> {
							frameL.dispose();
							new DupaLoginS(label.getText(), nume + " " + prenume, parola);
						}
						}
					} else
						JOptionPane.showMessageDialog(null, "NU EXISTI IN BAZA NOASTRA DE DATE!", "WARINIG!",
								JOptionPane.WARNING_MESSAGE);

				}
			}
		});
		buttonLog.setBounds(60, 260, 89, 23);
		panel.add(buttonLog);
	}

	private void initialize(String x) {

//frame
		frameL = new JFrame("LOG IN");
		frameL.setSize(500, 500);
		frameL.setBounds(300, 300, 500, 500);
		frameL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//panel
		panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		frameL.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		labels(x);
		textFields();
		buttons();
		//frameL.setIconImage(new ImageIcon(getClass().getResource("wink1.png")).getImage());
		frameL.setVisible(true);
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

	private int doQuery(String tabela, String nume, String prenume, String CNP) {
		try {
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			if (tabela.equals("Super-administrator"))
				tabela = "super_administrator";
			ResultSet rs = s.executeQuery("SELECT * FROM proiect." + tabela.toLowerCase() + " WHERE nume = '" + nume
					+ "' AND prenume = '" + prenume + "' AND CNP = '" + CNP + "';");
			int rows = 0;
			while (rs.next()) {
				rows++;
			}
			if (rows == 0)
				return 0;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 1;
	}

}
