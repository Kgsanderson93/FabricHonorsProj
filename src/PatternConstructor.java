//window to take user input to create a pattern 
//takes user input
//checks to see if can be split is true
// use internal frame to create dialog asking for specifics of split
//check if split is valid(ie not more than original yardage) if not create dialog to complain
//checks that minimum information is input 
//

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	private static final int DEFAULT_X_SIZE = 1900;
	private static final int DEFAULT_Y_SIZE = 600;

	private String name;

	private JLabel jLNamePrompt = new JLabel("Pattern Name:");
	private JTextField jTNameInput = new JTextField();

	private String patternTypesSelected;
	private JLabel jLPatternTypesPrompt = new JLabel(
			"Select the types of items that can be created from this pattern:");
	private JCheckBox[] jCBPatternTypes = new JCheckBox[Pattern.PATTERN_TYPES.length];

	private String base;

	private JLabel jlBaseSelector = new JLabel("Select bases most appropriate");
	private JCheckBox[] jCBfabricBase = new JCheckBox[Fabric.FABRIC_BASES.length];

	private String whofor;
	private JLabel jLWhoFor = new JLabel("This pattern is suitable for:");
	private JCheckBox[] jCBwhofor = new JCheckBox[Pattern.WHO_FOR.length];

	private int minStretch = 0;
	private int maxStretch = 200;
	private JLabel jLMinStretch = new JLabel("Please enter the minimum stretch");
	private JLabel jLMaxStretch = new JLabel("please enter the maximum stretch");
	private JTextField jTMinStretch = new JNumberField();
	private JTextField jTMaxStretch = new JNumberField();

	private double yardage;
	private JLabel jLYardagePrompt = new JLabel("how many yards does this pattern require");
	private JTextField jTYardageInput = new JNumberField();

	private boolean splitYardage;
	private JLabel jLSplitYardagePrompt = new JLabel(
			"Do you want to split the yardage requirements into main, contrast, and bands?");
	private JRadioButton yes = new JRadioButton("yes");
	private JRadioButton no = new JRadioButton("no");
	private ButtonGroup groupYesNo = new ButtonGroup();

	private double contrastFabric;
	private JLabel jLContrastYardagePrompt = new JLabel("how many yards of this pattern will be the contrast fabric");
	private JNumberField jTContrastYardageInput = new JNumberField();

	private double mainFabric;
	private JLabel jLMainYardagePrompt = new JLabel("how many yards of this pattern will be the main fabric");
	private JTextField jTMainYardageInput = new JNumberField();

	private double bandFabric;
	private JLabel jLBandYardagePrompt = new JLabel("How many yards of this pattern will be used as banding");
	private JTextField jTBandYardageInput = new JNumberField();

	private JLabel prompt = new JLabel("Please enter the Details of this Pattern");
	private JButton add = new JButton("Continue to fabric selection");
	private Pattern newPattern = null;

	public PatternConstructor(SaveFile<Fabric> fabricSave, SaveFile<Pattern> patternSave) {
		// create panel, layout and layout constraints
		GridBagLayout layout = new GridBagLayout();
		JPanel addAPatternMain = new JPanel(layout);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		this.setContentPane(addAPatternMain);
		this.setSize(DEFAULT_X_SIZE, DEFAULT_Y_SIZE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);

		// create Split Yardage Panel
		JPanel splitYardagePanel = new JPanel(layout);
		constraints.gridx = 0;
		splitYardagePanel.add(jLMainYardagePrompt, constraints);
		constraints.gridx++;
		splitYardagePanel.add(jTMainYardageInput, constraints);

		constraints.gridy = 9;
		constraints.gridx = 0;
		splitYardagePanel.add(jLBandYardagePrompt, constraints);
		constraints.gridx++;
		splitYardagePanel.add(jTBandYardageInput, constraints);
		constraints.gridy = 10;
		constraints.gridx = 0;
		splitYardagePanel.add(jLContrastYardagePrompt, constraints);
		constraints.gridx++;
		splitYardagePanel.add(jTContrastYardageInput, constraints);
		constraints.gridy = 9;
		constraints.gridx = 0;
		addAPatternMain.add(splitYardagePanel, constraints);
		splitYardagePanel.setVisible(false);

		// name added to panel 1
		constraints.gridy = 0;
		constraints.gridx = 0;
		addAPatternMain.add(prompt, constraints);
		constraints.gridy = 1;
		constraints.gridx = 0;
		addAPatternMain.add(jLNamePrompt, constraints);
		constraints.gridx = 1;
		addAPatternMain.add(jTNameInput, constraints);

		// pattern type added to panel 1
		constraints.gridy = 2;
		constraints.gridx = 0;
		addAPatternMain.add(jLPatternTypesPrompt, constraints);
		constraints.gridx = 1;
		for (int i = 0; i < jCBPatternTypes.length; i++) {
			jCBPatternTypes[i] = new JCheckBox(Pattern.PATTERN_TYPES[i]);
			addAPatternMain.add(jCBPatternTypes[i], constraints);
			constraints.gridx++;
		}
		// who the pattern is for added to panel 1
		constraints.gridy = 3;
		constraints.gridx = 0;
		addAPatternMain.add(jLWhoFor, constraints);
		constraints.gridx = 1;
		for (int i = 0; i < jCBwhofor.length; i++) {
			jCBwhofor[i] = new JCheckBox(Pattern.WHO_FOR[i]);
			addAPatternMain.add(jCBwhofor[i], constraints);
			constraints.gridx++;
		}
		// bases the pattern is suitable for added to panel 1
		constraints.gridy = 4;
		constraints.gridx = 0;
		addAPatternMain.add(jlBaseSelector, constraints);
		constraints.gridx++;
		for (int i = 0; i < jCBfabricBase.length; i++) {
			jCBfabricBase[i] = new JCheckBox(Fabric.FABRIC_BASES[i]);
			addAPatternMain.add(jCBfabricBase[i], constraints);
			constraints.gridx++;
		}
		// add stretch requirements
		constraints.gridy = 5;
		constraints.gridx = 0;
		addAPatternMain.add(jLMinStretch, constraints);
		constraints.gridx++;
		addAPatternMain.add(jTMinStretch, constraints);
		constraints.gridy = 6;
		constraints.gridx = 0;
		addAPatternMain.add(jLMaxStretch, constraints);
		constraints.gridx++;
		addAPatternMain.add(jTMaxStretch, constraints);

		// add yardage
		constraints.gridy = 7;
		constraints.gridx = 0;
		addAPatternMain.add(jLYardagePrompt, constraints);
		constraints.gridx++;
		addAPatternMain.add(jTYardageInput, constraints);

		// add buttons to group
		groupYesNo.add(yes);
		groupYesNo.add(no);

		ActionListener yesSplitListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				splitYardagePanel.setVisible(true);
			}
		};
		ActionListener noSplitListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				splitYardagePanel.setVisible(false);
				Utility.clearFields(splitYardagePanel);

			}
		};
		ActionListener continueListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean tryAdd = tryAdd();
				if (tryAdd == true) {
					showConfirmDialog(patternSave, fabricSave);
				}

			}
		};
		yes.addActionListener(yesSplitListener);
		no.addActionListener(noSplitListener);
		add.addActionListener(continueListener);
		constraints.gridy = 8;
		constraints.gridx = 0;
		addAPatternMain.add(jLSplitYardagePrompt, constraints);
		constraints.gridx++;
		addAPatternMain.add(yes, constraints);
		constraints.gridx++;
		addAPatternMain.add(no, constraints);

		constraints.gridx = 10;
		constraints.gridy = 12;
		add.setPreferredSize(new Dimension(301, 40));
		addAPatternMain.add(add, constraints);
	}

	private void showConfirmDialog(SaveFile<Pattern> patternSave, SaveFile<Fabric> fabricSave) {

		JPanel info2display = confirmationPanel();
		@SuppressWarnings("unused")
		ConfirmationDialog<Pattern, Fabric> confirmationDialog = new ConfirmationDialog<Pattern, Fabric>(this, patternSave, fabricSave,
				info2display, newPattern, "Add A Pattern");

	}

	public boolean isSplityardage() {
		boolean splitYardageb = false;
		if (yes.isSelected() == true)
			splitYardageb = true;
		return splitYardageb;
	}

	public void setSplityardage(boolean splityardage) {
		this.splitYardage = splityardage;
	}

	public boolean getSplitYardage() {
		return splitYardage;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getPatternTypesSelected() {
		return patternTypesSelected;
	}

	public void setPatternTypesSelected(String patternTypesSelected) {
		if (patternTypesSelected.equals(null))
			patternTypesSelected = Utility.arrayToString(Pattern.PATTERN_TYPES);
		this.patternTypesSelected = patternTypesSelected;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		if (base.equals(null))
			base = Utility.arrayToString(Fabric.FABRIC_BASES);
		this.base = base;
	}

	public String getWhofor() {
		return whofor;
	}

	public void setWhofor(String whofor) {
		if (whofor.equals(null))
			whofor = Utility.arrayToString(Pattern.WHO_FOR);
		this.whofor = whofor;
	}

	public int getMinStretch() {
		return minStretch;
	}

	public void setMinStretch(int minStretch) {

		this.minStretch = minStretch;
	}

	public int getMaxStretch() {
		return maxStretch;
	}

	public void setMaxStretch(int maxStretch) {
		if (maxStretch == 0) {
			maxStretch = 200;
		}
		this.maxStretch = maxStretch;
	}

	public double getYardage() {
		return yardage;
	}

	public void setYardage(double yardage) {
		this.yardage = yardage;
	}

	public double getContrastFabric() {
		return contrastFabric;
	}

	public void setContrastFabric(double d) {
		this.contrastFabric = d;
	}

	public double getMainFabric() {
		return mainFabric;
	}

	public void setMainFabric(double mainFabric) {
		this.mainFabric = mainFabric;
	}

	public double getBandFabric() {
		return bandFabric;
	}

	public void setBandFabric(double bandFabric) {
		this.bandFabric = bandFabric;
	}



	public Pattern getNewPattern() {
		return newPattern;
	}

	private void setNewPattern(Pattern pattern) {
		newPattern = pattern;
	}

	public boolean populateFields() {
		boolean populated = false;
		if (jTNameInput.getText().equals(null) || jTYardageInput.getText().equals(null)) {
			@SuppressWarnings("unused")
			InvalidDialog popup = new InvalidDialog("Both a name and yardage amount must be specified. \n");
			populated = false;
		} else {
			setName(jTNameInput.getText());
			setBase(Utility.getSelectedBoxes(jCBfabricBase));
			setWhofor(Utility.getSelectedBoxes(jCBwhofor));
			setPatternTypesSelected(Utility.getSelectedBoxes(jCBPatternTypes));
			setSplityardage(isSplityardage());
			try {
				setMinStretch(Integer.parseInt(jTMinStretch.getText()));
				setMaxStretch(Integer.parseInt(jTMaxStretch.getText()));
				setYardage(Double.parseDouble(jTYardageInput.getText()));
				setContrastFabric(Double.parseDouble(jTContrastYardageInput.getText()));
				setMainFabric(Double.parseDouble(jTMainYardageInput.getText()));
				setBandFabric(Double.parseDouble(jTBandYardageInput.getText()));
			} catch (NumberFormatException e) {
				@SuppressWarnings("unused")
				InvalidDialog popup = new InvalidDialog("Numbers cannot be left blank. Please enter a valid number\n");
				e.printStackTrace();
			}
			populated = true;
		}
		return populated;
	}

	public JPanel confirmationPanel() {
		// create Panel and JLabels
		JPanel info2display = new JPanel();
		JLabel jLNameEntered = new JLabel("You have named the pattern: " + getName());
		JLabel jLBaseSelected = new JLabel("You reported this pattern works with these bases:" + getBase());
		JLabel jLWhoForSelected = new JLabel("You have indicated this pattern works for: " + getWhofor());
		JLabel jLPatternTypesSelected = new JLabel(
				"You have indicated this pattern creates these types of items: " + getPatternTypesSelected());
		JLabel jLMaxStretchEntered = new JLabel(
				"You reported a range of stretch from: " + String.valueOf(getMaxStretch()));
		JLabel jLMinStretchEntered = new JLabel(" to: " + String.valueOf(getMinStretch()));
		JLabel jLYardageEntered = new JLabel("The pattern requires:" + String.valueOf(getYardage()) + " yards");
		boolean split = getSplitYardage();
		JLabel jLMainFabricEntered = new JLabel("The patterns main fabric requirement is: "
				+ String.valueOf(getMainFabric()) + "\n *note if yardage is not split all fabric is main fabric");
		JLabel jLContrastFabricEntered = new JLabel("" + String.valueOf(getContrastFabric()));
		JLabel jLBandFabricEntered = new JLabel(String.valueOf(getBandFabric()));
		// add Labels to Panel
		info2display.add(jLNameEntered);
		info2display.add(jLPatternTypesSelected);
		info2display.add(jLWhoForSelected);
		info2display.add(jLBaseSelected);
		info2display.add(jLYardageEntered);
		info2display.add(jLMinStretchEntered);
		info2display.add(jLMaxStretchEntered);
		info2display.add(jLMainFabricEntered);
		// check split to add additional Labels
		if (split == true) {
			info2display.add(jLContrastFabricEntered);
			info2display.add(jLBandFabricEntered);

		}
		return info2display;

	}

	public boolean createNewPattern() {
		boolean created = false;
		if (name == null || yardage == 0) {
			@SuppressWarnings("unused")
			InvalidDialog popup = new InvalidDialog("Both a name and yardage amount must be specified. \n");
			created = false;
		} else {
			if (splitYardage == false) {
				setNewPattern(new Pattern(name, patternTypesSelected, whofor, yardage, base, minStretch, maxStretch));
				created = true;
			} else if (splitYardage == true) {
				setNewPattern(new Pattern(name, patternTypesSelected, whofor, yardage, base, minStretch, maxStretch,
						mainFabric, contrastFabric, bandFabric));
				created = true;
			}
		}

		return created;
	}

	public boolean tryAdd() {
		boolean created = false;
		boolean populate = populateFields();
		double main = getMainFabric();
		double band = getBandFabric();
		double contrast = getContrastFabric();
		double yardage = getYardage();
		if (main + band + contrast <= yardage) {
			if (minStretch < maxStretch) {
				if (populate == true) {
					created = createNewPattern();
				}
			} else {
				@SuppressWarnings("unused")
				InvalidDialog dialog = new InvalidDialog("Minimum stretch cannot be greater than Maximum stretch");
			}
		} else {
			@SuppressWarnings("unused")
			InvalidDialog dialog = new InvalidDialog(
					"main contrast and band fabric must be equal to yardage specified");
		}
		return created;
	}
}
