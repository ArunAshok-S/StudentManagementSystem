import java.awt.event.*;
import java.sql.*;

//Form to add a new student record to the database.
public class Add extends BaseForm implements ActionListener {
    public Add() {
        setupForm("Add Student Data", "Add");
        actionButton.addActionListener(this);
        menuButton.addActionListener(this);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == actionButton) {
            try {
                int id = Integer.parseInt(tID.getText());
                String name = tName.getText();
                String email = tEmail.getText();
                String course = tCourse.getText();

                Connection con = DBConnection.getConnection();
                String query = "INSERT INTO students (id, name, email, course) VALUES (?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setInt(1, id);
                pst.setString(2, name);
                pst.setString(3, email);
                pst.setString(4, course);

                int rows = pst.executeUpdate();
                bottom.setText(rows > 0 ? "Data added successfully." : "Failed to add data.");

                pst.close(); con.close();
                tID.setText(""); tName.setText(""); tEmail.setText(""); tCourse.setText("");
            } catch (SQLException | NumberFormatException ex) {
                bottom.setText("Error: " + ex.getMessage());
            }
        }
        if (e.getSource() == menuButton) {
            frame.dispose();
            new ManagementSystem();
        }
    }
}