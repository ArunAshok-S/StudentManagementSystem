import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
* Main menu interface of the Student Management System.
* Allows navigation to Add, Search, Update, Delete, and View operations.
*/
public class ManagementSystem implements ActionListener{
	JFrame frame;
	JLabel label;
	JPanel panel;
	JButton add, search, update, delete, view;
	ManagementSystem(){
		frame=new JFrame("Student Management");
		frame.setSize(350,250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		
		label=new JLabel("ABC STUDENT MANAGEMENT SYSTEM");
		label.setBounds(60, 0, 250, 50);
		frame.add(label);
		
		panel=new JPanel();
		panel.setBounds(0, 0, 350, 200);
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(10, 10, 10, 10);
		gbc.fill=GridBagConstraints.HORIZONTAL;
		
		add=new JButton("Add");
		add.addActionListener(this);
		add.setFocusable(false);
		gbc.gridx=0;
		gbc.gridy=0;
		panel.add(add, gbc);
		
		search=new JButton("Search");
		search.addActionListener(this);
		search.setFocusable(false);
		gbc.gridx=1;
		gbc.gridy=0;
		panel.add(search, gbc);
		
		update=new JButton("Update");
		update.addActionListener(this);
		update.setFocusable(false);
		gbc.gridx=0;
		gbc.gridy=1;
		panel.add(update, gbc);
		
		delete=new JButton("Delete");
		delete.addActionListener(this);
		delete.setFocusable(false);
		gbc.gridx=1;
		gbc.gridy=1;
		panel.add(delete, gbc);
		
		view=new JButton("View All");
		view.addActionListener(this);
		view.setFocusable(false);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx=0;
		gbc.gridy=2;
		panel.add(view, gbc);
		
		frame.add(panel);
		
		frame.setVisible(true);
	}
	
	//Handles button clicks for different operations.
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==add) {
			frame.dispose();
			new Add();
		}
		if(e.getSource()==search) {
			frame.dispose();
			new Search();
		}
		if(e.getSource()==update) {
			frame.dispose();
			new Update();
		}
		if(e.getSource()==delete) {
			frame.dispose();
			new Delete();
		}
		if(e.getSource()==view) {
			frame.dispose();
			new View();
		}
	}
}
