
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.*;
 
public class MyWindowApp extends JFrame { //Наследуя от JFrame мы получаем всю функциональность окна
	private static JLabel no;
	private static JLabel fname;
	private static JLabel lname;
	private static JLabel faculty;
	private static JLabel year;
	private static JPanel mainPanel;
	private static ManageStudent ms;
	static Vector<String> txt;
	static Vector<JTextField> flds;
	static Vector<JButton> btns;
	static Vector<JTextField> newFlds;
  public MyWindowApp(){
    super("My First Window"); //Заголовок окна
    setBounds(100, 100, 500, 500); //Если не выставить размер и положение - то окно будет мелкое и незаметное
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //это нужно для того чтобы при закрытии окна закрывалась и программа, иначе она останется висеть в процессах
    ms=new ManageStudent();
    String str=ms.listStudents();
    txt=new Vector<String>();
    StringTokenizer tk=new StringTokenizer(str,"!");
    GridLayout grid=new GridLayout(0,7);
    mainPanel=new JPanel();
    mainPanel.setLayout(grid);
    
    
    no=new JLabel("ID");
    fname=new JLabel("First Name");
    lname=new JLabel("Last Name");
    faculty=new JLabel("Faculty");
    year=new JLabel("Year");
    mainPanel.add(no);
    mainPanel.add(fname);
    mainPanel.add(lname);
    mainPanel.add(faculty);
    mainPanel.add(year);
    mainPanel.add(new JLabel(""));
    mainPanel.add(new JLabel(""));
    while(tk.hasMoreTokens()){
    	txt.add(tk.nextToken());
    }
    
    flds=new Vector<JTextField>();
    JButton edBtn;
    JButton delBtn;
 
    btns=new Vector<JButton>();
    for(int i=0; i < txt.size(); i++){
    	tk=new StringTokenizer(txt.get(i),"&");
       	while(tk.hasMoreTokens()){
       		flds.add(new JTextField(tk.nextToken()));
       		flds.get(flds.size()-1).setEditable(false);
    		mainPanel.add(flds.get(flds.size()-1));
    	}  	 
       	edBtn=new JButton("Edit");
       	delBtn=new JButton("Delete");
       	edBtn.setActionCommand(new Integer(flds.size()).toString());
    	delBtn.setActionCommand(new Integer(flds.size()).toString());
    	btns.add(edBtn);
    	btns.add(delBtn);
       	edBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton btn=(JButton)e.getSource();
				int begin=new Integer(e.getActionCommand());				
				if(btn.getText().equals("Edit")){
					btn.setText("Upgrade");
					for(int i=begin-1; i > begin-5; i--){
						flds.get(i).setEditable(true);
					}
					 
				}else{
					btn.setText("Edit");
					int id=new Integer(flds.get(begin-5).getText());
					for(int i=begin-1; i > begin-5; i--){
						flds.get(i).setEditable(false);
					}
					ms.updateYear(id,flds.get(begin-4).getText(),flds.get(begin-3).getText(),flds.get(begin-2).getText(),new Integer(flds.get(begin-1).getText()));
				}
				
			}
       		
       	});
       	delBtn.addActionListener(new  ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton btn=(JButton)e.getSource();
				int begin=new Integer(e.getActionCommand());
				int id=new Integer(flds.get(begin-5).getText());
				for(int i=begin-1; i >=begin -5; i--){
					mainPanel.remove(flds.get(i));
				}
				ms.deleteStudent(id);
				int index=btns.indexOf(btn);
				mainPanel.remove(btns.get(index));
				mainPanel.remove(btns.get(index-1));
				repaint();
				revalidate();
			}
       		
       	});
       	mainPanel.add(edBtn);
       	mainPanel.add(delBtn);
    }
    JPanel sub=new JPanel();
    sub.setLayout(grid);
    JLabel empLbl=new JLabel("");
   	
    newFlds=new Vector<JTextField>();
    
    sub.add(empLbl);
    for(int i=0; i<4; i++){
    	newFlds.add(new JTextField());
    	sub.add(newFlds.get(i));
    }
    JButton addNew=new JButton("ADD");
    sub.add(addNew);
    
    
    addNew.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String fname=newFlds.get(0).getText();
			String lname=newFlds.get(1).getText();
			String faculty=newFlds.get(2).getText();
			String year=newFlds.get(3).getText();
			System.out.println(fname);
			System.out.println(lname);
			System.out.println(faculty);
			System.out.println(year);
			Integer id=ms.addStudent(fname, lname, new Integer(year), faculty);
			System.out.println("ID is: "+id);
			JTextField id_fld=new JTextField(id.toString());
			JTextField fname_fld=new JTextField(fname);
			JTextField lname_fld=new JTextField(lname);
			JTextField faculty_fld=new JTextField(faculty);
			JTextField year_fld=new JTextField(year);
			mainPanel.add(id_fld);
			mainPanel.add(fname_fld);
			mainPanel.add(lname_fld);
			mainPanel.add(faculty_fld);
			mainPanel.add(year_fld);
			id_fld.setEditable(false);
			fname_fld.setEditable(false);
			lname_fld.setEditable(false);
			faculty_fld.setEditable(false);
			year_fld.setEditable(false);
			flds.add(id_fld);
			flds.add(fname_fld);
			flds.add(lname_fld);
			flds.add(faculty_fld);
			flds.add(year_fld);
			JButton edBtn=new JButton("EDIT"); 
			JButton delBtn=new JButton("DELETE"); 
			//
			edBtn.setActionCommand(new Integer(flds.size()).toString());
	    	delBtn.setActionCommand(new Integer(flds.size()).toString());
	    	btns.add(edBtn);
	    	btns.add(delBtn);
	    	System.out.println();
	       	edBtn.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JButton btn=(JButton)e.getSource();
					int begin=new Integer(e.getActionCommand());				
					if(btn.getText().equals("Edit")){
						btn.setText("Upgrade");
						for(int i=begin-1; i > begin-5; i--){
							flds.get(i).setEditable(true);
						}
						 
					}else{
						btn.setText("Edit");
						int id=new Integer(flds.get(begin-5).getText());
						for(int i=begin-1; i > begin-5; i--){
							flds.get(i).setEditable(false);
						}
						ms.updateYear(id,flds.get(begin-4).getText(),flds.get(begin-3).getText(),flds.get(begin-2).getText(),new Integer(flds.get(begin-1).getText()));
					}
					
				}
	       		
	       	});
	       	delBtn.addActionListener(new  ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JButton btn=(JButton)e.getSource();
					int begin=new Integer(e.getActionCommand());
					int id=new Integer(flds.get(begin-5).getText());
					for(int i=begin-1; i >=begin -5; i--){
						mainPanel.remove(flds.get(i));
					}
					ms.deleteStudent(id);
					int index=btns.indexOf(btn);
					mainPanel.remove(btns.get(index));
					mainPanel.remove(btns.get(index-1));
					repaint();
					revalidate();
				}
	       		
	       	});
	       	mainPanel.add(edBtn);
	       	mainPanel.add(delBtn);
			//
			repaint();
			revalidate();
		}
    	
    });
    JPanel panel=new JPanel();
    panel.add(mainPanel);
    panel.add(sub);
    add(panel);
  }
  public static void main(String[] args) { //эта функция может быть и в другом классе
    MyWindowApp app = new MyWindowApp(); //Создаем экземпляр нашего приложения
    app.setVisible(true); //С этого момента приложение запущено!
  }
}