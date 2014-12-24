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

@SuppressWarnings("serial")
public class TBAGUI extends JFrame{
	public static void main (String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TBAGUI gui = new TBAGUI();
				gui.setTitle("Carle's TBA Tool");
				gui.getContentPane().setLayout(new BoxLayout(gui.getContentPane(), BoxLayout.PAGE_AXIS));
				gui.setSize(400,800);
				gui.setVisible(true);
				gui.setLocationRelativeTo(null);
				gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gui.addButtonPanel();
				gui.add(Box.createHorizontalBox());
			}
		});
	
	}
		
	public TBAGUI () {
	
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
			add(empGraph);
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
	
	public void addButtonPanel() {
		JPanel btnPnl = new JPanel();
			
		JButton load = new JButton("Load Employee");
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
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
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnPnl.add(load);
		//btnPnl.add(delete);
		btnPnl.add(exit);		
		add(btnPnl);
		
	}
	
	
}
