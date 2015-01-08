import java.io.*;
import java.util.*;


public class WorkCenter {
	private String name;
	private HashMap <String, Employee> empList = new HashMap <String, Employee> ();
	
	public WorkCenter(File inputFile) throws IOException {
		FileInputStream file = new FileInputStream(inputFile);
		TaskGroup.loadWorkCenter(file, empList);
	}
	
	protected HashMap <String, Employee> getEmpList() {
		return empList;
	}
	
	public String getName() {
		return name;
	}

}
