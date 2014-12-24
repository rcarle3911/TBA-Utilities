import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Employee {
	
	//Variables
	private String name;
	private int emplID, breakInTrng;
	private HashSet <Task> loadedTasks = new HashSet <Task> ();
	private HashSet <String> roles = new HashSet <String> ();
	private Date dateEnteredUGT;
	private Date datePrepared;
	
	//Constructors
	public Employee() {
		dateEnteredUGT = new Date();
		roles.add("Trainee");
	}
	
	public Employee(File inputFile) throws Exception {
		datePrepared = readExcel(inputFile);
		setName(loadedTasks.iterator().next().getEmployeeName());
		roles.add("Trainee");
	}
	
	public Employee(String name) {
		this();
		this.name = name;		
	}
	
	public Employee(String name, int emplID) {
		this(name);
		this.emplID = emplID;
	}
	
	public Employee(String name, int emplID, Date dateEnteredUGT) {
		this(name, emplID);
		this.dateEnteredUGT = dateEnteredUGT;
	}
	
	//Methods
	//Calculates and returns percentage.
	public float calcPerc() {
		return calcPerc(loadedTasks);
	}
	
	public float calcPerc(HashSet <Task> taskList) {
		return calcPerc(taskList, loadedTasks);
	}
	
	public int calcComp(HashSet <Task> taskList) {
		return calcComp(taskList, loadedTasks);
	}
	
	public int calcComp(HashSet <Task> taskList, HashSet <Task> loadedTasks) {
		int count = 0;
		
		for (Task s : loadedTasks) {
			if (s.getComplDate() != null && taskList.contains(s)) {
				count++;
			}
				
		}
		return count;
		
	}
	public float calcPerc(HashSet <Task> taskList, HashSet <Task> loadedTasks) {
		return 100 * ((float)calcComp(taskList, loadedTasks)) / ((float)taskList.size());
	}
	
	//Returns the latest date a task was signed off.
	public Date lastTrained() {
		Date date = new Date(0);
		for (Task s : loadedTasks) {
			if (s.getComplDate() != null && s.getComplDate().after(date)) {
				date = s.getComplDate();
			}
		}
		
		return date;
	}
	
	//Outputs chart to track progress
	public JPanel createGraph() throws FileNotFoundException {
		return new TBAGraph(this);

	}
	
	//Reads Files
	public Date readExcel(File inputFile) throws Exception {
		FileInputStream inputStream = new FileInputStream(inputFile);		
		return ReadExcel.loadData(inputStream, loadedTasks);
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRoles() {
		return roles.toString();
	}
	
	public String getLastTrained() {
		return DateFormat.getDateInstance().format(lastTrained());
	}
	
	public Date getPreparedDate() {
		return datePrepared;
	}
	
	@Override
	public String toString() {
		return    "Name:\t\t\t" + name +
				"\nRoles:\t\t\t" + roles.toString() +
				"\nEmplID:\t\t\t" + emplID +
				"\nDate Entered UGT:\t" + DateFormat.getDateInstance().format(dateEnteredUGT) +
				"\nPercent Complete:\t" + String.format("%.2f", calcPerc()) + "%" +
				"\nLast Trained:\t\t" + DateFormat.getDateInstance().format(lastTrained());
	}
			
}
