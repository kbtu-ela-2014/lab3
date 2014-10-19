package org.manageDatabase;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.model.Student;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class Manage implements ActionListener 

{
	static JFrame frame = new JFrame("Database of KBTU");
	static JPanel panelfor = new JPanel(new GridLayout());
	static JPanel panel = new JPanel();

	static JTable table;
	static TableModel tableModel;
	static JButton addButton;
	
        
	public Manage(){
		addButton.addActionListener(this);
	}
	
	
		public static void updatee() throws ClassNotFoundException, SQLException {
			panel.removeAll();
			showTable();
		}	
		
		public void actionPerformed(ActionEvent e)
	    {
	    	if (e.getSource() == addButton){
	    		new Adding();
	    	}
	    }
	
		public static void showTable(){
		  try {
			  String url = "jdbc:sqlite:mydatabase.sqlite";                
			  ConnectionSource  source = new JdbcConnectionSource(url);
			  Dao<Student, String> dao;  
			  dao = DaoManager.createDao(source,Student.class);
			  
			  ArrayList<Student> list = (ArrayList<Student>)dao.queryForAll();
	          String[][] values = new String[list.size()][3];
	          String[] columns = {"Name","Surname","ID"};
	          addButton = new JButton("Add Student");
	          for (int i = 0; i < list.size(); i++) {
	        	  values[i][0] = list.get(i).getName();
	        	  values[i][1] = list.get(i).getSurName();
	        	  values[i][2] = list.get(i).getID();
	          }
	          
	       tableModel = new DefaultTableModel(values, columns);
	          
	          
	          table = new JTable(tableModel);
	          
	          MyTatbleEditor2 e = new MyTatbleEditor2(list.size());
			  
	          panel.add(new JScrollPane(table));
	          panel.add(e.getFrame());
	          addButton.setSize(30,30);
	          new Manage();
	          
	          panel.add(addButton);
	          frame.setLocationRelativeTo(null);
	          frame.setSize(1000, 800);
	          frame.setContentPane(panel);
	          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	          frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	          frame.setVisible(true);

	      } catch (SQLException e) {
	          e.printStackTrace();
	      } 
	}
		
	
  public static void main( String args[] ) throws SQLException
  {

	  showTable();
	   
  }
}