import java.util.*;

public class Task {
	
	//Variables
	private String productName = "Missing: Check ART File", 
				   taskNum = productName, 
				   taskDesc = productName, 
				   trainer = productName, 
				   employeeName = productName;
				   
	
	private Date complDate;
	
	public void setEmployeeName (String employeeName) {
		this.employeeName = employeeName;
	}
	
	//public void setGroupID (String groupID) {
	//	this.groupID = groupID;
	//}
	
	public void setProductName (String productName) {
		this.productName = productName;
	}

	public void setTaskNum (String taskNum) {
		this.taskNum = taskNum;
	}
	
	public void setTaskDesc (String taskDesc) {
		this.taskDesc = taskDesc;
	}
	
	public void setTrainer (String trainer) {
		this.trainer = trainer;
	}
	
	public void setComplDate (Date complDate) {
		this.complDate = complDate;
	}
	
	public String getEmployeeName () {
		return employeeName;
	}
	
	//public String getGroupID () {
	//	return groupID;
	//}
	
	public String getProductName () {
		return productName;
	}
	
	public String getTaskNum () {
		return taskNum;
	}
	
	public String getTaskDesc () {
		return taskDesc;
	}
	
	public String getTrainer () {
		return trainer;
	}
	
	public Date getComplDate () {
		return complDate;
	}

	@Override
	public boolean equals(Object task) {
		return (this.productName.equals(((Task)task).getProductName()) && this.taskNum.equals(((Task)task).getTaskNum()));		
	}
	
	@Override
	public int hashCode() {
		return (productName + taskNum).hashCode();
	}
	
	@Override
	public String toString() {
		return getEmployeeName() + " \n" +
				//getGroupID() + "\n" +
				getProductName() + "\n" +
				getTaskNum() + "\n" +
				getTaskDesc() + "\n" +
				getTrainer() + "\n";
	}
}
