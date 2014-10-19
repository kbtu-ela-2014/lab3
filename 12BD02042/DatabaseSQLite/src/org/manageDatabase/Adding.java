package org.manageDatabase;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.transform.Source;

import org.model.Student;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.query.In;
import com.j256.ormlite.support.ConnectionSource;

public class Adding extends JFrame implements ActionListener
{
    

	JPanel myPanel;
	JTextField name,surname,id;
    JButton calbtn = new JButton("Add Student");
	private Object String;
	String newName;
	String newSurName;
	String idStudent;
	Student tmp;
	Dao<Student, String> dao;  
    public Adding()
    {
    	 myPanel = new JPanel();
        add(myPanel);
        myPanel.setLayout(new GridLayout(3, 2));
        name = new JTextField();
        name.setUI(new HintTextFieldUI("Enter a name",true));
        surname = new JTextField();
        surname.setUI(new HintTextFieldUI("Enter a new Surname",true));
        id = new JTextField();
        id.setUI(new HintTextFieldUI("Enter an id",true));
        myPanel.add(name);
        myPanel.add(surname);
        myPanel.add(id);
		myPanel.add(calbtn);
        calbtn.addActionListener(this);
        setLocation(10, 10);
        setSize(250, 250);
        setVisible(true);
    
        String url = "jdbc:sqlite:mydatabase.sqlite";                
    	ConnectionSource source;
    	try {
    		source = new JdbcConnectionSource(url);
    		dao = DaoManager.createDao(source,Student.class);
    	
    	
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}}

    public void actionPerformed(ActionEvent e)
    {
    	if (e.getSource() == calbtn) {
    		tmp = new Student();
   			tmp.setName(name.getText()); //perform your operation
            tmp.setSurName(surname.getText()); //perform your operation
            //tmp.setID(Integer.parseInt(id.getText()));
            tmp.setID(id.getText());
            try {
            	System.out.println(dao.countOf());
                dao.create(tmp);
				Manage.updatee();
				this.setVisible(false);
            } catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}
    }
    
        
}