package proiect;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class InscriereActivitati {

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

	public InscriereActivitati(String profil, String nume, String CNP) {
		connection();
		initialize(profil, nume, CNP);
	}

	private void textFields() {
		// testFields
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(100, 100, 139, 30);
		panel.add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(100, 146, 139, 30);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(100, 190, 139, 30);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(100, 230, 139, 30);
		panel.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(100, 270, 139, 30);
		panel.add(textField_4);

	}

	private void labels(String profil) {
		label_1 = new JLabel("Nume");
		// label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(0, 100, 467, 37);
		panel.add(label_1);

		label_2 = new JLabel("Prenume");
		// label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(0, 145, 464, 23);
		panel.add(label_2);

		label_3 = new JLabel("CNP");
		// label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(0, 195, 464, 14);
		panel.add(label_3);

		label_4 = new JLabel("Nume grup");
		// label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(0, 235, 464, 14);
		panel.add(label_4);

		label_5 = new JLabel("Nume activitate");
		// label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(0, 275, 464, 14);
		panel.add(label_5);

	}

	private void buttons(String profil, String nume, String CNP) {
		// buttons
		JButton button = new JButton("Inscrie-te");
		button.setBounds(75, 330, 89, 23);
		panel.add(button);
		button.addActionListener(e -> studRegToGroupActivity());

		JButton buttonBack = new JButton("Back");

		buttonBack.setBounds(0, 0, 89, 23);
		buttonBack.addActionListener(e -> {
			frameO.dispose();
			new DupaLoginS(profil, nume, CNP);
		});
		panel.add(buttonBack);
	}

	private void initialize(String profil, String nume, String CNP) {
		frameO = new JFrame("Inscriere la o activitate grup");
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
		// panel

		panel.setBackground(new Color(204, 204, 255));
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

	private void studRegToGroupActivity() {
		try {
			int rows = 0;
			int rows1 = 0;
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = s.executeQuery("select * from proiect.activ_grup");
			while (rs.next())
				rows++;
			CallableStatement statement = con.prepareCall("{call proiect.studentToAGroupActivity(?,?,?,?,?)}");

			statement.setString(1, textField.getText().toString());
			statement.setString(2, textField_1.getText().toString());
			statement.setString(3, textField_2.getText().toString());
			statement.setString(4, textField_3.getText().toString());
			statement.setString(5, textField_4.getText().toString());

			statement.execute();
			statement.close();

			rs = s.executeQuery("select * from proiect.activ_grup");
			while (rs.next())
				rows1++;

			if (rows1 != rows) {
				JOptionPane.showMessageDialog(null, "Nu te-ai putut inscrie la activitate!", "ATENTIE",
						JOptionPane.WARNING_MESSAGE);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
