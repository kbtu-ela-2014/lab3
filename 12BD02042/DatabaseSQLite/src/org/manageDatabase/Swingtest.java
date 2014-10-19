package org.manageDatabase;

import java.awt.Component;
import java.awt.Frame;
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
import com.j256.ormlite.support.ConnectionSource;

public class Swingtest extends JFrame implements ActionListener
{
    

	
	JTextField name,surname;
    JButton calbtn = new JButton("Edit");
	private Object String;
	String newName;
	String newSurName;
	JPanel myPanel;
	Student tmp;
	Dao<Student, String> dao;  
    public Swingtest(int row)
    {
        myPanel = new JPanel();
        add(myPanel);
        myPanel.setLayout(new GridLayout(3, 2));
        name = new JTextField();
        name.setUI(new HintTextFieldUI("Enter a name",true));
        surname = new JTextField("Enter a new Surname");
        surname.setUI(new HintTextFieldUI("Enter a new Surname",true));
        myPanel.add(name);
        myPanel.add(surname);
		myPanel.add(calbtn);
        calbtn.addActionListener(this);
        setLocation(10, 10);
        setSize(200, 150);
        setVisible(true);
    
        String url = "jdbc:sqlite:mydatabase.sqlite";                
    	ConnectionSource source;
    	try {
    		source = new JdbcConnectionSource(url);
    		dao = DaoManager.createDao(source,Student.class);
    		ArrayList<Student> all = (ArrayList<Student>)dao.queryForAll();
    	
    		for(int i=0; i<all.size(); i++){
    			if(i==row){
    				tmp = all.get(i);
    				break;
    			}
    		}
    	
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}}

    public void actionPerformed(ActionEvent e)
    {
    	if (e.getSource() == calbtn) {
            tmp.setName(name.getText()); //perform your operation
            tmp.setSurName(surname.getText()); //perform your operation
            try {
				dao.update(tmp);
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