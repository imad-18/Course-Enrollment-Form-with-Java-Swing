package part1;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentRegistration extends JFrame {
    public StudentRegistration() {

        //Creating the Main Window:
        this.setTitle("Student registration form");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //1.2. Structuring the Layout with Panels:
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        JMenuItem helpMenuItem = new JMenuItem("Online Help");


        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(exitMenuItem);
        helpMenu.add(helpMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        fileMenu.addMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                System.out.println("Menu selected");
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });

        helpMenu.addMenuListener(new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {

            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });

        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Creating a new file...");
            }
        });
        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Opening a file...");
            }
        });
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exiting program...");
            }
        });
        helpMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Online Help");
            }
        });

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 0, 30));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20,70,20,50));


        // Add components to the panel
        JTextField studentIDField = new JTextField();
        JTextField studentNameField = new JTextField();
        JComboBox studentMajorField = new JComboBox(new String[]{"UI/UX", "Optic", "Devops"});
        JRadioButton maleStudent = new JRadioButton("Male");
        JRadioButton femaleStudent = new JRadioButton("Female");
        ButtonGroup group = new ButtonGroup();
        group.add(maleStudent);
        group.add(femaleStudent);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(maleStudent);
        genderPanel.add(femaleStudent);


        //Adding labels
        Label studentID = new Label("Student ID");
        Label studentName = new Label("Student Name");
        Label studentMajor = new Label("Major");
        Label studentGender = new Label("Gender");

        //Adding labels to the panel
        inputPanel.add(studentID);
        inputPanel.add(studentIDField);

        inputPanel.add(studentName);
        inputPanel.add(studentNameField);

        inputPanel.add(studentMajor);
        inputPanel.add(studentMajorField);

        inputPanel.add(studentGender);
        inputPanel.add(genderPanel);

        JPanel buttnsPanel = new JPanel();
        JButton registerButton = new JButton("Register");
        JButton clearButton = new JButton("Clear");
        JButton exitButton = new JButton("Exit");
        buttnsPanel.add(registerButton);
        buttnsPanel.add(clearButton);
        buttnsPanel.add(exitButton);
        registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String studentID = studentIDField.getText();
                String studentName = studentNameField.getText();
                String studentMajor = studentMajorField.getSelectedItem().toString();

                if (studentID.isEmpty() || studentName.isEmpty() || studentMajor.isEmpty() || (!maleStudent.isSelected() && !femaleStudent.isSelected())) {
                    JOptionPane.showMessageDialog(null, "Please fill all the required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "You have registered successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                studentIDField.setText("");
                studentNameField.setText("");
                studentMajorField.setSelectedIndex(0);
                group.clearSelection();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.setJMenuBar(menuBar);
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(buttnsPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }
}
