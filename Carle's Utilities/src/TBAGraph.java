import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


@SuppressWarnings("serial")
public class TBAGraph extends JPanel implements ActionListener{
	private Employee emp;
	private ProgressBar graphPanel = new ProgressBar ();
	
	public TBAGraph(Employee emp) throws FileNotFoundException {
		super();
		this.emp = emp;
		addInfo();
		addGraph();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setSize(400,200);
		setVisible(true);
	}
	
	public void addGraph() throws FileNotFoundException {
				
		JButton loadPBar = new JButton("Load Group");
		loadPBar.addActionListener(this);
		
		add(loadPBar);
		addPBar();
		add(graphPanel);
		
	}
	
	//Adds a phase group
	private void addPBar() throws FileNotFoundException {
		int answer = JOptionPane.showConfirmDialog(this, "Would you like to load a task group?", "Load a Task Group", JOptionPane.YES_NO_OPTION);
		while (answer == JOptionPane.YES_OPTION) {
			JFileChooser fc = new JFileChooser("./");
			fc.setFileFilter(new FileNameExtensionFilter("xls", "xlsx"));
			fc.showOpenDialog(this);			
			TaskGroup tg = new TaskGroup(new FileInputStream(fc.getSelectedFile()));
			HashMap <String, HashSet<Task>> tgList = new HashMap <String, HashSet<Task>> ();
			
			Iterator<Task> tgIterator = tg.tg.iterator();
			while (tgIterator.hasNext()) {
				Task currentTask = tgIterator.next();
				String groupID = currentTask.getGroupID();
				if (!groupID.equals("Missing: Check ART File")) {
					if (!tgList.containsKey(groupID)) {
						System.out.println("name: " + groupID);
						tgList.put(groupID, new HashSet<Task>());
					}
					tgList.get(groupID).add(currentTask);
				}
				
			}
			
			Iterator<Entry<String, HashSet<Task>>> mapIterator = tgList.entrySet().iterator();
			while (mapIterator.hasNext()) {
				TaskGroup tgBasic = new TaskGroup(mapIterator.next().getValue());
				graphPanel.addProgressBar(tgBasic.getName(),emp.calcComp(tgBasic.tg), tgBasic.tg.size());
			}	
			
			answer = JOptionPane.showConfirmDialog(this, "Would you like to load another task group?", "Load a Task Group", JOptionPane.YES_NO_OPTION);
		}
	}
	
	public void addInfo() {
		JPanel infoPanel = new JPanel ();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
		JLabel nameLbl = new JLabel (emp.getName());
		JLabel roles = new JLabel ("Roles: " + emp.getRoles());
		Date date = new Date();
		long daysLastTrained = date.getTime()/86400000 - emp.lastTrained().getTime()/86400000;
		JLabel lastTrained = new JLabel ("Last Training Date: " + emp.getLastTrained() + " - " + daysLastTrained);
			
		infoPanel.add(nameLbl);
		infoPanel.add(roles);
		infoPanel.add(lastTrained);
		add(infoPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(((JComponent) e.getSource()).getWidth());
		System.out.println(((JComponent) e.getSource()).getHeight());
		try {
			addPBar();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		revalidate();
		
	}
}
