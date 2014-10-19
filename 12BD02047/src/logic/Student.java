/**
 * 
 */
/**
 * @author Administrator
 *
 */
package logic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Student")
public class Student {
   
    private int id;    
    private String name;    
    private int age;
   
    public Student(){
        name = null;
    }
   
    public Student(Student s){
        name = s.getName();
    }  
   
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    public int getId() {
        return id;
    }
   
    @Column(name="name")
    public String getName(){
        return name;
    }
   
    @Column(name="age")
    public int getAge(){
        return age;
    }
   
    public void setId(int i){
        id = i;    
    }
   
    public void setName(String s){
        name = s;
    }  
   
    public void setAge(int l){
        age = l;
    }  
}