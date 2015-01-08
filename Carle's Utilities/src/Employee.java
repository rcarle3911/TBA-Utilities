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
	private TaskGroup loadedTasks = new TaskGroup();
	private Date dateEnteredUGT;
	private Date datePrepared;
	
	//Constructors
	public Employee() {
		dateEnteredUGT = new Date();
	}
	
	public Employee(File inputFile) throws Exception {
		FileInputStream inputStream = new FileInputStream(inputFile);		
		datePrepared = loadedTasks.loadFile(inputStream);
		setName(loadedTasks.tg.iterator().next().getEmployeeName());
	}
	
	public Employee(TaskGroup loadedTasks) {
		this.loadedTasks = loadedTasks;
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
	
	public Employee(Task task) {
		this(task.getEmployeeName());
		loadedTasks.tg.add(task);
	}
	
	//Methods
	//Calculates and returns percentage.
	public float calcPerc() {
		return calcPerc(loadedTasks);
	}
	
	public float calcPerc(TaskGroup taskList) {
		return calcPerc(taskList, loadedTasks);
	}
	
	public int calcComp(TaskGroup taskList) {
		return calcComp(taskList, loadedTasks);
	}
	
	public int calcComp(TaskGroup taskList, TaskGroup loadedTasks) {
		int count = 0;
		
		for (Task s : loadedTasks.tg) {
			if (s.getComplDate() != null && taskList.tg.contains(s)) {
				count++;
			}
				
		}
		return count;
		
	}
	public float calcPerc(TaskGroup taskList, TaskGroup loadedTasks) {
		return 100 * ((float)calcComp(taskList, loadedTasks)) / ((float)taskList.tg.size());
	}
	
	//Returns the latest date a task was signed off.
	public Date lastTrained() {
		Date date = new Date(0);
		for (Task s : loadedTasks.tg) {
			if (s.getComplDate() != null && s.getComplDate().after(date)) {
				date = s.getComplDate();
			}
		}
		
		return date;
	}
		
	//Reads Files

	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}	
	
	public String getLastTrained() {
		return DateFormat.getDateInstance().format(lastTrained());
	}
	
	public Date getPreparedDate() {
		return datePrepared;
	}
	
	protected TaskGroup getLoadedTasks() {
		return loadedTasks;
	}
	
	@Override
	public String toString() {
		return    "Name:\t\t\t" + name +
				"\nEmplID:\t\t\t" + emplID +
				"\nDate Entered UGT:\t" + DateFormat.getDateInstance().format(dateEnteredUGT) +
				"\nPercent Complete:\t" + String.format("%.2f", calcPerc()) + "%" +
				"\nLast Trained:\t\t" + DateFormat.getDateInstance().format(lastTrained());
	}
			
}
