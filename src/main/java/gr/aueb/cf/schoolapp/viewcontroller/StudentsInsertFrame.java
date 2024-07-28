package gr.aueb.cf.schoolapp.viewcontroller;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.IStudentDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.dto.StudentReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.IStudentService;
import gr.aueb.cf.schoolapp.service.IStudentServiceImpl;
import gr.aueb.cf.schoolapp.service.IStudentServiceImpl;
import gr.aueb.cf.schoolapp.dao.IStudentDAO;
import gr.aueb.cf.schoolapp.dao.IStudentDAOImpl;
import gr.aueb.cf.schoolapp.service.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolapp.dto.StudentInsertDTO;
import gr.aueb.cf.schoolapp.validator.StudentValidator;
import gr.aueb.cf.schoolapp.validator.TeacherValidator;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public class StudentsInsertFrame extends JFrame {

    // Wiring
    private final IStudentDAO studentDAO = new IStudentDAOImpl();
    private final IStudentService studentService = new IStudentServiceImpl(studentDAO);

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField idText;
    private JTextField firstnameText;
    private JTextField lastnameText;
    private JLabel errorFirstname;
    private JLabel errorLastname;



    public StudentsInsertFrame() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
        setTitle("Εισαγωγή Μαθητών");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                firstnameText.setText("");
                lastnameText.setText("");
            }
        });
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
        firstnameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String inputFirstname;
                inputFirstname = firstnameText.getText().trim();

                if (inputFirstname.equals("")) {
                    errorFirstname.setText("Το όνομα είναι υποχρεωτικό");
                }

                if (!inputFirstname.equals("")) {
                    errorFirstname.setText("");
                }
            }
        });
        firstnameText.setFont(new Font("Tahoma", Font.PLAIN, 12));
        firstnameText.setBounds(100, 67, 150, 20);
        contentPane.add(firstnameText);
        firstnameText.setColumns(10);

        JLabel lastnameLabel = new JLabel("Επώνυμο");
        lastnameLabel.setForeground(new Color(0, 0, 255));
        lastnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lastnameLabel.setBounds(30, 110, 60, 14);
        contentPane.add(lastnameLabel);

        lastnameText = new JTextField();
        lastnameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String inputLastname;
                inputLastname = lastnameText.getText().trim();

                if (inputLastname.equals("")) {
                    errorLastname.setText("Το επώνυμο είναι υποχρεωτικό");
                }

                if (!inputLastname.equals("")) {
                    errorLastname.setText("");
                }

            }
        });
        lastnameText.setBounds(100, 107, 150, 20);
        contentPane.add(lastnameText);
        lastnameText.setColumns(10);

        JPanel panel = new JPanel();
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.setBounds(21, 23, 354, 179);
        contentPane.add(panel);
        panel.setLayout(null);

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

                Map<String, String> errors;
                StudentInsertDTO insertDTO = new StudentInsertDTO();
                String firstnameMessage;
                String lastnameMessage;
                Student student;

                try {

                    // Data Binding
                    insertDTO.setFirstname(firstnameText.getText().trim());
                    insertDTO.setLastname(lastnameText.getText().trim());

                    // Validation
                    errors = TeacherValidator.validate(insertDTO);

                    if (!errors.isEmpty()) {
                        firstnameMessage = errors.getOrDefault("firstname", "");
                        lastnameMessage = errors.getOrDefault("lastname", "");


                        errorFirstname.setText(firstnameMessage);

                        if (!lastnameMessage.isEmpty()) {
                            errorLastname.setText(lastnameMessage);
                        }

                        if (lastnameMessage.isEmpty()) {
                            errorLastname.setText("");
                        }

                        return;

                    }

                    student = studentService.insertStudent(insertDTO);
                    StudentReadOnlyDTO readOnlyDTO = mapToReadOnlyDTO(student);

                    JOptionPane.showMessageDialog(null, "Ο μαθητής με επίθετο: " +
                                    readOnlyDTO.getLastname() + " εισήχθη επιτυχώς",
                            "Εισαγωγή μαθητή", JOptionPane.INFORMATION_MESSAGE);

                } catch (StudentDAOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Σφάλμα Εισαγωγής", "Προσοχή", JOptionPane.INFORMATION_MESSAGE);
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

    private StudentReadOnlyDTO mapToReadOnlyDTO(Student student) {
        return new StudentReadOnlyDTO(student.getId(),student.getFirstname(),student.getLastname());
    }
}

