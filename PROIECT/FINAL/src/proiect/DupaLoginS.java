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

public class DupaLoginS {

	private String url = "jdbc:mysql://localhost/proiect";
	private String user = "root";
	private String password = "17052002Da";
	private Connection con;

	private JFrame frameS;

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
	private JCheckBox checkBox_9;
	private JCheckBox checkBox_10;
	private JCheckBox checkBox_11;
	private JCheckBox checkBox_12;
	private JCheckBox checkBox_13;
	private JCheckBox checkBox_14;
	private JCheckBox checkBox_15;
	private JCheckBox checkBox_16;
	private JCheckBox checkBox_17;
	private JCheckBox checkBox_18;

	private ButtonGroup checkboxes;

	private JComboBox comboBox;

	private JButton button;
	private JButton button_1;

	public DupaLoginS(String profil, String nume, String CNP) {
		connection();
		initialize(profil, nume, CNP);
	}

	private void labels(String profil, String nume) {
		// labels

		label_1 = new JLabel("WELCOME!");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_1.setForeground(new Color(150, 75, 0));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 70, 464, 37);
		panel.add(label_1);

	}

	private void checkBoxes() {
		// checkBoxes

		checkBox_3 = new JCheckBox("Orar");
		checkBox_3.setBounds(43, 169, 97, 23);
		checkBox_3.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_3.setBackground(new Color(153, 153, 255));
		panel.add(checkBox_3);

		checkBox_4 = new JCheckBox("Note");
		checkBox_4.setBounds(43, 195, 97, 23);
		checkBox_4.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_4.setBackground(new Color(153, 153, 255));
		panel.add(checkBox_4);

		checkBox_5 = new JCheckBox("Inscriere grup");
		checkBox_5.setBounds(43, 221, 115, 23);
		checkBox_5.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_5.setBackground(new Color(153, 153, 255));
		panel.add(checkBox_5);

		checkBox_6 = new JCheckBox("Inscriere curs");
		checkBox_6.setBounds(43, 247, 123, 23);
		checkBox_6.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_6.setBackground(new Color(153, 153, 255));
		panel.add(checkBox_6);

		checkBox_7 = new JCheckBox("Cursuri");
		checkBox_7.setBounds(43, 273, 123, 23);
		checkBox_7.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_7.setBackground(new Color(153, 153, 255));
		panel.add(checkBox_7);

		checkBox_8 = new JCheckBox("Inscriere activitate grup");
		checkBox_8.setBounds(43, 299, 200, 23);
		checkBox_8.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_8.setBackground(new Color(153, 153, 255));
		panel.add(checkBox_8);

		checkBox_9 = new JCheckBox("Renuntare la un curs");
		checkBox_9.setBounds(43, 325, 200, 23);
		checkBox_9.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_9.setBackground(new Color(153, 153, 255));
		panel.add(checkBox_9);

		checkBox_18 = new JCheckBox("Renuntare la un grup");
		checkBox_18.setBounds(270, 169, 161, 23);
		checkBox_18.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_18.setBackground(new Color(153, 153, 255));
		panel.add(checkBox_18);

		checkBox_17 = new JCheckBox("Creare activitate grup");
		checkBox_17.setBounds(270, 195, 161, 23);
		checkBox_17.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_17.setBackground(new Color(153, 153, 255));
		panel.add(checkBox_17);

		checkBox_10 = new JCheckBox("Vizualizare membrii grup");
		checkBox_10.setBounds(270, 221, 200, 23);
		checkBox_10.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_10.setBackground(new Color(153, 153, 255));
		panel.add(checkBox_10);

		checkBox_12 = new JCheckBox("Vizualizare activitati");
		checkBox_12.setBounds(270, 247, 161, 23);
		checkBox_12.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_12.setBackground(new Color(153, 153, 255));
		panel.add(checkBox_12);

		checkBox_13 = new JCheckBox("Mesaje");
		checkBox_13.setBounds(270, 273, 161, 23);
		checkBox_13.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_13.setBackground(new Color(153, 153, 255));
		panel.add(checkBox_13);

		checkBox_14 = new JCheckBox("Date personale");
		checkBox_14.setBounds(270, 299, 161, 23);
		checkBox_14.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_14.setBackground(new Color(153, 153, 255));
		panel.add(checkBox_14);

		checkBox_15 = new JCheckBox("Sugestii grup studiu");
		checkBox_15.setBounds(270, 325, 161, 23);
		checkBox_15.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_15.setBackground(new Color(153, 153, 255));
		panel.add(checkBox_15);

		checkBox_16 = new JCheckBox("Adauga profesorul de la curs");
		checkBox_16.setBounds(270, 351, 500, 23);
		checkBox_16.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_16.setBackground(new Color(153, 153, 255));
		panel.add(checkBox_16);

		checkboxes = new ButtonGroup();

		checkboxes.add(checkBox_3);
		checkboxes.add(checkBox_4);
		checkboxes.add(checkBox_5);
		checkboxes.add(checkBox_6);
		checkboxes.add(checkBox_7);
		checkboxes.add(checkBox_8);
		checkboxes.add(checkBox_9);
		checkboxes.add(checkBox_10);
		checkboxes.add(checkBox_12);
		checkboxes.add(checkBox_13);
		checkboxes.add(checkBox_14);
		checkboxes.add(checkBox_15);
		checkboxes.add(checkBox_16);
		checkboxes.add(checkBox_17);
		checkboxes.add(checkBox_18);
	}

	private void buttons(String profil, String nume, String CNP) {
		// buttons
		button = new JButton("Continue");
		button.addActionListener(e -> {
			String op = "";
			if (checkBox_18.isSelected()) {
				frameS.dispose();
				new RenuntareGrup(profil, nume, CNP);
			} else if (checkBox_17.isSelected()) {
				frameS.dispose();
				new CreareActivitateGrup(profil, nume, CNP);
			} else if (checkBox_16.isSelected()) {
				frameS.dispose();
				new addProf(profil, nume, CNP);
			} else if (checkBox_15.isSelected()) {
				frameS.dispose();
				new SugestiiGrup(profil, nume, CNP);
			} else if (checkBox_5.isSelected()) {
				frameS.dispose();
				new InscriereGrup(profil, nume, CNP);
			} else if (checkBox_14.isSelected()) {
				frameS.dispose();
				new VeziDetalii(profil, nume, CNP);
			} else if (checkBox_12.isSelected()) {
				frameS.dispose();
				new veziActiv(profil, nume, CNP);
			} else if (checkBox_13.isSelected()) {
				frameS.dispose();
				new MesajeActivitati(profil, nume, CNP);
			} else if (checkBox_10.isSelected()) {
				frameS.dispose();
				new MembriiGrup(profil, nume, CNP);
			} else if (checkBox_9.isSelected()) {
				frameS.dispose();
				new RenuntareCurs(profil, nume, CNP);
			} else if (checkBox_4.isSelected()) {
				frameS.dispose();
				new seeGrades(profil, nume, CNP);
			} else if (checkBox_8.isSelected()) {
				frameS.dispose();
				new InscriereActivitati(profil, nume, CNP);
			} else if (checkBox_6.isSelected()) {
				frameS.dispose();
				new InregistrareStudent(profil, nume, CNP);
			} else if (checkBox_7.isSelected()) {
				frameS.dispose();
				new seeCursuri(profil, nume, CNP);
			} else if (checkBox_3.isSelected()) {
				frameS.dispose();
				new Orar(profil, nume, CNP);
			} else {

				if (op.equals(""))
					JOptionPane.showMessageDialog(null, "Alege una din optiuni", "!!!!!", JOptionPane.WARNING_MESSAGE);
				else {
					frameS.dispose();
					new UpdateInsert(label.getText(), nume, comboBox.getSelectedItem().toString(), op, CNP);
				}
			}
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
				frameS.dispose();
				new Login(profil);
			}
		});
		button_1.setBounds(0, 0, 89, 23);
		panel.add(button_1);

	}

	private void initialize(String profil, String nume, String CNP) {

//frame
		frameS = new JFrame("Student");
		frameS.setFont(new Font("Times new roman", Font.BOLD, 16));
		frameS.setSize(500, 500);
		frameS.setBounds(300, 300, 500, 500);
		frameS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//panel
		panel = new JPanel();
		frameS.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		labels(profil, nume);
		checkBoxes();
		buttons(profil, nume, CNP);

		panel.setBackground(new Color(153, 153, 255));
		frameS.setVisible(true);
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
