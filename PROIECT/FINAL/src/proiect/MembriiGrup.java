package proiect;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MembriiGrup {

	private String url = "jdbc:mysql://localhost/proiect";
	private String user = "root";
	private String password = "17052002Da";
	private Connection con;

	private JFrame frameO;

	private JTextField textField_1;

	private JLabel label_1;

	private JTextArea textArea = new JTextArea();

	public MembriiGrup(String profil, String nume, String CNP) {
		connection();
		initialize(profil, nume, CNP);
	}

	private void textFields() {
		// testFields

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(70, 300, 139, 28);

		frameO.add(textField_1);

	}

	private void labels(String profil) {

		label_1 = new JLabel("Nume grup:");
		label_1.setFont(new Font("times new roman", Font.ITALIC, 14));
		label_1.setBounds(2, 300, 80, 28);
		frameO.add(label_1);

	}

	private void buttons(String profil, String nume, String CNP) {
		// buttons
		JButton button = new JButton("Vezi membri");
		button.setBounds(190, 427, 120, 23);
		frameO.getContentPane().add(button);
		button.addActionListener(e -> listamembrii());

		JButton buttonBack = new JButton("Back");

	}

	private void initialize(String profil, String nume, String CNP) {
		frameO = new JFrame();
		frameO.getContentPane().setLayout(null);

		buttons("Student", nume, CNP);

		JButton buttonBack = new JButton("Back");

		buttonBack.setBounds(0, 0, 89, 23);
		buttonBack.addActionListener(e -> {
			frameO.dispose();
			new DupaLoginS(profil, nume, CNP);
		});
		frameO.getContentPane().add(buttonBack);

		textFields();
		labels("Student");

		textArea = new JTextArea("Membrii grupului");
		textArea.setFont(new Font("Times new roman", Font.ITALIC, 14));
		textArea.setEditable(false);
		JScrollPane sp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(10, 45, 364, 150);
		// frameMA.add(textArea);
		frameO.add(sp);

		frameO.getContentPane().add(sp);

		frameO.setSize(500, 500);
		frameO.setBounds(300, 300, 500, 500);
		frameO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameO.setTitle("Vizualizare Membri Grup");
		frameO.setFont(new Font("times new roman", Font.ITALIC, 14));
		frameO.setVisible(true);

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

	private void listamembrii() {

		String curs = new String();
		String[] det = new String[1000];
		String x = null;
		x = this.textField_1.getText().toString();
		int contor = -1;
		try {
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery("call proiect.showMembers('" + x + "')");
			ResultSetMetaData rsmd = rs.getMetaData();
			int rows = 0;
			while (rs.next()) {
				rows++;
			}
			if (rows == 0)
				System.out.println("Nu sunt membrii");
			else {
				rs.beforeFirst();
				while (rs.next())
					for (int i = 1; i <= rsmd.getColumnCount(); i++)
						det[++contor] = rs.getObject(i).toString();
			}
			for (int i = 0; i <= contor; i++) {
				if (i % 2 == 0)
					curs = curs + "\n";
				curs = curs + det[i] + " ";
			}
			textArea.setText(curs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
