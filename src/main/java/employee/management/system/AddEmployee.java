package employee.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class AddEmployee extends JFrame implements ActionListener {

    private static final String[] EDUCATION_OPTIONS = {
        "BBA", "BCA", "BA", "BSC", "B.COM", "BTech", "MBA",
        "MCA", "MA", "MTech", "MSC", "PHD"
    };

    private final Random random = new Random();
    private final int employeeId = random.nextInt(999999);

    private JTextField tfName;
    private JTextField tfFatherName;
    private JTextField tfAddress;
    private JTextField tfPhone;
    private JTextField tfAadhar;
    private JTextField tfEmail;
    private JTextField tfSalary;
    private JTextField tfDesignation;
    private JDateChooser dateOfBirthChooser;
    private JComboBox<String> educationComboBox;
    private JLabel lblEmployeeId;
    private JButton addButton;
    private JButton backButton;

    public AddEmployee() {
        initComponents();
        setFrameProperties();
    }

    private void initComponents() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Add Employee Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        addLabelAndTextField("Name", 50, 150, 200, 150, 150, true);
        addLabelAndTextField("Father's Name", 400, 150, 600, 150, 150, true);

        JLabel dobLabel = new JLabel("Date of Birth");
        dobLabel.setBounds(50, 200, 150, 30);
        dobLabel.setFont(new Font("serif", Font.PLAIN, 20));
        add(dobLabel);

        dateOfBirthChooser = new JDateChooser();
        dateOfBirthChooser.setBounds(200, 200, 150, 30);
        add(dateOfBirthChooser);

        JLabel salaryLabel = new JLabel("Salary");
        salaryLabel.setBounds(400, 200, 150, 30);
        salaryLabel.setFont(new Font("serif", Font.PLAIN, 20));
        add(salaryLabel);

        tfSalary = new JTextField();
        tfSalary.setBounds(600, 200, 150, 30);
        add(tfSalary);

        addLabelAndTextField("Address", 50, 250, 200, 150, 150, true);
        addLabelAndTextField("Phone", 400, 250, 600, 150, 150, true);

        addLabelAndTextField("Email", 50, 300, 200, 150, 150, true);

        JLabel educationLabel = new JLabel("Highest Education");
        educationLabel.setBounds(400, 300, 180, 30);
        educationLabel.setFont(new Font("serif", Font.PLAIN, 20));
        add(educationLabel);

        educationComboBox = new JComboBox<>(EDUCATION_OPTIONS);
        educationComboBox.setBackground(Color.WHITE);
        educationComboBox.setBounds(600, 300, 150, 30);
        add(educationComboBox);

        addLabelAndTextField("Designation", 50, 350, 200, 150, 150, true);
        addLabelAndTextField("Aadhar Number", 400, 350, 600, 150, 150, true);

        JLabel empIdLabel = new JLabel("Employee ID");
        empIdLabel.setBounds(50, 400, 150, 30);
        empIdLabel.setFont(new Font("serif", Font.PLAIN, 20));
        add(empIdLabel);

        lblEmployeeId = new JLabel(String.valueOf(employeeId));
        lblEmployeeId.setBounds(200, 400, 150, 30);
        lblEmployeeId.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblEmployeeId);

        addButton = new JButton("Add Details");
        addButton.setBounds(250, 550, 150, 40);
        addButton.addActionListener(this);
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        add(addButton);

        backButton = new JButton("Back");
        backButton.setBounds(450, 550, 150, 40);
        backButton.addActionListener(this);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        add(backButton);
    }

    private void addLabelAndTextField(String labelText, int labelX, int labelY, int fieldX, int fieldY, int width, boolean addField) {
        JLabel label = new JLabel(labelText);
        label.setBounds(labelX, labelY, 150, 30);
        label.setFont(new Font("serif", Font.PLAIN, 20));
        add(label);

        if (addField) {
            JTextField textField = new JTextField();
            textField.setBounds(fieldX, fieldY, width, 30);
            add(textField);

            switch (labelText) {
                case "Name":
                    tfName = textField;
                    break;
                case "Father's Name":
                    tfFatherName = textField;
                    break;
                case "Address":
                    tfAddress = textField;
                    break;
                case "Phone":
                    tfPhone = textField;
                    break;
                case "Aadhar Number":
                    tfAadhar = textField;
                    break;
                case "Email":
                    tfEmail = textField;
                    break;
                case "Designation":
                    tfDesignation = textField;
                    break;
                default:
                    break;
            }
        }
    }

    private void setFrameProperties() {
        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addButton) {
            saveEmployee();
        } else {
            setVisible(false);
            new Home();
        }
    }

    private void saveEmployee() {
        String name = tfName.getText().trim();
        String fatherName = tfFatherName.getText().trim();
        String dob = dateOfBirthChooser.getDate() != null
                ? dateOfBirthChooser.getDate().toString()
                : "";
        String salary = tfSalary.getText().trim();
        String address = tfAddress.getText().trim();
        String phone = tfPhone.getText().trim();
        String email = tfEmail.getText().trim();
        String education = (String) educationComboBox.getSelectedItem();
        String designation = tfDesignation.getText().trim();
        String aadhar = tfAadhar.getText().trim();
        String empId = lblEmployeeId.getText();

        if (name.isEmpty() || fatherName.isEmpty() || dob.isEmpty() || salary.isEmpty() ||
            address.isEmpty() || phone.isEmpty() || email.isEmpty() || designation.isEmpty() || aadhar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }

        try {
            Conn conn = new Conn();
            String query = "INSERT INTO employee (name, fname, dob, salary, address, phone, email, education, designation, aadhar, empId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.c.prepareStatement(query);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, fatherName);
            preparedStatement.setString(3, dob);
            preparedStatement.setString(4, salary);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, phone);
            preparedStatement.setString(7, email);
            preparedStatement.setString(8, education);
            preparedStatement.setString(9, designation);
            preparedStatement.setString(10, aadhar);
            preparedStatement.setString(11, empId);

            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Details added successfully");
            setVisible(false);
            new Home();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to add employee: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}
