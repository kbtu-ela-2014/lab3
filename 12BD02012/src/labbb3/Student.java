package labbb3;
public class Student {
   private int id;
   private String firstName; 
   private String lastName;   
   private int year;  
   
   public Student() {}
   public Student(String fname, String lname, int year) {
      this.firstName = fname;
      this.lastName = lname;
      this.year = year;
   }
   public int getId() {
      return id;
   }
   public void setId( int id ) {
      this.id = id;
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
   public int getYear() {
      return year;
   }
   public void setYear( int year ) {
      this.year = year;
   }
}