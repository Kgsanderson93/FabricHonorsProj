//Takes User input to construct a new fabric 
// need to implement check that all required input is valid 

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
	
	private JLabel jlStretchPrompt=new JLabel("Enter the stretch percentage as an integer from 0-200");
	private JTextField jtStretchInput= new JTextField(20);
	
	private JButton addButton = new JButton("Add");
	
	private String[] fabricBaseLabels = { "Double Brushed Poly", "Cotton Lycra", "FrenchTerry", "Rayon", "Denim" };
	private JLabel jlBaseSelector = new JLabel("Select base");
	private JComboBox<String> cbfabricBase = new JComboBox<String>(fabricBaseLabels);
	
	private JLabel jlSuitableForPrompt = new JLabel("Select what the fabric is suitable for");
	private JCheckBox[] boxes = new JCheckBox[Fabric.ALL_USES.length]; // Each checkbox will get a name of garment from suitablefor array.
	

	public FabricConstructor(FirstWindow parent,ArrayList<Fabric> fabricList) {
		GridBagLayout layout = new GridBagLayout();
		JPanel addAFabric= new JPanel(layout);
		GridBagConstraints constraints= new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor=GridBagConstraints.NORTHWEST;
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.weightx=1;
		constraints.weighty=0;
		
		cbfabricBase.setSelectedIndex(0);
		addAFabric.add(jlNameInputPrompt,constraints);
		constraints.gridx++;
		
		addAFabric.add(fabricNameInput,constraints);
		constraints.gridx=0;
		constraints.gridy++;
		
		addAFabric.add(jlYardagePrompt,constraints);
		constraints.gridx++;
		addAFabric.add(fabricYardageInput,constraints);
		constraints.gridx=0;
		constraints.gridy++;
		addAFabric.add(jlStretchPrompt,constraints);
		constraints.gridx++;
		addAFabric.add(jtStretchInput,constraints);
		constraints.gridx=0;
		constraints.gridy++;
		addAFabric.add(jlBaseSelector,constraints);
		constraints.gridx++;
		addAFabric.add(cbfabricBase,constraints);
		constraints.gridx=0;
		constraints.gridy++;
		addAFabric.add(jlSuitableForPrompt,constraints);
		constraints.gridx++;
		for (int i = 0; i < boxes.length; i++) {
			boxes[i] = new JCheckBox(Fabric.ALL_USES[i]);
			
			addAFabric.add(boxes[i],constraints);
			constraints.gridy++;
		}
		
		//addAndWait(addButton);
		ActionListener addButtonListener=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showConfirmDialog(fabricList);	
			}       
		};
		
		constraints.anchor=GridBagConstraints.SOUTHEAST;
		constraints.weighty=1;
		addButton.addActionListener(addButtonListener);
		addAFabric.add(addButton,constraints);
		this.setContentPane(addAFabric);
		this.setSize(DEFAULT_X_SIZE, DEFAULT_Y_SIZE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public String getFabricName() {
		return fabricNameInput.getText();
	}

	public double getYardage() {
		return Double.parseDouble(fabricYardageInput.getText());
	}

	public int getStretch() {
		return Integer.parseInt(jtStretchInput.getText());
	}

	public String getFabricBase() {
		return cbfabricBase.getSelectedItem().toString();
	}
	
	public String getSelectedNames() {
		String selected="";
	    for(JCheckBox box : boxes)
	        if(box.isSelected())
	            selected=selected+" "+ (box.getText());
		return selected;
	}
	
	public void showConfirmDialog(ArrayList<Fabric> fabricList) {
		String fabricName = getFabricName();
		double yardage = getYardage();
		int stretch= getStretch();
		String baseSelection = getFabricBase();
		String selected = getSelectedNames();
		Fabric newFabric = new Fabric(fabricName, yardage, baseSelection, stretch, selected);
		
		 JPanel displayInfo2Confirm = new JPanel(); 
		 
		JLabel jlBaseSelected = new JLabel("You have selected " +baseSelection+ " as the fabric base");
		JLabel jlnamed = new JLabel("You have named the fabric: " + fabricName);
		JLabel jlYardage=new JLabel("You have reported: "+ yardage+ " yards");
		JLabel jlStretch=new JLabel("You have reported that this fabric has "+String.valueOf(stretch)+" stretch percentage");
		JLabel boxesSelected = new JLabel("You report that this fabric is suitable for these uses" +selected);
		
		
		//Add the components to the window
		 displayInfo2Confirm.add(jlnamed);
		 displayInfo2Confirm.add(jlBaseSelected);
		 displayInfo2Confirm.add(boxesSelected);
		 displayInfo2Confirm.add(jlStretch);
		 displayInfo2Confirm.add(jlYardage);
		@SuppressWarnings("unused")
		ConfirmationDialog confirmationDialog=new ConfirmationDialog(this, fabricList, displayInfo2Confirm, newFabric);
		//Add the Fabric to the Inventory
		
		
	
		 }
	
	
	public void clearFields() {
		// TODO Auto-generated method stub
		fabricNameInput.setText(null);
		fabricYardageInput.setText(null);
		jtStretchInput.setText(null);
		cbfabricBase.setSelectedIndex(0);
		for (JCheckBox box : boxes) {
			box.setSelected(false);
		}
	}
	
		
		
	
	public void writeFabricList(ArrayList<Fabric> fabricList) throws IOException {
		SaveFile<Fabric> fabricSave = new SaveFile<>(fabricList);
		fabricSave.saveInventory(FABRIC_SAVE);
	}

	
}

