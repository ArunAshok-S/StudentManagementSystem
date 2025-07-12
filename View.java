//Form to display all student records from the database.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class View implements ActionListener {
    JFrame frame;
    JTextArea area;
    JButton menuButton;

    public View() {
        frame = new JFrame("All Students");
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        area = new JTextArea();
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        frame.add(scroll, BorderLayout.CENTER);

        menuButton = new JButton("Menu");
        menuButton.addActionListener(this);
        frame.add(menuButton, BorderLayout.SOUTH);

        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM students";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            StringBuilder str = new StringBuilder();
            str.append("ID\tName\tEmail\tCourse\n");
            while (rs.next()) {
                str.append(rs.getInt("id"))
                    .append("\t")
                    .append(rs.getString("name"))
                    .append("\t")
                    .append(rs.getString("email"))
                    .append("\t")
                    .append(rs.getString("course"))
                    .append("\n");
            }
            area.setText(str.toString());
            rs.close(); st.close(); con.close();
        } catch (SQLException e) {
            area.setText("Error loading data: " + e.getMessage());
        }
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        new ManagementSystem();
    }
}