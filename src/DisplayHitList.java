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
	
	JLabel contrastFabricLabel;
	JCheckBox[] contrastFabricBoxes;
	JLabel bandFabricLabel;
	JCheckBox[] bandFabricBoxes;
	JCheckBox[] mainFabricBoxes;
	JLabel mainFabricLabel;

	String patternName;
	ArrayList<Fabric> mainFabric;
	ArrayList<Fabric> contrastFabric;
	ArrayList<Fabric> bandFabric;
	ArrayList<Fabric> fabricInventory;
	
	JTextArea selected;
	
	String contrast="";
	String band="";
	String main ="";
	HitList hitList;
	SaveFile<Fabric> fabricSave;
	
	public static final int DEFAULT_X_SIZE = 800;
	public static final int DEFAULT_Y_SIZE = 400;
	
	
	public DisplayHitList(HitList hits, SaveFile<Fabric> fabricInventorySave) {
		GridBagLayout layout = new GridBagLayout();
		JPanel display = new JPanel(layout);
		this.setContentPane(display);
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

		patternName = hits.getPatternName();
		mainFabric = hits.getNewListMain();
		contrastFabric = hits.getNewListContrast();
		bandFabric = hits.getNewListBand();
		fabricInventory=fabricInventorySave.getInventory();
		fabricSave=fabricInventorySave;
		hitList=hits;
		
		mainFabricLabel = new JLabel("These fabrics match the pattern, " + patternName
				+ "'s , specified requitements for the Main Fabric: ");
		display.add(mainFabricLabel, constraints);
		constraints.gridy++;
		mainFabricBoxes = new JCheckBox[mainFabric.size()];
		for (int i = 0; i < mainFabricBoxes.length; i++) {
			Fabric temp = mainFabric.get(i);
			String holder = temp.getName();
			mainFabricBoxes[i] = new JCheckBox(holder);

			display.add(mainFabricBoxes[i], constraints);
			constraints.gridy++;
		}

		if (contrastFabric.isEmpty() == false) {
			display.add(new JSeparator(SwingConstants.VERTICAL));
			contrastFabricLabel = new JLabel("These fabrics match the pattern, " + patternName
					+ "'s , specified requitements for the Contrast Fabric: ");
			display.add(contrastFabricLabel, constraints);
			constraints.gridy++;
			contrastFabricBoxes = new JCheckBox[contrastFabric.size()];
			for (int i = 0; i < contrastFabricBoxes.length; i++) {
				Fabric temp = contrastFabric.get(i);
				String holder = temp.getName();
				contrastFabricBoxes[i] = new JCheckBox(holder);

				display.add(contrastFabricBoxes[i], constraints);
				constraints.gridy++;
			}
		}
		if (bandFabric.isEmpty() == false) {
			display.add(new JSeparator(SwingConstants.VERTICAL));
			bandFabricLabel = new JLabel("These fabrics match the pattern, " + patternName
					+ "'s , specified requitements for the Band Fabric: ");
			display.add(bandFabricLabel, constraints);
			constraints.gridy++;
			bandFabricBoxes = new JCheckBox[bandFabric.size()];
			for (int i = 0; i < bandFabricBoxes.length; i++) {
				Fabric temp = bandFabric.get(i);
				String holder = temp.getName();
				bandFabricBoxes[i] = new JCheckBox(holder);

				display.add(bandFabricBoxes[i], constraints);
				constraints.gridy++;
			}
		}
		JButton popAndClose = new JButton("Use these Fabrics");
		JButton closeNoPop = new JButton("Close without saving");

		ActionListener popCloseListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getSelected();
				popupConfirm();

			}
		};
		ActionListener closeNoPopListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		};
		
		popAndClose.addActionListener(popCloseListener);
		closeNoPop.addActionListener(closeNoPopListener);
		display.add(popAndClose, constraints);
		constraints.gridx++;
		display.add(closeNoPop, constraints);
		
	}

	private void popupConfirm() {
		int response=JOptionPane.showConfirmDialog(null, selected, "Is this correct?", JOptionPane.YES_NO_OPTION);
		if(response==JOptionPane.YES_OPTION) {
			popConfirmed();
			this.dispose();
		}
		
	}

	private void popConfirmed() {
		//takes selected pull up each fabric by name(?) edit yardage by patterns held values if yardage ==0 after remove;
		Fabric contrastFabricSelected;
		Fabric bandFabricSelected;
		
		Fabric mainFabricSelected=Utility.searchName(main.strip(), fabricInventory);
		
		double hold=mainFabricSelected.getYardage()-hitList.getMainFabric();
		if (hold>0) {
		mainFabricSelected.setYardage(hold);
		} 
		else {
			fabricInventory.remove(mainFabricSelected);	
		}
		
		
		if (contrastFabric.isEmpty() == false) {
			contrastFabricSelected=Utility.searchName(contrast.strip(), fabricInventory);
			hold=contrastFabricSelected.getYardage()-hitList.getContrastFabric();
			if (hold>0) {
				contrastFabricSelected.setYardage(hold);
				} 
			else {
			fabricInventory.remove(contrastFabricSelected);
			}
		}
		if (bandFabric.isEmpty() == false) {
			bandFabricSelected=Utility.searchName(band.strip(), fabricInventory);
			hold=bandFabricSelected.getYardage()-hitList.getBandFabric();
			if (hold>0) {
				bandFabricSelected.setYardage(hold);
				} 
			else {
			fabricInventory.remove(bandFabricSelected);
			}
		}
			try {
				fabricSave.saveInventory(StartApp.FABRIC_SAVE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void getSelected() {
		String mainFabricPrompt="Main Fabric selected: \n\t";
		String contrastFabricPrompt="";
		String bandFabricPrompt="";
		main= (Utility.getSelectedBoxes(mainFabricBoxes));
		if (contrastFabric.isEmpty() == false) {
			contrastFabricPrompt="\nContrast Fabric selected: \n\t";
			contrast = (Utility.getSelectedBoxes(contrastFabricBoxes));
		}
		if (bandFabric.isEmpty() == false) {
			bandFabricPrompt="\nBand Fabric selected:\n\t";
			band =(Utility.getSelectedBoxes(bandFabricBoxes));
		}
		selected=new JTextArea(mainFabricPrompt+main+contrastFabricPrompt+contrast+ bandFabricPrompt+band);
	}

}
