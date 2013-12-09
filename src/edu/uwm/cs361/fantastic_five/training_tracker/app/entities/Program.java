package edu.uwm.cs361.fantastic_five.training_tracker.app.entities;

import java.util.List;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.datanucleus.annotations.Unowned;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Program {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String name;

	@Persistent
	private String instructor;

	@Persistent
	private double price;
	
	
	@Persistent
	private double discount;
	
	@Persistent
	private boolean chooseTimes;

	@Unowned
	@Persistent
	private Set<Student> students;
	
	@Unowned
	@Persistent
	private Set<Session> sessions;
	
	@Persistent
	private List<time> times;
	
	
	public Program(String name, String instructor, double price)
	{
		this.name = name;
		this.instructor = instructor;
		this.price = price;		
	}

	public Program(String name, String instructor, double price, List<time> times)
	{
		this.name = name;
		this.instructor = instructor;
		this.price = price;
		this.times= times;
		
	}

	public Key getKey()
	{
		return key;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public String getInstructor()
	{
		return instructor;
	}
	public void setInstructor(String instructor)
	{
		this.instructor = instructor;
	}

	public double getPrice(){
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public double getRevenue(){
		return students.size()*price;
	}

	public void addStudent(Student student){
		students.add(student);
	}
	public Set<Student> getStudents(){
		return students;
	}
	
	public List<time> getTimes(){

		return times;
	}
	
	public void addSession(Session session){
		sessions.add(session);
	}
	public Set<Session> getSessions(){
		return sessions;
	}
	
	private class dates{
		
	}

	public Object getStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getEndDate() {
		// TODO Auto-generated method stub
		return null;
	}
	
}



