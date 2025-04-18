package part2;

import javax.swing.*;
import java.awt.*;


public class CoursEnrollmentForm extends JFrame{
    /*String[] columnNames = {"ID", "Name", "Course", "Enrollment Date"};
    Object[][] data = {{"1", "Mehdia", "Computer Network", "22/03/2025"},
            {"2","Achraf", "AI", "10/12/2023"},
            {"3", "Hakim", "Data Analysis", "11/12/2020"},
            {"4", "Leao", "Computer Science", "12/12/2021"}
    };*/
    ImageIcon agenda = new ImageIcon("C:\\Users\\lenovo\\Documents\\S8 dump\\UXUI design\\lab2\\src\\work_lab\\calendar.png");
    private JTextField student_id;
    private JTextField student_name;
    private JTextField student_date;
    private JButton submitButton;
    private JTable table1;
    private JPanel mainPanel;
    private JComboBox courseName;
    private JButton deleteSelectedRowButton;
    private JPanel buttns;
    private JButton exitButton;
    private JLabel headLabel ;
    private JPanel fieldsPanel;
    private JPanel tablePanel;

    EnrollmentController controller = new EnrollmentController();
    public CoursEnrollmentForm(){
        super("Course Enrollment Form");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,500);
        this.setContentPane(mainPanel);

        // Resize the icon to 32x32 (or whatever size you prefer)
        Image scaledImage = agenda.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        headLabel.setIcon(scaledIcon); // Set the icon
        headLabel.setText("  Course Enrollment"); // Set the title text
        controller.createTable(table1);
        controller.addCourseName(courseName);
        controller.submitChanges(student_id, student_name, courseName, student_date, table1, submitButton);
        controller.deleteSelectedRow(deleteSelectedRowButton, table1);
        controller.exitProgram(exitButton);

        fieldsPanel.setBorder(new RoundedBorderView(20)); // 20 is the corner radius
        fieldsPanel.setPreferredSize(new Dimension(300, 130));
        
    }






    public static void main(String[] args) {
        CoursEnrollmentForm frame = new CoursEnrollmentForm();
        frame.setVisible(true);
    }
    
}




    /*private void createTable() {
        table1.setModel(new DefaultTableModel(data, columnNames));
    }*/