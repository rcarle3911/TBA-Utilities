import java.io.*;
import java.text.DateFormat;
import java.util.*;

import org.apache.poi.openxml4j.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ReadExcel {

	
	@SuppressWarnings("deprecation")
	public static Date loadData(FileInputStream file, HashSet <Task> loadedTasks) throws Exception {
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet st = wb.getSheetAt(0);
		
		Iterator <Row> rowIterator = st.iterator();
		
		Row row1 = rowIterator.next();
		Iterator <Cell> cellDateIterator = row1.cellIterator();
		
		Date datePrepared = new Date(cellDateIterator.next().getStringCellValue().substring(9, 21));
		
		Row row2 = rowIterator.next();
		Iterator <Cell> headerIterator = row2.cellIterator();
		ArrayList <String> headers = new ArrayList <String> ();
		while (headerIterator.hasNext()) {
			headers.add(headerIterator.next().getStringCellValue());
		}
		
		while (rowIterator.hasNext()) {
			Row allRows = rowIterator.next();
			Iterator<Cell> cellIterator = allRows.cellIterator();
			int count = 0;
			Task task = new Task();
			while (cellIterator.hasNext() && count < headers.size()) {
				Cell cell = cellIterator.next();
				if (headers.get(count).equals("EMPLOYEE NAME")) {
					task.setEmployeeName(cell.getStringCellValue());
				} else if (headers.get(count).equals("TASKGROUPID")) {
					task.setGroupID(cell.getStringCellValue());
				} else if (headers.get(count).equals("PRODUCT NUMBER") || headers.get(count).equals("PRODUCT NAME")) {
					task.setProductName(cell.getStringCellValue());
				} else if (headers.get(count).equals("TASK NUMBER")) {
					task.setTaskNum(cell.getStringCellValue());
				} else if (headers.get(count).equals("TASK KNOWLEDGE")) {
					task.setTaskDesc(cell.getStringCellValue());
				} else if (headers.get(count).equals("COMPLETION DATE")) {
					task.setComplDate(cell.getDateCellValue());
				} else if (headers.get(count).equals("TRAINER")) {
					task.setTrainer(cell.getStringCellValue());
				}
				
				//Switch/Case used in java 1.7 and above
/*				switch (headers.get(count)) {
	
				case "EMPLOYEE NAME":
					task.setEmployeeName(cell.getStringCellValue());						
					//System.out.println(name);									
					break;
					
				case "TASKGROUPID":
					//System.out.println("check1.1");
					task.setGroupID(cell.getStringCellValue());
					//System.out.println("check1.2");
					break;
					
				case "PRODUCT NUMBER":	
				case "PRODUCT NAME":
					task.setProductName(cell.getStringCellValue());
					//System.out.println("check2");
					break;
												
				case "TASK NUMBER":
					task.setTaskNum(cell.getStringCellValue());
					//System.out.println("check3");
					break;
					
				case "TASK KNOWLEDGE":
					task.setTaskDesc(cell.getStringCellValue());
					//System.out.println(task.getTaskDesc());
					break;
					
				case "COMPLETION DATE":
					//System.out.println("check5.1");
					task.setComplDate(cell.getDateCellValue());
					//System.out.println("check5.2");
					break;
					
				case "TRAINER":
					//System.out.println("check6.1");
					task.setTrainer(cell.getStringCellValue());
					//System.out.println("check6.2");
					break;
					
				}*/
				count++;
			}
			//System.out.println("Preparing to load " + task.getTaskDesc());
			if (task.getTaskNum() != null) {
				loadedTasks.add(task);
			}
			//System.out.println("Loaded task " + task.getTaskDesc());
		}

		return datePrepared;
	}
}
