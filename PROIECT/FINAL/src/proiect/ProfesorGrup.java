package proiect;

import java.awt.LayoutManager;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProfesorGrup {
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

	public ProfesorGrup(String profil, String nume, String CNP) {
		this.connection();
		this.initialize(profil, nume, CNP);
	}

	private void textFields() {
		this.textField = new JTextField();
		this.textField.setColumns(10);
		this.textField.setBounds(2, 5, 139, 30);
		this.panel.add(this.textField);
		this.textField_1 = new JTextField();
		this.textField_1.setColumns(10);
		this.textField_1.setBounds(2, 46, 139, 30);
		this.panel.add(this.textField_1);
		this.textField_2 = new JTextField();
		this.textField_2.setColumns(10);
		this.textField_2.setBounds(2, 87, 139, 30);
		this.panel.add(this.textField_2);

	}

	private void labels(String profil) {
		this.label = new JLabel(profil);
		this.label.setHorizontalAlignment(0);
		this.label.setBounds(357, 11, 117, 23);
		this.panel.add(this.label);
		this.label_1 = new JLabel("Nume grup");
		this.label_1.setHorizontalAlignment(0);
		this.label_1.setBounds(10, 5, 467, 37);
		this.panel.add(this.label_1);
		this.label_2 = new JLabel("Nume profesor");
		this.label_2.setHorizontalAlignment(0);
		this.label_2.setBounds(10, 46, 464, 23);
		this.panel.add(this.label_2);
		this.label_3 = new JLabel("Prenume profesor");
		this.label_3.setHorizontalAlignment(0);
		this.label_3.setBounds(10, 87, 464, 14);
		this.panel.add(this.label_3);

	}

	private void buttons(String profil, String nume, String CNP) {
		JButton button = new JButton("Adauga profesor");
		button.setBounds(385, 400, 89, 23);
		this.panel.add(button);
		button.addActionListener((e) -> {
			this.studDrop();
		});
		JButton buttonBack = new JButton("Back");
		buttonBack.setBounds(10, 400, 89, 23);
		buttonBack.addActionListener((e) -> {
			this.frameO.dispose();
			new DupaLoginS(profil, nume, CNP);
		});
		this.panel.add(buttonBack);
	}

	private void initialize(String profil, String nume, String CNP) {
		this.frameO = new JFrame();
		this.frameO.setSize(500, 500);
		this.frameO.setBounds(300, 300, 500, 500);
		this.frameO.setDefaultCloseOperation(3);
		this.panel = new JPanel();
		this.frameO.getContentPane().add(this.panel, "Center");
		this.panel.setLayout((LayoutManager) null);
		this.textFields();
		this.labels("Student");
		this.buttons("Student", nume, CNP);
		this.frameO.setVisible(true);
	}

	private void connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException var3) {
			System.err.println(var3);
		}

		this.con = null;

		try {
			this.con = DriverManager.getConnection(this.url, this.user, this.password);
		} catch (SQLException var2) {
			System.err.println("SQLException: " + var2);
			System.exit(1);
		}

	}

	private void studDrop() {
		try {
			CallableStatement statement = this.con.prepareCall("{call proiect.addTeacherToGroup(?,?,?)}");

			try {
				statement.setString(1, this.textField.getText().toString());
				statement.setString(2, this.textField_1.getText().toString());
				statement.setString(3, this.textField_2.getText().toString());

				statement.execute();
				statement.close();
			} catch (Throwable var5) {
				if (statement != null) {
					try {
						statement.close();
					} catch (Throwable var4) {
						var5.addSuppressed(var4);
					}
				}

				throw var5;
			}

			if (statement != null) {
				statement.close();
			}
		} catch (SQLException var6) {
			var6.printStackTrace();
		}

	}
}
