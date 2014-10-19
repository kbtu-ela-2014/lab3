package org.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable( tableName="Students")
public class Student {
	@DatabaseField
	private String Name;
	@DatabaseField()
	private String Surname;
	@DatabaseField(id=true)
	String ID;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public String getSurName() {
		return Surname;
	}
	public void setSurName(String surName) {
		this.Surname = surName;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		this.ID = iD;
	}
	
	
	
}
