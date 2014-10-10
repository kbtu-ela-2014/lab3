package lab3ela;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;	

	@Column(name="name")
	private String empName;

	@Column(name="address")
	private String address;	  

	@Column(name="age")
	private String age;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", empName=" + empName + ", address="
				+ address + ", age=" + age + "]";
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	
/*	public static void main(String[] args) throws Exception {

		
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		org.hibernate.Transaction tr = session.beginTransaction();
		Employee emp = new Employee();
		emp.setEmpName("Deepak Kumar");
		emp.setEmpMobileNos("000000");
		emp.setEmpAddress("Delhi - India");
		session.save(emp);
		tr.commit();
		System.out.println("Successfully inserted");
		sessFact.close();
	}*/
}
