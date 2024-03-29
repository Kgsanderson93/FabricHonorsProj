
/**
 * Takes User input to construct a new fabric need to implement check that all required input is valid first class created thus little user error coverage and methods tend towards brute force rather than any manner of sophisticated handing 
 */

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FabricConstructor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -633832205472738284L;
	public static final String FABRIC_SAVE = "fabric_inventory.sav";

	public static final int DEFAULT_X_SIZE = 800;
	public static final int DEFAULT_Y_SIZE = 400;
	private JLabel jlNameInputPrompt = new JLabel("Enter a fabric name");
	private JTextField fabricNameInput = new JTextField(60);

	private JLabel jlYardagePrompt = new JLabel("Enter number of yards");
	private JTextField fabricYardageInput = new JTextField(20);

	private JLabel jlStretchPrompt = new JLabel("Enter the stretch percentage as an integer from 0-200");
	private JTextField jtStretchInput = new JTextField(20);

	private JButton addButton = new JButton("Add");

	private JLabel jlBaseSelector = new JLabel("Select base");
	private JComboBox<String> cbfabricBase = new JComboBox<String>(Fabric.FABRIC_BASES);

	private JLabel jlSuitableForPrompt = new JLabel("Select what the fabric is suitable for");
	private JCheckBox[] boxes = new JCheckBox[Pattern.PATTERN_TYPES.length]; // Each checkbox will get a name of garment
																				// from
	// suitablefor array.

	/**
	 * constructor creates an instance of fabric constructor
	 * 
	 * @param fabricSave
	 * @param patternSave
	 */
	public FabricConstructor(SaveFile<Fabric> fabricSave, SaveFile<Pattern> patternSave) {
		GridBagLayout layout = new GridBagLayout();
		JPanel addAFabric = new JPanel(layout);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 0;

		cbfabricBase.setSelectedIndex(0);
		addAFabric.add(jlNameInputPrompt, constraints);
		constraints.gridx++;

		addAFabric.add(fabricNameInput, constraints);
		constraints.gridx = 0;
		constraints.gridy++;

		addAFabric.add(jlYardagePrompt, constraints);
		constraints.gridx++;
		addAFabric.add(fabricYardageInput, constraints);
		constraints.gridx = 0;
		constraints.gridy++;
		addAFabric.add(jlStretchPrompt, constraints);
		constraints.gridx++;
		addAFabric.add(jtStretchInput, constraints);
		constraints.gridx = 0;
		constraints.gridy++;
		addAFabric.add(jlBaseSelector, constraints);
		constraints.gridx++;
		addAFabric.add(cbfabricBase, constraints);
		constraints.gridx = 0;
		constraints.gridy++;
		addAFabric.add(jlSuitableForPrompt, constraints);
		constraints.gridx++;
		for (int i = 0; i < boxes.length; i++) {
			boxes[i] = new JCheckBox(Pattern.PATTERN_TYPES[i]);

			addAFabric.add(boxes[i], constraints);
			constraints.gridy++;
		}

		/**
		 * listener for adding the fabric that creates a show confirm dialog
		 */
		ActionListener addButtonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showConfirmDialog(fabricSave, patternSave);
			}
		};

		constraints.anchor = GridBagConstraints.SOUTHEAST;
		constraints.weighty = 1;
		addButton.addActionListener(addButtonListener);
		addAFabric.add(addButton, constraints);
		this.setContentPane(addAFabric);
		this.setSize(DEFAULT_X_SIZE, DEFAULT_Y_SIZE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * 
	 * @return
	 */
	public String getFabricName() {
		return fabricNameInput.getText();
	}

	/**
	 * 
	 * @return
	 */
	public double getYardage() {
		return Double.parseDouble(fabricYardageInput.getText());
	}

	/**
	 * 
	 * @return
	 */
	public int getStretch() {
		return Integer.parseInt(jtStretchInput.getText());
	}

	/**
	 * 
	 * @return
	 */
	public String getFabricBase() {
		return cbfabricBase.getSelectedItem().toString();
	}

	/**
	 * 
	 * @return
	 */
	public String getSelectedNames() {
		String selected = "";
		for (JCheckBox box : boxes)
			if (box.isSelected())
				selected = selected + " " + (box.getText());
		if (selected == "") {
			selected = Utility.arrayToString(Pattern.PATTERN_TYPES);
		}
		return selected;
	}

	/**
	 * creates dialog to confirm fabric specifications and creates panel with this
	 * information to pass to an instance of confirmation dialog
	 * 
	 * @param fabricSave
	 * @param patternSave
	 */
	public void showConfirmDialog(SaveFile<Fabric> fabricSave, SaveFile<Pattern> patternSave) {
		String fabricName = getFabricName();
		double yardage = getYardage();
		int stretch = getStretch();
		String baseSelection = getFabricBase();
		String selected = getSelectedNames();

		Fabric newFabric = new Fabric(fabricName, yardage, baseSelection, stretch, selected);

		JPanel displayInfo2Confirm = new JPanel();
		JTextArea info = new JTextArea(newFabric.toString());
		info.setOpaque(false);

		info.setBackground(new Color(0, 0, 0, 0));
		displayInfo2Confirm.add(info);

		// Add the components to the window

		@SuppressWarnings("unused")
		ConfirmationDialog<Fabric, Pattern> confirmationDialog = new ConfirmationDialog<Fabric, Pattern>(this,
				fabricSave, patternSave, displayInfo2Confirm, newFabric, "Add A Fabric");
		// Add the Fabric to the Inventory

	}

	/**
	 * clears all fields after add, might be outdated after utilities clear fields
	 */
	public void clearFields() {

		fabricNameInput.setText(null);
		fabricYardageInput.setText(null);
		jtStretchInput.setText(null);
		cbfabricBase.setSelectedIndex(0);
		for (JCheckBox box : boxes) {
			box.setSelected(false);
		}
	}

	/**
	 * 
	 * @param fabricList
	 * @throws IOException
	 */
	public void writeFabricList(ArrayList<Fabric> fabricList) throws IOException {
		SaveFile<Fabric> fabricSave = new SaveFile<>(fabricList);
		fabricSave.saveInventory(FABRIC_SAVE);
	}

}