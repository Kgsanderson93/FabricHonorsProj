import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FirstWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2806031471341192758L;
	private JButton addAFabric = new JButton("Add a fabric");
	private JButton addAPattern = new JButton("Add a pattern");
	private JButton browseFabric = new JButton("Browse fabric");
	private JButton browsePatterns = new JButton("Brose patterns");
	public static final String SAVE_FILE = "fabric_inventory.sav";
	private static final int DEFAULT_X_SIZE = 400;
	private static final int DEFAULT_Y_SIZE = 400;
	

	public FirstWindow(ArrayList<Fabric> fabricList, ArrayList<Pattern> patternList) {
		//make actionlisteners for buttons{
		ActionListener addFabricListener=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showfabricconstructor(fabricList);	
			}
	     
		};
		ActionListener addPatternListener=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showPatternConstructor(fabricList, patternList);	
			}       
		};
		ActionListener browseFabricListener=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Fabric fabric : fabricList)
					System.out.println(fabric.toString());
			}       
		};
		ActionListener browsePatternListener=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Pattern pattern : patternList)
					System.out.println(pattern.toString());
					
			}       
		};
		//}
		
		GridBagLayout gbl = new GridBagLayout();
		JPanel firstWindow = new JPanel(gbl);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 0;
		addAFabric.addActionListener(addFabricListener);
		firstWindow.add(addAFabric,constraints);
		
		constraints.gridx++;
		addAPattern.addActionListener(addPatternListener);
		firstWindow.add(addAPattern, constraints);
		
		constraints.gridx=0;
		constraints.gridy++;
		browseFabric.addActionListener(browseFabricListener);
		firstWindow.add(browseFabric,constraints);
		
		constraints.gridx++;
		browsePatterns.addActionListener(browsePatternListener);
		firstWindow.add(browsePatterns, constraints);
		this.setContentPane(firstWindow);
		this.setSize(DEFAULT_X_SIZE, DEFAULT_Y_SIZE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		

	
	
}
protected void showPatternConstructor(ArrayList<Fabric> fabricList, ArrayList<Pattern> patternList) {
		PatternConstructor pc = new PatternConstructor(fabricList, patternList);
		
	}
private void showfabricconstructor(ArrayList<Fabric> fabricList) {
	FabricConstructor fc = new FabricConstructor(this, fabricList);
}
}