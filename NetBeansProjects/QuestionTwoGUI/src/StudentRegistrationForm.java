import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class StudentRegistrationForm extends JFrame {

    JTextField txtFirst, txtLast, txtEmail, txtConfirmEmail;
    JPasswordField txtPassword, txtConfirmPassword;
    JComboBox<Integer> cbYear, cbDay;
    JComboBox<String> cbMonth, cbDept;
    JRadioButton rbMale, rbFemale;
    JTextArea outputArea;

    static int counter = 1;

    public StudentRegistrationForm() {
        setTitle("New Student Registration");
        setSize(900, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // LEFT PANEL
        JPanel form = new JPanel(new GridLayout(10, 2, 5, 5));

        txtFirst = new JTextField();
        txtLast = new JTextField();
        txtEmail = new JTextField();
        txtConfirmEmail = new JTextField();
        txtPassword = new JPasswordField();
        txtConfirmPassword = new JPasswordField();

        cbYear = new JComboBox<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int y = currentYear - 60; y <= currentYear - 16; y++) {
            cbYear.addItem(y);
        }

        cbMonth = new JComboBox<>(new String[]{
                "January","February","March","April","May","June",
                "July","August","September","October","November","December"
        });

        cbDay = new JComboBox<>();
        for (int d = 1; d <= 31; d++) {
            cbDay.addItem(d);
        }

        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);

        cbDept = new JComboBox<>(new String[]{
                "Civil","CSE","Electrical","E&C","Mechanical"
        });

        form.add(new JLabel("First Name:"));
        form.add(txtFirst);
        form.add(new JLabel("Last Name:"));
        form.add(txtLast);
        form.add(new JLabel("Email:"));
        form.add(txtEmail);
        form.add(new JLabel("Confirm Email:"));
        form.add(txtConfirmEmail);
        form.add(new JLabel("Password:"));
        form.add(txtPassword);
        form.add(new JLabel("Confirm Password:"));
        form.add(txtConfirmPassword);

        JPanel dobPanel = new JPanel();
        dobPanel.add(cbYear);
        dobPanel.add(cbMonth);
        dobPanel.add(cbDay);
        form.add(new JLabel("Date of Birth:"));
        form.add(dobPanel);

        JPanel genderPanel = new JPanel();
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        form.add(new JLabel("Gender:"));
        form.add(genderPanel);

        form.add(new JLabel("Department:"));
        form.add(cbDept);

        JButton btnSubmit = new JButton("Submit");
        JButton btnCancel = new JButton("Cancel");

        form.add(btnSubmit);
        form.add(btnCancel);

        add(form, BorderLayout.WEST);

        // RIGHT PANEL
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        btnSubmit.addActionListener(e -> submitForm());
        btnCancel.addActionListener(e -> clearForm());

        setVisible(true);
    }

    void submitForm() {
        String first = txtFirst.getText().trim();
        String last = txtLast.getText().trim();
        String email = txtEmail.getText().trim();
        String confirmEmail = txtConfirmEmail.getText().trim();
        String pass = new String(txtPassword.getPassword());
        String confirmPass = new String(txtConfirmPassword.getPassword());

        if (first.isEmpty() || last.isEmpty() || email.isEmpty() || confirmEmail.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required");
            return;
        }

        if (!email.equals(confirmEmail)) {
            JOptionPane.showMessageDialog(this, "Emails do not match");
            return;
        }

        if (pass.length() < 8 || !pass.equals(confirmPass)) {
            JOptionPane.showMessageDialog(this, "Password must be at least 8 characters and match");
            return;
        }

        if (!rbMale.isSelected() && !rbFemale.isSelected()) {
            JOptionPane.showMessageDialog(this, "Select gender");
            return;
        }

        int year = (int) cbYear.getSelectedItem();
        int age = Calendar.getInstance().get(Calendar.YEAR) - year;

        if (age < 16 || age > 60) {
            JOptionPane.showMessageDialog(this, "Age must be between 16 and 60");
            return;
        }

        String gender = rbMale.isSelected() ? "M" : "F";
        String dept = cbDept.getSelectedItem().toString();

        String id = Calendar.getInstance().get(Calendar.YEAR)
                + "-" + String.format("%05d", counter++);

        String record = id + " | " + first + " " + last + " | " +
                gender + " | " + dept + " | " + email;

        outputArea.append(record + "\n");

        try {
            FileWriter fw = new FileWriter("students.csv", true);
            fw.write(record + "\n");
            fw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "File error");
        }
    }

    void clearForm() {
        txtFirst.setText("");
        txtLast.setText("");
        txtEmail.setText("");
        txtConfirmEmail.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
    }

    public static void main(String[] args) {
        new StudentRegistrationForm();
    }
}