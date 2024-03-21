package proiect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DupaLoginP {

	private String url = "jdbc:mysql://localhost/proiect";
	private String user = "root";
	private String password = "17052002Da";
	private Connection con;

	private JFrame frameP;

	private JPanel panel;

	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
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

	private ButtonGroup checkboxes;

	private JComboBox comboBox;

	private JButton button;
	private JButton button_1;

	public DupaLoginP(String profil, String nume, String CNP) {
		connection();
		initialize(profil, nume, CNP);
	}

	private void labels(String profil, String nume) {
		// labels
		label_1 = new JLabel("WELCOME!");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_1.setForeground(new Color(0, 116, 151));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 70, 464, 37);
		panel.add(label_1);
	}

	private void checkBoxes() {
		// checkBoxes

		checkBox_4 = new JCheckBox("Catalog");
		checkBox_4.setBounds(43, 150, 97, 23);
		checkBox_4.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_4.setBackground(new Color(117, 255, 196));
		panel.add(checkBox_4);

		checkBox_5 = new JCheckBox("Adaugare note");
		checkBox_5.setBounds(43, 175, 141, 23);
		checkBox_5.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_5.setBackground(new Color(117, 255, 196));
		panel.add(checkBox_5);

		checkBox_6 = new JCheckBox("Vizualizare studenti");
		checkBox_6.setBounds(43, 200, 150, 23);
		checkBox_6.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_6.setBackground(new Color(117, 255, 196));
		panel.add(checkBox_6);

		checkBox_7 = new JCheckBox("Adaugare procente pentru activitati");
		checkBox_7.setBounds(43, 225, 280, 23);
		checkBox_7.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_7.setBackground(new Color(117, 255, 196));
		panel.add(checkBox_7);

		checkBox_8 = new JCheckBox("Date personale");
		checkBox_8.setBounds(43, 250, 161, 23);
		checkBox_8.setFont(new Font("Times new roman", Font.ITALIC, 14));
		checkBox_8.setBackground(new Color(117, 255, 196));
		panel.add(checkBox_8);

		checkboxes = new ButtonGroup();
		checkboxes.add(checkBox_4);
		checkboxes.add(checkBox_5);
		checkboxes.add(checkBox_6);
		checkboxes.add(checkBox_7);
		checkboxes.add(checkBox_8);
		// checkboxes.add(checkBox_9);
	}

	private void buttons(String profil, String nume, String CNP) {
		// buttons
		button = new JButton("Continue");
		button.addActionListener(e -> {
			String op = "";
			if (checkBox_8.isSelected()) {
				frameP.dispose();
				new VeziDetalii(profil, nume, CNP);

			} else if (checkBox_7.isSelected()) {
				frameP.dispose();
				new AddProcente(profil, nume, CNP);
			} else if (checkBox_6.isSelected()) {
				frameP.dispose();
				new seeStudents(profil, nume, CNP);
			} else if (checkBox_5.isSelected()) {
				frameP.dispose();
				new AddGrades(profil, nume, CNP);
			} else if (checkBox_4.isSelected()) {
				catalog(nume, CNP);
			} else if (checkBox_3.isSelected()) {
				frameP.dispose();
				new Orar(profil, nume, CNP);
			} else {

				if (op.equals(""))
					JOptionPane.showMessageDialog(null, "Alege una din optiuni", "!!!!!", JOptionPane.WARNING_MESSAGE);
				else {
					frameP.dispose();
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
				frameP.dispose();
				new Login("Profesor");
			}
		});
		button_1.setBounds(0, 0, 89, 23);
		panel.add(button_1);

	}

	private void initialize(String profil, String nume, String CNP) {

//frame
		frameP = new JFrame("Profesor");
		frameP.setFont(new Font("Times new roman", Font.BOLD, 16));
		frameP.setSize(500, 500);
		frameP.setBounds(300, 300, 500, 500);
		frameP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//panel
		panel = new JPanel();
		frameP.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		labels(profil, nume);
		checkBoxes();
		buttons(profil, nume, CNP);

		panel.setBackground(new Color(117, 255, 196));
		frameP.setVisible(true);
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

	private ArrayList<String> materie(String CNP) {

		ArrayList<String> raspuns = new ArrayList<>();

		try {
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery("call proiect.obtinmaterie('" + CNP + "');");
			ResultSetMetaData rsmd = rs.getMetaData();

			int rows = 0;
			while (rs.next()) {
				rows++;
			}
			if (rows == 0) {
				return null;
			}

			rs.beforeFirst();

			while (rs.next()) {

				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					Object obj = rs.getObject(i);
					assert false;
					raspuns.add(obj.toString());
				}
				System.out.println("");
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return raspuns;
	}

	private void catalog(String nume, String CNP) {

		try {
			FileWriter file = new FileWriter(
					"D:\\Facultate\\Anul 2\\BD\\PROIECT\\TXT-uri\\Catalog_profesor\\" + nume + ".txt");
			ArrayList<String> materii = materie(CNP);

			if (materii != null) {
				for (String materie : materii) {

					Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ResultSet rs = s.executeQuery("call proiect.incercare('" + materie + "')");
					ResultSetMetaData rsmd = rs.getMetaData();

					int rows = 0;
					while (rs.next()) {
						rows++;
					}

					if (rows != 0)
						rs.beforeFirst();

					file.write(materie + ":\n");
					while (rs.next()) {
						for (int i = 1; i <= rsmd.getColumnCount(); i++) {
							file.write(rs.getObject(i).toString() + " ");
							if (i % 5 == 0)
								file.write("\n");
						}
					}
					file.write("\n");
				}
			} else
				JOptionPane.showMessageDialog(null, "Nu exista studenti inscrisi la curs!", "WARNING!",
						JOptionPane.WARNING_MESSAGE);
			file.close();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

}
