import java.awt.*;

import javax.swing.*;

public class ProgressBar extends JPanel {
	JPanel namePnl = new JPanel ();
	JPanel pBPnl = new JPanel ();
	
	public ProgressBar () {
		super();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		namePnl.setLayout(new BoxLayout(namePnl, BoxLayout.Y_AXIS));
		pBPnl.setLayout(new BoxLayout(pBPnl, BoxLayout.Y_AXIS));
		add(namePnl);
		add(Box.createRigidArea(new Dimension(5,0)));
		add(pBPnl);
				
	}
	
	public void addProgressBar (String label, int current, int max) {
				
		JProgressBar pBar = new JProgressBar(0, max);
		pBar.setStringPainted(true);
		pBar.setValue(current);
		pBar.setName(label);
								
		JLabel name = new JLabel(label);
				
		namePnl.add(name);
		namePnl.add(Box.createRigidArea(new Dimension (0,5)));
		pBPnl.add(pBar);
				
		
	}
	
}

