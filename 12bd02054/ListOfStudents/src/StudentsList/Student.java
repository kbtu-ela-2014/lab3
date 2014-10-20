package StudentsList;

import java.io.Serializable;
//import org.hibernate.annotations.GenericCenerator;

@Entity
@Table(name="Student")
public class Student implements Serializable {
	private int id;
	private String firstName;
	private String lastName;
	

	
	public Student(){}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getId() {
		return id;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == 0) ? 0 : "id".hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Student other = (Student) obj;
		if (id == 0) {
			if (other.id != 0) {
				return false;
			}
		} else if (!(id==(other.id))) {
			return false;
		}
		return true;
	}

	public int compareTo(Student s) {
		return (id+"").compareTo(s.getId()+"");
		
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
