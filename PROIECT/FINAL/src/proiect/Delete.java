package proiect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Delete {

	private String url = "jdbc:mysql://127.0.0.1:3306/proiect";
	private String user = "root";
	private String password = "17052002Da";
	private Connection con;

	private JFrame frameDelete;
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

	public Delete(String profil, String nume, String CNP, String tabela) {
		connection();
		initialize(profil, nume, CNP, tabela);
	}

	private void initialize(String profil, String nume, String CNP, String tabela) {
		frameDelete = new JFrame();
		frameDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frameDelete.setTitle("Delete");
		frameDelete.setBounds(100, 100, 742, 529);
		frameDelete.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameDelete.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frameDelete.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel label = new JLabel(profil);
		label.setForeground(Color.BLACK);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(557, 20, 135, 32);
		panel.add(label);

		JLabel lblTeRugamSa = new JLabel("Te rugam sa introduci datele pe care vrei sa le stergi");
		lblTeRugamSa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTeRugamSa.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeRugamSa.setBounds(115, 40, 431, 57);
		panel.add(lblTeRugamSa);

		JButton button = new JButton("Confirma");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] raspunsuri = { "Da", "Nu", "Cancel" };
				int raspuns = JOptionPane.showOptionDialog(null,
						"Aceasta opreatiune va aduce modifica asupra bazei de date. Continuati?", "ATENTIE!",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, raspunsuri, 0);
				if (raspuns == 0) {
					// frameDelete.dispose();
					// System.out.println("Operatiune realizata cu succes");
					// JOptionPane op = new JOptionPane("Operatiune realizata cu succes");

					if (textField.getText().equals("") && textField_1.getText().equals("")
							&& textField_2.getText().equals("") && textField_3.getText().equals("")
							&& textField_4.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Te rugam sa introduci datele", "ATENTIE",
								JOptionPane.WARNING_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Operatiunea a fost realizata cu succes", "SUCCES",
								JOptionPane.PLAIN_MESSAGE);
					if (tabela.equals("cursuri")) {
						deleteCurs();
						cursuri();
					}
					if (tabela.equals("grup_studiu")) {
						deleteGrupStudiu();
					}
					if (tabela.equals("profesor")) {
						deleteProfesor();
					}
					if (tabela.equals("student")) {
						deleteStudent();
					}

				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.setBounds(557, 430, 120, 32);
		panel.add(button);

		JButton button_1 = new JButton("Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] raspunsuri = { "Da", "Nu", "Cancel" };
				int raspuns = JOptionPane.showOptionDialog(null, "Esti sigur ca vrei sa te intorci?", "ATENTIE!",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, raspunsuri, 0);
				if (raspuns == 0) {
					frameDelete.dispose();
					if (label.getText().equals("Super-administrator") || label.getText().equals("Administrator")) {
						DupaLoginA frameA = new DupaLoginA(profil, nume, CNP);
					}
					if (label.getText().equals("Profesor")) {
						DupaLoginP frameP = new DupaLoginP(profil, nume, CNP);
					}
					if (label.getText().equals("Student")) {
						DupaLoginS frameS = new DupaLoginS(profil, nume, CNP);
					}
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_1.setBounds(22, 430, 120, 32);
		panel.add(button_1);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(10, 114, 96, 19);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setBounds(10, 143, 96, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setBounds(10, 172, 96, 19);
		panel.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_3.setBounds(10, 201, 96, 19);
		panel.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_4.setBounds(10, 230, 96, 19);
		panel.add(textField_4);
		textField_4.setColumns(10);

		JLabel label_3 = new JLabel("New label");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBounds(10, 19, 120, 35);
		label_3.setText(tabela);
		panel.add(label_3);

		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_5.setBounds(10, 259, 96, 19);
		panel.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_6.setBounds(10, 288, 96, 19);
		panel.add(textField_6);
		textField_6.setColumns(10);

		textField_7 = new JTextField();
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_7.setBounds(10, 317, 96, 19);
		panel.add(textField_7);
		textField_7.setColumns(10);

		textField_8 = new JTextField();
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_8.setBounds(10, 346, 96, 19);
		panel.add(textField_8);
		textField_8.setColumns(10);

		textField_9 = new JTextField();
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_9.setBounds(10, 375, 96, 19);
		panel.add(textField_9);
		textField_9.setColumns(10);

		frameDelete.setVisible(true);
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

	private void deleteCurs() {

		try {
			CallableStatement st = this.con.prepareCall("{call proiect.deletecurs(?,?,?,?,?,?,?,?,?,?)}");

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
				;
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

	private void deleteGrupStudiu() {
		try {
			CallableStatement st = this.con.prepareCall("{call proiect.deletegrup_studiu(?,?,?,?,?,?,?,?,?,?)}");

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
				;
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

	private void deleteProfesor() {
		try {
			CallableStatement st = this.con.prepareCall("{call proiect.deleteprofesor(?,?,?,?,?,?,?,?,?,?)}");

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
				;
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

	private void deleteStudent() {
		try {
			CallableStatement st = this.con.prepareCall("{call proiect.deletestudent(?,?,?,?,?,?,?,?,?,?)}");

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

	private String[] cursuri() {
		String[] curs = new String[6];
		String nume = "PC";
		try {
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery("call proiect.showCourses('" + nume + "');");
			ResultSetMetaData rsmd = rs.getMetaData();
			int rows = 0;
			while (rs.next())
				rows++;
			if (rows == 0)
				return curs;
			else {
				int contor = -1;
				rs.beforeFirst();
				while (rs.next()) {
					for (int i = 1; i <= rsmd.getColumnCount(); i++)
						curs[++contor] = rs.getObject(i).toString();
				}
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return curs;
	}

}
