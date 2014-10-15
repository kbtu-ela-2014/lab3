public class Student {
   private int id;
   private String firstName; 
   private String lastName;   
   private int year;  
   private String faculty;

   public Student() {}
   public Student(String fname, String lname, int year, String faculty) {
      this.firstName = fname;
      this.lastName = lname;
      this.year = year;
      this.faculty=faculty;
   }
   public int getId() {
      return id;
   }
   public void setId( int id ) {
      this.id = id;
   }
   public String getFaculty() {
	      return faculty;
   }
   public void setFaculty( String faculty ) {
	      this.faculty=faculty;
   }
   public String getFirstName() {
      return firstName;
   }
   public void setFirstName( String first_name ) {
      this.firstName = first_name;
   }
   public String getLastName() {
      return lastName;
   }
   public void setLastName( String last_name ) {
      this.lastName = last_name;
   }
   public int getyear() {
      return year;
   }
   public void setyear( int year ) {
      this.year = year;
   }
}