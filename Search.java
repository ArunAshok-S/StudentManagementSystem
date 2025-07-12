//Form to search for a student by ID and display their details.
import java.awt.event.*;
import java.sql.*;

public class Search extends BaseForm implements ActionListener {
    public Search() {
        setupForm("Search Student", "Search");
        tName.setEditable(false);
        tEmail.setEditable(false);
        tCourse.setEditable(false);
        actionButton.addActionListener(this);
        menuButton.addActionListener(this);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == actionButton) {
            try {
                int id = Integer.parseInt(tID.getText());
                Connection con = DBConnection.getConnection();
                String query = "SELECT * FROM students WHERE id = ?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setInt(1, id);

                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    tName.setText(rs.getString("name"));
                    tEmail.setText(rs.getString("email"));
                    tCourse.setText(rs.getString("course"));
                    bottom.setText("Record found.");
                } else {
                    bottom.setText("No student found with ID: " + id);
                }

                rs.close();
                pst.close();
                con.close();
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