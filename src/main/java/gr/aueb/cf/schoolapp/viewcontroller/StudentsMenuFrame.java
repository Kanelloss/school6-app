package gr.aueb.cf.schoolapp.viewcontroller;

import gr.aueb.cf.schoolapp.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentsMenuFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public StudentsMenuFrame() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
        setTitle("Μενού Μαθητών");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 427, 358);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton studentsViewBtn = new JButton("Προβολή Μαθητών");
        studentsViewBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getStudentsUpdateDeleteFrame().setVisible(true);
                Main.getStudentsMenuFrame().setEnabled(false);
            }
        });
        studentsViewBtn.setForeground(new Color(0, 0, 255));
        studentsViewBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
        studentsViewBtn.setBounds(124, 57, 158, 52);
        contentPane.add(studentsViewBtn);

        JButton insertBtn = new JButton("Εισαγωγή Μαθητή");
        insertBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getStudentsInsertFrame().setVisible(true);
                Main.getStudentsMenuFrame().setEnabled(false);
            }
        });
        insertBtn.setForeground(Color.BLUE);
        insertBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
        insertBtn.setBounds(124, 147, 158, 52);
        contentPane.add(insertBtn);

        JButton closeBtn = new JButton("Κλείσιμο");
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getMainMenuFrame().setEnabled(true);
                Main.getStudentsMenuFrame().setVisible(false);
            }
        });
        closeBtn.setForeground(new Color(0, 0, 255));
        closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
        closeBtn.setBounds(271, 272, 96, 38);
        contentPane.add(closeBtn);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 241, 393, 1);
        contentPane.add(separator);
    }
}

