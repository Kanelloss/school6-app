package gr.aueb.cf.schoolapp.viewcontroller;


import gr.aueb.cf.schoolapp.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;

public class MainMenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Connection connection;

	/**
	 * Create the frame.
	 */
	public MainMenuFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
		
		setForeground(Color.WHITE);
		setTitle("Ποιότητα στην Εκπαίδευση");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 927, 666);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator headerSeperator = new JSeparator();
		headerSeperator.setBounds(0, 71, 935, 1);
		contentPane.add(headerSeperator);
		
		JPanel header = new JPanel();
		header.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		header.setBackground(new Color(65, 105, 225));
		header.setBounds(0, 0, 913, 72);
		contentPane.add(header);
		header.setLayout(null);
		
		JLabel CodingFactoryLabel = new JLabel("Coding Factory");
		CodingFactoryLabel.setBounds(368, 26, 148, 20);
		CodingFactoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		header.add(CodingFactoryLabel);
		CodingFactoryLabel.setVerticalAlignment(SwingConstants.TOP);
		CodingFactoryLabel.setForeground(Color.WHITE);
		CodingFactoryLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		CodingFactoryLabel.setBackground(Color.WHITE);
		
		JButton teachersBtn = new JButton("...");
		teachersBtn.setSelectedIcon(null);
		teachersBtn.setIcon(null);
		teachersBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		teachersBtn.setForeground(new Color(51, 102, 255));
		teachersBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersMenuFrame().setVisible(true);
				Main.getMainMenuFrame().setEnabled(false);
			}
		});
		teachersBtn.setBackground(new Color(255, 255, 255));
		teachersBtn.setBounds(10, 122, 48, 50);
		contentPane.add(teachersBtn);
		
		JLabel teachersLabel = new JLabel("Εκπαιδευτές");
		teachersLabel.setForeground(new Color(65, 105, 225));
		teachersLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		teachersLabel.setBounds(79, 127, 114, 39);
		contentPane.add(teachersLabel);
		
		JButton studentsBtn = new JButton("...");
		studentsBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		studentsBtn.setForeground(new Color(51, 102, 255));
		studentsBtn.setBackground(new Color(255, 255, 255));
		studentsBtn.setBounds(10, 222, 48, 50);
		contentPane.add(studentsBtn);
		
		JLabel studentsLabel = new JLabel("Εκπαιδευόμενοι");
		studentsLabel.setForeground(new Color(65, 105, 225));
		studentsLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		studentsLabel.setBounds(79, 221, 137, 50);
		contentPane.add(studentsLabel);
		
		JPanel footer = new JPanel();
		footer.setBackground(Color.LIGHT_GRAY);
		footer.setBounds(-15, 571, 950, 70);
		contentPane.add(footer);
		footer.setLayout(null);
		
		JLabel manual = new JLabel("Εγχειρίδιο Χρήσης");
		manual.setBounds(25, 10, 127, 37);
		footer.add(manual);
		manual.setFont(new Font("Tahoma", Font.PLAIN, 14));
		manual.setForeground(new Color(65, 105, 225));
	}
	
	
}
