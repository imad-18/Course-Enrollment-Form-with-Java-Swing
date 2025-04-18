package part2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

public class EnrollmentController {
    String[] courses = {"Computer Science","Computer Network", "Data Science", "Algorithms", "AI", "Data Analysis"};


    //Create the JComboBox statically
    public void addCourseName(JComboBox courseName) {

        courseName.setModel(new DefaultComboBoxModel(courses));
    }


    // Example method to insert enrollment data into my 'students_db' database using MySQL server
    public void enrollStudent(String studentId, String student_name, String courseName, String enrollmentDate) {
        try {
            // Load the MySQL JDBC driver (optional for newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/students_db",
                    "root", "imad0003"
            );

            // Prepare SQL query
            String query = "INSERT INTO Enrollments (student_id, student_name, course_name, enrollment_date) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);

            // Set query parameters
            stmt.setString(1, studentId);
            stmt.setString(2, student_name);
            stmt.setString(3, courseName);
            stmt.setString(4, enrollmentDate);

            // Execute the query
            stmt.executeUpdate();

            // Show success message
            JOptionPane.showMessageDialog(null, "Enrollment Successful!");

            // Close connection
            con.close();

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
        }
    }

    //Load enrollment courses data from 'students_db' database into the GUI
    public void createTable(JTable table1) {

        //table1.setModel(new DefaultTableModel(data, columnNames));
        String[] columnNames = {"Student ID", "Student Name", "Course Name", "Enrollment Date"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Empty table with headers
        table1.setModel(model);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/students_db",
                    "root", "imad0003"
            );

            String query = "SELECT * FROM Enrollments";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String id = String.valueOf(rs.getInt("student_id"));
                String student_name = rs.getString("student_name");
                String course = rs.getString("course_name");
                String date = rs.getString("enrollment_date");

                model.addRow(new Object[]{id, student_name, course, date});
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load enrollment data: " + e.getMessage());
        }

    }



    // Inserting data into the 'students_db' database and the GUI as well, then clear the textfields..
    /*  Since we already have the EnrollmentController class to handle database insertion,
        we just need to call the enrollStudent method inside our submitButton's action listener.
     */
    public void submitChanges(JTextField student_id, JTextField student_name, JComboBox courseName, JTextField student_date, JTable table1, JButton submitButton) {
        submitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String studentID = student_id.getText();
                String studentName = student_name.getText();
                String studentCourse = Objects.requireNonNull(courseName.getSelectedItem()).toString();
                String studentDate = student_date.getText();
                if (studentID.isEmpty() || studentName.isEmpty() || studentCourse.isEmpty() || studentDate.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all the required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    // Insert into database

                    enrollStudent(studentID, studentName,studentCourse, studentDate);

                    // Add data to table
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.addRow(new Object[]{studentID, studentName, studentCourse, studentDate});

                    JOptionPane.showMessageDialog(null, "Your course enrollment has been submitted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Optionally clear the input fields
                    student_id.setText("");
                    student_name.setText("");
                    courseName.setSelectedIndex(0);
                    student_date.setText("");
                }

            }
        });
    }

    //Delete a selected row from the 'students_db' and the GUI
    /*
        1-Get the student_id from the selected row.
        2-Delete the corresponding row from the Enrollments table in MySQL.
        3-If successful, remove the row from the table UI.
    */
    public void deleteSelectedRow(JButton deleteSelectedRowButton, JTable table1) {
        deleteSelectedRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = table1.getSelectedRow();
                if (row != -1) {
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    String studentId = model.getValueAt(row, 0).toString(); // Assuming student_id is in column 0

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/students_db",
                                "root", "imad0003"
                        );

                        String deleteQuery = "DELETE FROM Enrollments WHERE student_id = ?";
                        PreparedStatement stmt = con.prepareStatement(deleteQuery);
                        stmt.setString(1, studentId);
                        int rowsAffected = stmt.executeUpdate();

                        if (rowsAffected > 0) {
                            model.removeRow(row);
                            JOptionPane.showMessageDialog(null, "Row deleted successfully.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to delete row from database.");
                        }

                        con.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }


    //Quit the program
    public void exitProgram(JButton exitButton){
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
