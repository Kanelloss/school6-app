package gr.aueb.cf.schoolapp.viewcontroller;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.service.IStudentService;
import gr.aueb.cf.schoolapp.service.StudentServiceImpl;
import gr.aueb.cf.schoolapp.dao.IStudentDAO;
import gr.aueb.cf.schoolapp.dao.StudentDAOImpl;
import gr.aueb.cf.schoolapp.service.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.service.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolapp.dto.StudentInsertDTO;
import gr.aueb.cf.schoolapp.validator.StudentValidator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentsInsertFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField idText;
    private JTextField firstnameText;
    private JTextField lastnameText;
    private JLabel errorFirstname;
    private JLabel errorLastname;
    private final IStudentDAO studentDAO = new StudentDAOImpl();
    private final IStudentService studentService = new StudentServiceImpl(studentDAO);

    public StudentsInsertFrame() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
        setTitle("Εισαγωγή Μαθητή");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel idLabel = new JLabel("Κωδικός");
        idLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        idLabel.setForeground(new Color(0, 0, 255));
        idLabel.setBounds(30, 30, 50, 14);
        contentPane.add(idLabel);

        idText = new JTextField();
        idText.setBounds(100, 27, 150, 20);
        contentPane.add(idText);
        idText.setColumns(10);

        JLabel firstnameLabel = new JLabel("Όνομα");
        firstnameLabel.setForeground(new Color(0, 0, 255));
        firstnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        firstnameLabel.setBounds(30, 70, 50, 14);
        contentPane.add(firstnameLabel);

        firstnameText = new JTextField();
        firstnameText.setBounds(100, 67, 150, 20);
        contentPane.add(firstnameText);
        firstnameText.setColumns(10);

        JLabel lastnameLabel = new JLabel("Επώνυμο");
        lastnameLabel.setForeground(new Color(0, 0, 255));
        lastnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lastnameLabel.setBounds(30, 110, 60, 14);
        contentPane.add(lastnameLabel);

        lastnameText = new JTextField();
        lastnameText.setBounds(100, 107, 150, 20);
        contentPane.add(lastnameText);
        lastnameText.setColumns(10);

        errorFirstname = new JLabel("");
        errorFirstname.setForeground(new Color(255, 0, 0));
        errorFirstname.setBounds(100, 87, 200, 20);
        contentPane.add(errorFirstname);

        errorLastname = new JLabel("");
        errorLastname.setForeground(new Color(255, 0, 0));
        errorLastname.setBounds(100, 127, 200, 20);
        contentPane.add(errorLastname);

        JButton insertBtn = new JButton("Εισαγωγή");
        insertBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstname = firstnameText.getText().trim();
                String lastname = lastnameText.getText().trim();

                // Validate
                Map<String, String> errors = StudentValidator.validate(new StudentInsertDTO(idText.getText().trim(), firstname, lastname));

                errorFirstname.setText(errors.getOrDefault("firstname", ""));
                errorLastname.setText(errors.getOrDefault("lastname", ""));

                if (errors.isEmpty()) {
                    try {
                        studentService.addStudent(new StudentInsertDTO(idText.getText().trim(), firstname, lastname));
                        JOptionPane.showMessageDialog(null, "Μαθητής προστέθηκε επιτυχώς", "Εισαγωγή", JOptionPane.INFORMATION_MESSAGE);
                        idText.setText("");
                        firstnameText.setText("");
                        lastnameText.setText("");
                    } catch (StudentDAOException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        insertBtn.setForeground(new Color(0, 0, 255));
        insertBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
        insertBtn.setBounds(100, 150, 150, 30);
        contentPane.add(insertBtn);

        JButton closeBtn = new JButton("Κλείσιμο");
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getStudentsMenuFrame().setEnabled(true);
                Main.getStudentsInsertFrame().setVisible(false);
            }
        });
        closeBtn.setForeground(new Color(0, 0, 255));
        closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
        closeBtn.setBounds(260, 150, 100, 30);
        contentPane.add(closeBtn);
    }
}

