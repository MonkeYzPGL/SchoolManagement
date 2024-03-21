package proiect;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PrimaPagina {

	private JFrame framePP;

	private JPanel panel;

	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;

	private JRadioButton rButton;
	private JRadioButton rButton_1;
	private JRadioButton rButton_2;
	private JRadioButton rButton_3;
	private ButtonGroup checkboxes;

	private JButton button;

	public PrimaPagina() {
		initialize();
	}

	private void labels() {
		// labels
		label = new JLabel("Super-administrator");
		label.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		label.setBounds(50, 180, 143, 22);
		panel.add(label);

		label_1 = new JLabel("Administrator");
		label_1.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		label_1.setBounds(50, 210, 86, 22);
		panel.add(label_1);

		label_2 = new JLabel("Profesor");
		label_2.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		label_2.setBounds(350, 180, 62, 22);
		panel.add(label_2);

		label_3 = new JLabel("Student");
		label_3.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		label_3.setBounds(350, 210, 62, 22);
		panel.add(label_3);

		label_4 = new JLabel("CHOOSE A TYPE OF USER");
		label_4.setForeground(Color.red);
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_4.setBounds(160, 50, 450, 60);
		panel.add(label_4);

	}

	private void radioButtons() {
		// radio buttons
		rButton = new JRadioButton("");
		rButton.setBounds(190, 180, 50, 23);
		rButton.setBackground(Color.cyan);
		panel.add(rButton);

		rButton_1 = new JRadioButton("");
		rButton_1.setBounds(190, 210, 50, 23);
		rButton_1.setBackground(Color.cyan);
		panel.add(rButton_1);

		rButton_2 = new JRadioButton("");
		rButton_2.setBounds(420, 180, 50, 23);
		rButton_2.setBackground(Color.cyan);
		panel.add(rButton_2);

		rButton_3 = new JRadioButton("");
		rButton_3.setBounds(420, 210, 50, 23);
		rButton_3.setBackground(Color.cyan);
		panel.add(rButton_3);

		// creez un grup pentru a putea selecta doar un singur buton si nu mai multe
		// dintr o data
		checkboxes = new ButtonGroup();
		checkboxes.add(rButton);
		checkboxes.add(rButton_1);
		checkboxes.add(rButton_2);
		checkboxes.add(rButton_3);
	}

	private void buttons() {

		// buton
		button = new JButton("Confirm");
		button.setFont(new Font("Times New Roman", Font.BOLD, 16));
		button.setForeground(Color.blue);
		button.setFocusable(false);
		button.addActionListener(e -> {

			if (rButton.isSelected()) {
				framePP.dispose();
				new Login(label.getText());
			}
			if (rButton_1.isSelected()) {
				framePP.dispose();
				new Login(label_1.getText());
			}
			if (rButton_2.isSelected()) {
				framePP.dispose();
				new Login(label_2.getText());
			}
			if (rButton_3.isSelected()) {
				framePP.dispose();
				new Login(label_3.getText());
			}
		});
		button.setBounds(200, 320, 99, 22);
		panel.add(button);
	}

	private void initialize() {

//frameul
		framePP = new JFrame();
		framePP.setTitle("Choose type of user");
		framePP.setSize(500, 500);
		framePP.setBounds(300, 300, 500, 500);
		framePP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePP.getContentPane().setLayout(null);

//panel
		panel = new JPanel();
		panel.setBounds(0, 0, 500, 500);
		framePP.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.CYAN);

		labels();
		radioButtons();
		buttons();
		// framePP.setIconImage(new
		// ImageIcon(getClass().getResource("wink1.png")).getImage());
		framePP.setVisible(true);

	}

}
