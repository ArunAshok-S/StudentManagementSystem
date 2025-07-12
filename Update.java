//Form to update a student’s record after fetching by ID.
import java.awt.event.*;
import java.sql.*;

public class Update extends BaseForm implements ActionListener {
    private int id;

    public Update() {
        setupForm("Update Student", "Fetch");
        actionButton.addActionListener(e -> fetchStudent());
        menuButton.addActionListener(e -> {
            frame.dispose();
            new ManagementSystem();
        });
        frame.setVisible(true);
    }

    private void fetchStudent() {
        try {
            id = Integer.parseInt(tID.getText());
            tID.setEditable(false);
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM students WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tName.setText(rs.getString("name"));
                tEmail.setText(rs.getString("email"));
                tCourse.setText(rs.getString("course"));
                bottom.setText("Record loaded. Edit and click Update.");
                actionButton.setText("Update");
                actionButton.removeActionListener(actionButton.getActionListeners()[0]);
                actionButton.addActionListener(this);
            } else {
                bottom.setText("No student found.");
            }
            rs.close(); pst.close(); con.close();
        } catch (Exception ex) {
            bottom.setText("Error: " + ex.getMessage());
        }
    }

    public void actionPerformed(ActionEvent e) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "UPDATE students SET name=?, email=?, course=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, tName.getText());
            pst.setString(2, tEmail.getText());
            pst.setString(3, tCourse.getText());
            pst.setInt(4, id);
            int rows = pst.executeUpdate();
            bottom.setText(rows > 0 ? "Update successful." : "Update failed.");
            pst.close(); con.close();
        } catch (SQLException ex) {
            bottom.setText("Error: " + ex.getMessage());
        }
    }
}