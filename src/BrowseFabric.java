import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

/**
 * A class which visualizes the save file for fabric and allows a user to see
 * previously entered details and delete fabrics with a pop up confiming
 * selection has a button to create an instance of fabric constructor.
 * 
 * @author Kayla Sanderson
 *
 */
public class BrowseFabric extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4167910755450703876L;
	private static final int DEFAULT_X_SIZE = 400;
	private static final int DEFAULT_Y_SIZE = 600;
	private JPanel display;
	private JPanel fabricInfoDisplay;
	private JPanel fabricButtonDisplay;
	private JPanel buttonPanel;
	private JTextArea fabricInfo;
	private SaveFile<Pattern> patternSave;
	private SaveFile<Fabric> fabricSave;
	private ArrayList<Fabric> fabricList;
	private JRadioButton[] fabricButton;
	private ActionListener[] buttonListeners;
	private ButtonGroup jFabricButtons;
	private Fabric selected;

	public BrowseFabric(SaveFile<Fabric> fabricSave2, SaveFile<Pattern> patternSave2) {
		/**
		 * create layouts
		 */
		BorderLayout layout = new BorderLayout();
		BorderLayout layout2 = new BorderLayout();
		GridBagLayout gridLayout = new GridBagLayout();
		GridBagLayout gridLayout2 = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 0;
		/**
		 * create panels
		 */

		display = new JPanel(layout);
		fabricInfoDisplay = new JPanel(layout2);
		fabricButtonDisplay = new JPanel(gridLayout);
		buttonPanel = new JPanel(gridLayout2);

		/**
		 * set up frame display
		 */
		this.setContentPane(display);
		this.setSize(DEFAULT_X_SIZE, DEFAULT_Y_SIZE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);

		/**
		 * initialize other instance variables
		 */
		fabricInfoDisplay.setVisible(false);
		fabricInfo = new JTextArea("");
		fabricInfo.setOpaque(false);
		fabricInfo.setBackground(new Color(0, 0, 0, 0));

		fabricInfoDisplay.add(fabricInfo, BorderLayout.LINE_END);
		JLabel fabricInfoLabel = new JLabel("Selected fabric's available information");
		fabricInfoDisplay.add(fabricInfoLabel, BorderLayout.PAGE_START);
		JLabel fabricAvailableLabel = new JLabel("Fabric available");
		display.add(fabricAvailableLabel, BorderLayout.PAGE_START);
		constraints.gridy++;
		patternSave = patternSave2;
		fabricSave = fabricSave2;
		fabricList = fabricSave.getInventory();
		fabricButton = new JRadioButton[fabricList.size()];
		buttonListeners = new ActionListener[fabricList.size()];
		jFabricButtons = new ButtonGroup();
		/**
		 * create JRadio buttons their listeners and add buttons to group and add
		 * listeners to each button, lastly add each button to the button panel and
		 * upgridx.
		 */

		if (fabricList.isEmpty() == true) {
			JLabel isEmpty = new JLabel("You have no Patterns entered currently");
			fabricButtonDisplay.add(isEmpty, constraints);
		} else {
			for (int i = 0; i < fabricList.size(); i++) {
				Fabric temp = fabricList.get(i);
				String holdName = temp.getName();
				fabricButton[i] = new JRadioButton(holdName);
				jFabricButtons.add(fabricButton[i]);
				/**
				 * creates an action listener for each fabric to trigger the display for fabric
				 * details and set the fabric as the temporary fabric for the delete button
				 */
				buttonListeners[i] = new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						fabricInfo.setText(temp.toString());
						fabricInfoDisplay.setVisible(true);
						selected = temp;

					}
				};
				fabricButton[i].addActionListener(buttonListeners[i]);
				fabricButtonDisplay.add(fabricButton[i], constraints);
				constraints.gridy++;
			}
		}
		JButton delete = new JButton("delete this Fabric");
		JButton addFabric = new JButton("Add another Fabric");
		/**
		 * listener to delete selected fabric
		 */
		ActionListener deleteListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				confirmDelete();

			}
		};
		/**
		 * 
		 * listener to create a new instance of fabric constructor
		 */
		ActionListener addListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				FabricConstructor constructFabric = new FabricConstructor(fabricSave, patternSave);
			}

		};

		delete.addActionListener(deleteListener);
		addFabric.addActionListener(addListener);
		buttonPanel.add(delete, constraints);
		constraints.gridx++;
		constraints.gridx++;
		buttonPanel.add(addFabric, constraints);
		display.add(fabricInfoDisplay, BorderLayout.LINE_END);
		display.add(fabricButtonDisplay, BorderLayout.LINE_START);
		display.add(buttonPanel, BorderLayout.PAGE_END);
	}

	/**
	 * method creates pop up of delete fabric
	 */
	public void confirmDelete() {
		int response = JOptionPane.showConfirmDialog(null, selected.toString(),
				"Are you sure you want to delete this Pattern?", JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
			popConfirmed();
			this.dispose();

		}
	}

	/**
	 * method deletes fabric after confirmation
	 */
	public void popConfirmed() {
		fabricList.remove(selected);
		try {
			patternSave.saveInventory(StartApp.PATTERN_SAVE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}