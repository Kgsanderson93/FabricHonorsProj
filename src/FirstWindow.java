import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * creates the first window that allows the user to select what they want to do
 * using 4 buttons each button calling a constructor for that class
 * 
 * @author Kayla
 *
 */
public class FirstWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2806031471341192758L;
	private JButton addAFabric = new JButton("Add a fabric");
	private JButton addAPattern = new JButton("Add a pattern");
	private JButton browseFabric = new JButton("Browse fabric");
	private JButton browsePatterns = new JButton("Browse patterns");
	public static final String SAVE_FILE = "fabric_inventory.sav";
	private static final int DEFAULT_X_SIZE = 400;
	private static final int DEFAULT_Y_SIZE = 400;

	public FirstWindow(SaveFile<Fabric> fabricSave, SaveFile<Pattern> patternSave) {
		// make actionlisteners for buttons{
		ActionListener addFabricListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showfabricconstructor(fabricSave, patternSave);
			}

		};
		ActionListener addPatternListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showPatternConstructor(fabricSave, patternSave);
			}
		};
		ActionListener browseFabricListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Fabric> fabricList = fabricSave.getInventory();
				for (Fabric fabric : fabricList)
					System.out.println(fabric.toString());
				@SuppressWarnings("unused")
				BrowseFabric browse = new BrowseFabric(fabricSave, patternSave);
			}
		};
		ActionListener browsePatternListener = new ActionListener() {
			ArrayList<Pattern> patternList = patternSave.getInventory();

			public void actionPerformed(ActionEvent e) {
				for (Pattern pattern : patternList)
					System.out.println(pattern.toString());
				@SuppressWarnings("unused")
				BrowsePatterns browse = new BrowsePatterns(fabricSave, patternSave);
			}
		};
		// }

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
		firstWindow.add(addAFabric, constraints);

		constraints.gridx++;
		addAPattern.addActionListener(addPatternListener);
		firstWindow.add(addAPattern, constraints);

		constraints.gridx = 0;
		constraints.gridy++;
		browseFabric.addActionListener(browseFabricListener);
		firstWindow.add(browseFabric, constraints);

		constraints.gridx++;
		browsePatterns.addActionListener(browsePatternListener);
		firstWindow.add(browsePatterns, constraints);
		this.setContentPane(firstWindow);
		this.setSize(DEFAULT_X_SIZE, DEFAULT_Y_SIZE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);

	}

	/**
	 * 
	 * @param fabricSave
	 * @param patternSave
	 */
	private void showPatternConstructor(SaveFile<Fabric> fabricSave, SaveFile<Pattern> patternSave) {
		@SuppressWarnings("unused")
		PatternConstructor pc = new PatternConstructor(fabricSave, patternSave);

	}

	/**
	 * 
	 * @param fabricSave
	 * @param patternSave
	 */
	@SuppressWarnings("unused")
	private void showfabricconstructor(SaveFile<Fabric> fabricSave, SaveFile<Pattern> patternSave) {
		FabricConstructor fc = new FabricConstructor(fabricSave, patternSave);
	}
}