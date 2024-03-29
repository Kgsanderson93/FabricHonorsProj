
/**
 * window to take user input to create a pattern 
 *checks to see if can be split is true
 *use internal frame to create dialog asking for specifics of split
 *check if split is valid(ie not more than original yardage) if not create dialog to complain
 *checks that minimum information is input 
 */

import java.awt.Color;
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
import javax.swing.JTextArea;
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

	/**
	 * constructor for window
	 * 
	 * @param fabricSave
	 * @param patternSave
	 */
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

		/**
		 * create Split Yardage Panel
		 */
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

		/**
		 * name added to panel 1
		 */
		constraints.gridy = 0;
		constraints.gridx = 0;
		addAPatternMain.add(prompt, constraints);
		constraints.gridy = 1;
		constraints.gridx = 0;
		addAPatternMain.add(jLNamePrompt, constraints);
		constraints.gridx = 1;
		addAPatternMain.add(jTNameInput, constraints);

		/**
		 * pattern type added to panel 1
		 */
		constraints.gridy = 2;
		constraints.gridx = 0;
		addAPatternMain.add(jLPatternTypesPrompt, constraints);
		constraints.gridx = 1;
		for (int i = 0; i < jCBPatternTypes.length; i++) {
			jCBPatternTypes[i] = new JCheckBox(Pattern.PATTERN_TYPES[i]);
			addAPatternMain.add(jCBPatternTypes[i], constraints);
			constraints.gridx++;
		}
		/**
		 * who the pattern is for added to panel 1
		 */
		constraints.gridy = 3;
		constraints.gridx = 0;
		addAPatternMain.add(jLWhoFor, constraints);
		constraints.gridx = 1;
		for (int i = 0; i < jCBwhofor.length; i++) {
			jCBwhofor[i] = new JCheckBox(Pattern.WHO_FOR[i]);
			addAPatternMain.add(jCBwhofor[i], constraints);
			constraints.gridx++;
		}
		/**
		 * bases the pattern is suitable for added to panel 1
		 */
		constraints.gridy = 4;
		constraints.gridx = 0;
		addAPatternMain.add(jlBaseSelector, constraints);
		constraints.gridx++;
		for (int i = 0; i < jCBfabricBase.length; i++) {
			jCBfabricBase[i] = new JCheckBox(Fabric.FABRIC_BASES[i]);
			addAPatternMain.add(jCBfabricBase[i], constraints);
			constraints.gridx++;
		}
		/**
		 * add stretch requirements
		 */
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

		/**
		 * add yardage
		 */
		constraints.gridy = 7;
		constraints.gridx = 0;
		addAPatternMain.add(jLYardagePrompt, constraints);
		constraints.gridx++;
		addAPatternMain.add(jTYardageInput, constraints);

		/**
		 * add buttons to group
		 */
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

	/**
	 * creates an instance of confirm dialog and passes it the panel to display the
	 * information to confirm
	 * 
	 * @param patternSave
	 * @param fabricSave
	 */
	private void showConfirmDialog(SaveFile<Pattern> patternSave, SaveFile<Fabric> fabricSave) {

		JPanel info2display = confirmationPanel();
		@SuppressWarnings("unused")
		ConfirmationDialog<Pattern, Fabric> confirmationDialog = new ConfirmationDialog<Pattern, Fabric>(this,
				patternSave, fabricSave, info2display, newPattern, "Add A Pattern");

	}

	/**
	 * grabs entered information creates dialog if minimum information is not
	 * entered to insist on it being entered if unnecessary information is not
	 * supplied supplies defaults all int numbers are behind a try to catch no
	 * entered information to avoid a number format exception returns a boolean to
	 * let the caller know everything went as planned
	 * 
	 * @return
	 */
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

	/**
	 * creates the panel to hold the information and takes the new pattern and runs
	 * to string to put into the textarea in the panel sets panel background to
	 * transparent
	 * 
	 * @return
	 */
	public JPanel confirmationPanel() {
		// create Panel and JLabels
		JPanel info2display = new JPanel();
		JTextArea info = new JTextArea(newPattern.toString());
		info.setOpaque(false);

		info.setBackground(new Color(0, 0, 0, 0));
		info2display.add(info);

		return info2display;

	}

	/**
	 * creates new pattern
	 * 
	 * @return
	 */
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

	/**
	 * sets fields to input for easy access later
	 * 
	 * @return
	 */
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

	/**
	 * 
	 * @return
	 */
	public boolean isSplityardage() {
		boolean splitYardageb = false;
		if (yes.isSelected() == true)
			splitYardageb = true;
		return splitYardageb;
	}

	/**
	 * 
	 * @param splityardage
	 */
	public void setSplityardage(boolean splityardage) {
		this.splitYardage = splityardage;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getSplitYardage() {
		return splitYardage;
	}

	/**
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public String getPatternTypesSelected() {
		return patternTypesSelected;
	}

	/**
	 * 
	 * @param patternTypesSelected
	 */
	public void setPatternTypesSelected(String patternTypesSelected) {
		if (patternTypesSelected.equals(null))
			patternTypesSelected = Utility.arrayToString(Pattern.PATTERN_TYPES);
		this.patternTypesSelected = patternTypesSelected;
	}

	/**
	 * 
	 * @return
	 */
	public String getBase() {
		return base;
	}

	/**
	 * 
	 * @param base
	 */
	public void setBase(String base) {
		if (base.equals(null))
			base = Utility.arrayToString(Fabric.FABRIC_BASES);
		this.base = base;
	}

	/**
	 * 
	 * @return
	 */
	public String getWhofor() {
		return whofor;
	}

	/**
	 * 
	 * @param whofor
	 */
	public void setWhofor(String whofor) {
		if (whofor.equals(null))
			whofor = Utility.arrayToString(Pattern.WHO_FOR);
		this.whofor = whofor;
	}

	/**
	 * 
	 * @return
	 */
	public int getMinStretch() {
		return minStretch;
	}

	/**
	 * 
	 * @param minStretch
	 */
	public void setMinStretch(int minStretch) {

		this.minStretch = minStretch;
	}

	/**
	 * 
	 * @return
	 */
	public int getMaxStretch() {
		return maxStretch;
	}

	/**
	 * 
	 * @param maxStretch
	 */
	public void setMaxStretch(int maxStretch) {
		if (maxStretch == 0) {
			maxStretch = 200;
		}
		this.maxStretch = maxStretch;
	}

	/**
	 * 
	 * @return
	 */
	public double getYardage() {
		return yardage;
	}

	/**
	 * 
	 * @param yardage
	 */
	public void setYardage(double yardage) {
		this.yardage = yardage;
	}

	/**
	 * 
	 * @return
	 */
	public double getContrastFabric() {
		return contrastFabric;
	}

	/**
	 * 
	 * @param d
	 */
	public void setContrastFabric(double d) {
		this.contrastFabric = d;
	}

	/**
	 * 
	 * @return
	 */
	public double getMainFabric() {
		return mainFabric;
	}

	/**
	 * 
	 * @param mainFabric
	 */
	public void setMainFabric(double mainFabric) {
		this.mainFabric = mainFabric;
	}

	/**
	 * 
	 * @return
	 */
	public double getBandFabric() {
		return bandFabric;
	}

	/**
	 * 
	 * @param bandFabric
	 */
	public void setBandFabric(double bandFabric) {
		this.bandFabric = bandFabric;
	}

	/**
	 * 
	 * @return
	 */
	public Pattern getNewPattern() {
		return newPattern;
	}

	/**
	 * 
	 * @param pattern
	 */
	public void setNewPattern(Pattern pattern) {
		newPattern = pattern;
	}
}
