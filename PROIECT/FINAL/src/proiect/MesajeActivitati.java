package proiect;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MesajeActivitati {

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

	public MesajeActivitati(String profil, String nume, String CNP) {
		connection();
		initialize(profil, nume, CNP);
	}

	private void initialize(String profil, String nume, String CNP) {
		frameMA = new JFrame();
		frameMA.getContentPane().setLayout(null);

		buttons(profil, nume, CNP);

		butonBack = new JButton("Back");
		butonBack.setFont(new Font("Times new roman", Font.BOLD, 14));
		butonBack.setBounds(0, 0, 80, 25);
		butonBack.addActionListener(e -> {
			frameMA.dispose();
			new DupaLoginS(profil, nume, CNP);
		});
		frameMA.getContentPane().add(butonBack);

		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane sp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(10, 45, 464, 200);
		// frameMA.add(textArea);
		frameMA.add(sp);

		textAreaMesaj = new JTextArea();
		textAreaMesaj.setBounds(10, 250, 464, 58);
		frameMA.getContentPane().add(textAreaMesaj);

		comboBox = new JComboBox();
		comboBox.setBounds(10, 350, 117, 22);
		comboBox.setModel(new DefaultComboBoxModel(activitati(nume, CNP)));
		comboBox.setFont(new Font("times new roman", Font.ITALIC, 14));
		activitati(nume, CNP);
		afisMesaje(comboBox.getSelectedItem().toString(), textAreaMesaj.getText());

		frameMA.getContentPane().add(sp);

		frameMA.getContentPane().add(comboBox);
		frameMA.setSize(500, 500);
		frameMA.setBounds(300, 300, 500, 500);
		frameMA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMA.setVisible(true);
	}

	private void buttons(String profil, String nume, String CNP) {
		butonSend = new JButton("Trimite");
		butonSend.setFont(new Font("times new roman", Font.BOLD, 14));
		butonSend.setBounds(357, 350, 117, 22);
		butonSend.addActionListener(e -> {
			afisMesaje(comboBox.getSelectedItem().toString(), nume + ":\n" + textAreaMesaj.getText());
		});
		frameMA.setBackground(new Color(255, 153, 255));
		frameMA.setTitle("Mesaje grup");
		frameMA.getContentPane().add(butonSend);
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

	private String[] activitati(String nume, String CNP) {
		String[] np = nume.split(" ");
		String[] act = new String[6];
		try {
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery("call proiect.showGrupuri('" + np[0] + "','" + np[1] + "','" + CNP + "');");
			ResultSetMetaData rsmd = rs.getMetaData();
			int rows = 0;
			while (rs.next())
				rows++;
			if (rows == 0)
				return act;
			else {
				int contor = -1;
				rs.beforeFirst();
				while (rs.next())
					for (int i = 1; i <= rsmd.getColumnCount(); i++)
						act[++contor] = rs.getObject(i).toString();
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return act;
	}

	private void afisMesaje(String x, String mesajTrimis) {
		ArrayList<String> randuri = new ArrayList<>();
		try {
			File f = new File("D:\\Facultate\\Anul 2\\BD\\PROIECT\\TXT-uri\\Mesaje\\" + x + ".txt");
			Scanner citesc = new Scanner(f);
			while (citesc.hasNext())
				randuri.add(citesc.nextLine());
			citesc.close();

		} catch (Exception e) {
			System.out.println("Fisierul nu a fost gasit");
		}

		String text = new String();
		for (String z : randuri)
			text = text + " " + z + "\n";
		text = text + " " + mesajTrimis + "\n";
		textArea.setText(text);
		try {
			FileWriter fw = new FileWriter("D:\\Facultate\\Anul 2\\BD\\PROIECT\\TXT-uri\\Mesaje\\" + x + ".txt");
			randuri.add(mesajTrimis);
			for (String z : randuri)
				fw.write(z + "\n");
			fw.close();
		} catch (Exception e) {
			System.out.println("Fisierul nu a fost gasit");
		}
	}

}
