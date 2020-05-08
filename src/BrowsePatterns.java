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
 *  a window created from the start window that allows the user to browse all previously created patterns and search against fabric for a particular one.
 * @author Kayla Sanderson
 *
 */
public class BrowsePatterns extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4545816644881277994L;

	private static final int DEFAULT_X_SIZE = 800;
	private static final int DEFAULT_Y_SIZE = 800;

	private JRadioButton[] patternButton;
	private SaveFile<Fabric> fabricSave;
	private SaveFile<Pattern> patternSave;
	private ArrayList<Pattern> patternList;
	private ButtonGroup jPatternButtons;
	private ActionListener[] buttonListeners;

	private JTextArea patternInfo;
	private JPanel patternInfoPanel;
	private JPanel patternButtonDisplay;
	private JPanel display;
	private JPanel buttonPanel;
	private Pattern selected;

	public BrowsePatterns(SaveFile<Fabric> fabricSave2, SaveFile<Pattern> patternSave2) {
		// create layouts
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
		patternInfoPanel = new JPanel(layout2);
		patternButtonDisplay = new JPanel(gridLayout);
		buttonPanel = new JPanel(gridLayout2);

		/**
		 *  set up frame display
		 */
		this.setContentPane(display);
		this.setSize(DEFAULT_X_SIZE, DEFAULT_Y_SIZE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);

		/**
		 *  set info panel to start with a blank JTextArea that is hidden
		 */
		patternInfoPanel.setVisible(false);
		patternInfo = new JTextArea("");
		patternInfo.setOpaque(false);
		patternInfo.setBackground(new Color(0, 0, 0, 0));
		patternInfoPanel.add(patternInfo, BorderLayout.CENTER);
		JLabel patternInfoLabel = new JLabel("Selected pattern's available information");
		patternInfoPanel.add(patternInfoLabel, BorderLayout.PAGE_START);

		/**
		 *  initialize other instance variables
		 */
		patternSave = patternSave2;
		fabricSave = fabricSave2;
		patternList = patternSave.getInventory();
		patternButton = new JRadioButton[patternList.size()];
		buttonListeners = new ActionListener[patternList.size()];
		jPatternButtons = new ButtonGroup();

		/**
		* / create JRadio buttons their listeners and add buttons to group and add
		*listeners to each button, lastly add each button to the button panel and up
		*gridx.
		 * 
		 */
		if (patternList.isEmpty() == true) {
			JLabel isEmpty = new JLabel("You have no Patterns entered currently");
			patternButtonDisplay.add(isEmpty, constraints);
		} else {
			for (int i = 0; i < patternList.size(); i++) {
				Pattern temp = patternList.get(i);
				String holdName = temp.getName();
				patternButton[i] = new JRadioButton(holdName);
				jPatternButtons.add(patternButton[i]);
				buttonListeners[i] = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						/**
						 *  display text for pattern
						 */
						patternInfo.setText(temp.toString());
						patternInfoPanel.setVisible(true);
						/**
						 *  set selected button to temp for delete hitlist button use.
						 */
						selected = temp;

					}
				};
				patternButton[i].addActionListener(buttonListeners[i]);
				patternButtonDisplay.add(patternButton[i], constraints);
				constraints.gridy++;
			}
		}
			/**create buttons delete, hitList, add
			*/
			
			JButton delete = new JButton("delete this Pattern");
			JButton hitList = new JButton("search for fabric for this Pattern");
			JButton add = new JButton("add a new Pattern");
			ActionListener deleteListener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					confirmDelete();

				}
			};
			ActionListener hitListListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					HitList hits = new HitList(selected, fabricSave);
					@SuppressWarnings("unused")
					DisplayHitList hitDisplay = new DisplayHitList(hits, fabricSave);
				}
			};
			ActionListener addListener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					@SuppressWarnings("unused")
					PatternConstructor constructPattern = new PatternConstructor(fabricSave, patternSave);
				}

			};
			delete.addActionListener(deleteListener);
			hitList.addActionListener(hitListListener);
			add.addActionListener(addListener);
			buttonPanel.add(delete, constraints);
			constraints.gridx++;
			buttonPanel.add(hitList, constraints);
			constraints.gridx++;
			buttonPanel.add(add, constraints);
			display.add(patternInfoPanel, BorderLayout.LINE_END);
			display.add(patternButtonDisplay, BorderLayout.LINE_START);
			display.add(buttonPanel, BorderLayout.PAGE_END);
		
	}
/**
 * confirmDelete creates a pop up window that allows the user to sure that they intend to delete a given pattern
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
 * confirmDelete handles the actual deletion if the user if sure that the pattern should be deleted 
 * it saves after deletion so as to not allow the pattern to be accidentally re established if getInventory should be called before save
 * IO Exception should not occur but if it does the program simply prints the stack
 */
	public void popConfirmed() {
		patternList.remove(selected);
		try {
			patternSave.saveInventory(StartApp.PATTERN_SAVE);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
