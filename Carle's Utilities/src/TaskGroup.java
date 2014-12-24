import java.io.*;
import java.util.HashSet;

import javax.swing.JOptionPane;


public class TaskGroup {
	public HashSet <Task> tg = new HashSet <Task> ();	
	private String name;
		
	public TaskGroup(FileInputStream file){
		try {
			ReadExcel.loadData(file, tg);
			name = tg.iterator().next().getGroupID();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error Check Task group file");
		}
	}
	
	public TaskGroup(HashSet <Task> tg) {
		this.tg = tg;
		name = tg.iterator().next().getGroupID();
	}
	
	public String getName() {
		return name;
	}
	
	@Override public boolean equals(Object obj) {
		return ((TaskGroup) obj).getName().equals(getName());
	}
}
