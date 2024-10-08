package gr.aueb.cf.schoolapp.viewcontroller;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.StudentDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.service.IStudentService;
import gr.aueb.cf.schoolapp.service.StudentServiceImpl;
import gr.aueb.cf.schoolapp.dao.IStudentDAO;
import gr.aueb.cf.schoolapp.service.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolapp.dto.StudentReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.validator.StudentValidator;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class StudentsUpdateDeleteFrame extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable studentsTable;
    private DefaultTableModel model = new DefaultTableModel();
    private JLabel lastnameSearchLabel;
    private JTextField lastnameSearchText;
    private JButton btnSearch;
    private JLabel idLabel;
    private JTextField idText;
    private JLabel firstnameLabel;
    private JTextField firstnameText;
    private JLabel lastnameLabel;
    private JTextField lastnameText;
    private JLabel errorFirstname;
    private JLabel errorLastname;
    private JPanel panel;
    private JButton updateBtn;
    private JButton deleteBtn;
    private JButton closeBtn;
    private final IStudentDAO studentDAO = new StudentDAOImpl();
    private final IStudentService studentService = new StudentServiceImpl(studentDAO);

    public StudentsUpdateDeleteFrame() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
        setTitle("Ενημέρωση / Διαγραφή Μαθητή");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                lastnameSearchText.setText("");
                buildTable();	// initial rendering
                idText.setText("");
                firstnameText.setText("");
                lastnameText.setText("");
            }
            @Override
            public void windowActivated(WindowEvent e) {
                lastnameSearchText.setText("");
                buildTable();	// refresh after update / delete
                idText.setText("");
                firstnameText.setText("");
                lastnameText.setText("");
            }
        });
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 870, 632);
        setLocationRelativeTo(null); // Center the window
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        studentsTable = new JTable();
        studentsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                idText.setText((String) model.getValueAt(studentsTable.getSelectedRow(), 0));
                firstnameText.setText((String) model.getValueAt(studentsTable.getSelectedRow(), 1));
                lastnameText.setText((String) model.getValueAt(studentsTable.getSelectedRow(), 2));
            }
        });
        studentsTable.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"Κωδικός", "Όνομα", "Επώνυμο"}
        ));

        model = (DefaultTableModel) studentsTable.getModel();

        studentsTable.setBounds(53, 53, 387, 498);
        contentPane.add(studentsTable);

        JScrollPane scrollPane = new JScrollPane(studentsTable);
        scrollPane.setBounds(53, 53, 387, 498);
        contentPane.add(scrollPane);

        lastnameSearchLabel = new JLabel("Επώνυμο");
        lastnameSearchLabel.setForeground(new Color(128, 0, 64));
        lastnameSearchLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lastnameSearchLabel.setBounds(57, 11, 58, 14);
        contentPane.add(lastnameSearchLabel);

        lastnameSearchText = new JTextField();
        lastnameSearchText.setBounds(114, 8, 177, 20);
        contentPane.add(lastnameSearchText);
        lastnameSearchText.setColumns(10);

        btnSearch = new JButton("Αναζήτηση");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buildTable();
            }
        });
        btnSearch.setForeground(new Color(0, 0, 255));
        btnSearch.setBounds(301, 7, 124, 23);
        contentPane.add(btnSearch);

        idLabel = new JLabel("Κωδικός");
        idLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        idLabel.setForeground(new Color(0, 0, 255));
        idLabel.setBounds(497, 80, 49, 14);
        contentPane.add(idLabel);

        idText = new JTextField();
        idText.setEditable(false);
        idText.setBounds(556, 77, 96, 20);
        contentPane.add(idText);
        idText.setColumns(10);

        firstnameLabel = new JLabel("Όνομα");
        firstnameLabel.setForeground(new Color(0, 0, 255));
        firstnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        firstnameLabel.setBounds(505, 125, 41, 14);
        contentPane.add(firstnameLabel);

        firstnameText = new JTextField();
        firstnameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String inputFirstname = firstnameText.getText().trim();
                validateFirstname(inputFirstname);
            }
        });
        firstnameText.setBounds(556, 122, 177, 20);
        contentPane.add(firstnameText);
        firstnameText.setColumns(10);

        lastnameLabel = new JLabel("Επώνυμο");
        lastnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lastnameLabel.setForeground(new Color(0, 0, 255));
        lastnameLabel.setBounds(497, 175, 49, 14);
        contentPane.add(lastnameLabel);

        lastnameText = new JTextField();
        lastnameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String inputLastname = lastnameText.getText().trim();
                validateLastname(inputLastname);
            }
        });
        lastnameText.setBounds(556, 172, 177, 20);
        contentPane.add(lastnameText);
        lastnameText.setColumns(10);

        errorFirstname = new JLabel("");
        errorFirstname.setFont(new Font("Tahoma", Font.PLAIN, 10));
        errorFirstname.setForeground(new Color(255, 0, 0));
        errorFirstname.setBounds(556, 147, 300, 20);
        contentPane.add(errorFirstname);

        errorLastname = new JLabel("");
        errorLastname.setFont(new Font("Tahoma", Font.PLAIN, 10));
        errorLastname.setForeground(new Color(255, 0, 0));
        errorLastname.setBounds(556, 203, 300, 15);
        contentPane.add(errorLastname);

        panel = new JPanel();
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.setBounds(473, 53, 335, 200);
        contentPane.add(panel);
        panel.setLayout(null);

        updateBtn = new JButton("Ενημέρωση");
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Map<String, String> errors;
                String firstnameMessage;
                String lastnameMessage;
                Student student;

                if (idText.getText().trim().isEmpty()) return;

                // Data Binding
                try {
                    StudentUpdateDTO updateDTO = new StudentUpdateDTO();
                    updateDTO.setId(Integer.parseInt(idText.getText().trim()));
                    updateDTO.setFirstname(firstnameText.getText().trim());
                    updateDTO.setLastname(lastnameText.getText().trim());

                    // Validate
                    errors = StudentValidator.validate(updateDTO);

                    // If errors, assign messages to UI
                    if (!errors.isEmpty()) {
                        firstnameMessage = errors.getOrDefault("firstname", "");
                        lastnameMessage = errors.getOrDefault("lastname", "");

                        errorFirstname.setText(firstnameMessage);
                        errorLastname.setText(lastnameMessage);
                        return;
                    }

                    // On validation success, call the update service
                    student = studentService.updateStudent(updateDTO);

                    // Results mapped to ReadOnlyDTO
                    StudentReadOnlyDTO readOnlyDTO = mapToReadOnlyDTO(student);
                    // Feedback
                    JOptionPane.showMessageDialog(null, "Ο μαθητής με id: " + readOnlyDTO.getId()
                            + " ενημερώθηκε επιτυχώς", "Ενημέρωση", JOptionPane.INFORMATION_MESSAGE);

                } catch (StudentDAOException | StudentNotFoundException e1) {
                    e1.printStackTrace();
                    // On failure, show message
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Σφάλμα",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        updateBtn.setForeground(new Color(0, 0, 255));
        updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
        updateBtn.setBounds(473, 315, 156, 59);
        contentPane.add(updateBtn);

        deleteBtn = new JButton("Διαγραφή");
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int response;

                try {
                    if (idText.getText().trim().isEmpty()) return;
                    int inputId = Integer.parseInt(idText.getText().trim());

                    response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος/η;", "Προσοχή", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        studentService.deleteStudent(inputId);
                        JOptionPane.showMessageDialog(null, "Επιτυχής Διαγραφή", "Διαγραφή", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (StudentDAOException | StudentNotFoundException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        deleteBtn.setForeground(Color.BLUE);
        deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
        deleteBtn.setBounds(652, 315, 156, 59);
        contentPane.add(deleteBtn);

        closeBtn = new JButton("Κλείσιμο");
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getStudentsMenuFrame().setEnabled(true);
                Main.getStudentsUpdateDeleteFrame().setVisible(false);
            }
        });
        closeBtn.setForeground(new Color(0, 0, 255));
        closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
        closeBtn.setBounds(652, 465, 156, 59);
        contentPane.add(closeBtn);
    }

    private void buildTable() {
        Vector<String> vector;
        List<StudentReadOnlyDTO> readOnlyDTOS = new ArrayList<>();
        StudentReadOnlyDTO readOnlyDTO;




        try {
            String lastname = lastnameSearchText.getText().trim();

            List<Student> students = studentService.getStudentsByLastname(lastname);
            for (Student student : students) {
                readOnlyDTO = mapToReadOnlyDTO(student);
                readOnlyDTOS.add(readOnlyDTO);
            }

            for (int i = model.getRowCount() - 1; i >=0; i--) {
                model.removeRow(i);
            }

            for (StudentReadOnlyDTO studentReadOnlyDTO : readOnlyDTOS) {
                vector = new Vector<>(3);
                vector.add(String.valueOf(studentReadOnlyDTO.getId()));
                vector.add(studentReadOnlyDTO.getFirstname());
                vector.add(studentReadOnlyDTO.getLastname());
                model.addRow(vector);
            }

        } catch (StudentDAOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Σφάλμα", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validateFirstname(String inputFirstname) {
        if (inputFirstname.equals("")) {
            errorFirstname.setText("Το όνομα είναι υποχρεωτικό");
        }

        if (!inputFirstname.equals("")) {
            errorFirstname.setText("");
        }
    }

    private void validateLastname(String inputLastname) {
        if (inputLastname.equals("")) {
            errorLastname.setText("Το επώνυμο είναι υποχρεωτικό");
        }

        if (!inputLastname.equals("")) {
            errorLastname.setText("");
        }
    }

    private StudentReadOnlyDTO mapToReadOnlyDTO(Student student) {
        return new StudentReadOnlyDTO(student.getId(), student.getFirstname(), student.getLastname());
    }
}

