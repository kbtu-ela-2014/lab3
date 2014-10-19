package labbb3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class MyDeleteButton extends JButton{
	MyDeleteButton(){
		super();
	}
	MyDeleteButton(String s){
		super(s);
		super.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MyDeleteButton btn=(MyDeleteButton)e.getSource();
				int begin=new Integer(e.getActionCommand());
				int id=new Integer(Window.allFields.get(begin-3).getText());
				for(int i=begin-1; i >=begin -3; i--){
					Window.listPanel.remove(Window.allFields.get(i));
				}
				Window.ms.deleteStudent(id);
				int index=Window.allButtons.indexOf(btn);
				Window.listPanel.remove(btn);
				Window.listPanel.remove(Window.allButtons.get(index-1));
				repaint();
				revalidate();
			}
		});
	}
}
