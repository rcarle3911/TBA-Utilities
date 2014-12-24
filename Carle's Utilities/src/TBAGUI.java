/* Code by Robert Carle
 * Excel reader code written by JExcelAPI
 * Program is used to display TBA statistics via ART generated excel sheets
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;
import javax.swing.GroupLayout.Alignment;

@SuppressWarnings("serial")
public class TBAGUI extends JFrame{
	public static void main (String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TBAGUI gui = new TBAGUI();
				
			}
		});
	
	}
		
	public TBAGUI () {		
		setTitle("Carle's TBA Tool");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setSize(400,800);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmLoadEmployee = new JMenuItem("Load Employee");
		mntmLoadEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser fc = new JFileChooser("./");
					fc.setFileFilter(new FileNameExtensionFilter("xls", "xlsx"));
					fc.showOpenDialog((Component) e.getSource());
					if (fc.getSelectedFile() != null) {
						addEmployee(fc.getSelectedFile());
					}				
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnFile.add(mntmLoadEmployee);
		
		JMenuItem mntmLoadTaskGroup = new JMenuItem("Load Task Group");
		mnFile.add(mntmLoadTaskGroup);
		
		JMenuItem mntmLoadDirectory = new JMenuItem("Load Directory");
		mnFile.add(mntmLoadDirectory);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
	}
		
	public void update() {
		revalidate();
	}
	
	public void addEmployee(File inputFile) {
		try {
			Employee emp = new Employee (inputFile);
			JPanel empGraph = emp.createGraph();
			empGraph.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder()));
			empGraph.add(delBtn());
			getContentPane().add(empGraph);
		} catch (Exception e) {
			e.printStackTrace();
		}

		update();
		
	}
	
	public JButton delBtn() {
		JButton delete = new JButton("Delete");
		delete.setPreferredSize(new Dimension(99, 26));
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Container cont = ((JButton) e.getSource()).getParent().getParent();
				cont.remove(((Component) e.getSource()).getParent());
				cont.revalidate();
			}
		});
		
		return delete;
	}		
}
