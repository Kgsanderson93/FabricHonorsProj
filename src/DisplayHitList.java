
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

//display HitList
public class DisplayHitList extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 692755252935320356L;

	private JLabel contrastFabricLabel;
	private JCheckBox[] contrastFabricBoxes;
	private JLabel bandFabricLabel;
	private JCheckBox[] bandFabricBoxes;
	private JCheckBox[] mainFabricBoxes;
	private JLabel mainFabricLabel;

	private String patternName;
	private ArrayList<Fabric> mainFabric;
	private ArrayList<Fabric> contrastFabric;
	private ArrayList<Fabric> bandFabric;
	private ArrayList<Fabric> fabricInventory;

	private JTextArea selected;

	private String contrast = "";
	private String band = "";
	private String main = "";
	private HitList hitList;
	private SaveFile<Fabric> fabricSave;

	private ActionListener mainListener;
	private ActionListener bandListener;
	private ActionListener contrastListener;

	private Fabric temp;
	private String holder;
	private double yardage;

	public static final int DEFAULT_X_SIZE = 800;
	public static final int DEFAULT_Y_SIZE = 400;

	private int i;
	private JTextArea mainDisplay;
	private JTextArea contrastDisplay;
	private JTextArea bandDisplay;

	public DisplayHitList(HitList hits, SaveFile<Fabric> fabricInventorySave) {
		GridBagLayout master = new GridBagLayout();
		JPanel masterPanel = new JPanel(master);

		GridBagLayout displaylayout = new GridBagLayout();
		JPanel displayButtons = new JPanel(displaylayout);

		GridBagLayout infoLayout = new GridBagLayout();
		JPanel infoPanel = new JPanel(infoLayout);

		GridBagLayout bottomButtonsLayout = new GridBagLayout();
		JPanel bottomButtons = new JPanel(bottomButtonsLayout);

		this.setContentPane(masterPanel);
		this.setSize(DEFAULT_X_SIZE, DEFAULT_Y_SIZE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		hitList = hits;
		patternName = hitList.getPatternName();
		mainFabric = hitList.getNewListMain();
		contrastFabric = hitList.getNewListContrast();
		bandFabric = hitList.getNewListBand();
		fabricInventory = fabricInventorySave.getInventory();
		fabricSave = fabricInventorySave;

		masterPanel.add(displayButtons, constraints);
		constraints.gridx++;
		masterPanel.add(infoPanel, constraints);
		constraints.gridx = 0;
		constraints.gridy++;
		masterPanel.add(bottomButtons, constraints);
		constraints.gridy = 0;
		constraints.gridx = 0;

		mainDisplay = new JTextArea(" ");
		mainDisplay.setOpaque(false);
		mainDisplay.setBackground(new Color(0, 0, 0, 0));
		contrastDisplay = new JTextArea(" ");
		contrastDisplay.setOpaque(false);
		contrastDisplay.setBackground(new Color(0, 0, 0, 0));
		bandDisplay = new JTextArea(" ");
		bandDisplay.setOpaque(false);
		bandDisplay.setBackground(new Color(0, 0, 0, 0));
		JTextArea spacer = new JTextArea("\n \n \n \n");
		spacer.setOpaque(false);
		spacer.setBackground(new Color(0, 0, 0, 0));
		infoPanel.setVisible(false);
		infoPanel.add(spacer, constraints);
		constraints.gridy++;
		infoPanel.add(mainDisplay, constraints);
		constraints.gridy++;
		infoPanel.add(contrastDisplay, constraints);
		constraints.gridy++;
		infoPanel.add(bandDisplay, constraints);

		constraints.gridy = 0;
		constraints.gridx = 0;

		// check if any lists were empty after pop yardage is run and display error
		// message
		if (hitList.getEmptyContrast() == true || hitList.getEmptyBand() == true || hitList.getEmptyMain() == true) {
			notEnoughFabric();
		} else {
			if (mainFabric.isEmpty() == false) {
				// JLabel creation and add
				mainFabricLabel = new JLabel("These fabrics match the pattern, " + patternName
						+ "'s , specified requitements for the Main Fabric: ");
				displayButtons.add(mainFabricLabel, constraints);
				constraints.gridy++;
				// CheckboxCreation
				mainListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JCheckBox source = (JCheckBox) e.getSource();
						String sourceName = source.getText();
						Fabric actionFabric = Utility.searchName(sourceName, mainFabric);
						if (source.isSelected() == true) {
							displayFabricInfoMain(actionFabric);
							infoPanel.setVisible(true);
							adjustYardageDown(actionFabric, yardage);
							hitList.popBand();
							hitList.popContrast();
						} else if (source.isSelected() == false) {
							adjustYardageUp(temp, yardage);
							hitList.popBand();
							hitList.popContrast();
						}
					}
				};
				mainFabricBoxes = new JCheckBox[mainFabric.size()];
				for (i = 0; i < mainFabricBoxes.length; i++) {
					temp = mainFabric.get(i);
					yardage = hitList.getMainFabric();
					holder = temp.getName();
					mainFabricBoxes[i] = new JCheckBox(holder);
					mainFabricBoxes[i].addActionListener(mainListener);
					displayButtons.add(mainFabricBoxes[i], constraints);
					constraints.gridy++;
				}
			}
			if (contrastFabric.isEmpty() == false) {
				displayButtons.add(new JSeparator(SwingConstants.HORIZONTAL), constraints);
				constraints.gridy++;
				contrastFabricLabel = new JLabel("These fabrics match the pattern, " + patternName
						+ "'s , specified requitements for the Contrast Fabric: ");
				displayButtons.add(contrastFabricLabel, constraints);
				constraints.gridy++;
				contrastListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JCheckBox source = (JCheckBox) e.getSource();
						String sourceName = source.getText();
						Fabric actionFabric = Utility.searchName(sourceName, contrastFabric);
						if (source.isSelected() == true) {
							displayFabricInfoContrast(actionFabric);
							infoPanel.setVisible(true);
							adjustYardageDown(actionFabric, yardage);
							hitList.popBand();
							hitList.popMain();
						} else if (source.isSelected() == false) {
							adjustYardageUp(actionFabric, yardage);
							hitList.popBand();
							hitList.popMain();
						}
					}
				};
				contrastFabricBoxes = new JCheckBox[contrastFabric.size()];
				for (i = 0; i < contrastFabricBoxes.length; i++) {
					temp = contrastFabric.get(i);
					yardage = hitList.getContrastFabric();
					holder = temp.getName();
					contrastFabricBoxes[i] = new JCheckBox(holder);

					contrastFabricBoxes[i].addActionListener(contrastListener);
					displayButtons.add(contrastFabricBoxes[i], constraints);
					constraints.gridy++;
				}
			}
			if (bandFabric.isEmpty() == false) {
				displayButtons.add(new JSeparator(SwingConstants.HORIZONTAL), constraints);
				constraints.gridy++;
				bandFabricLabel = new JLabel("These fabrics match the pattern, " + patternName
						+ "'s , specified requitements for the Band Fabric: ");
				displayButtons.add(bandFabricLabel, constraints);
				constraints.gridy++;
				bandListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JCheckBox source = (JCheckBox) e.getSource();
						String sourceName = source.getText();
						Fabric actionFabric = Utility.searchName(sourceName, bandFabric);
						if (source.isSelected() == true) {
							displayFabricInfoBand(actionFabric);
							infoPanel.setVisible(true);
							adjustYardageDown(actionFabric, yardage);
							hitList.popContrast();
							hitList.popMain();
						} else if (source.isSelected() == false) {
							adjustYardageUp(actionFabric, yardage);
							hitList.popContrast();
							hitList.popMain();
						}
					}
				};
				bandFabricBoxes = new JCheckBox[bandFabric.size()];
				for (i = 0; i < bandFabricBoxes.length; i++) {
					temp = bandFabric.get(i);
					holder = temp.getName();
					yardage = hitList.getBandFabric();
					bandFabricBoxes[i] = new JCheckBox(holder);

					bandFabricBoxes[i].addActionListener(bandListener);
					displayButtons.add(bandFabricBoxes[i], constraints);
					constraints.gridy++;
				}
			}
		}

		JButton popAndClose = new JButton("Use these Fabrics");
		ActionListener popCloseListener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				getSelected();
				popupConfirm();
			}
		};

		JButton closeNoPop = new JButton("Close without saving");
		ActionListener closeNoPopListener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		};

		popAndClose.addActionListener(popCloseListener);
		closeNoPop.addActionListener(closeNoPopListener);

		constraints.gridy = 0;
		constraints.gridx = 0;
		bottomButtons.add(popAndClose, constraints);
		constraints.gridx++;
		bottomButtons.add(closeNoPop, constraints);
		constraints.gridy = 0;
		constraints.gridx = 0;
	}

	private void popupConfirm() {
		int response = JOptionPane.showConfirmDialog(null, selected, "Is this correct?", JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
			popConfirmed();
			this.dispose();
		}

	}

	private void popConfirmed() {
		// takes selected pull up each fabric by name(?) edit yardage by patterns held
		// values if yardage ==0 after remove;
		Fabric contrastFabricSelected;
		Fabric bandFabricSelected;

		Fabric mainFabricSelected = Utility.searchName(main.strip(), fabricInventory);

		double hold = mainFabricSelected.getYardage() - hitList.getMainFabric();
		if (hold > 0) {
			mainFabricSelected.setYardage(hold);
		} else {
			fabricInventory.remove(mainFabricSelected);
		}

		if (contrastFabric.isEmpty() == false) {
			contrastFabricSelected = Utility.searchName(contrast.strip(), fabricInventory);
			hold = contrastFabricSelected.getYardage() - hitList.getContrastFabric();
			if (hold > 0) {
				contrastFabricSelected.setYardage(hold);
			}
		}
		if (bandFabric.isEmpty() == false) {
			bandFabricSelected = Utility.searchName(band.strip(), fabricInventory);
			hold = bandFabricSelected.getYardage() - hitList.getBandFabric();
			if (hold > 0) {
				bandFabricSelected.setYardage(hold);
			} else {
				fabricInventory.remove(bandFabricSelected);
			}
		}
		try {
			fabricSave.saveInventory(StartApp.FABRIC_SAVE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getSelected() {
		String mainFabricPrompt = "Main Fabric selected: \n\t";
		String contrastFabricPrompt = "";
		String bandFabricPrompt = "";
		main = (Utility.getSelectedBoxes(mainFabricBoxes));
		if (contrastFabric.isEmpty() == false) {
			contrastFabricPrompt = "\nContrast Fabric selected: \n\t";
			contrast = (Utility.getSelectedBoxes(contrastFabricBoxes));
		}
		if (bandFabric.isEmpty() == false) {
			bandFabricPrompt = "\nBand Fabric selected:\n\t";
			band = (Utility.getSelectedBoxes(bandFabricBoxes));
		}
		selected = new JTextArea(mainFabricPrompt + main + contrastFabricPrompt + contrast + bandFabricPrompt + band);
	}

	private void notEnoughFabric() {
		JOptionPane.showMessageDialog(this, "No fabrics match all specified requirements", "Error", DISPOSE_ON_CLOSE);

	}

	private void displayFabricInfoMain(Fabric temp) {
		String fabric = temp.toString();
		mainDisplay.setText(fabric);

	}

	private void displayFabricInfoContrast(Fabric temp) {
		String fabric = temp.toString();
		contrastDisplay.setText(fabric);
	}

	private void displayFabricInfoBand(Fabric temp) {
		String fabric = temp.toString();
		bandDisplay.setText(fabric);
	}

	public void adjustYardageDown(Fabric temp2, double yardage2) {
		double original = temp2.getYardage();
		double after = original - yardage2;
		temp2.setYardage(after);
	}

	public void adjustYardageUp(Fabric temp2, double yardage2) {

		double original = temp2.getYardage();
		double after = original + yardage2;
		temp2.setYardage(after);
	}
}