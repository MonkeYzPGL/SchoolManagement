package proiect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DupaLoginA {

	private final String url = "jdbc:mysql://localhost/proiect";
	private final String user = "root";
	private final String password = "17052002Da";
	private Connection con;

	private JFrame frameA;

	private JPanel panel;

	private JLabel label_2;
	private JLabel label_1;
	private JLabel label;
	private JLabel label_3;

	private JCheckBox checkBox;
	private JCheckBox checkBox_1;
	private JCheckBox checkBox_2;
	private JCheckBox checkBox_3;
	private JCheckBox checkBox_4;
	private JCheckBox checkBox_5;
	private JCheckBox checkBox_6;
	private JCheckBox checkBox_7;
	private JCheckBox checkBox_8;
	private ButtonGroup checkboxes;

	private JComboBox comboBox;

	private JButton button;
	private JButton button_1;

	public DupaLoginA(String profil, String nume, String CNP) {
		connection();
		initialize(profil, nume, CNP);
	}

	private void labels(String profil, String nume) {
		// labels

		label_1 = new JLabel("WELCOME!");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_1.setForeground(new Color(125, 110, 10));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 70, 464, 37);
		panel.add(label_1);

	}

	private void checkBoxes() {
		// checkBoxes
		checkBox = new JCheckBox("Update info");
		checkBox.setBounds(43, 169, 150, 23);
		checkBox.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox.setBackground(new Color(227, 255, 131));
		panel.add(checkBox);

		checkBox_1 = new JCheckBox("Delete info");
		checkBox_1.setBounds(43, 195, 150, 23);
		checkBox_1.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_1.setBackground(new Color(227, 255, 131));
		panel.add(checkBox_1);

		checkBox_2 = new JCheckBox("Insert user");
		checkBox_2.setBounds(43, 221, 150, 23);
		checkBox_2.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_2.setBackground(new Color(227, 255, 131));
		panel.add(checkBox_2);

		checkBox_3 = new JCheckBox("Informatii personale");
		checkBox_3.setBounds(43, 245, 180, 23);
		checkBox_3.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_3.setBackground(new Color(227, 255, 131));
		panel.add(checkBox_3);

		checkBox_4 = new JCheckBox("Cauta");
		checkBox_4.setBounds(43, 270, 97, 23);
		checkBox_4.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_4.setBackground(new Color(227, 255, 131));
		panel.add(checkBox_4);

		checkBox_5 = new JCheckBox("Filtrare dupa tip");
		checkBox_5.setBounds(43, 290, 140, 23);
		checkBox_5.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_5.setBackground(new Color(227, 255, 131));
		panel.add(checkBox_5);

		checkBox_6 = new JCheckBox("Cauta curs");
		checkBox_6.setBounds(43, 315, 140, 23);
		checkBox_6.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_6.setBackground(new Color(227, 255, 131));
		panel.add(checkBox_6);

		checkBox_7 = new JCheckBox("Asigneaza profesor ");
		checkBox_7.setBounds(43, 340, 150, 23);
		checkBox_7.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_7.setBackground(new Color(227, 255, 131));
		panel.add(checkBox_7);

		checkBox_8 = new JCheckBox("Sterge un user");
		checkBox_8.setBounds(43, 365, 150, 23);
		checkBox_8.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_8.setBackground(new Color(227, 255, 131));
		panel.add(checkBox_8);

		checkboxes = new ButtonGroup();
		checkboxes.add(checkBox);
		checkboxes.add(checkBox_1);
		checkboxes.add(checkBox_2);
		checkboxes.add(checkBox_3);
		checkboxes.add(checkBox_4);
		checkboxes.add(checkBox_5);
		checkboxes.add(checkBox_6);
		checkboxes.add(checkBox_7);
		checkboxes.add(checkBox_8);
	}

	private void buttons(String profil, String nume, String CNP) {
		// buttons
		button = new JButton("Continue");
		button.addActionListener(e -> {

			String op = "";

			if (checkBox_7.isSelected()) {
				frameA.dispose();
				new AsignareProfesor(profil, nume, CNP);
			} else if (checkBox_6.isSelected()) {
				frameA.dispose();
				new CautaCurs(profil, nume, CNP);
			} else if (checkBox_5.isSelected()) {
				frameA.dispose();
				new Filtrare(profil, nume, CNP);
			} else if (checkBox_4.isSelected()) {
				frameA.dispose();
				new CautareUser(profil, nume, CNP);
			} else if (checkBox_3.isSelected()) {
				frameA.dispose();
				new VeziDetalii(profil, nume, CNP);
			} else if (checkBox.isSelected()) {
				frameA.dispose();
				new updateUser(profil, nume, CNP);
			} else if (checkBox_8.isSelected()) {
				frameA.dispose();
				new deleteUser(profil, nume, CNP);
			} else if (checkBox_2.isSelected()) {
				frameA.dispose();
				new insertUser(profil, nume, CNP);
			} else if (checkBox_1.isSelected()) {
				frameA.dispose();
				new deleteInfo(profil, nume, CNP);
			}
			;
		});
		button.setBounds(200, 427, 89, 23);
		panel.add(button);
		panel.setBackground(Color.WHITE);

		button_1 = new JButton("Back");
		button_1.addActionListener(e -> {
			String[] raspunsuri = { "Da", "Nu" };
			int raspuns = JOptionPane.showOptionDialog(null, "O sa fii delogat. Continui?", "!!!!!",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, raspunsuri, 0);
			if (raspuns == 0) {
				frameA.dispose();
				new Login(profil);
			}
		});
		button_1.setBounds(0, 0, 89, 23);
		panel.add(button_1);
	}

	private void initialize(String profil, String nume, String CNP) {

//frame
		frameA = new JFrame(profil.toString());
		frameA.setFont(new Font("Times new roman", Font.BOLD, 16));
		frameA.setSize(500, 500);
		frameA.setBounds(300, 300, 500, 500);
		frameA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//panel
		panel = new JPanel();
		frameA.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		labels(profil, nume);
		checkBoxes();
		// combobox(profil);
		buttons(profil, nume, CNP);

		panel.setBackground(new Color(227, 255, 131));
		frameA.setVisible(true);
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
