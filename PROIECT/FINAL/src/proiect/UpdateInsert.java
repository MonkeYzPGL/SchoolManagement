package proiect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class UpdateInsert {

	private String url = "jdbc:mysql://127.0.0.1:3306/proiect";
	private String user = "root";
	private String password = "17052002Da";
	private Connection con;

	private JFrame frame;

	private JPanel panel;

	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;

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

	JButton buttonBack;
	JButton buttonConf;

	public UpdateInsert(String profil, String nume, String tabel, String operatiune, String CNP) {
		connection();
		initialize(profil, nume, tabel, operatiune, CNP);
	}

	private void textFields() {
		// testFields
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(0, 5, 139, 30);
		panel.add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(0, 46, 139, 30);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(0, 87, 139, 30);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(0, 128, 139, 30);
		panel.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(0, 169, 139, 30);
		panel.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(0, 210, 139, 30);
		panel.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(0, 251, 139, 30);
		panel.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(0, 292, 139, 30);
		panel.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(0, 333, 139, 30);
		panel.add(textField_8);

		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(0, 374, 139, 30);
		panel.add(textField_9);
	}

	private void labels(String profil, String nume, String tabel, String operatiune) {
		// labels
		label = new JLabel(profil);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(251, 0, 203, 41);
		panel.add(label);

		label_1 = new JLabel(nume);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(251, 52, 403, 41);
		panel.add(label_1);

		label_2 = new JLabel(tabel);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(251, 112, 203, 41);
		panel.add(label_2);

		label_3 = new JLabel(operatiune);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(251, 164, 203, 41);
		panel.add(label_3);

	}

	private ArrayList<String> parametrii() {
		ArrayList<String> x = new ArrayList<>();
		if (!textField.getText().equals(""))
			x.add(textField.getText());
		if (!textField_1.getText().equals(""))
			x.add(textField_1.getText());
		if (!textField_2.getText().equals(""))
			x.add(textField_2.getText());
		if (!textField_3.getText().equals(""))
			x.add(textField_3.getText());
		if (!textField_4.getText().equals(""))
			x.add(textField_4.getText());
		if (!textField_5.getText().equals(""))
			x.add(textField_5.getText());
		if (!textField_6.getText().equals(""))
			x.add(textField_6.getText());
		if (!textField_7.getText().equals(""))
			x.add(textField_7.getText());
		if (!textField_8.getText().equals(""))
			x.add(textField_8.getText());
		if (!textField_9.getText().equals(""))
			x.add(textField_9.getText());
		return x;
	}

	private void buttons(String CNP, String tabel, String operatiune) {
		buttonConf = new JButton("Confirm");
		buttonConf.setBounds(365, 416, 89, 23);
		buttonConf.addActionListener(e -> {
			// inserturi
			if (textField.getText().equals("") && textField_1.getText().equals("") && textField_2.getText().equals("")
					&& textField_3.getText().equals("") && textField_4.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Te rugam sa introduci datele", "ATENTIE",
						JOptionPane.WARNING_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Operatiunea a fost realizata cu succes", "SUCCES",
						JOptionPane.PLAIN_MESSAGE);
			if (tabel.equals("administrator") && operatiune.equals("Insert"))
				addAdmin();
			if (tabel.equals("cursuri") && operatiune.equals("Insert"))
				addCursuri();
			if (tabel.equals("informatii_profesor") && operatiune.equals("Insert"))
				addInfo_Prof();
			if (tabel.equals("profesor") && operatiune.equals("Insert"))
				addProfesor();
			if (tabel.equals("student") && operatiune.equals("Insert"))
				addStudent();
			if (tabel.equals("activ_grup") && operatiune.equals("Insert"))
				createActivityGroup();
			if (tabel.equals("grup_studiu") && operatiune.equals("Insert"))
				createGrupStudiu();
			if (tabel.equals("calendar") && operatiune.equals("Insert"))
				try {
					addToCalendar();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			// updateuri

			if (tabel.equals("cursuri") && operatiune.equals("Update"))
				updateCursuri();
			if (tabel.equals("grup_studiu") && operatiune.equals("Update"))
				updateGrupStudiu();
			if (tabel.equals("profesor") && operatiune.equals("Update"))
				updateProfesor();
			if (tabel.equals("student") && operatiune.equals("Update"))
				updateStudent();
		});
		panel.add(buttonConf);

		buttonBack = new JButton("Back");
		buttonBack.addActionListener(e -> {
			switch (label.getText()) {
			case "Super-administrator", "Administrator" -> {
				frame.dispose();
				new DupaLoginA(label.getText(), label_1.getText(), CNP);
			}
			case "Profesor" -> {
				frame.dispose();
				new DupaLoginP(label.getText(), label_1.getText(), CNP);
			}
			case "Student" -> {
				frame.dispose();
				new DupaLoginS(label.getText(), label_1.getText(), CNP);
			}
			}
		});
		buttonBack.setBounds(0, 416, 89, 23);
		panel.add(buttonBack);
	}

	private void initialize(String profil, String nume, String tabel, String operatiune, String CNP) {

		// frame
		frame = new JFrame();
		frame.setSize(500, 500);
		frame.setBounds(300, 300, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// panel
		panel = new JPanel();
		panel.setBounds(10, 11, 464, 439);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		labels(profil, nume, tabel, operatiune);
		textFields();
		buttons(CNP, tabel, operatiune);

		frame.setVisible(true);

	}

	private void connection() {
		try {
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

	private void addStudent() {
		try {
			CallableStatement st = this.con.prepareCall("{call proiect.insertstudent(?,?,?,?,?,?,?,?,?,?)}");

			try {
				st.setString(1, this.textField.getText().toString());
				st.setString(2, this.textField_1.getText().toString());
				st.setString(3, this.textField_2.getText().toString());
				st.setString(4, this.textField_3.getText().toString());
				st.setString(5, this.textField_4.getText().toString());
				st.setString(6, this.textField_5.getText().toString());
				st.setString(7, this.textField_6.getText().toString());
				st.setString(8, this.textField_7.getText().toString());
				st.setInt(9, Integer.parseInt(this.textField_8.getText()));
				st.setInt(10, Integer.parseInt(this.textField_9.getText()));
				;
				st.execute();
				st.close();
			} catch (Throwable var5) {
				if (st != null) {
					try {
						st.close();
					} catch (Throwable var4) {
						var4.addSuppressed(var5);
					}
				}
				throw var5;
			}
			if (st != null) {
				st.close();
			}

		} catch (SQLException var6) {
			var6.printStackTrace();
		}
	}

	private void updateStudent() {
		try {
			CallableStatement st = this.con.prepareCall("{call proiect.updatestudent(?,?,?,?,?,?,?,?,?,?)}");

			try {
				st.setString(1, this.textField.getText().toString());
				st.setString(2, this.textField_1.getText().toString());
				st.setString(3, this.textField_2.getText().toString());
				st.setString(4, this.textField_3.getText().toString());
				st.setString(5, this.textField_4.getText().toString());
				st.setString(6, this.textField_5.getText().toString());
				st.setString(7, this.textField_6.getText().toString());
				st.setString(8, this.textField_7.getText().toString());
				st.setInt(9, Integer.parseInt(this.textField_8.getText()));
				st.setInt(10, Integer.parseInt(this.textField_9.getText()));
				;
				st.execute();
				st.close();
			} catch (Throwable var5) {
				if (st != null) {
					try {
						st.close();
					} catch (Throwable var4) {
						var4.addSuppressed(var5);
					}
				}
				throw var5;
			}
			if (st != null) {
				st.close();
			}

		} catch (SQLException var6) {
			var6.printStackTrace();
		}
	}

	private void addCursuri() {
		try {
			CallableStatement st = this.con.prepareCall("{call proiect.insertcursuri(?,?,?,?,?,?,?,?,?,?)}");

			try {
				st.setString(1, this.textField.getText().toString());
				st.setString(2, this.textField_1.getText().toString());
				st.setInt(3, Integer.parseInt(this.textField_2.getText()));
				st.setString(4, this.textField_3.getText().toString());
				st.setString(5, this.textField_4.getText().toString());
				st.setString(6, this.textField_5.getText().toString());
				st.setInt(7, Integer.parseInt(this.textField_6.getText()));
				st.setInt(8, Integer.parseInt(this.textField_7.getText()));
				st.setInt(9, Integer.parseInt(this.textField_8.getText()));
				st.setString(10, this.textField_9.getText().toString());

				st.execute();
				st.close();
			} catch (Throwable var5) {
				if (st != null) {
					try {
						st.close();
					} catch (Throwable var4) {
						var5.addSuppressed(var4);
					}
				}
				throw var5;
			}

			if (st != null) {
				st.close();
			}

		} catch (SQLException var6) {
			var6.printStackTrace();
		}
	}

	private void updateCursuri() {
		try {
			CallableStatement st = this.con.prepareCall("{call proiect.updatecursuri(?,?,?,?,?,?,?,?,?)}");

			try {
				st.setString(1, this.textField.getText().toString());
				st.setString(2, this.textField_1.getText().toString());
				st.setString(3, this.textField_2.getText().toString());
				st.setString(4, this.textField_3.getText().toString());
				st.setString(5, this.textField_4.getText().toString());
				st.setInt(6, Integer.parseInt(this.textField_5.getText()));
				st.setInt(7, Integer.parseInt(this.textField_6.getText()));
				st.setInt(8, Integer.parseInt(this.textField_7.getText()));
				st.setInt(9, Integer.parseInt(this.textField_8.getText()));

				st.execute();
				st.close();
			} catch (Throwable var5) {
				if (st != null) {
					try {
						st.close();
					} catch (Throwable var4) {
						var5.addSuppressed(var4);
					}
				}
				throw var5;
			}

			if (st != null) {
				st.close();
			}
		} catch (SQLException var6) {
			var6.printStackTrace();
		}
	}

	private void addAdmin() {
		try {
			CallableStatement st = this.con.prepareCall("{call proiect.insertadministrator(?,?,?,?,?,?,?,?,?,?)}");

			try {
				st.setString(1, this.textField.getText().toString());
				st.setString(2, this.textField_1.getText().toString());
				st.setString(3, this.textField_2.getText().toString());
				st.setString(4, this.textField_3.getText().toString());
				st.setString(5, this.textField_4.getText().toString());
				st.setString(6, this.textField_5.getText().toString());
				st.setString(7, this.textField_6.getText().toString());
				st.setString(8, this.textField_7.getText().toString());
				st.setString(9, this.textField_8.getText().toString());
				st.setString(10, this.textField_9.getText().toString());

				st.execute();
				st.close();
			} catch (Throwable var5) {
				if (st != null) {
					try {
						st.close();
					} catch (Throwable var4) {
						var5.addSuppressed(var4);
					}
				}
				throw var5;
			}

			if (st != null) {
				st.close();
			}
		} catch (SQLException var6) {
			var6.printStackTrace();
		}
	}

	private void addInfo_Prof() {
		try {
			CallableStatement st = this.con
					.prepareCall("{call proiect.insertinformatii_profesor(?,?,?,?,?,?,?,?,?,?)}");

			try {
				st.setString(1, this.textField.getText().toString());
				st.setString(2, this.textField_1.getText().toString());
				st.setString(3, this.textField_2.getText().toString());
				st.setInt(4, Integer.parseInt(this.textField_3.getText()));
				st.setInt(5, Integer.parseInt(this.textField_4.getText()));
				st.setString(6, this.textField_5.getText().toString());
				st.setString(7, this.textField_6.getText().toString());
				st.setString(8, this.textField_7.getText().toString());
				st.setString(9, this.textField_8.getText().toString());
				st.setString(10, this.textField_9.getText().toString());

				st.execute();
				st.close();
			} catch (Throwable var5) {
				if (st != null) {
					try {
						st.close();
					} catch (Throwable var4) {
						var5.addSuppressed(var4);
					}
				}
				throw var5;
			}

			if (st != null) {
				st.close();
			}
		} catch (SQLException var6) {
			var6.printStackTrace();
		}

	}

	private void addProfesor() {
		try {
			CallableStatement st = this.con.prepareCall("{call proiect.insertprofesor(?,?,?,?,?,?,?,?,?,?)}");

			try {
				st.setString(1, this.textField.getText().toString());
				st.setString(2, this.textField_1.getText().toString());
				st.setString(3, this.textField_2.getText().toString());
				st.setString(4, this.textField_3.getText().toString());
				st.setString(5, this.textField_4.getText().toString());
				st.setString(6, this.textField_5.getText().toString());
				st.setString(7, this.textField_6.getText().toString());
				st.setString(8, this.textField_7.getText().toString());
				st.setString(9, this.textField_8.getText().toString());
				st.setString(10, this.textField_9.getText().toString());

				st.execute();
				st.close();
			} catch (Throwable var5) {
				if (st != null) {
					try {
						st.close();
					} catch (Throwable var4) {
						var5.addSuppressed(var4);
					}
				}
				throw var5;
			}

			if (st != null) {
				st.close();
			}
		} catch (SQLException var6) {
			var6.printStackTrace();
		}

	}

	private void createActivityGroup() {
		try {
			CallableStatement st = this.con.prepareCall("{call proiect.createActivityGroup(?,?,?,?,?)}");

			try {
				st.setString(1, this.textField.getText().toString());
				st.setString(2, this.textField_1.getText().toString());
				st.setString(3, this.textField_2.getText().toString());
				st.setString(4, this.textField_3.getText().toString());
				st.setInt(5, Integer.parseInt(this.textField_4.getText()));
				st.execute();
				st.close();
			} catch (Throwable var5) {
				if (st != null) {
					try {
						st.close();
					} catch (Throwable var4) {
						var5.addSuppressed(var4);
					}
				}
				throw var5;
			}

			if (st != null) {
				st.close();
			}
		} catch (SQLException var6) {
			var6.printStackTrace();
		}

	}

	private void createGrupStudiu() {
		try {
			CallableStatement st = this.con.prepareCall("{call proiect.creategrup_studiu(?,?,?,?,?,?,?,?,?,?)}");

			try {
				st.setString(1, this.textField.getText().toString());
				st.setInt(2, Integer.parseInt(this.textField_1.getText()));
				st.setString(3, this.textField_2.getText().toString());
				st.setString(4, this.textField_3.getText().toString());
				st.setString(5, this.textField_4.getText().toString());
				st.setString(6, this.textField_5.getText().toString());
				st.setString(7, this.textField_6.getText().toString());
				st.setString(8, this.textField_7.getText().toString());
				st.setString(9, this.textField_8.getText().toString());
				st.setString(10, this.textField_9.getText().toString());

				st.execute();
				st.close();
			} catch (Throwable var5) {
				if (st != null) {
					try {
						st.close();
					} catch (Throwable var4) {
						var5.addSuppressed(var4);
					}
				}
				throw var5;
			}

			if (st != null) {
				st.close();
			}
		} catch (SQLException var6) {
			var6.printStackTrace();
		}
	}

	private void updateGrupStudiu() {
		try {
			CallableStatement st = this.con.prepareCall("{call proiect.updategrup_studiu(?,?,?,?,?,?,?,?,?,?)}");

			try {
				st.setString(1, this.textField.getText().toString());
				st.setString(2, this.textField_1.getText().toString());
				st.setInt(3, Integer.parseInt(this.textField_2.getText()));
				st.setString(4, this.textField_3.getText().toString());
				st.setString(5, this.textField_4.getText().toString());
				st.setString(6, this.textField_5.getText().toString());
				st.setString(7, this.textField_6.getText().toString());
				st.setString(8, this.textField_7.getText().toString());
				st.setString(9, this.textField_8.getText().toString());
				st.setString(10, this.textField_9.getText().toString());

				st.execute();
				st.close();
			} catch (Throwable var5) {
				if (st != null) {
					try {
						st.close();
					} catch (Throwable var4) {
						var5.addSuppressed(var4);
					}
				}
				throw var5;
			}

			if (st != null) {
				st.close();
			}
		} catch (SQLException var6) {
			var6.printStackTrace();
		}
	}

	private void updateProfesor() {
		try {
			CallableStatement st = this.con.prepareCall("{call proiect.updateprofesor(?,?,?,?,?,?,?,?,?,?)}");

			try {
				st.setString(1, this.textField.getText().toString());
				st.setString(2, this.textField_1.getText().toString());
				st.setString(3, this.textField_2.getText().toString());
				st.setString(4, this.textField_3.getText().toString());
				st.setString(5, this.textField_4.getText().toString());
				st.setString(6, this.textField_5.getText().toString());
				st.setString(7, this.textField_6.getText().toString());
				st.setString(8, this.textField_7.getText().toString());
				st.setString(9, this.textField_8.getText().toString());
				st.setString(10, this.textField_9.getText().toString());

				st.execute();
				st.close();
			} catch (Throwable var5) {
				if (st != null) {
					try {
						st.close();
					} catch (Throwable var4) {
						var5.addSuppressed(var4);
					}
				}
				throw var5;
			}

			if (st != null) {
				st.close();
			}
		} catch (SQLException var6) {
			var6.printStackTrace();
		}

	}

	private void addToCalendar() throws Throwable {
		try {
			CallableStatement st = this.con.prepareCall("{call proiect.addToCalendar(?,?,?,?,?,?,?)}");

			try {
				st.setString(1, this.textField.getText().toString());
				st.setString(2, this.textField_1.getText().toString());
				st.setString(3, this.textField_2.getText().toString());
				st.setString(4, this.textField_3.getText().toString());
				st.setString(5, this.textField_4.getText().toString());
				st.setString(6, this.textField_5.getText().toString());
				st.setString(7, this.textField_6.getText().toString());

				st.execute();
				st.close();
			} catch (Throwable var5) {
				if (st != null) {
					try {
						st.close();
					} catch (Throwable var4) {
						var5.addSuppressed(var4);
					}
				}
				throw var5;
			}

			if (st != null) {
				st.close();
			}
		} catch (SQLException var6) {
			var6.printStackTrace();
		}
	}

}
