package proiect;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RenuntareCurs {
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
	private JLabel label_3;
	private JLabel label_4;

	public RenuntareCurs(String profil, String nume, String CNP) {
		this.connection();
		this.initialize(profil, nume, CNP);
	}

	private void textFields() {
		this.textField = new JTextField();
		this.textField.setColumns(10);
		this.textField.setBounds(70, 46, 139, 30);
		this.panel.add(this.textField);
		this.textField_1 = new JTextField();
		this.textField_1.setColumns(10);
		this.textField_1.setBounds(70, 87, 139, 30);
		this.panel.add(this.textField_1);
		this.textField_2 = new JTextField();
		this.textField_2.setColumns(10);
		this.textField_2.setBounds(70, 128, 139, 30);
		this.panel.add(this.textField_2);
		this.textField_3 = new JTextField();
		this.textField_3.setColumns(10);
		this.textField_3.setBounds(70, 169, 139, 30);
		this.panel.add(this.textField_3);

	}

	private void labels(String profil) {

		this.label_1 = new JLabel("Nume curs:");
		this.label_1.setBounds(2, 46, 467, 37);
		label_1.setFont(new Font("Times new roman", Font.ITALIC, 14));
		this.panel.add(this.label_1);

		this.label_2 = new JLabel("Nume:");
		this.label_2.setBounds(2, 87, 464, 23);
		label_2.setFont(new Font("Times new roman", Font.ITALIC, 14));
		this.panel.add(this.label_2);

		this.label_3 = new JLabel("Prenume:");
		this.label_3.setBounds(2, 128, 464, 14);
		label_3.setFont(new Font("Times new roman", Font.ITALIC, 14));
		this.panel.add(this.label_3);

		this.label_4 = new JLabel("CNP:");
		this.label_4.setBounds(2, 169, 464, 14);
		label_4.setFont(new Font("Times new roman", Font.ITALIC, 14));
		this.panel.add(this.label_4);
		panel.setBackground(new Color(127, 0, 255));

	}

	private void buttons(String profil, String nume, String CNP) {
		JButton button = new JButton("Renuntare");
		button.setBounds(200, 427, 100, 23);
		this.panel.add(button);
		button.addActionListener((e) -> {
			this.studDrop();
		});
		JButton buttonBack = new JButton("Back");
		buttonBack.setBounds(0, 0, 89, 23);
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
		frameO.setTitle("Renuntare Curs");
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
			CallableStatement statement = this.con.prepareCall("{call proiect.dropCourse(?,?)}");
			try {
				statement.setString(1, this.textField.getText().toString());
				statement.setString(2, this.textField_1.getText().toString());

				;
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
