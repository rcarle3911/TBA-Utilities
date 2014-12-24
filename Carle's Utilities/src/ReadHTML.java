import java.io.*;
import java.text.*;
import java.util.*;


public class ReadHTML {
	
	public static void main (String[] args) throws IOException, ParseException {
		System.out.println(ReadHTML.findName(new FileReader("IMDST_ITL_ITP.HTML")));
		System.out.println(ReadHTML.buildTotalList(new FileReader("IMDST_ITL_ITP.HTML")).toString());
		System.out.println(ReadHTML.buildTrainedlList(new FileReader("IMDST_ITL_ITP.HTML")).toString());
		System.out.println(ReadHTML.buildMTL(new FileReader("IMDST_MTL.HTML")).size());
	}
	
	
	public static String findName (FileReader htmlPage) throws IOException {
		BufferedReader in = new BufferedReader(htmlPage);
		String name = "Error";
		while(in.ready()) {
			name = in.readLine();
			if (name.contains("Employee Name:")) {
				in.readLine();
				in.readLine();
				name = in.readLine();
				int start = name.indexOf('>') + 1;
				int end = name.lastIndexOf('<');
				name = name.substring(start, end);
				break;
				
			}
		}
		in.close();
		return name;
	}
	
	public static LinkedHashSet <String> buildTotalList (FileReader htmlPage) throws IOException {
		BufferedReader in = new BufferedReader(htmlPage);
		LinkedHashSet <String> totalList = new LinkedHashSet <String> ();
		String name = "Error";
		String taskID = "Error";
		while (in.ready()) {
			taskID = "Error";
			name = in.readLine();
			if (name.contains("Product Number:")) {
				in.readLine();
				in.readLine();
				name = in.readLine();
				int start = name.indexOf('>') + 1;
				int end = name.lastIndexOf('<');
				name = name.substring(start, end);
				while (in.ready() && !taskID.contains("Employee Name:")) {
					taskID = in.readLine();
					if (taskID.contains("001.")) {
						start = taskID.indexOf('>') + 1;
						end = taskID.lastIndexOf('<');
						totalList.add(name + " - " + taskID.substring(start, end));
					}
				}
			}
		}
		in.close();
		return totalList;
	}
	
	public static LinkedHashMap <String, Date> buildTrainedlList (FileReader htmlPage) throws ParseException, IOException {
		BufferedReader in = new BufferedReader(htmlPage);
		LinkedHashMap <String, Date> trainedList = new LinkedHashMap <String, Date> ();
		String name = "Error";
		String taskID = "Error";
		String temp = "Error";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date date = df.parse("19700101");
		
		while (in.ready()) {
			taskID = "Error";
			name = in.readLine();			
			if (name.contains("Product Number:")) {
				in.readLine();
				in.readLine();
				name = in.readLine();
				int start = name.indexOf('>') + 1;
				int end = name.lastIndexOf('<');
				name = name.substring(start, end);
				while (in.ready() && !taskID.contains("Employee Name:")) {
					taskID = in.readLine();
					if (taskID.contains("001.")) {
						start = taskID.indexOf('>') + 1;
						end = taskID.lastIndexOf('<');
						int count = 0;
						temp = "Error";
						while (!temp.contains("</tr>")) {
							temp = in.readLine();
							if (temp.contains("<div")) {
								temp = temp.substring(temp.indexOf('>') + 1, temp.lastIndexOf('<'));
								if (temp.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
									count++;
									if (count > 1) {
										date = df.parse(temp);
										trainedList.put(name + " - " + taskID.substring(start, end), date);
									}
								}
							}															
						}
					}						
				}
			}
		}
		in.close();
		return trainedList;
		
	}
	
	public static LinkedHashMap <String, float[]> buildMTL (FileReader htmlPage) throws IOException {
		BufferedReader in = new BufferedReader(htmlPage);
		LinkedHashMap <String, float[]> mtl = new LinkedHashMap <String, float[]> ();
		String name = "Error";
		String taskID = "Error";
		String statStr = "Error";
		
		int count = 0;
		while (in.ready()) {
			taskID = "Error";
			name = in.readLine();
			if (name.contains("Product No:")) {
				in.readLine();
				in.readLine();
				name = in.readLine();
				int start = name.indexOf('>') + 1;
				int end = name.lastIndexOf('<');
				name = name.substring(start, end);
				while (in.ready() && !taskID.contains("Org Name:")) {
					taskID = in.readLine();
					if (taskID.contains("001.")) {
						start = taskID.indexOf('>') + 1;
						end = taskID.lastIndexOf('<');
						statStr = "Error";
						count = 0;
						float[] stats = new float[4];
						while (!statStr.contains("</tr>")) {
							statStr = in.readLine();
							if (statStr.contains("<div")) {
								statStr = statStr.substring(statStr.indexOf('>') + 1, statStr.lastIndexOf('<'));
								try {
									stats[count] = Float.parseFloat(statStr);
									count++;									
								} catch (Exception e) {
									
								} 
							}
							
						}
						if (count == 4) {
							mtl.put(name + " - " + taskID.substring(start, end), stats);
						}
						
					}
				}
			}
		}
		in.close();
		return mtl;
		
	}
		
		
}

