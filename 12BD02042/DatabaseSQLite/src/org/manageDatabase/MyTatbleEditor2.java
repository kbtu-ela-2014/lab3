package org.manageDatabase;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.model.Student;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class MyTatbleEditor2 {

	JPanel frame = new JPanel();
    

    public MyTatbleEditor2(final int in) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                } catch (InstantiationException ex) {
                } catch (IllegalAccessException ex) {
                } catch (UnsupportedLookAndFeelException ex) {
                }

                frame.setSize(50, 200);
                frame.setLayout(new BorderLayout());

                Vector rows = new Vector();
                for (int index = 0; index < in; index++) {
                    Vector data = new Vector();
                    Vector data2 = new Vector();
                    data.add(new Object());
                    rows.add(data);
                }
                Vector cols = new Vector();
                cols.add("Edit");
                cols.add("Delete");
                DefaultTableModel model = new DefaultTableModel(rows, cols);

                JTable table = new JTable(model);

                table.getColumn("Edit").setCellEditor(new MyTableButtonEditor());
                table.getColumn("Edit").setCellRenderer(new MyTableButtonRenderer("Edit"));

                table.getColumn("Delete").setCellEditor(new MyTableButtonEditor());
                table.getColumn("Delete").setCellRenderer(new MyTableButtonRenderer("Delete"));

                
                frame.add(new JScrollPane(table));

            }
        });
    }

    protected class MyTableButtonEditor extends AbstractCellEditor implements TableCellEditor {

        private JButton button;
        private int column;
        private int row;

        public MyTableButtonEditor() {
            button = new JButton(" ");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            if(column!=0)JOptionPane.showMessageDialog(button, "Done", "Fancy that", JOptionPane.INFORMATION_MESSAGE);
                            if(column==1){
                            		Student tmp = null;
                            		String url = "jdbc:sqlite:mydatabase.sqlite";                
                            		ConnectionSource source;
								try {
									source = new JdbcConnectionSource(url);
									Dao<Student, String> dao;  
									dao = DaoManager.createDao(source,Student.class);
									ArrayList<Student> all = (ArrayList<Student>)dao.queryForAll();
									for(int i=0; i<all.size(); i++){
										if(i==row){
											tmp = all.get(i);
											break;
										}
									}
									
									dao.delete(tmp);
									Manage.updatee();									
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
                  	        	  
                            }
                            if(column==0){
                            	Swingtest g = new Swingtest(row);
                            }
                        }
                    });
                }
            });
        }

        @Override
        public boolean isCellEditable(EventObject e) {
            boolean isEditable = false;
            if (e instanceof MouseEvent) {
                MouseEvent me = (MouseEvent) e;
                if (me.getButton() == MouseEvent.BUTTON1 && me.getID() == MouseEvent.MOUSE_PRESSED) {
                    isEditable = true;
                }
            } 
            return isEditable;
        }

        @Override
        public Object getCellEditorValue() {
            return new Object();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (isSelected) {
                button.requestFocus();
            }
            this.column = column;
            this.row = row;
            return button;
        }
    }

    public JPanel getFrame(){
    	return frame;
    }
    
    protected class MyTableButtonRenderer implements TableCellRenderer {

        private JButton button;

        public MyTableButtonRenderer(String columnName) {
        	button = new JButton(columnName);	
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return button;
        }

    }
}