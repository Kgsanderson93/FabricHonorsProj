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
	private String[] patternTypes = { "Shirt", "Dress", "Pants", "Swim", "Lingerie", "Pjs", "Bags", "Toys", "Romper" };
	private JCheckBox[] jCBPatternTypes = new JCheckBox[patternTypes.length];

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

	private boolean splitYardage;
	private JLabel jLSplitYardagePrompt = new JLabel(
			"Do you want to split the yardage requirements into main, contrast, and bands?");
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
	private JLabel jLBandYardagePrompt = new JLabel("How many yards of this pattern will be used as banding");
	private JTextField jTBandYardageInput = new JTextField();

	private JLabel prompt = new JLabel("Please enter the Details of this Pattern");
	private JButton add = new JButton("Continue to fabric selection");
	private Pattern newPattern = null;
	private JPanel info2display = new JPanel();
	
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
			jCBPatternTypes[i] = new JCheckBox(patternTypes[i]);
			addAPatternMain.add(jCBPatternTypes[i], constraints);
			constraints.gridx++;
		}
		// who the pattern is for added to panel 1
		constraints.gridy = 3;
		constraints.gridx = 0;
		addAPatternMain.add(jLWhoFor, constraints);
		constraints.gridx = 1;
		for (int i = 0; i < jCBwhofor.length; i++) {
			jCBwhofor[i] = new JCheckBox(patternWho[i]);
			addAPatternMain.add(jCBwhofor[i], constraints);
			constraints.gridx++;
		}
		// bases the pattern is suitable for added to panel 1
		constraints.gridy = 4;
		constraints.gridx = 0;
		addAPatternMain.add(jlBaseSelector, constraints);
		constraints.gridx++;
		for (int i = 0; i < jCBfabricBase.length; i++) {
			jCBfabricBase[i] = new JCheckBox(fabricBaseLabels[i]);
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
					showConfirmDialog(patternSave);
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

	private void showConfirmDialog(SaveFile<Pattern> patternSave) {
		@SuppressWarnings("unused")
		ConfirmationDialog<Pattern> confirmationDialog = new ConfirmationDialog<Pattern>(this, patternSave, info2display,
				newPattern, "Add A Pattern");
		

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
			patternTypesSelected = Utility.arrayToString(patternTypes);
		this.patternTypesSelected = patternTypesSelected;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		if (base.equals(null))
			base = Utility.arrayToString(fabricBaseLabels);
		this.base = base;
	}

	public String getWhofor() {
		return whofor;
	}

	public void setWhofor(String whofor) {
		if (whofor.equals(null))
			whofor = Utility.arrayToString(patternWho);
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

	public int getContrastFabric() {
		return contrastFabric;
	}
	public void setContrastFabric(int contrastFabric) {
		this.contrastFabric = contrastFabric;
	}

	public int getMainFabric() {
		return mainFabric;
	}

	public void setMainFabric(int mainFabric) {
		this.mainFabric = mainFabric;
	}

	public int getBandFabric() {
		return bandFabric;
	}

	public void setBandFabric(int bandFabric) {
		this.bandFabric = bandFabric;
	}

	public String getSelectedBoxes(JCheckBox[] boxes) {
		String selected = "";
		for (JCheckBox box : boxes)
			if (box.isSelected())
				selected = selected + " " + (box.getText());
		return selected;
	}
	public Pattern getNewPattern() {
		return newPattern;
	}
	private void setNewPattern(Pattern pattern) {
		newPattern=pattern;	
	}
	
	public boolean populateFields() {
		boolean populated = false;
		if (jTNameInput.getText().equals(null) || jTYardageInput.getText().equals(null)) {
			@SuppressWarnings("unused")
			InvalidDialog popup = new InvalidDialog();
			populated = false;
		}
		else {
		setName(jTNameInput.getText());
		setBase(getSelectedBoxes(jCBfabricBase));
		setWhofor(getSelectedBoxes(jCBwhofor));
		setPatternTypesSelected(getSelectedBoxes(jCBPatternTypes));
		setSplityardage(isSplityardage());
		setMinStretch(Integer.parseInt(jTMinStretch.getText()));
		setMaxStretch(Integer.parseInt(jTMaxStretch.getText()));
		setYardage(Integer.parseInt(jTYardageInput.getText()));
		setContrastFabric(Integer.parseInt(jTContrastYardageInput.getText()));
		setMainFabric(Integer.parseInt(jTMainYardageInput.getText()));
		setBandFabric(Integer.parseInt(jTBandYardageInput.getText()));
		populated=true;
	}
		return populated;
	}

	public JPanel confirmationPanel() {
		// create Panel(maybe own method?) and JLabels
		
		JLabel jLNameEntered = new JLabel(getName());
		JLabel jLBaseSelected = new JLabel(getBase());
		JLabel jLWhoForSelected = new JLabel(getWhofor());
		JLabel jLPatternTypesSelected = new JLabel(getPatternTypesSelected());
		JLabel jLMaxStretchEntered = new JLabel(String.valueOf(getMaxStretch()));
		JLabel jLMinStretchEntered = new JLabel(String.valueOf(getMinStretch()));
		JLabel jLYardageEntered = new JLabel(String.valueOf(getYardage()));
		boolean split = getSplitYardage();
		JLabel jLMainFabricEntered = new JLabel(String.valueOf(getMainFabric()));
		JLabel jLContrastFabricEntered = new JLabel(String.valueOf(getContrastFabric()));
		JLabel jLBandFabricEntered = new JLabel(String.valueOf(getBandFabric()));
		// add Labels to Panel
		info2display.add(jLNameEntered);
		info2display.add(jLPatternTypesSelected);
		info2display.add(jLWhoForSelected);
		info2display.add(jLBaseSelected);
		info2display.add(jLYardageEntered);
		info2display.add(jLMinStretchEntered);
		info2display.add(jLMaxStretchEntered);
		// check split to add additional Labels
		if (split == true) {
			info2display.add(jLMainFabricEntered);
			info2display.add(jLContrastFabricEntered);
			info2display.add(jLBandFabricEntered);

		}
		return info2display;

	}

	public boolean createNewPattern() {
		boolean created = false;
		if (name.contentEquals(null) || yardage == 0) {
			@SuppressWarnings("unused")
			InvalidDialog popup = new InvalidDialog();
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
		populateFields();
		boolean created = createNewPattern();
		return created;
	}
	}
