//Form to delete a student after confirming ID.
import java.awt.event.*;
import java.sql.*;

import javax.swing.JOptionPane;

public class Delete extends BaseForm implements ActionListener {
    private int id;

    public Delete() {
        setupForm("Delete Student", "Fetch");
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
                actionButton.setText("Delete");
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
    	if (e.getSource() == actionButton) {
    		 int confirm = JOptionPane.showConfirmDialog(frame,
                     "Are you sure you want to delete this student?",
                     "Confirm Delete",
                     JOptionPane.YES_NO_OPTION);
    		 if (confirm == JOptionPane.YES_OPTION) {
	    			try {
		                int id = Integer.parseInt(tID.getText());
		                Connection con = DBConnection.getConnection();
		                String query = "DELETE FROM students WHERE id=?";
		                PreparedStatement pst = con.prepareStatement(query);
		                pst.setInt(1, id);
		                int rows = pst.executeUpdate();
		                if(rows>0) {
		                	bottom.setText("Deleted successfully");
		                	tID.setText("");
		                	tName.setText("");
		                	tEmail.setText("");
		                	tCourse.setText("");
		                }
		                else {
		                	bottom.setText("ID not found");
		                }
		                pst.close(); 
		                con.close();
	            } catch (SQLException | NumberFormatException ex) {
	                bottom.setText("Error: " + ex.getMessage());
	            }
    		 }
    		 else {
                 bottom.setText("Deletion cancelled.");
             }
        }
        if (e.getSource() == menuButton) {
            frame.dispose();
            new ManagementSystem();
        }
    }
}