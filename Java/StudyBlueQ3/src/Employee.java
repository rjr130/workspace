/**
 * A class for the Question 3, indicate the different variables that a Employee
 * may have.
 * 
 * @author Junrui Ruan
 *
 */
import java.util.*;

public class Employee {
	// Employee Name
	private String name;
	// Employee's Department's name
	private List<String> dept = new ArrayList<String>();
	// Employee's age
	private int age;
	// Employee's salary (All salaries in the csv file using int, otherwise
	// double may be a better choice)
	private int salary;
	
	/**
	 * Getter for Employee's Name
	 * @return
	 */
	public String getName () {
		return this.name;
	}
	
	/**
	 * Getter for Employee's Department name
	 * @return
	 */
	public List<String> getDept () {
		return this.dept;
	}
	
	/**
	 * Getter for Employee's Age
	 * @return
	 */
	public int getAge () {
		return this.age;
	}
	
	/**
	 * Getter for Employee's Salary
	 * @return
	 */
	public int getSalary () {
		return this.salary;
	}
	
	/**
	 * Set the Employee's name to n
	 * @param n
	 */
	public void setName (String n) {
		this.name = n;
	}
	
	/**
	 * Set the Employee's Department name to d
	 * @param n
	 */
	public void setDept (String d) {
		this.dept.add(d);
	}
	
	/**
	 * Set the Employee's Age to a
	 * @param n
	 */
	public void setAge (int a) {
		this.age = a;
	}
	
	/**
	 * Set the Employee's Salary to s
	 * @param n
	 */
	public void setSalary (int s) {
		this.salary = s;
	}
	
	
}
