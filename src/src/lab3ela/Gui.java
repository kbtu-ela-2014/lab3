package lab3ela;
 

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;
 

public class Gui extends JPanel {
    private boolean DEBUG = false;
    JTable table = null;
    JButton addButton = null;
    MyTableModel mtb = null;
    public Gui() {
    	super(new GridLayout(1,0));
    	mtb = new MyTableModel();
        table = new JTable(mtb);
        
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        ButtonColumn buttonColumn = new ButtonColumn(table, mtb.delete, 4);
  //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
       
        System.out.print("eadasdsa");
        //Add the scroll pane to this panel.
        add(scrollPane);
      addButton = new JButton("add");
      MyListener l = new MyListener();
      addButton.addActionListener(l);;
        add(addButton);
    }

    class MyListener implements ActionListener
    {

		@Override
		public void actionPerformed(ActionEvent e) {
			mtb.addStudent();
		}
    	
    }

	
    class MyTableModel extends AbstractTableModel {
    	
    	
    	
    	Vector<String> vecNames = getTitles();
    	Vector rows = getRows();
    	Action delete = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
               
                ((MyTableModel)table.getModel()).removeRow(modelRow);
               
            }
        };
        public void addStudent(){
        	Session s = HibernateUtil.getSessionFactory().openSession();
    	    Transaction tx = null;
    	    System.out.println("INside");
    	    try {
    	    	
    	        tx = s.beginTransaction();        
    	        Student stu = new Student();
    	        s.save(stu);
    	        tx.commit();
    	    } catch (HibernateException ex) {
    	        if (tx != null) {
    	            tx.rollback();
    	        }            
    	        Logger.getLogger("con").info("Exception: " + ex.getMessage());
    	        ex.printStackTrace(System.err);
    	    } finally {
    	        s.close(); 
    	        rows = getRows();
    	        this.fireTableDataChanged();
    	    }
        
        }
    	public Vector<String> getTitles(){
    		String[] columnNames = {"ID","Name","Address","Age","Action"};
    	    Vector<String> vecNames = new Vector<String>();
    	     vecNames.addAll(Arrays.asList(columnNames));
    	     return vecNames;
    	}
    	protected void removeRow(int modelRow) {
    		Session s = HibernateUtil.getSessionFactory().openSession();
    	    Transaction tx = null;
    	    try {
    	    	int id = Integer.parseInt((String)String.valueOf(getValueAt(modelRow, 0)));
    	        tx = s.beginTransaction();
    	        
    	        Student dbStudent = (Student)s.get(Student.class, id);

    	        System.out.print(dbStudent);
    	        s.delete(dbStudent);
    	        System.out.print("DELETED");
    	        tx.commit();
    	    } catch (HibernateException ex) {
    	        if (tx != null) {
    	            tx.rollback();
    	        }            
    	        Logger.getLogger("con").info("Exception: " + ex.getMessage());
    	        ex.printStackTrace(System.err);
    	    } finally {
    	    	
    	        s.close(); 
    	        getRows();
    	        this.fireTableDataChanged();
    	    }
			
		}
		public Vector getRows(){
			
    		List<Student> list = Gui.getStudents();

    		Vector rows = new Vector();
    	           for (int i = 0; i < list.size(); i++)
    	           {
    	        	   Vector aRow = new Vector();
    	               aRow.add(list.get(i).getId());
    	               aRow.add(list.get(i).getEmpName());
    	               aRow.add(list.get(i).getAddress());
    	               aRow.add(list.get(i).getAge());
    	               aRow.add("delete");
    	               rows.add(aRow);
    	           }
    	        this.rows = rows;
    	        
    			return rows;
    	}
    	    
       public int getColumnCount() {
           return vecNames.size();
       }

       public int getRowCount() {
    	   return rows.size();
       }

       public String getColumnName(int col) {
           return vecNames.get(col);
       }
       

       		
     @Override public Object getValueAt(final int rowIndex, final int columnIndex) {
                /*Adding components*/
          
    	return ((Vector<String>) rows.get(rowIndex)).get(columnIndex);
    	      
     }
     
    
     public Class getColumnClass(int column) {
         switch (column) {
             case 0:
                 return  String.class;
             case 1:
                 return  String.class;
             case 2:
                 return  String.class;
             case 3:
                 return  String.class;
             case 4:
                 return  String.class;
             case 5:
                 return Button.class;
             default:
                 return String.class;
         }
     }

       /*
        * Don't need to implement this method unless your table's
        * editable.
        */
       public boolean isCellEditable(int row, int col) {
           //Note that the data/cell address is constant,
           //no matter where the cell appears onscreen.
           if (col < 1) {
               return false;
           } else {
               return true;
           }
       }
       public void setValueAt(Object value, int row, int col) {
           if (DEBUG) {
               System.out.println("Setting value at " + row + "," + col
                                  + " to " + value
                                  + " (an instance of "
                                  + value.getClass() + ")");
           }
           
           ((Vector<String>) rows.get(row)).setElementAt((String) value, col);
           
           int idToBeModified = Integer.parseInt(String.valueOf(getValueAt(row,0)));
           modifyRowInDb(idToBeModified,String.valueOf(getValueAt(row,1)),String.valueOf(getValueAt(row,2)),String.valueOf(getValueAt(row,3)));
           if (DEBUG) {
               System.out.println("New value of data:");
               printDebugData();
           }
       }
       private void printDebugData() {
           int numRows = getRowCount();
           int numCols = getColumnCount();

           for (int i=0; i < numRows; i++) {
               System.out.print("    row " + i + ":");
               for (int j=0; j < numCols; j++) {
                   System.out.print("  ");
               }
               System.out.println();
           }
           System.out.println("--------------------------");
       }
       
      
    }
    
   
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Students info");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        Gui newContentPane = new Gui();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
        public Boolean modifyRowInDb(int id, String name, String address, String age){
    	Session s = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = null;
	    try {
	        tx = s.beginTransaction();        
	        Student dbStudent = (Student)s.get(Student.class, id);
	         dbStudent.setEmpName(name);
	        dbStudent.setAddress(address);
	        dbStudent.setAge(age);
	         tx.commit();
	        return true;

	    } catch (HibernateException ex) {
	        if (tx != null) {
	            tx.rollback();
	        }            
	        Logger.getLogger("con").info("Exception: " + ex.getMessage());
	        ex.printStackTrace(System.err);
	    } finally {
	        s.close(); 
	    }
	    return false;
    }
    public static List<Student> getStudents(){
    	Session s = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = null;
	    try {

	        tx = s.beginTransaction();        

	        // here get object
	        List<Student> list = s.createCriteria(Student.class).list();

	        tx.commit();
	        return list;

	    } catch (HibernateException ex) {
	        if (tx != null) {
	            tx.rollback();
	        }            
	        Logger.getLogger("con").info("Exception: " + ex.getMessage());
	        ex.printStackTrace(System.err);
	    } finally {
	        s.close(); 
	    }
	    return null;
    }
 
   
    public static void main(String[] args) {
    		
      
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	
                createAndShowGUI();
            }
        });
    }
}
