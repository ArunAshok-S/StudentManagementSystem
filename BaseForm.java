import javax.swing.*;
import java.awt.*;

//Base form that defines common UI fields used by Add, Search, Update, Delete.
public abstract class BaseForm {
    protected JFrame frame;
    protected JLabel top, bottom;
    protected JPanel panel;
    protected JLabel lID, lName, lEmail, lCourse;
    protected JTextField tID, tName, tEmail, tCourse;
    protected JButton actionButton, menuButton;

    //Sets up a common student form with title and button labels.
    public void setupForm(String title, String actionText) {
        frame = new JFrame(title);
        frame.setSize(400, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        top = new JLabel(title);
        top.setBounds(150, 20, 200, 30);
        frame.add(top);

        panel = new JPanel();
        panel.setBounds(40, 50, 300, 250);
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lID = new JLabel("ID");
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(lID, gbc);
        tID = new JTextField(15);
        gbc.gridx = 1;
        panel.add(tID, gbc);

        lName = new JLabel("Name");
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lName, gbc);
        tName = new JTextField(15);
        gbc.gridx = 1;
        panel.add(tName, gbc);

        lEmail = new JLabel("Email");
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(lEmail, gbc);
        tEmail = new JTextField(15);
        gbc.gridx = 1;
        panel.add(tEmail, gbc);

        lCourse = new JLabel("Course");
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(lCourse, gbc);
        tCourse = new JTextField(15);
        gbc.gridx = 1;
        panel.add(tCourse, gbc);

        menuButton = new JButton("Menu");
        menuButton.setFocusable(false);
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(menuButton, gbc);

        actionButton = new JButton(actionText);
        actionButton.setFocusable(false);
        gbc.gridx = 1;
        panel.add(actionButton, gbc);

        frame.add(panel);

        bottom = new JLabel("");
        bottom.setBounds(100, 310, 300, 30);
        frame.add(bottom);
    }
}