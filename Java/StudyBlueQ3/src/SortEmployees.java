/**
 * Sort the Employees in specific orders
 * 
 * Question3 from StudyBlue for summer internship position:
 * You should have received a csv file called person_department_age_salary.csv 
 * along with these questions.  The csv file has a list of employees along with 
 * their age, department and salary.  Create a Program that outputs it¡¯s 
 * employees in the following specific order.
 * 
 * 1. Alphabetical by Department Name.
 * 2. Oldest to youngest within the department.
 * 3. Higher salary to lower salary if same age and department.
 * 
 * @author Junrui Ruan
 *
 */
import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class SortEmployees {	
	public static void main (String args[]) 
			throws FileNotFoundException, IOException {
		List<Employee> employee = new ArrayList<Employee>();
		List<String> depts = new ArrayList<String>();
		List<String> header = new ArrayList<String>();
		readCSV(employee, depts, header);
		sorting(employee, depts);
		writeCSV(employee, header);
	}
	
	/**
	 * Read the data from the given CSV and store data to corresponding 
	 * variables
	 * @param employee
	 * @param depts
	 * @param header
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void readCSV (List<Employee> employee, List<String> depts, 
			List<String> header) 
			throws IOException, FileNotFoundException {
		String fileName = "person_department_age_salary.csv";
		CSVReader reader = new CSVReader(new FileReader(fileName));
		// if the first line is the header 
		String[] h = reader.readNext();
		// From the CSV file we know there are only 4 columns
		for (int i = 0; i < 4; i++) {
			header.add(h[i]);
		}
		String[] nextLine;
		// iterate over reader.readNext until it returns null
		while ((nextLine = reader.readNext()) != null) {
			Employee e = new Employee();
			// Remove all the space before employee's First Name
			while (nextLine[0].startsWith(" ")) {
				nextLine[0] = nextLine[0].substring(1);
			}
			e.setName(nextLine[0]);
			String temp = null;
			if (nextLine[1].contains(",")) {
				int index = nextLine[1].indexOf(",");
				temp = nextLine[1].substring(index + 1, 
						nextLine[1].length() - 1);
				nextLine[1] = nextLine[1].substring(0, index);
			}
			e.setDept(nextLine[1]);
			// If one employee has two departments, store the second but not 
			// using it for sorting
			if (temp != null) {
				e.setDept(temp);
			}
			depts.add(nextLine[1]);
			e.setAge(Integer.parseInt(nextLine[2]));
			e.setSalary(Integer.parseInt(nextLine[3]));
			employee.add(e);
		}	
	}
	
	/**
	 * Write a CSV file for outputing result
	 * @param employee
	 * @param header
	 * @throws IOException
	 */
	public static void writeCSV (List<Employee> employee, List<String> header)
			throws IOException {
		 System.out.println("Start Output");
		 BufferedWriter out = new BufferedWriter(new FileWriter("sorted.csv"));
		 CSVWriter writer = new CSVWriter(out);
		 String[] h = new String[4];
		 for (int i = 0; i < 4; i++) {
			 h[i] = header.get(i);
		 }
		 writer.writeNext(h);
		 for (Employee e : employee) {
			 String[] nextLine = new String[4];
			 nextLine[0] = e.getName();
			 if (e.getDept().size() > 1) {
				 nextLine[1] = e.getDept().get(0) + "," + e.getDept().get(1);
			 }
			 else {
				 nextLine[1] = e.getDept().get(0);
			 }
			 nextLine[2] = (new Integer(e.getAge())).toString();
			 nextLine[3] = (new Integer(e.getSalary())).toString();
			 writer.writeNext(nextLine);
		 }
		 out.close();
		 System.out.println("Output Ended, there should be a file called " +
		 		"sorted.csv\nin this folder containing the result.");
	}
	
	/**
	 * Sort the employees with the given rules (Since there are only 13 entries
	 * in the CSV file, we use bubble sort here, otherwise, merge sort would
	 * be a better choice)
	 * @param employee
	 * @param depts
	 */
	public static void sorting (List<Employee> employee, List<String> depts) {
		// For storing sorted Employee
		for (int i = 0; i < employee.size(); i++) {
			for (int j = 0; j < employee.size() - 1; j++) {
				if (compare(employee.get(j), employee.get(j + 1)) > 0) {
					Employee temp = employee.get(j + 1);
					employee.set(j + 1, employee.get(j));
					employee.set(j, temp);
				}
			}
		}
	}
	
	/**
	 * Write a compareTo rule for compare two employees
	 * @param e1
	 * @param e2
	 * @return
	 */
	public static int compare(Employee e1, Employee e2)
	{
	    int i = e1.getDept().get(0).compareTo(e2.getDept().get(0));
	    if (i != 0) {
	    	return i;
	    }
	    else {
	    	if (e1.getAge() > e2.getAge()) {
	    		return -1;
	    	}
	    	else if (e1.getAge() < e2.getAge()) {
	    		return 1;
	    	}
	    	else {
	    		return e2.getSalary() - e1.getSalary();
			}
	    }
	}		
}
