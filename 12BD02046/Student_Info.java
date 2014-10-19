package student;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity //hibernate annotation which makes class Student_Info as an entity bean
@Table(name="student_info") //to specify the details of the table that will be used to persist the entity in the database
public class Student_Info {
	private String studentName;
	private String studentLastName;
	
	@Id  //primary key annotation
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private int studentId;
	public Student_Info(){}
	public Student_Info(String studentName, String studentLastName, int studentId){
		this.studentName = studentName;
		this.studentLastName = studentLastName;
		this.studentId = studentId;
	}
	public String getStudentLastName() {
		return studentLastName;
	}
	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	

}
