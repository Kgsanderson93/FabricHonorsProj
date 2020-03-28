//window to take user input to create a pattern 
//takes user input
//checks to see if can be split is true
// use internal frame to create dialog asking for specifics of split
//check if split is valid(ie not more than original yardage) if not create dialog to complain
//checks that minimum information is input 
//

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PatternConstructor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7835405366703823555L;
	private static final int DEFAULT_X_SIZE = 1200;
	private static final int DEFAULT_Y_SIZE = 800;

	private String name;
	private JLabel jLNamePrompt = new JLabel("Pattern Name:");
	private JTextField jTNameInput = new JTextField();

	private String patternTypesSelected;
	private JLabel jLPatternTypesPrompt = new JLabel(
			"Select the types of items that can be created from this pattern:");
	private String[] patternTypes = { "Shirt", "Dress", "Pants", "Swim", "Lingerie", "Pjs", "Bags", "Toys", "Romper" };
	private JCheckBox[] jCBPaternTypes = new JCheckBox[patternTypes.length];

	private String base;
	private String[] fabricBaseLabels = { "Double Brushed Poly", "Cotton Lycra", "French Terry", "Rayon", "Denim" };
	private JLabel jlBaseSelector = new JLabel("Select bases most appropriate");
	private JCheckBox[] jCBfabricBase = new JCheckBox[fabricBaseLabels.length];

	private String whofor;
	private JLabel jLWhoFor = new JLabel("This pattern is suitable for:");
	private String[] patternWho = { "Men", "Women", "Children", "Baby", "Other" };
	private JCheckBox[] jCBwhofor = new JCheckBox[patternWho.length];

	private int minStretch = 30;
	private int maxStretch = 100;
	private JLabel jLMinStretch = new JLabel("Please enter the minimum stretch");
	private JLabel jLMaxStretch = new JLabel("please enter the maximum stretch");
	private JTextField jTMinStretch = new JTextField();
	private JTextField jTMaxStretch = new JTextField();

	private double yardage;
	private JLabel jLYardagePrompt = new JLabel("how many yards does this pattern require");
	private JTextField jTYardageInput = new JTextField();

	private boolean splityardage;
	private JLabel jLSplitYardagePrompt = new JLabel("Do you want to split the yardage requirements into main, contrast, and bands?");
	private JRadioButton yes = new JRadioButton("yes");
	private JRadioButton no = new JRadioButton("no");
	private ButtonGroup groupYesNo = new ButtonGroup();

	private int contrastFabric;
	private JLabel jLContrastYardagePrompt = new JLabel("how many yards of this pattern will be the contrast fabric");
	private JTextField jTContrastYardageInput = new JTextField();

	private int mainFabric;
	private JLabel jLMainYardagePrompt = new JLabel("how many yards of this pattern will be the main fabric");
	private JTextField jTMainYardageInput = new JTextField();

	private int bandFabric;
	private JLabel jLBandYardagePrompt = new JLabel(
			"how many yards of this pattern will be used as banding (requiring more than 50% stretch and good recovery)");
	private JTextField jTBandYardageInput = new JTextField();

	public PatternConstructor(ArrayList<Fabric> fabricList, ArrayList<Pattern> patternList) {
		// create panel, layout and layout constraints
		GridBagLayout layout = new GridBagLayout();
		JPanel addAPatternMain = new JPanel(layout);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 0;
		this.setContentPane(addAPatternMain);
		this.setSize(DEFAULT_X_SIZE, DEFAULT_Y_SIZE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);

		// create Split Yardage Panel
		JPanel splitYardagePanel= new JPanel(layout);
		
		// name added to panel 1
		addAPatternMain.add(jLNamePrompt, constraints);
		constraints.gridx++;
		addAPatternMain.add(jTNameInput, constraints);
		constraints.gridy++;
		constraints.gridx = 0;

		// pattern type added to panel 1
		addAPatternMain.add(jLPatternTypesPrompt, constraints);
		constraints.gridx++;
		for (int i = 0; i < jCBPaternTypes.length; i++) {
			jCBPaternTypes[i] = new JCheckBox(patternTypes[i]);
			addAPatternMain.add(jCBPaternTypes[i], constraints);
			constraints.gridx++;
		}
		// who the pattern is for added to panel 1
		constraints.gridy++;
		constraints.gridx = 0;
		addAPatternMain.add(jLWhoFor, constraints);
		constraints.gridx++;
		for (int i = 0; i < jCBwhofor.length; i++) {
			jCBwhofor[i] = new JCheckBox(patternWho[i]);
			addAPatternMain.add(jCBwhofor[i], constraints);
			constraints.gridx++;
		}
		// bases the pattern is suitable for added to panel 1
		constraints.gridy++;
		constraints.gridx = 0;
		addAPatternMain.add(jlBaseSelector, constraints);
		constraints.gridx++;
		for (int i = 0; i < jCBfabricBase.length; i++) {
			jCBfabricBase[i] = new JCheckBox(fabricBaseLabels[i]);
			addAPatternMain.add(jCBfabricBase[i], constraints);
			constraints.gridx++;
		}
		// add stretch requirements
		constraints.gridy++;
		constraints.gridx = 0;
		addAPatternMain.add(jLMinStretch, constraints);
		constraints.gridx++;
		addAPatternMain.add(jTMinStretch, constraints);
		constraints.gridx++;
		addAPatternMain.add(jLMaxStretch, constraints);
		constraints.gridx++;
		addAPatternMain.add(jTMaxStretch, constraints);

		// add yardage
		constraints.gridy++;
		constraints.gridx = 0;
		addAPatternMain.add(jLYardagePrompt, constraints);
		constraints.gridx++;
		addAPatternMain.add(jTYardageInput, constraints);

		// add buttons to group
		groupYesNo.add(yes);
		groupYesNo.add(no);
		// addAndWait(addButton);
		ActionListener yesSplitListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				splitYardagePanel.setVisible(true);
			}
		};
		yes.addActionListener(yesSplitListener);
		
	
		constraints.gridy++;
		constraints.gridx =0;
		addAPatternMain.add(jLSplitYardagePrompt);
		constraints.gridx++;
		addAPatternMain.add(yes, constraints);
		constraints.gridx++;
		addAPatternMain.add(no, constraints);

	}
}