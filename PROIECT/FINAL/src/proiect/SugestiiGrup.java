package proiect;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SugestiiGrup {

	private String url = "jdbc:mysql://localhost/proiect";
	private String user = "root";
	private String password = "17052002Da";
	private Connection con;

	JComboBox comboBox;
	JButton butonSend;
	JButton butonBack;
	JTextArea textArea;
	JTextArea textAreaMesaj;
	private JFrame frameMA;

	public SugestiiGrup(String profil, String nume, String CNP) {
		connection();
		initialize(profil, nume, CNP);
	}

	private void initialize(String profil, String nume, String CNP) {
		frameMA = new JFrame("Sugestii de grupuri");
		frameMA.getContentPane().setLayout(null);

		buttons(profil, nume, CNP);

		butonBack = new JButton("Back");
		butonBack.setFont(new Font("Times new Roman", Font.BOLD, 14));
		butonBack.setBounds(0, 0, 80, 23);
		butonBack.addActionListener(e -> {
			if (profil.equals("Student")) {
				frameMA.dispose();
				new DupaLoginS(profil, nume, CNP);
			}
		});
		frameMA.getContentPane().add(butonBack);
		textArea = new JTextArea("Grupuri sugerate:");
		textArea.setFont(new Font("Times new roman", Font.ITALIC, 14));
		textArea.setEditable(false);
		JScrollPane sp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(10, 45, 464, 150);
		// frameMA.add(textArea);
		frameMA.add(sp);

		frameMA.getContentPane().add(sp);

		frameMA.setSize(500, 500);
		frameMA.setBounds(300, 300, 500, 500);
		frameMA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMA.setTitle("Grupuri de studii sugerate");
		frameMA.setVisible(true);
	}

	private void buttons(String profil, String nume, String CNP) {
		butonSend = new JButton("SHOW");
		butonSend.setFont(new Font("Times new roman", Font.BOLD, 14));
		butonSend.setBounds(200, 427, 80, 23);
		butonSend.addActionListener(e -> {
			sugestii(profil, nume, CNP);

		});
		frameMA.getContentPane().add(butonSend);
	}

	public void sugestii(String profil, String nume, String CNP) {
		String curs = new String();
		String[] det = new String[1000];
		int contor = -1;
		try {
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery("call proiect.showSugestii('" + CNP + "')");
			ResultSetMetaData rsmd = rs.getMetaData();
			int rows = 0;
			while (rs.next()) {
				rows++;
			}
			if (rows == 0)
				System.out.println("Nu ai grupuri programate");
			else {
				rs.beforeFirst();
				while (rs.next())
					for (int i = 1; i <= rsmd.getColumnCount(); i++)
						det[++contor] = rs.getObject(i).toString();
			}
			for (int i = 0; i <= contor; i++) {
				curs = curs + det[i] + "\n";
			}
			textArea.setText(curs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
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
