package StudentsList;

import java.io.IOException;

public class TransformData {

	
	
	public static String viewStudent(Student student) throws IOException{
		String response = null;
		response = Main.readFromFile("form.html");
		response = response.replace("student.getId()", student.getId()+"");
		response = response.replace("student.getFirstName()", student.getFirstName());
		response = response.replace("student.getLastName()", student.getLastName());
	
		
		return response;
		
	}
}
