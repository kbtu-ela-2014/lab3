package labbb3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class MyEditButton extends JButton{
	MyEditButton(){
		super();
	}
	MyEditButton(String s){
		super(s);
		super.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
			System.out.println("3432");
			MyEditButton source = (MyEditButton)arg0.getSource();
			int begin=new Integer(arg0.getActionCommand());				
			if(source.getText().equals("Edit")){
				source.setText("Done");
				for(int i=begin; i > begin-3; i--){

					Window.allFields.get(i).setEditable(true);
				}
				 
			}else{
				source.setText("Edit");
				int id=new Integer(Window.allFields.get(begin-3).getText());
				for(int i=begin; i > begin-3; i--){
					Window.allFields.get(i).setEditable(false);
				}
				Window.ms.update(id, Window.allFields.get(begin-2).getText(),Window.allFields.get(begin-1).getText(),new Integer(Window.allFields.get(begin).getText()));
			}
			}
		});
	}
}
